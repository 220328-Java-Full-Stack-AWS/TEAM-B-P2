package com.revature.p2backend.dao;

import com.revature.p2backend.entities.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDao implements HibernateDao<Product>{

    private Session session;

    public ProductDao(Session session){
        this.session = session;
    }

    @Override
    public void save(Product product) {
        Transaction tx = session.beginTransaction();
        session.save(product);
        tx.commit();
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
