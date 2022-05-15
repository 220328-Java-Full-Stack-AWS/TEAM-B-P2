package com.revature.p2backend.beans.services;


import com.revature.p2backend.beans.dao.OrdersDao;
import com.revature.p2backend.entities.Orders;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

private final OrdersDao ordersDao;


    @Autowired
    public OrdersService(OrdersDao ordersDao){

        this.ordersDao = ordersDao;
    }

//public Orders save(Orders order) {
public void createOrder(Orders orders){

        ordersDao.save(orders);
}

    //public List<Orders> getAll() {
    public List<Orders> getAllOrders(){

        return ordersDao.getAll();
    }

    //public Orders getById(Integer id) {
    public Orders getOrdersById(Integer id){

        return ordersDao.getById(id);
    }

    //public Orders update(Orders orders) {
    public void updateOrder(Orders orders){

        ordersDao.update(orders);
    }


    //public void delete(Orders orders) {
    public void deleteOrder(Orders orders){

        ordersDao.delete(orders);
    }

    //public List<Orders> getOrdersByUser(User user){
    public List<Orders> getOrdersByUser(User user){

        return ordersDao.getOrdersByUser(user);
    }


}
