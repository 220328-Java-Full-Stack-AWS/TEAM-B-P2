package com.revature.p2backend.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Integer productCategoryId;

    @Column(name="product_category")
    private String productCategory;

    @OneToMany(mappedBy = "productCategory",fetch = FetchType.LAZY)
    private Set<Product> products;

    public ProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
