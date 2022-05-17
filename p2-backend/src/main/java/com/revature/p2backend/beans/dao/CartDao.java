package com.revature.p2backend.beans.dao;

import com.revature.p2backend.beans.utilities.StorageManager;
import com.revature.p2backend.entities.Cart;
import com.revature.p2backend.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CartDao implements HibernateDao<Cart>{
    /**
     * This is the StorageManager we will use and call throughout this class. It will
     * give us access to all the StorageManager methods with in the StorageManager class.
     * The StorageManager is also marked as a "service" bean. We have not Autowired this class
     * declaration, as it is not a good practice.
     */
    private final StorageManager storageManager;
    private boolean running = false;//Used to tell if the bean is running
    private Session session;//session that becomes usable upon start, see below
    private OrdersDao ordersDao;


    /**
     * This is a constructor and is better practice to Autowire here. This initializes the
     * StorageManager without having to initialize it to null or as a new object which would make it tightly coupled.
     * @param storageManager
     */
    @Autowired
    public CartDao(StorageManager storageManager) {
        this.storageManager = storageManager;
    }//make connection to the table cart

    @Override
    public Cart save(Cart cart) {
        Transaction tx = session.beginTransaction();
        session.save(cart);
        tx.commit();
        return cart;
    }

    @Override
    public List<Cart> getAll() {
        TypedQuery<Cart> query = session.createQuery("FROM Cart", Cart.class);
        List<Cart> cart = query.getResultList();
        return cart;
    }

    @Override
    public Cart getById(Integer id) {
        TypedQuery<Cart> query = session.createQuery("FROM Cart where id = :id", Cart.class);
        query.setParameter("id", id);
        Cart cart = query.getSingleResult();
        return cart;
    }

    @Override
    public void delete(Cart cart) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("DELETE Cart where id = :id");
        query.setParameter("id", cart.getId());
        query.executeUpdate();
    }

    @Override
    public Cart update(Cart cart) {
        Transaction tx = session.beginTransaction();
        session.merge(cart);
        tx.commit();
        return cart;
    }

    public List<Cart> getCartByUser(User user){
        TypedQuery<Cart> query = session.createQuery("FROM Cart where user = :user", Cart.class);
        query.setParameter("user", user);
        List<Cart> cart = query.getResultList();
        return cart;
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
