import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import examples.model.Employee;
import examples.model.EmployeeService;
import examples.model.ManagerService;
import java.util.List;

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
        es.createEmployee(1, "Sang Shin", 15000);
        es.createEmployee(2, "Bill Clinton", 8000);
        es.createEmployee(3, "Angela Caicedo", 6000);
        es.createEmployee(4, "Annie Song", 25000);
        es.createEmployee(5, "Charles Song", 24500);
        ms.createManager(6, "Shelley Noting", 10000, "Engineering");
        ms.createManager(7, "Micheal Jackson", 20000, "Marketing");
        em.getTransaction().commit();

        // Display the table
        JPAUtil.checkData("select * from EMPLOYEE");

        // Get all employees whose salary is bigger than salaries of all managers
        String jpql = "SELECT e FROM Employee e "
                + "WHERE e.salary > ALL (SELECT m.salary from Manager m)";
        List<Employee> emps = em.createQuery(jpql).getResultList();
        displayQueryResult(jpql, emps);

        // Get all employees whose salary is bigger than salaries of any manager
        jpql = "SELECT e FROM Employee e "
                + "WHERE e.salary > ANY (SELECT m.salary from Manager m)";
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
