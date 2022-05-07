package com.revature.p2backend.repositories;

import com.revature.p2backend.entities.Order;
import com.revature.p2backend.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class OrderRepository implements HibernateRepository<Order> {

    private Session session;
    String tableName;


    public OrderRepository(Session session) {
        this.session = session;
        this.tableName = "order";
    }


    @Override
    public void save(Order order) {
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
    }
    public void update(Order order){
        Transaction transaction = session.beginTransaction();
        session.update(order);
        transaction.commit();
    }

    public void delete(Order order){
        Transaction transaction = session.beginTransaction();
        session.delete(order);
        transaction.commit();
    }


    @Override
    public List<Order> getAll() {
        String sql = "select * from p2.order";
        Query query = session.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        List<Order> orderList = new LinkedList<>();

        for(Object[] result : results) {
           Order order = new Order();
           order.setId((Integer) result[0]);
           order.setCreatedAt((Date) result[1]);
           order.setTotal((Double) result[2]);
           order.setUser((Integer) result[3]);
           orderList.add(order);
        }

    return orderList;
    }

    @Override
    public Order getById(Integer id) {
        String hql = "FROM Order WHERE id = :id";
        TypedQuery<Order> query = session.createQuery(hql, Order.class);

        query.setParameter("id", id);

        Order order= query.getSingleResult();

        return order;

    }

}
