package com.revature.p2backend.entities;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="order", schema = "p2")
public class Order {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;


@Column(name="created_at")
private Date createdAt;

@Column
private double total;

@ManyToOne(fetch = FetchType.LAZY)
private User user;

@Column
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
private List<OrderItem> orderItems;


    public Order() {
    }

    public Order(Date createdAt, double total, User user) {
        this.createdAt = createdAt;
        this.total = total;
        this.user = user;
    }


    public Order(double total, User user) {
        this.total = total;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
}
