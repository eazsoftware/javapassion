package mypackage;

import entities.EmployeeId;
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
        es.createEmployee(new EmployeeId("marketing", "MA"),
                "Sang Shin", 1000000);
        es.createEmployee(new EmployeeId("marketing", "NY"),
                "Bill Clinton", 800000);
        es.createEmployee(new EmployeeId("engineering", "MA"),
                "Angela Caicedo", 700000);
        em.getTransaction().commit();

        em.close();
        emf.close();

        // Display the table for verification
        JPAUtil.checkData("select * from EMPLOYEE");
    }
}
