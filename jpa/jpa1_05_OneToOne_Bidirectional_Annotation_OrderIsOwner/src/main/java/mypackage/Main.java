package mypackage;

import entities.Customer;
import entities.Order;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import util.JPAUtil;

public class Main {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void main(String[] args) {

        // Delete join table that might have been created by other exercises so that
        // you can run the application again
        JPAUtil.droptable("drop table MY_JOIN_TABLE");
        JPAUtil.droptable("drop table CUSTOMER_ORDER_TABLE");
        JPAUtil.droptable("drop table ORDER_TABLE");

        emf = Persistence.createEntityManagerFactory("pu1");
        em = emf.createEntityManager();

        // Create test data
        CreateTestData.createTestData(em);

        System.out.println("------------> Navigating from customer to order");
        // Get a customer and then navigate from the ustomer to his order
        Customer c1 = em.find(Customer.class, 1);
        System.out.println("---->Customer 1 = " + c1.getName());

        Order order = c1.getOrder();
        System.out.println("---->Delivert address of the order of the customer 1 = " + order.getAddress());

        System.out.println("------------> Navigating from order to customer");
        // Get an order and then navigate from the order to the customer
        Order o1 = em.find(Order.class, 1);
        Customer c2 = o1.getCustomerX();
        System.out.println("---->Customer of Order 1 = " + c2.getName());

        em.close();
        emf.close();

        // Display the tables
        JPAUtil.checkData("select * from CUSTOMER ORDER BY ID");
        JPAUtil.checkData("select * from ORDER_TABLE ORDER BY ORDER_ID");

    }
}
