package com.revature.p2backend.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="orders" , schema="public")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Integer id;

    @Column(name="creation_date")
    private Timestamp creationDate;

    @Column(name="order_total")
    private BigDecimal OrderTotal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id", referencedColumnName = "address_id")
    private Address address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "orders",fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new LinkedList<OrderItem>();

    public Orders( Timestamp creationDate, Address address, User user) {
        this.creationDate = creationDate;
        this.address = address;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getOrderTotal() {
        return OrderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        OrderTotal = orderTotal;
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

    public void setOrderItems(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }
}
