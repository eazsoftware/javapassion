package mypackage;

import entities.HumanBeing;
import entities.Person;
import entities.Student;
import javax.persistence.EntityManager;

public class CreateTestData {

    public static void createTestData(EntityManager em) {
        em.getTransaction().begin();
        
        HumanBeing humanBeing1 = new HumanBeing();
        humanBeing1.setNationality("British");
        em.persist(humanBeing1);

        Person person1 = new Person();
        person1.setName("Person 1");
        em.persist(person1);

        Student student1 = new Student();
        student1.setName("Student 1");
        student1.setSchool("Andres Segovia");
        student1.setGrade(1.0d);
        em.persist(student1);
        
        
        Person person2 = new Student();
        person2.setName("Student 2");
        em.persist(person2);
        
        em.getTransaction().commit();

    }
}