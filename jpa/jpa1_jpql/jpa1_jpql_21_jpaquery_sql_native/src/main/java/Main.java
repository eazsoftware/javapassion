import examples.model.Employee;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import examples.model.EmployeeService;
import java.util.Iterator;
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
        es.createEmployee(1, "Sang Shin", 10000);
        es.createEmployee(2, "Bill Clinton", 8000);
        es.createEmployee(3, "Angela Caicedo", 6000);
        es.createEmployee(4, "Annie Song", 5000);
        es.createEmployee(5, "Charles Song", 4500);
        em.getTransaction().commit();

        // Display the table
        JPAUtil.checkData("select * from EMPLOYEE");

        // Get all employees whose name is 'Sang Shin'
        String sql = "SELECT * FROM Employee e WHERE e.salary > 5000";
        Query q = em.createNativeQuery(sql, Employee.class);
        List<Employee> emps = q.getResultList();
        displayQueryResult(sql, emps);

        em.close();
        emf.close();

    }

    // Display the query result
    public static void displayQueryResult(String jpql, List emps) {

        System.out.println("\n------ Query result of \"" + jpql + "\"");
        Iterator i = emps.iterator();
        Employee emp;
        while (i.hasNext()) {
            emp = (Employee) i.next();
            System.out.println("Employee name = " + emp.getName() + ", salary = " + emp.getSalary());
        }

    }
}