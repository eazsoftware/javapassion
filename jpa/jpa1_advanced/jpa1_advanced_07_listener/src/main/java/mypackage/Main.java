package mypackage;

import entities.Address;
import entities.Employee;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.EmployeeService;

import util.JPAUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        // Set up the table and data for testing
        JPAUtil.droptable("drop table MY_OWN_EMPLOYEE_TABLE");
        JPAUtil.droptable("drop table SALARY");
        JPAUtil.droptable("drop table ATABLE");

        // Perform JPA operations
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = emf.createEntityManager();
        
        // Display the table for verification
        JPAUtil.checkData("select * from MY_OWN_EMPLOYEE_TABLE ORDER BY ID");        

        // Create some employees
        EmployeeService es = new EmployeeService(em);

        em.getTransaction().begin();
        es.createEmployeeWithAddress(1, "Sang Shin", 1000000,
                new Address("1 broad st", "New York", "NY"));
        es.createEmployeeWithAddress(2, "Bill Clinton", 800000,
                new Address("4 dream st", "Boston", "MA"));
        es.createEmployee(3, "Angela Caicedo", 700000);

        em.getTransaction().commit();
        
        // Display the table for verification
        JPAUtil.checkData("select * from MY_OWN_EMPLOYEE_TABLE ORDER BY ID");        
        
        // *********************************************************************
        em.getTransaction().begin();
        Employee employee = es.findEmployee(1);
        
        employee.setName("Pepito");
        em.persist(employee);
        
        em.getTransaction().commit();  
        
        System.out.println("START - Refreshing Employee object");
        em.refresh(employee);
        System.out.println("FINISH - Refreshing Employee object");
        
        // Display the table for verification
        JPAUtil.checkData("select * from MY_OWN_EMPLOYEE_TABLE ORDER BY ID");        
        // *********************************************************************
        em.getTransaction().begin();
        employee = es.findEmployee(2);
        
        em.remove(employee);
                
        em.getTransaction().commit();     
        
        // Display the table for verification
        JPAUtil.checkData("select * from MY_OWN_EMPLOYEE_TABLE ORDER BY ID");     
        
        em.close();
        emf.close();         
    }
}
