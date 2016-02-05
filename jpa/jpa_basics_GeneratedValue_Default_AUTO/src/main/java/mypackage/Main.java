package mypackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.EmployeeService;
import entities.Student;

import util.JPAUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        // Set up the table and data for testing
        JPAUtil.droptable("drop table SEQUENCE");

        // Perform JPA operrations
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = emf.createEntityManager();

        // Create some employees
        EmployeeService es = new EmployeeService(em);

        em.getTransaction().begin();
        es.createEmployeeWithoutId("Sang Shin", 1000000);
        es.createEmployeeWithoutId("Bill Clinton", 800000);
        es.createEmployeeWithoutId("Angela Caicedo", 700000);

        Student student1 = new Student();
        student1.setName("StudentName1");
        em.persist(student1);
        Student student2 = new Student();
        student2.setName("StudentName2");
        em.persist(student2);


        em.getTransaction().commit();

        em.close();
        emf.close();

        // Display the table for verification
        JPAUtil.checkData("select * from MY_OWN_EMPLOYEE_TABLE ORDER BY ID");
        JPAUtil.checkData("select * from STUDENT ORDER BY ID");
        JPAUtil.checkData("select * from STUDENT ORDER BY ID");
    }
}
