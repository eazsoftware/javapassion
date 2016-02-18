package mypackage;

import javax.persistence.EntityManager;
import entities.*;

public class CreateTestData {

    public static void createTestData(EntityManager em) {
        em.getTransaction().begin();

        // Create customer 1
        Customer customer1 = new Customer();
        customer1.setName("Customer1");
        customer1.setId(1);

        // Create customer 2
        Customer customer2 = new Customer();
        customer2.setName("Customer2");
        customer2.setId(2);

        // Create customer 3
        Customer customer3 = new Customer();
        customer3.setName("Customer3");
        customer3.setId(3);

        // Create 3 orders 
        Order order1 = new Order();
        order1.setAddress("Address of order #1, Brazil");
        order1.setId(1);
        Order order2 = new Order();
        order2.setAddress("Address of order #2, USA");
        order2.setId(2);
        Order order3 = new Order();
        order3.setAddress("Address of order #3, Korea");
        order3.setId(3);

        customer1.setOrder(order1);
        customer2.setOrder(order2);
        customer3.setOrder(order3);

        order1.setCustomerX(customer1);
        order2.setCustomerX(customer2);
        order3.setCustomerX(customer3);

        em.persist(customer1);
        em.persist(customer2);
        em.persist(customer3);

        em.getTransaction().commit();

    }
}