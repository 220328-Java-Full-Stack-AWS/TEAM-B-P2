package com.revature.p2backend.beans.dao;


import com.revature.p2backend.entities.OrderItem;
import com.revature.p2backend.entities.Orders;
import com.revature.p2backend.beans.services.StorageManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderItemDao implements HibernateDao<OrderItem> {

    private final StorageManager storageManager;
    private Session session;
    private boolean running = false;
    private String tableName;

    @Autowired
    public OrderItemDao(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    public void save(OrderItem orderItem) {
        Transaction tx = session.beginTransaction();
        session.save(orderItem);
        tx.commit();
    }

    @Override
    public List<OrderItem> getAll() {
        TypedQuery<OrderItem> orderItems = session.createQuery("FROM OrderItem", OrderItem.class);
        List<OrderItem> o = orderItems.getResultList();
        return o;
    }

    @Override
    public OrderItem getById(Integer id) {
        TypedQuery<OrderItem> orderItems = session.createQuery("FROM OrderItem WHERE id = :id" , OrderItem.class);
        OrderItem o = orderItems.getSingleResult();
        return o;
    }

    @Override
    public void delete(OrderItem orderItem) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("DELETE OrderItem where id = :id");
        query.executeUpdate();
        tx.commit();
    }

    @Override
    public void update(OrderItem orderItem) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("UPDATE OrderItem SET " +
                "orders = :order, productId = :product, quantity = :quantity, itemTotalAmount = :total " +
                "where OrderItem = :id");
        query.setParameter("order", orderItem.getOrders());
        query.setParameter("product", orderItem.getProductId());
        query.setParameter("quantity", orderItem.getQuantity());
        query.setParameter("total", (orderItem.getProductId().getPrice()) * orderItem.getQuantity());
        query.setParameter("id", orderItem.getOrderItem());
        query.executeUpdate();
        tx.commit();
    }

    public List<OrderItem> getOrderItemsByOrder (Orders o){
        TypedQuery<OrderItem> query = session.createQuery("FROM OrderItem WHERE orders =:order", OrderItem.class);
        query.setParameter("order", o);
        List<OrderItem> order = query.getResultList();
        return order;
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

    @Override
    public boolean isRunning() {
        return running;
    }

    public String getTableName() {
        return tableName;
    }

    @Value("order_items")
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
