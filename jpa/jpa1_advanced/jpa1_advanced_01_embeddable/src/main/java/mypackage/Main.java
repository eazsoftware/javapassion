package mypackage;

import entities.Address;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.EmployeeService;

import util.JPAUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        // Set up the table and data for testing
        JPAUtil.droptable("drop table EMPLOYEE");

        // Perform JPA operrations
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = emf.createEntityManager();

        // Create some employees
        EmployeeService es = new EmployeeService(em);

        em.getTransaction().begin();
        es.createEmployeeWithAddress(1, "Sang Shin", 1000000,
                new Address("1 broad st", "New York", "NY"));
        es.createEmployeeWithAddress(2, "Bill Clinton", 800000,
                new Address("4 dream st", "Boston", "MA"));
        es.createEmployee(3, "Angela Caicedo", 700000);
        em.getTransaction().commit();

        em.close();
        emf.close();

        // Display the table for verification
        JPAUtil.checkData("select * from MY_OWN_EMPLOYEE_TABLE ORDER BY ID");
    }
}
