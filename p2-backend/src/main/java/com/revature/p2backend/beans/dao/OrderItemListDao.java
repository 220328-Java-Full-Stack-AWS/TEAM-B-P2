package com.revature.p2backend.beans.dao;

import com.revature.p2backend.beans.utilities.StorageManager;
import com.revature.p2backend.entities.OrderItem;
import com.revature.p2backend.entities.OrderItemList;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class OrderItemListDao implements HibernateDao<OrderItemList>{

    private final StorageManager storageManager;
    private boolean running = false;//Used to tell if the bean is running
    private Session session;//session that becomes usable upon start, see below
    private OrderItemList orderItemList;

    public OrderItemListDao(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    public LinkedList<OrderItem> updateOrderItemList(OrderItem orderItem) {
        return orderItemList.addToOrderItemList(orderItem);
    }

    @Override
    public OrderItemList save(OrderItemList orderItemList) {
        return null;
    }

    @Override
    public List<OrderItemList> getAll() {
        return null;
    }

    @Override
    public OrderItemList getById(Integer id) {
        return null;
    }

    @Override
    public OrderItemList update(OrderItemList orderItemList) {
        return null;
    }

    @Override
    public void delete(OrderItemList orderItemList) {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
