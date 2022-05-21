package com.revature.p2backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
    private String keywords; //added

    @Column
    @Min(value = 5, message = "Min discount should not be less than 5")
    @Max(value = 95, message = "Max discount should not be greater than 5")
    private Integer discount;//added

    @Column
    private String imageUrl;//added

    public Product() {
    }

    public Product(String name, String description, Double price, Integer inventory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
    }
    public Product(String name, String description, Double price, Integer inventory, Category category,Integer discount, String Keywords,String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.category = category;
        this.imageUrl = imageUrl;
        this.keywords= keywords;
        this.discount=discount;
    }
    public Product(Integer id,String name, String description, Double price, Integer inventory, Category category,Integer discount, String Keywords,String imageUrl) {
        this.productId=id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.discount=discount;
        this.category = category;
        this.imageUrl = imageUrl;
        this.keywords=keywords;
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

    public Category getCategory() {return category;}

    public void setCategory(Category category) {this.category = category;}

    public String getKeywords() {return keywords;}

    public void setKeywords(String keywords) {this.keywords = keywords;}

    public String getImageUrl() {return imageUrl;}
    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}


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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
