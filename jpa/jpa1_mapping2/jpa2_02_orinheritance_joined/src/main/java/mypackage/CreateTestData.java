package mypackage;

import entities.Person2;
import entities.Student2;
import javax.persistence.EntityManager;

public class CreateTestData {

    public static void createTestData(EntityManager em) {
        em.getTransaction().begin();

        Person2 person1 = new Person2();
        person1.setName("Person 1");
        // Persist person1 object
        em.persist(person1);

        Person2 person2 = new Person2();
        person2.setName("Person 2");
        // Persist person2 object
        em.persist(person2);        
        
        Student2 student1 = new Student2();
        student1.setName("Student 1");
        student1.setSchool("Calasanz School");
        student1.setGrade(1d);
        em.persist(student1);
        
        Student2 student2 = new Student2();
        student2.setName("Student 2");
        student2.setSchool("Miguel Servet School");
        student2.setGrade(2d);
        em.persist(student2);
        
        Student2 student3 = new Student2();
        student3.setName("Student 3");
        student3.setSchool("Andres Segoviva School");
        student3.setGrade(3d);
        em.persist(student3);

        em.getTransaction().commit();

    }
}