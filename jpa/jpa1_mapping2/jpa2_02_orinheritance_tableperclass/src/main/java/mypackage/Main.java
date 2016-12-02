package mypackage;

import entities.HumanBeing;
import entities.Person;
import entities.Student;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import util.JPAUtil;

public class Main {

    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] a) throws Exception {

        // Delete join table so that you can run the application again
        JPAUtil.droptable("drop table HUMANBEING");
        JPAUtil.droptable("drop table PERSON");
        JPAUtil.droptable("drop table STUDENT");        

        emf = Persistence.createEntityManagerFactory("AdvancedMapping");
        em = emf.createEntityManager();

        // Create test data
        CreateTestData.createTestData(em);
        
        em.close();
        emf.close();

        // Display the tables
        JPAUtil.checkData("SELECT * FROM HUMANBEING;");
        JPAUtil.checkData("SELECT * FROM PERSON;");
        JPAUtil.checkData("SELECT * FROM STUDENT;");
    }
}
