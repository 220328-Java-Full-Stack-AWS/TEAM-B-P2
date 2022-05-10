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
    private String creationDate;

    @Column(name="order_total")
    private Double OrderTotal;

    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "orders",fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new LinkedList<OrderItem>();

    public Orders( String creationDate, String address, User user) {
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Double getOrderTotal() {
        return OrderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        OrderTotal = orderTotal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", OrderTotal=" + OrderTotal +
                ", address=" + address +
                ", user=" + user +
                ", orderItems=" + orderItems +
                '}';
    }
}
