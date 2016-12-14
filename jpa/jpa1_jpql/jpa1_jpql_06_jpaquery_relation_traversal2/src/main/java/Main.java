import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import javax.persistence.Query;
import product.client.Product;
import util.JPAUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        // Display the test table
        JPAUtil.checkData("select MANUFACTURER_ID, NAME, CITY from MANUFACTURER");
        //JPAUtil.checkData("select * from MANUFACTURER");
        JPAUtil.checkData("select PRODUCT_ID, MANUFACTURER_ID, DESCRIPTION from PRODUCT");
        //JPAUtil.checkData("select * from PRODUCT");
        JPAUtil.checkData("select * from PRODUCT_CODE");

        // Perform JPA operrations
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("CustomerServicePU");
        EntityManager em = emf.createEntityManager();

        // Display all products whose manufacturer is "Sun Microsystems"
        String jpql = "SELECT p FROM Product p WHERE p.manufacturerId.name = 'Sun MicroSystems'";
        Query query = em.createQuery(jpql);
        List<Product> products = query.getResultList();
        displayQueryResult(jpql, products);

        em.close();
        emf.close();

    }

    // Display the query result
    public static void displayQueryResult(String jpql, List<Product> items) {
        System.out.println("\n------ Query result of \"" + jpql + "\"");
        for (Product i : items) {
            System.out.println("Product description = " + i.getDescription() );
        }
    }
}
