package com.revature.p2backend.entities;

import org.hibernate.criterion.Order;

import javax.persistence.*;

@Entity
@Table(name = "cart", schema = "public")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "order_item_id", referencedColumnName = "order_item_id")
    private OrderItem orderItemId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;


    public Cart() {
    }

    public Cart(Integer id, OrderItem orderItemId, User userId) {
        this.id = id;
        this.orderItemId = orderItemId;
        this.userId = userId;
    }

    public Cart(OrderItem orderItemId, User userId) {
        this.orderItemId = orderItemId;
        this.userId = userId;
    }

    public Cart(OrderItem orderItemId, OrderItem orderItemId2, User userId) {
        this.orderItemId = orderItemId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderItem getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(OrderItem orderItemId) {
        this.orderItemId = orderItemId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
