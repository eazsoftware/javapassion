package entities;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_TABLE")
public class Order {

    @Id
    // Not using the generated key so that we can feed the id for testing purpise
    //@GeneratedValue
    @Column(name = "ORDER_ID")
    private int id;

    @Column(name = "SHIPPING_ADDRESS")
    private String address;
    
    // Order is the reverse owner of the relationship.
    @OneToOne(cascade=CascadeType.ALL, mappedBy="order")
    private Customer customerX;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the customerX
     */
    public Customer getCustomerX() {
        return customerX;
    }

    /**
     * @param customerX the customerX to set
     */
    public void setCustomerX(Customer customerX) {
        this.customerX = customerX;
    }

}
