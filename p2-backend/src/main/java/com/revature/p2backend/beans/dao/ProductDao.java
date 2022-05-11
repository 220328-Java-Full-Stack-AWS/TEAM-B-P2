package com.revature.p2backend.beans.dao;

import com.revature.p2backend.beans.utilities.StorageManager;
import com.revature.p2backend.entities.Category;
import com.revature.p2backend.entities.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class ProductDao implements HibernateDao<Product>{
    private final StorageManager storageManager;
    private boolean running = false;
    private Session session;

    @Autowired
    public ProductDao(StorageManager storageManager) {
        this.storageManager = storageManager;

    }//make connection to the table products

    @Override
    public Product save(Product product) {
        Transaction tx = session.beginTransaction();
        session.save(product);
        tx.commit();
        return product;
    }

    @Override
    public List<Product> getAll() {
        TypedQuery<Product> query = session.createQuery("FROM Product", Product.class);
        List<Product> products = query.getResultList();

        return products;
    }

    @Override
    public Product getById(Integer id) {
        TypedQuery<Product> query = session.createQuery("FROM Product WHERE id = :productId", Product.class);
        query.setParameter("productId", id);
        Product product = query.getSingleResult();

        return product;
    }

    @Override
    public void delete(Product product) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("Delete Product WHERE id = :productId");
        query.setParameter("productId", product.getProductId());
        query.executeUpdate();
        tx.commit();
    }

    @Override
    public void update(Product product) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("UPDATE Product SET " +
                "name = :name, price = :price, description = :description, inventory = :inventory, category = :category " +
                "WHERE id = :id");
        query.setParameter("name", product.getName());
        query.setParameter("price", product.getPrice());
        query.setParameter("description", product.getDescription());
        query.setParameter("inventory", product.getInventory());
        query.setParameter("category", product.getCategory());
        query.setParameter("id", product.getProductId());
        query.executeUpdate();
        tx.commit();
    }

    public Product getByProductName(String name){
        TypedQuery<Product> query = session.createQuery("FROM Product WHERE name = :productName", Product.class);
        query.setParameter("productName", name);
        Product product = query.getSingleResult();

        return product;
    }

    public List<Product> getByCategory(Category category){
        TypedQuery<Product> query = session.createQuery("FROM Product WHERE category = :category", Product.class);
        query.setParameter("category", category);
        List<Product> products = query.getResultList();

        return products;
    }

    public List<Product> getByPriceRange(Double lower, Double upper){
        TypedQuery<Product> query = session.createQuery("FROM Product WHERE price between :lower and :upper");
        query.setParameter("lower", lower);
        query.setParameter("upper", upper);
        List<Product> products = query.getResultList();

        return products;
    }

    public List<Product> getProductWithSubstring(String substring){
        String newString = "%" + substring + "%";
        TypedQuery<Product> query = session.createQuery("FROM Product WHERE name LIKE :substring", Product.class);
        query.setParameter("substring", newString);
        List<Product> products = query.getResultList();

        return products;
    }

    @Override
    public void start() {
        this.session = storageManager.getSession();
        running = true;
    }

    @Override
    public void stop() {
        running = false;
        session.close();
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
