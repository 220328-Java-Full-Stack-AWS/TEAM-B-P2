package com.revature.p2backend.beans.dao;

import com.revature.p2backend.beans.services.StorageManager;
import com.revature.p2backend.entities.Orders;
import com.revature.p2backend.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

//we want to make this class a bean and that is why
//we put @Repository on the above
@Repository
public class OrdersDao implements HibernateDao<Orders> {

    private final StorageManager storageManager;
    private boolean running = false;
    private Session session;


    @Autowired
    public OrdersDao(StorageManager storageManager){
        this.storageManager = storageManager;
    }





    @Override
    public Orders save(Orders order) {
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
        return order;
    }
    public void updateBySession(Orders order){
        Transaction transaction = session.beginTransaction();
        session.update(order);
        transaction.commit();
    }

    public void deleteBySession(Orders order){
        Transaction transaction = session.beginTransaction();
        session.delete(order);
        transaction.commit();
    }


    @Override
    public List<Orders> getAll() {
        TypedQuery<Orders> query = session.createQuery("FROM Orders", Orders.class);
        List<Orders> orders = query.getResultList();
        return orders;

    }

    @Override
    public Orders getById(Integer id) {
        String hql = "FROM Order WHERE id = :id";
        TypedQuery<Orders> query = session.createQuery(hql, Orders.class);

        query.setParameter("id", id);

        Orders order= query.getSingleResult();

        return order;

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
        Query query = session.createQuery("UPDATE Orders SET " +
                "user = :user, ordetTotal = :order_total " +
                "where id = :id");
        query.setParameter("user", orders.getUser());
        query.setParameter("order_total", orders.getOrdetTotal());
        query.setParameter("id", orders.getId());
        query.executeUpdate();
        tx.commit();
        return orders;
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
    }

    public StorageManager getStorageManager() {
        return storageManager;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
