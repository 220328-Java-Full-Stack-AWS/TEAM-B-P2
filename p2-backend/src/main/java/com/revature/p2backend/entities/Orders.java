package com.revature.p2backend.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="orders", schema = "p2")
@JsonIgnoreProperties
public class Orders {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="order_id")
private Integer id;


@Column(name="creation_date")
private String creationDate;

@Column(name="order_total")
private Double ordetTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="address_id", referencedColumnName = "address_id")
    private Address address;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="billing_address_id", referencedColumnName =  "address_id")
    private Address billingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "orders",fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new LinkedList<OrderItem>();


    public Orders() {
    }

    public Orders(String creationDate, Address address, User user) {
        this.creationDate = creationDate;
        this.address = address;
        this.user = user;
    }

    public Orders(String creationDate, Address address,
                  Address billingAddress, User user) {
        this.creationDate = creationDate;
        this.address = address;
        this.billingAddress = billingAddress;
        this.user = user;
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

    public Double getOrdetTotal() {
        return ordetTotal;
    }

    public void setOrdetTotal(Double ordetTotal) {
        this.ordetTotal = ordetTotal;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", ordetTotal=" + ordetTotal +
                ", address=" + address +
                ", user=" + user +
                ", orderItems=" + orderItems +
                '}';
    }
}
