package entities;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_TABLE")
public class Order {

    @Id
    //@GeneratedValue
    @Column(name = "ORDER_ID")
    private int id;
    
    @Column(name = "SHIPPING_ADDRESS")
    private String address;
    
    // Order is the owner of the one-to-many relationship
    // In one to many relationship, the many side, Order in this
    // example, is always the owner of the relationship since
    // it is the one that contains the foreign key.
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID_FK")
    private Customer customer;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
