package com.revature.p2backend.entities;

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
    private BigDecimal price;

    @OneToOne(mappedBy = "productId")
    private OrderItem orderItem;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_category_id" , referencedColumnName = "category_id")
    private ProductCategory productCategory;

    public Product() {
    }

    public Product( String description, BigDecimal price,ProductCategory productCategory) {
        this.description = description;
        this.price = price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }
}
