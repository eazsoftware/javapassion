package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Customer implements Serializable {

    @Id
    //@GeneratedValue
    private int id;
    
    private String name;

    // By using annotation, you can specify more options
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "MY_ORDER_FK")
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
