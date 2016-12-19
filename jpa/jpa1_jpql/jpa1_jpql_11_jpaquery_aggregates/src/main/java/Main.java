import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.Query;
import util.JPAUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        // Perform JPA operrations
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("CustomerServicePU");
        EntityManager em = emf.createEntityManager();

        // Display the table
        JPAUtil.checkData("select CUSTOMER_ID, NAME, CREDIT_LIMIT, DISCOUNT_CODE from CUSTOMER ORDER BY DISCOUNT_CODE");
        //JPAUtil.checkData("select * from CUSTOMER");
        JPAUtil.checkData("select * from DISCOUNT_CODE");

        // Get the average credit limit on all customers
        String jpql = "SELECT AVG(c.creditLimit) FROM Customer c";
        Query query = em.createQuery(jpql);
        Number result = (Number) query.getSingleResult();
        displayQueryResult(jpql, result);

        // Get the total credit limit among all customers
        jpql = "SELECT SUM(c.creditLimit) FROM Customer c";
        query = em.createQuery(jpql);
        result = (Number) query.getSingleResult();
        displayQueryResult(jpql, result);

        // Get the maximum credit limit among all customers
        jpql = "SELECT MAX(c.creditLimit) FROM Customer c";
        query = em.createQuery(jpql);
        result = (Number) query.getSingleResult();
        displayQueryResult(jpql, result);

        // Get the maximum credit limit among the customers whose discount rate is less
        // than 10.0.
        jpql = "SELECT MAX(c.creditLimit) FROM Customer c "
                + "WHERE c.discountCode.rate < 10.0";
        query = em.createQuery(jpql);
        result = (Number) query.getSingleResult();
        displayQueryResult(jpql, result);

        em.close();
        emf.close();

    }

    // Display the query result
    public static void displayQueryResult(String jpql, Number result) {
        System.out.println("\n------ Query result of \"" + jpql + "\"");
        System.out.println("Result = " + result);
    }
}
