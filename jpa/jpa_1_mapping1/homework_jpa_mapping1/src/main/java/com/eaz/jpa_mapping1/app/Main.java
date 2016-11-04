package com.eaz.jpa_mapping1.app;

import com.eaz.jpa_mapping1.entities.School;
import com.eaz.jpa_mapping1.entities.Student;
import com.eaz.jpa_mapping1.util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author javier
 */
public class Main {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void main(String[] args) {

        // Delete join table so that you can run the application again
        JPAUtil.droptable("drop table SCHOOL_STUDENT_JOIN_TABLE");
        JPAUtil.droptable("drop table SCHOOL");
        JPAUtil.droptable("drop table STUDENT");        

        emf = Persistence.createEntityManagerFactory("homework_persitence_unit");
        em = emf.createEntityManager();

        // Create test data
        CreateTestData.createTestData(em);

        // Get a school and then navigate from the school to their students
        School school1 = em.find(School.class, 1);
        System.out.println("---->School 1 = " + school1.getName());
        List<Student> cStudent = school1.getStudents();
        for (Student student : cStudent) {
            System.out.println("---->Student ["+student.getId()+"] ["+student.getName()+"] ["+student.getSurname()+"] ["+student.getGrade()+"]");
        }       

        // Get a student and then navigate from the student to the school
        Student student1 = em.find(Student.class, 6);
        School schoolFromStudent1 = student1.getSchool();
        System.out.println("---->School from Student = ["+schoolFromStudent1.getId()+"] ["+schoolFromStudent1.getName()+"] ["+schoolFromStudent1.getStudents().size() + "]");

        em.close();
        emf.close();

        // Display the tables
        JPAUtil.checkData("select * from school");
        JPAUtil.checkData("select * from student");
        JPAUtil.checkData("select * from school_student_join_table");
    }
    
}
