package com.revature.p2backend.beans.dao;

import com.revature.p2backend.beans.utilities.StorageManager;
import com.revature.p2backend.entities.OrderItem;
import com.revature.p2backend.entities.Orders;
import com.revature.p2backend.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class OrdersDao implements HibernateDao<Orders>{

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
    public OrdersDao(StorageManager storageManager) {
        this.storageManager = storageManager;
    }//make connection to the table orders

    @Override
    public Orders save(Orders orders) {
        Transaction tx = session.beginTransaction();
        session.save(orders);
        tx.commit();
        return orders;
    }



    @Override
    public List<Orders> getAll() {
        TypedQuery<Orders> query = session.createQuery("FROM Orders", Orders.class);
        List<Orders> orders = query.getResultList();
        return orders;
    }

    @Override
    public Orders getById(Integer id) {
        TypedQuery<Orders> query = session.createQuery("FROM Orders where id = :id", Orders.class);
        query.setParameter("id", id);
        Orders orders = query.getSingleResult();
        return orders;
    }

    @Override
    public Orders delete(Orders orders) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("DELETE Orders where id = :id");
        query.setParameter("id", orders.getId());
        query.executeUpdate();
        return orders;
    }

    @Override
    public Orders update(Orders orders) {
        Transaction tx = session.beginTransaction();
        session.merge(orders);
        tx.commit();
        return orders;
    }

    public List<Orders> getOrdersByUser(User user){
        TypedQuery<Orders> query = session.createQuery("FROM Orders where user = :user", Orders.class);
        query.setParameter("user", user);
        List<Orders> orders = query.getResultList();
        return orders;
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
