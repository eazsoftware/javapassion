import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import examples.model.EmployeeService;

import javax.persistence.Query;
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
        es.createEmployee(2, "Bill Clinton", 8000);
        es.createEmployee(3, "Angela Caicedo", 6000);
        es.createEmployee(4, "Annie Song", 5000);
        em.getTransaction().commit();

        // Display the table
        JPAUtil.checkData("select * from EMPLOYEE");

        // Perorm bulk update - change salary of anybody whose current
        // salary is less than 6500 to 7500.
        String jpql = "UPDATE Employee e SET e.salary = :newSalary WHERE e.salary < 6500";
        Query q = em.createQuery(jpql);
        q.setParameter("newSalary", 7500);
        em.getTransaction().begin();
        int updated = q.executeUpdate();
        em.getTransaction().commit();
        displayQueryResult(jpql, updated);

        // Display the table
        JPAUtil.checkData("select * from EMPLOYEE");

        em.close();
        emf.close();

    }

    // Display the query result
    public static void displayQueryResult(String jpql, int deleted) {
        System.out.println("\n------ Query result of \"" + jpql + "\"");
        System.out.println("Number of updated records " + deleted);
    }
}
