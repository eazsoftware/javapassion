import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import examples.model.Employee;
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
        es.createEmployee(2, "Bill Clinton", 8000);
        es.createEmployee(3, "Angela Caicedo", 6000);
        es.createEmployee(4, "Annie Song", 5000);
        es.createEmployee(5, "Charles Song", 4500);
        em.getTransaction().commit();

        // Display the table
        JPAUtil.checkData("select * from EMPLOYEE");

        // Get all employees whose name is 'Sang Shin'
        String jpql = "SELECT e FROM Employee e where e.name = 'Sang Shin'";
        List<Employee> emps = em.createQuery(jpql).getResultList();
        displayQueryResult(jpql, emps);

        // Get all employees whose name is 'Sang Shin' or whose salary is greater than 7000
        jpql = "SELECT e FROM Employee e where e.name = 'Sang Shin' OR e.salary > 7000";
        emps = em.createQuery(jpql).getResultList();
        displayQueryResult(jpql, emps);

        // Get all emplyees whose name starts with A
        jpql = "SELECT e FROM Employee e where e.name LIKE 'A%'";
        emps = em.createQuery(jpql).getResultList();
        displayQueryResult(jpql, emps);

        // Get all employees whose name is either 'Sang Shin' or 'Bill Clinton" or 'Some name'
        jpql = "SELECT e FROM Employee e where e.name IN ('Sang Shin', 'Bill Clinton', 'Some name')";
        emps = em.createQuery(jpql).getResultList();
        displayQueryResult(jpql, emps);

          // Get all employees whose name is not either 'Sang Shin' or 'Bill Clinton'
        jpql = "SELECT e FROM Employee e where e.name NOT IN ('Sang Shin', 'Bill Clinton')";
        emps = em.createQuery(jpql).getResultList();
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
