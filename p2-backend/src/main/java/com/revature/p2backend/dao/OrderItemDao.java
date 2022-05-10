package com.revature.p2backend.dao;


import com.revature.p2backend.entities.OrderItem;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderItemDao implements HibernateDao<OrderItem> {

    private Session session;

    public OrderItemDao(Session session){
        this.session = session;
    }
    @Override
    public void save(OrderItem orderItem) {
        Transaction tx = session.beginTransaction();
        session.save(orderItem);
        tx.commit();
    }

    @Override
    public List<OrderItem> getAll() {
        return null;
    }

    @Override
    public OrderItem getById(Integer id) {
        return null;
    }

    @Override
    public void update(OrderItem orderItem) {

    }

    @Override
    public void delete(Integer id) {

    }
}
