package com.revature.p2backend.entities;


import javax.persistence.*;

@Entity
@Table(name="order_item", schema = "p2")
public class OrderItem {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@Column
private Integer quantity;


@ManyToOne(fetch = FetchType.LAZY)
private Order order;

@ManyToOne(fetch = FetchType.LAZY)
private Product product;


    public OrderItem() {
    }


    public OrderItem(Integer quantity, Order order) {
        this.quantity = quantity;
        this.order = order;
    }


    public OrderItem(Integer quantity, Order order, Product product) {
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
