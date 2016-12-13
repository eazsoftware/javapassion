import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import examples.model.Employee;
import examples.model.EmployeeService;
import java.util.List;

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
        es.createEmployee(1, "Sang Shin", 1000);
        es.createEmployee(2, "Bill Clinton", 8000);
        es.createEmployee(3, "Angela Caicedo", 6000);
        es.createEmployee(4, "Annie Song", 5000);
        es.createEmployee(5, "Charles Lannie", 7500);
        es.createEmployee(6, "Shelley Nichole", 8500);
        em.getTransaction().commit();

        // Display the table
        JPAUtil.checkData("select * from EMPLOYEE");

        // Perform JPA query
        String jpql = "SELECT e FROM Employee e " + 
                      "WHERE e.name = ?1 OR e.salary > ?2";
        Query q = em.createQuery(jpql);
        q.setParameter(1, "Sang Shin").setParameter(2, 7000);
        List<Employee> emps = q.getResultList();
        displayQueryResult(jpql, emps);

        em.close();
        emf.close();

    }

    // Display the query result
    public static void displayQueryResult(String jpql, List<Employee> emps) {
        System.out.println("\n------ Query result of \"" + jpql + "\"");
        for (Employee e : emps) {
            System.out.println("Employee " + e.getId() + ", " + e.getName() + ", " + e.getSalary());
        }
    }
}
