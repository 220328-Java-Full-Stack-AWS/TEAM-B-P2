package com.revature.p2backend.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="category", schema = "p2")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String category;

    @OneToMany
    private List<Product> products;


    public Category() {
    }

    public Category(String category, List<Product> products) {
        this.category = category;
        this.products = products;
    }

    public Category(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
