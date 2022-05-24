package com.revature.p2backend.beans.services;

import com.revature.p2backend.beans.dao.OrdersDao;
import com.revature.p2backend.entities.Orders;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    //TODO convert this into a service and add appropriate methods
    /*
    examples of methods: create, view
    Keep in mind, Orders does have a relationships.
    Please feel free to create OrderItemDao methods if needed.
    Also, we should think if we just want a new order created
    everytime a user logs in.
     */
    private final OrdersDao ordersDao;

    @Autowired
    public OrdersService(OrdersDao ordersDao){
        this.ordersDao = ordersDao;
    }

    public void createOrder(Orders orders){

        ordersDao.save(orders);
    }

    public List<Orders> getAllOrders(){
        return ordersDao.getAll();
    }

    public Orders getOrdersById(Integer id){
        return ordersDao.getById(id);
    }

    public void updateOrder(Orders orders){
        ordersDao.update(orders);
    }

    public void deleteOrder(Orders orders){
        ordersDao.delete(orders);
    }

    public List<Orders> getOrdersByUser(User user){
        return ordersDao.getOrdersByUser(user);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
