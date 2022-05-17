package com.revature.p2backend.beans.services;

import com.revature.p2backend.beans.dao.OrderItemDoa;
import com.revature.p2backend.entities.OrderItem;
import com.revature.p2backend.entities.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemDoa orderItemDoa;

    @Autowired
    public OrderItemService(OrderItemDoa orderItemDoa){
        this.orderItemDoa = orderItemDoa;
    }

    //public OrderItem save(OrderItem orderItem) {
    public  OrderItem createOrderItem(OrderItem orderItem){
        return orderItemDoa.save(orderItem);
    }

    //public List<OrderItem> getAll() {
    public List<OrderItem> getAllOrderItems(){

        return orderItemDoa.getAll();
    }


    //public OrderItem getById(Integer id) {
    public OrderItem getOrderItemById(Integer id){
        return orderItemDoa.getById(id);
    }


    //public void delete(OrderItem orderItem) {
    public void deleteOrderItem(OrderItem orderItem){

        orderItemDoa.delete(orderItem);
    }


    //public OrderItem update(OrderItem orderItem) {
    public OrderItem updateOrderItem(OrderItem orderItem){

        return orderItemDoa.update(orderItem);
    }


    //public List<OrderItem> getOrderItemsByOrder (Orders o){
    public List<OrderItem> getOrderItemsByOrder(Orders order){
        return orderItemDoa.getOrderItemsByOrder(order);
    }

}
