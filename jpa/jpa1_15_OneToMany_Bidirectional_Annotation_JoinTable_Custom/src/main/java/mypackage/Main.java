package mypackage;


import entities.Customer;
import entities.Order;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import util.JPAUtil;

public class Main {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void main(String[] args) {

        // Delete join table so that you can run the application again
        JPAUtil.droptable("drop table MY_JOIN_TABLE");
        JPAUtil.droptable("drop table CUSTOMER_ORDER_TABLE");

        emf = Persistence.createEntityManagerFactory("pu1");
        em = emf.createEntityManager();

        // Create test data
        CreateTestData.createTestData(em);

        // Get a customer and then navigate from the ustomer to his orders
        Customer c1 = em.find(Customer.class, 1);
        System.out.println("---->Customer 1 = " + c1.getName());

        List<Order> orders = c1.getOrders();
        System.out.println("---->First order of the customer 1 = " + orders.get(0).getAddress());

        // Get an order and then navigate from the order to the customer
        Order o1 = em.find(Order.class, 1);
        Customer c2 = o1.getCustomer();
        System.out.println("---->Customer of Order 1 = " + c2.getName());

        em.close();
        emf.close();

        // Display the tables
        JPAUtil.checkData("select * from CUSTOMER ORDER BY ID");
        JPAUtil.checkData("select * from ORDER_TABLE ORDER BY ORDER_ID");
        JPAUtil.checkData("select * from MY_JOIN_TABLE ORDER BY MY_CUSTOMER_ID");

    }

}
