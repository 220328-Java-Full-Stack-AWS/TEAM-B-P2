package com.revature.p2backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "products")
@JsonIgnoreProperties
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer productId ;

    @Column
    private String name; //added name column

    @Column
    private String description;

    @Column
    private Double price;//changed to Double

    @Column
    private Integer inventory;

//    @OneToOne(mappedBy = "productId")
//    private OrderItem orderItem;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    @Type(type = "com.revature.p2backend.beans.utilities.EnumConverter")
    private Category category;


    @Column
    private String keywords;

    public Product() {
    }

    public Product(String name, String description, Double price, Integer inventory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
    }


    public Product(String name, String description, Double price, Integer inventory, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.category = category;
    }
    public Product(Integer id,String name, String description, Double price, Integer inventory, Category category) {
        this.productId=id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
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


//    public OrderItem getOrderItem() {
//        return orderItem;
//    }
//
//    public void setOrderItem(OrderItem orderItem) {
//        this.orderItem = orderItem;
//    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", description='" + description + '\'' +
                ", price=" + price +
//                ", orderItem=" + orderItem +
                ", category=" + category +
                '}';
    }
}
