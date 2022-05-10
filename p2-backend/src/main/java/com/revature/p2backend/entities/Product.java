package com.revature.p2backend.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer productId ;

    @Column
    private String description;

    @Column
    private Double price;

    @OneToOne(mappedBy = "productId")
    private OrderItem orderItem;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    @Type(type = "com.revature.p2backend.utilities.EnumConverter")
    private Category category;

    public Product() {
    }

    public Product( String description, Double price,Category category) {
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
                ", description='" + description + '\'' +
                ", price=" + price +
                ", orderItem=" + orderItem +
                ", category=" + category +
                '}';
    }
}
