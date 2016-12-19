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

        // Display the test tables
        JPAUtil.checkData("select CUSTOMER_ID, NAME, EMAIL, DISCOUNT_CODE, CREDIT_LIMIT from CUSTOMER");
        //JPAUtil.checkData("select * from CUSTOMER");
        JPAUtil.checkData("select * from DISCOUNT_CODE");

        // Get customers whose absolute discount rate is greater than 10.0.
        // (Yes, it is not the best example to show the usage of ABS function
        //  given that the discount rate is always a positive value.)
        String jpql = "SELECT c FROM Customer c WHERE ABS(c.discountCode.rate) > 10.0";
        Query query = em.createQuery(jpql);
        List<Customer> customers = query.getResultList();
        displayQueryResult(jpql, customers);

        // Get customers whose credit limit MOD 10000 is 5000.
        jpql = "SELECT c FROM Customer c WHERE MOD(c.creditLimit, 10000) = 5000";
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
            System.out.println("Customer id = " + e.getCustomerId() + ", name = " + e.getName()
                    + ", discount rate = " + e.getDiscountCode().getRate()
                    + ", credit limit = " + e.getCreditLimit());
        }
    }
}
