package com.revature.p2backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
@JsonIgnoreProperties
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer productId;

    @Column(nullable = false)
    @NotNull
    private String name; //added name column

    @Column
    private String description;

    @Column
    private Double price;//changed to Double

    @Column
    private Integer inventory;

//    @OneToOne(mappedBy = "productId")
//    private OrderItem orderItem;

    @Column(name = "category" , nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Type(type = "com.revature.p2backend.beans.utilities.EnumConverter")
    private Category category;

    @Column
    private String keywords; //added

    @Column(unique = true , nullable = false)
    @NotNull
    private String sku; //added

    @Column
    @Min(value = 5, message = "Min discount should not be less than 5")
    @Max(value = 95, message = "Max discount should not be greater than 5")
    private Integer discount;//added

    @Column(nullable = false)
    @NotNull
    private String imageUrl;//added

    public Product() {
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                ", category=" + category +
                ", keywords='" + keywords + '\'' +
                ", sku='" + sku + '\'' +
                ", discount=" + discount +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
