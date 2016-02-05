package mypackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.EmployeeService;

import entities.EmployeeType;
import util.JPAUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        // Set up the table and data for testing
        JPAUtil.droptable("drop table EMPLOYEE");
        JPAUtil.setup("create table EMPLOYEE ( employee_id int, name VARCHAR(20), salary int)");

        // Perform JPA operrations
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = emf.createEntityManager();

        // Create some employees
        EmployeeService es = new EmployeeService(em);

        em.getTransaction().begin();
        es.createEmployeeWithEnum(1, "Sang Shin", 1000000, EmployeeType.OFFICER);
        es.createEmployeeWithEnum(2, "Bill Clinton", 800000, EmployeeType.ADMIN);
        es.createEmployeeWithEnum(3, "Angela Caicedo", 700000, EmployeeType.MANAGER);
        em.getTransaction().commit();

        em.close();
        emf.close();

        // Display the table for verification
        JPAUtil.checkData("select * from MY_OWN_EMPLOYEE_TABLE ORDER BY ID");
    }
}
