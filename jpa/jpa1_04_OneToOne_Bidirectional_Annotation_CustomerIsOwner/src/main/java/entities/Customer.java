package entities;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.CascadeType.*;

@Entity
public class Customer implements Serializable {

    @Id
    // Not using the generated key so that we can feed the id for testing purpise
    //@GeneratedValue
    private int id;
    private String name;

    // Customer is owner of the relationship so it gets to
    // specify the name of the join column if it wants to.
    @OneToOne(cascade=ALL)
    @JoinColumn(name = "MY_ORDER_ID_FK")
    private Order order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }
}
