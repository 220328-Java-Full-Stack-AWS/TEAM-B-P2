package com.revature.p2backend.dao;

import com.revature.p2backend.entities.Product;

import java.util.List;

public class ProductDao implements HibernateDao<Product>{
    @Override
    public void save(Product product) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product getById(Integer id) {
        return null;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Integer id) {

    }
}
