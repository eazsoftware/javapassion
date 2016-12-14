import customer.model.Customer;
import java.util.Arrays;
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
        JPAUtil.checkData("select CUSTOMER_ID, DISCOUNT_CODE, NAME, EMAIL from CUSTOMER");
        JPAUtil.checkData("select * from DISCOUNT_CODE");

        // Display all customers whose discount rate is bigger than 10%
        String jpql = "SELECT c FROM Customer c WHERE c.discountCode.rate > 10";
        Query query = em.createQuery(jpql);
        List<Customer> customers = query.getResultList();
        displayQueryResult(jpql, customers);

        // Display all customers whose discount rate code is L
        jpql = "SELECT c FROM Customer c WHERE c.discountCode.discountCode IN (:codes)";
        query = em.createQuery(jpql);
        query.setParameter("codes", new Character('L'));
        customers = query.getResultList();
        displayQueryResult(jpql, customers);        
        
        em.close();
        emf.close();

    }

    // Display the query result
    public static void displayQueryResult(String jpql, List<Customer> customers) {
        System.out.println("\n------ Query result of \"" + jpql + "\"");
        for (Customer e : customers) {
            System.out.println("Customer id = " + e.getCustomerId() + ", name = " + e.getName() + ", discount rate = " + e.getDiscountCode().getRate());
        }
    }
}
