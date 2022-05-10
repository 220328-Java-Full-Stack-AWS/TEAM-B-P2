package com.revature.p2backend.dao;

import com.revature.p2backend.entities.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrdersDao implements HibernateDao<Orders>{

    private Session session;

    public OrdersDao(Session session){
        this.session = session;
    }
    @Override
    public void save(Orders orders) {
        Transaction tx = session.beginTransaction();
        session.save(orders);
        tx.commit();
    }

    @Override
    public List<Orders> getAll() {
        return null;
    }

    @Override
    public Orders getById(Integer id) {
        return null;
    }

    @Override
    public void update(Orders orders) {

    }

    @Override
    public void delete(Integer id) {

    }
}
