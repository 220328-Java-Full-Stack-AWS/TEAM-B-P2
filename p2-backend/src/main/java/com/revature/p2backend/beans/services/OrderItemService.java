package com.revature.p2backend.beans.services;

import com.revature.p2backend.beans.dao.OrderItemDao;
import com.revature.p2backend.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemDao orderItemDao;


    @Autowired
    public OrderItemService(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    public OrderItem craeteOrderItem(OrderItem orderItem){
        return orderItemDao.save(orderItem);
    }



    public List<OrderItem> getAllOrderItems(){
        return orderItemDao.getAll();
    }


    public OrderItem getOrderItemById(Integer id){
        return orderItemDao.getById(id);
    }


    public void deleteOrderItem(OrderItem orderItem){
        orderItemDao.delete(orderItem);
    }


    public OrderItem updateOrderItem(OrderItem orderItem){
        return orderItemDao.update(orderItem);
    }

    public List<OrderItem> getOrderItemsByOrder(Orders order){
        return orderItemDao.getOrderItemsByOrder(order);
    }


}
