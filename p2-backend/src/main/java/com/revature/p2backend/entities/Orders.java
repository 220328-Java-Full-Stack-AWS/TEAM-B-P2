package com.revature.p2backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="orders" , schema="public")
@JsonIgnoreProperties
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id", unique = true, nullable = false)
    private Integer id;

    @Column(name="creation_date")
    private String creationDate;

    @Column(name="order_total")
    private Double orderTotal;//changed to Double from Big Decimal

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="address_id", referencedColumnName = "address_id")
    @JsonBackReference
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="billing_address_id", referencedColumnName =  "address_id")
    private Address billingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "orders",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderItem> orderItems;


    public Orders( String creationDate, Address address, User user) {
        this.orderItems = new LinkedList<>();
        this.creationDate = creationDate;
        this.address = address;
        this.user = user;
    }

    //added constructor for create order with billing address
    public Orders(String creationDate, Address address, Address billingAddress, User user) {
        this.creationDate = creationDate;
        this.address = address;
        this.billingAddress = billingAddress;
        this.user = user;
    }

    public Orders() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", OrderTotal=" + orderTotal +
                ", address=" + address +
                ", user=" + user +
                '}';
    }
}
