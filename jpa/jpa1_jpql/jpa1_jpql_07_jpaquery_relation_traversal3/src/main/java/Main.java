
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import javax.persistence.Query;
import product.client.Manufacturer;
import util.JPAUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        // Display the test table
        JPAUtil.checkData("select MANUFACTURER_ID, NAME, CITY from MANUFACTURER");
        //JPAUtil.checkData("select * from MANUFACTURER");
        JPAUtil.checkData("select PRODUCT_ID, MANUFACTURER_ID, MARKUP, DESCRIPTION from PRODUCT");
        //JPAUtil.checkData("select * from PRODUCT");
        JPAUtil.checkData("select * from PRODUCT_CODE");

        // Perform JPA operrations
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("CustomerServicePU");
        EntityManager em = emf.createEntityManager();

        // Display all manufacturers who have products whose markups are greater than 20.0
        String jpql = "SELECT m FROM Manufacturer m, "
                + "IN (m.productCollection) p WHERE p.markup > 20.0";
        Query query = em.createQuery(jpql);
        List<Manufacturer> manufacturers = query.getResultList();
        displayQueryResult(jpql, manufacturers);

        // Display all manufacturers who have products whose markups are greater than 20.0 - same as above
        jpql = "SELECT m FROM Manufacturer m "
                + "INNER JOIN m.productCollection p WHERE p.markup > 20.0";
        query = em.createQuery(jpql);
        manufacturers = query.getResultList();
        displayQueryResult(jpql, manufacturers);

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
