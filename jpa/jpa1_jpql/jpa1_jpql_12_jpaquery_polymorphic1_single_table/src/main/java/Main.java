import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import examples.model.Employee;
import examples.model.EmployeeService;
import examples.model.ManagerService;
import java.util.List;
import javax.persistence.Query;

import util.JPAUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        // Set up the table and data for testing
        JPAUtil.droptable("drop table EMPLOYEE");
        JPAUtil.setup("create table EMPLOYEE ( employee_id int, dtype VARCHAR(20), name VARCHAR(20), salary int, department VARCHAR(20))");

        // Perform JPA operrations
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = emf.createEntityManager();

        // Create some employees
        EmployeeService es = new EmployeeService(em);
        ManagerService ms = new ManagerService(em);

        em.getTransaction().begin();
        es.createEmployee(1, "Sang Shin", 10000);
        es.createEmployee(2, "Bill Clinton", 8000);
        es.createEmployee(3, "Angela Caicedo", 6000);
        es.createEmployee(4, "Annie Song", 5000);
        es.createEmployee(5, "Charles Song", 4500);
        ms.createManager(6, "Shelley Noting", 10000, "Engineering");
        ms.createManager(7, "Micheal Jackson", 20000, "Marketing");
        em.getTransaction().commit();

        // Display the table
        JPAUtil.checkData("select * from EMPLOYEE");

        //Get average salary for employees
        String jpql = "SELECT AVG(f.salary) FROM Employee f";
        Query query = em.createQuery(jpql);
        Number result = (Number) query.getSingleResult();        
        displayQueryResult(jpql, result);
        
        // Get all employees whose salary is greater than average salary
        jpql = "SELECT e FROM Employee e " +
                      "WHERE e.salary > (SELECT AVG(f.salary) FROM Employee f)";
        List<Employee> emps = em.createQuery(jpql).getResultList();
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
    
    public static void displayQueryResult(String jpql, Number result) {
        System.out.println("\n------ Query result of \"" + jpql + "\"");
        System.out.println("Result = " + result);
    }    
}