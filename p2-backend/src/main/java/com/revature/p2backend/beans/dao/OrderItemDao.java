package com.revature.p2backend.beans.dao;


import com.revature.p2backend.beans.utilities.StorageManager;
import com.revature.p2backend.entities.OrderItem;
import com.revature.p2backend.entities.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class OrderItemDao implements HibernateDao<OrderItem> {
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
    public OrderItemDao(StorageManager storageManager) {
        this.storageManager = storageManager;
    }//make connection ot the table order_item

    @Override
    public OrderItem save(OrderItem orderItem) {
        Transaction tx = session.beginTransaction();
        session.save(orderItem);
        tx.commit();
        return orderItem;
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
    public OrderItem update(OrderItem orderItem) {
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
        return orderItem;
    }

    public List<OrderItem> getOrderItemsByOrder (Orders o){
        TypedQuery<OrderItem> query = session.createQuery("FROM OrderItem WHERE orders =:order", OrderItem.class);
        query.setParameter("order", o);
        List<OrderItem> order = query.getResultList();
        return order;
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
