import customer.model.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import javax.persistence.Query;
import util.JPAUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        // Perform JPA operrations
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("CustomerServicePU");
        EntityManager em = emf.createEntityManager();

        // Display the test table
        JPAUtil.checkData("select CUSTOMER_ID, NAME, EMAIL from CUSTOMER");
        //JPAUtil.checkData("select * from CUSTOMER");

        // Get all customers whose name has e as the 2nd character
        String jpql = "SELECT c FROM Customer c WHERE SUBSTRING(c.name, 2, 1) = 'e'";
        Query query = em.createQuery(jpql);
        List<Customer> customers = query.getResultList();
        displayQueryResult(jpql, customers);

        // Get a customer whose name in lower character is 'jumbocom'
        jpql = "SELECT c FROM Customer c WHERE LOWER(c.name) = 'jumbocom'";
        query = em.createQuery(jpql);
        customers = query.getResultList();
        displayQueryResult(jpql, customers);

        // Get customers length of whose names are greater than 15
        jpql = "SELECT c FROM Customer c WHERE LENGTH(c.name) > 15";
        query = em.createQuery(jpql);
        customers = query.getResultList();
        displayQueryResult(jpql, customers);

        // Get customers whose email address has 'net'
        jpql = "SELECT c FROM Customer c WHERE LOCATE('net', c.email) > 1 ";
        query = em.createQuery(jpql);
        customers = query.getResultList();
        displayQueryResult(jpql, customers);
        
        // Get customers whose email address DID NOT CONTAIN 'net'
        jpql = "SELECT c FROM Customer c WHERE c.email NOT LIKE '%net%' ";
        query = em.createQuery(jpql);
        customers = query.getResultList();
        displayQueryResult(jpql, customers);        

        // Get customers whose email address DID NOT CONTAIN 'net' OR 'example.com'
        jpql = "SELECT c FROM Customer c WHERE c.email NOT LIKE '%net%' AND c.email NOT LIKE '%www%'";
        query = em.createQuery(jpql);
        customers = query.getResultList();
        displayQueryResult(jpql, customers);          
        
        em.close();
        emf.close();
    }

    // Get the query result
    public static void displayQueryResult(String jpql, List<Customer> customers) {
        System.out.println("\n------ Query result of \"" + jpql + "\"");
        for (Customer e : customers) {
            System.out.println("Customer id = " + e.getCustomerId() + ", Name = " + e.getName() + ", Email = " + e.getEmail());
        }
    }
}
