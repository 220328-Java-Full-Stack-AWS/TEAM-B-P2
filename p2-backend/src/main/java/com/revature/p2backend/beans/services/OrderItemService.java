package com.revature.p2backend.beans.services;

import com.revature.p2backend.beans.dao.OrderItemDao;
import com.revature.p2backend.entities.OrderItem;
import com.revature.p2backend.entities.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderItemService {
    //TODO convert this into a service and add appropriate methods
    /*
    examples of methods: create, update, view
    Keep in mind, OrderItem does have a relationship.
    Also, for each product received by the front end, a new
    order item will need to be created. We need to find a
    way to send back the quantity as well.
    Please feel free to create OrderItemDao methods if needed.

     */
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
