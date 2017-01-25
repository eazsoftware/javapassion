import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import entities.Manufacturer;
import util.JPAUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        // Display the test table
        JPAUtil.checkData("select MANUFACTURER_ID, NAME, CITY from MANUFACTURER ORDER BY MANUFACTURER_ID");
        //JPAUtil.checkData("select * from MANUFACTURER");
        JPAUtil.checkData("select PRODUCT_ID, MANUFACTURER_ID, MARKUP from PRODUCT ORDER BY PRODUCT_ID");
        //JPAUtil.checkData("select * from PRODUCT");
        JPAUtil.checkData("select * from PRODUCT_CODE ORDER BY PROD_CODE");

        // Perform JPA operrations
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("CustomerServicePU");
        EntityManager em = emf.createEntityManager();

        // Load the Manufacturer 19941212
        System.out.println("----> Before calling em.find(Manufacturer.class, 19941212) ");
        em.find(Manufacturer.class, 19941212);
        System.out.println("----> After calling em.find(Manufacturer.class, 19941212) ");

        em.close();
        emf.close();

    }

    // Display the query result
    public static void displayQueryResult(String jpql, List<Manufacturer> items) {
        System.out.println("\n------ Query result of \"" + jpql + "\"");
        for (Manufacturer i : items) {
            System.out.println("Manufacturer name = " + i.getName());
        }
    }
}
