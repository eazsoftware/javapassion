package com.eaz.jpa_mapping1.app;

import com.eaz.jpa_mapping1.entities.School;
import com.eaz.jpa_mapping1.entities.Student;
import javax.persistence.EntityManager;

/**
 *
 * @author javier
 */
public class CreateTestData {
    
    public static void createTestData(EntityManager em) {
        em.getTransaction().begin();

        // Create school 1
        School school1 = new School();
        school1.setId(1);
        school1.setName("UCD");

        // Create school 2
        School school2 = new School();
        school2.setId(2);
        school2.setName("PSOE");

        // Create school 3
        School school3 = new School();
        school3.setId(3);
        school3.setName("PP");        
        
        // Create student 1
        Student student1 = new Student();
        student1.setId(1);
        student1.setName("Adolfo");
        student1.setSurname("Suarez");
        student1.setGrade(1);

        // Create student 2
        Student student2 = new Student();
        student2.setId(2);
        student2.setName("Leopoldo");
        student2.setSurname("Calvo-Sotelo");
        student2.setGrade(2);

        // Create student 3
        Student student3 = new Student();
        student3.setId(3);
        student3.setName("Felipe");
        student3.setSurname("Gonzalez");
        student3.setGrade(2);
        
        // Create student 4
        Student student4 = new Student();
        student4.setId(4);
        student4.setName("Jose Maria");
        student4.setSurname("Aznar");
        student4.setGrade(1);
        
        // Create student 5
        Student student5 = new Student();
        student5.setId(5);
        student5.setName("Jose Luis");
        student5.setSurname("Rodriguez Zapatero");
        student5.setGrade(1);
        
        Student student6 = new Student();
        student6.setId(6);
        student6.setName("Mariano");
        student6.setSurname("Rajoy");
        student6.setGrade(2);
        
        /*********************************/        
        
        //School UCD
        school1.getStudents().add(student1);
        school1.getStudents().add(student2);
        student1.setSchool(school1);
        student2.setSchool(school1);
        //School PSOE
        school2.getStudents().add(student3);
        school2.getStudents().add(student5);
        student3.setSchool(school2);
        student4.setSchool(school2);
        //School PP
        school3.getStudents().add(student4);
        school3.getStudents().add(student6);  
        student5.setSchool(school3);
        student6.setSchool(school3);        

        em.persist(school1);
        em.persist(school2);
        em.persist(school3);

        em.getTransaction().commit();

    }    
    
}
