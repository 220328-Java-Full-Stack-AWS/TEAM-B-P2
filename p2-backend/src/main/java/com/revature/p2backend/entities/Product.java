package com.revature.p2backend.entities;


import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "products", schema = "p2")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column
    private String name;


    @Column
    private String description;

    @Column
    private Double price;



    @Column
    private Integer inventory;


    @OneToOne(mappedBy = "productId")
    private OrderItem orderItem;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    @Type(type = "com.revature.p2backend.beans.utilities.EnumConverter")
    private Category category;


    public Product() {
    }

    public Product(String name, String description, Double price,
                   Integer inventory, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.category = category;
    }


    public Product(String name, String description, Double price,
                   Integer inventory, OrderItem orderItem,
                   Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.orderItem = orderItem;
        this.category = category;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                ", orderItem=" + orderItem +
                ", category=" + category +
                '}';
    }
}


