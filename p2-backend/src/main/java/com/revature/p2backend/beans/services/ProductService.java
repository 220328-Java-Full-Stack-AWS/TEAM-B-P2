package com.revature.p2backend.beans.services;

import com.revature.p2backend.beans.dao.ProductDao;
import com.revature.p2backend.entities.Category;
import com.revature.p2backend.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    //TODO convert this into a service and add appropriate methods
    /*
    examples of methods: view
    really the user should not be able to do anything
    with the product besides view and add to cart. Adding
    to cart will be handled by front.
     */
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao= productDao;
    }

    public Product save(Product product){
        return productDao.save(product);
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public Product getByProductId(Integer id) {
        return productDao.getById(id);
    }

    public List<Product> getByProductName(String name) {
        return productDao.getByProductName(name);
    }

    public List<Product> getByProductCategory(Category category) {
        return productDao.getByCategory(category);
    }

    public List<Product> getByPriceRange(Double lower, Double upper) {
        return productDao.getByPriceRange(lower, upper);
    }

    public List<Product> getByProductNamePartialMatch(String name) {
        return productDao.getProductWithSubstring(name);
    }

    public Product updateProductInfo(Product product) {
        return productDao.update(product);
    }

    public Product update(Product product){
        return productDao.update(product);
    }

    public void delete(Product product){
        productDao.delete(product);
    }



}
