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
    /**
     * This is the StorageManager we will use and call throughout this class. It will
     * give us access to all the StorageManager methods with in the StorageManager class.
     * The StorageManager is also marked as a "service" bean. We have not Autowired this class
     * declaration, as it is not a good practice.
     */
    private final StorageManager storageManager;
    private boolean running = false;//Used to tell if the bean is running
    private Session session;//session that becomes usable upon start, see below
    /**
     * This is a constructor and is better practice to Autowire here. This initializes the
     * StorageManager without having to initialize it to null or as a new object which would make it tightly coupled.
     * @param storageManager
     */
    @Autowired
    public ProductDao(StorageManager storageManager) {
        this.storageManager = storageManager;

    }//make connection to the table products

    @Override
    public Product save(Product product) {
        Transaction tx = session.beginTransaction();
        session.save(product);
        tx.commit();
        session.persist(product);
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
    public Product update(Product product) {
        session.clear();
        Transaction tx = session.beginTransaction();
        session.update(product);
        tx.commit();
        session.persist(product);
        return product;
    }
//    @Override
//    public Product update(Product product) {
//        Transaction tx = session.beginTransaction();
//        Query query = session.createQuery("UPDATE Product SET " +
//                "name = :name, price = :price, description = :description, inventory = :inventory, category = :category " +
//                "WHERE id = :id");
//        query.setParameter("name", product.getName());
//        query.setParameter("price", product.getPrice());
//        query.setParameter("description", product.getDescription());
//        query.setParameter("inventory", product.getInventory());
//        query.setParameter("category", product.getCategory());
//        query.setParameter("id", product.getProductId());
//        query.executeUpdate();
//        tx.commit();
//        return product;
//    }

    public List<Product> getByProductName(String name){
        TypedQuery<Product> query = session.createQuery("FROM Product WHERE name = :productName", Product.class);
        query.setParameter("productName", name);
        List<Product> products = query.getResultList();
        return products;
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
    /**
     * This method will start the component and establish a usable session
     * for the rest of the class.
     */
    @Override
    public void start() {
        this.session = storageManager.getSession();
        running = true;
    }
    /**
     * Will destroy the connection when complete.
     */
    @Override
    public void stop() {
        running = false;
        session.close();
    }
    /**
     * This will return a boolean if this component/bean is running.
     * @return
     */
    @Override
    public boolean isRunning() {
        return running;
    }
}


