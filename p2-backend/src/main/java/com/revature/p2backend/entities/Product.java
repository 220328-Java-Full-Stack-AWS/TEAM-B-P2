package com.revature.p2backend.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product", schema = "p2")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   @Column
   private String name;

   @Column
   private Double price;

   @Column
   private String description;

   @Column(name = "image_url")
   private String imgeUrl;

   @Column(name = "inventory_id")
   private Integer inventoryId;

   @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
   private List<OrderItem> orderItems;

   @ManyToOne(fetch = FetchType.LAZY)
    private Category category;


    public Product() {
    }

    public Product(String name, Double price, String description,
                   String imgeUrl, Integer inventoryId,
                   List<OrderItem> orderItems, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imgeUrl = imgeUrl;
        this.inventoryId = inventoryId;
        this.orderItems = orderItems;
        this.category = category;
    }

    public Product(String name, Double price, String description,
                   String imgeUrl, Integer inventoryId, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imgeUrl = imgeUrl;
        this.inventoryId = inventoryId;
        this.category = category;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgeUrl() {
        return imgeUrl;
    }

    public void setImgeUrl(String imgeUrl) {
        this.imgeUrl = imgeUrl;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
