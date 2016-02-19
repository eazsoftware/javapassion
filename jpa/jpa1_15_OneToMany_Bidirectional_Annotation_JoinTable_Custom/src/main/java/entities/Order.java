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

    // Since Order has a field of Customer, the relationship
    // is now bi-directional.
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
