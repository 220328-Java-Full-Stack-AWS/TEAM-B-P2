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

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name="address_id", referencedColumnName = "address_id")
    private Address address;

    @ManyToOne()
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;

    //removed fetch
    @OneToMany(mappedBy = "orders",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItem> orderItems = new LinkedList<OrderItem>();

//    public Orders(String creationDate, Integer addressId, Integer userId) {
//        this.creationDate = creationDate;
//      //  this.address.setAddressId(addressId);
//        this.user.setId(userId);
//    }

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
      this.orderItems =  orderItems;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", OrderTotal=" + orderTotal +
                ", address=" + address.toString() +
                ", user=" + user.toString() +
                '}';
    }
}
