import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import examples.model.EmployeeService;
import java.util.List;

import util.JPAUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        // Set up the table and data for testing
        JPAUtil.droptable("drop table EMPLOYEE");
        JPAUtil.setup("create table EMPLOYEE ( employee_id int, name VARCHAR(20), salary int)");

        // Perform JPA operrations
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = emf.createEntityManager();

        // Create some employees
        EmployeeService es = new EmployeeService(em);

        em.getTransaction().begin();
        es.createEmployee(1, "Sang Shin", 10000);
        es.createEmployee(2, "Bill Clinton", 10000);
        es.createEmployee(3, "Angela Caicedo", 6000);
        es.createEmployee(4, "Annie Song", 6000);
        es.createEmployee(5, "Charles Song", 4000);
        em.getTransaction().commit();

        // Display the table
        JPAUtil.checkData("select * from EMPLOYEE");

        String jpql;
        List emps;

        // Display number of people for each salary 
        jpql = "SELECT e.salary, COUNT(e) FROM Employee e " +
               "GROUP BY e.salary";
        emps = em.createQuery(jpql).getResultList();
        displayQueryResult(jpql, emps);

        // Display number of people for each salary among the ones above 5000
        jpql = "SELECT e.salary, COUNT(e) FROM Employee e " +
               "GROUP BY e.salary " +
               "HAVING e.salary > 5000";
        emps = em.createQuery(jpql).getResultList();
        displayQueryResult(jpql, emps);

        em.close();
        emf.close();

    }

    // Display the query result
    public static void displayQueryResult(String jpql, List emps) {
        System.out.println("\n------ Query result of \"" + jpql + "\"");
        for (Object e : emps) {
            Object[] x = (Object[]) e;
            System.out.println("Salary = " + x[0] + ": " + x[1] + " people have this salary");
        }
    }
}
