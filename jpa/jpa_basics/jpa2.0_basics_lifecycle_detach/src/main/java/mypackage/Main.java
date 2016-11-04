package mypackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import entities.Student;

import util.JPAUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        // Set up the table and data for testing
        JPAUtil.droptable("drop table SEQUENCE");

        // Perform JPA operation
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Student student1 = new Student("Sang Shin");
        em.persist(student1);
        boolean b = em.contains(student1); // true
        System.out.println("----> Right after persist() operation, is it in persistence context? = " + b);

        em.flush();

        // Detach the object
        em.detach(student1); // is supported only from JPA 2.0
        b = em.contains(student1); // false
        System.out.println("----> Right after detach() operation, is it in persistence context? = " + b);

        // Change the value of detached object
        student1.setName("Park Ji Sung");
        
        // Merge it, it will return merged object, that is managed
        Student studentCopy = em.merge(student1);
        
        // Original object is not in the persistence context
        b = em.contains(student1); // false
        System.out.println("----> Right after merge() operation, is the original object in persistence context? = " + b);
        
        // But the merged object is
        b = em.contains(studentCopy); // true
        System.out.println("----> Right after merge() operation, is the merged object in persistence context? = " + b);

        em.getTransaction().commit();

        em.close();
        emf.close();

        // Display the table for verification
        JPAUtil.checkData("select * from STUDENT");
    }
}
