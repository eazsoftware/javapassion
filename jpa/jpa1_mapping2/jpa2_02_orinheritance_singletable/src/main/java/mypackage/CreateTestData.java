package mypackage;

import entities.Person;
import entities.Student;
import javax.persistence.EntityManager;

public class CreateTestData {

    public static void createTestData(EntityManager em) {
        em.getTransaction().begin();

        Person person1 = new Person();
        person1.setName("Person 1");
        // Persist person1 object
        em.persist(person1);

        Person person2 = new Person();
        person2.setName("Person 2");
        // Persist person2 object
        em.persist(person2);        
        
        Student student1 = new Student();
        student1.setName("Student 1");
        student1.setSchool("Calasanz School");
        student1.setGrade(1d);
        em.persist(student1);
        
        Student student2 = new Student();
        student2.setName("Student 2");
        student2.setSchool("Miguel Servet School");
        student2.setGrade(2d);
        em.persist(student2);
        
        Student student3 = new Student();
        student3.setName("Student 3");
        student3.setSchool("Andres Segoviva School");
        student3.setGrade(3d);
        em.persist(student3);

        em.getTransaction().commit();

    }
}