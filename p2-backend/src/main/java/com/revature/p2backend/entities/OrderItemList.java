package com.revature.p2backend.entities;

import java.util.LinkedList;


public class OrderItemList {

    private LinkedList<OrderItem> orderItemList = new LinkedList<>();

    public OrderItemList(LinkedList<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }



    public LinkedList<OrderItem> addToOrderItemList(OrderItem orderItem) {
        for(Integer i = 0; i < orderItemList.size(); i++) {
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }


    public LinkedList<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(LinkedList<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
