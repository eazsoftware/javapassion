package mypackage;

import entities.Person2;
import entities.Student2;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import util.JPAUtil;

public class Main {

    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] a) throws Exception {

        // Delete join table so that you can run the application again
        JPAUtil.droptable("drop table STUDENT2");
        JPAUtil.droptable("drop table PERSON2");

        emf = Persistence.createEntityManagerFactory("pu1");
        em = emf.createEntityManager();

        // Create test data
        CreateTestData.createTestData(em);

        // Get a Person
        Person2 person1 = em.find(Person2.class, new Long(2));
        System.out.println("---->Person 1 = " + person1.getName());

        // Get a Student
        Student2 student1 = em.find(Student2.class, new Long(5));
        System.out.println("---->Student 1 = " + student1.getName());
        
        em.close();
        emf.close();

        // Display the tables
        JPAUtil.checkData("select * from PERSON2 ORDER BY ID");
    }
}
