package com.revature.p2backend.beans.dao;

import com.revature.p2backend.beans.utilities.StorageManager;
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
    private final StorageManager storageManager;
    private boolean running = false;
    private Session session;
    String tableName;
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
    public void delete(Orders orders) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("DELETE Orders where id = :id");
        query.setParameter("id", orders.getId());
        query.executeUpdate();
    }

    @Override
    public void update(Orders orders) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("UPDATE Orders SET " +
                "user = :user, orderTotal = :order_total " +
                "where id = :id");
        query.setParameter("user", orders.getUser());
        query.setParameter("order_total", orders.getOrderTotal());
        query.setParameter("id", orders.getId());
        query.executeUpdate();
        tx.commit();
    }

    public List<Orders> getOrdersByUser(User user){
        TypedQuery<Orders> query = session.createQuery("FROM Orders where user = :user", Orders.class);
        query.setParameter("user", user);
        List<Orders> orders = query.getResultList();
        return orders;
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
