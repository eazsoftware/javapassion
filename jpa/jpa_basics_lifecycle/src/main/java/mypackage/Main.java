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

//  em.detach() is supported only from JPA 2.0
//        em.detach(student1);
//        b = em.contains(student1); // true
//        System.out.println("----> Right after detach() operation, is it in persistence context? = " + b);

        em.remove(student1);
        b = em.contains(student1); // false
        System.out.println("----> Right after remove() operation, is it in persistence context? = " + b);

        em.persist(student1);
        b = em.contains(student1); // true
        System.out.println("----> Right after 2nd persist() operation, is it in persistence context? = " + b);

        em.clear();
        b = em.contains(student1); // false
        System.out.println("----> Right after clear() operation, is it in persistence context? = " + b);

        em.persist(student1);
        b = em.contains(student1); // true
        System.out.println("----> Right after 3rd persist() operation, is it in persistence context? = " + b);

        em.getTransaction().commit();

        b = em.contains(student1); // true
        System.out.println("----> Right after commit, is it in persistence context? = " + b);

        // Start the 2nd transaction
        em.getTransaction().begin();
        b = em.contains(student1); // true
        System.out.println("----> Right after new transaction, is it in persistence context? = " + b);

        // What would happen if we have the following code?
        //em.clear();

        em.refresh(student1);
        b = em.contains(student1); // true
        System.out.println("----> Right after refresh, is it in persistence context? = " + b);

        em.getTransaction().commit();

        em.close();
        emf.close();

        // Display the table for verification
        JPAUtil.checkData("select * from STUDENT ORDER BY ID");
    }
}
