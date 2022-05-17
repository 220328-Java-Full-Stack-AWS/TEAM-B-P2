package com.revature.p2backend.Dto;

import com.revature.p2backend.entities.Address;
import com.revature.p2backend.entities.OrderItem;
import com.revature.p2backend.entities.User;

import java.util.LinkedList;
import java.util.List;

public class CartDto {
    private LinkedList<OrderItem> orderItemList;
    private Address address;
    private User user;

    public CartDto() {
    }

    public CartDto(LinkedList<OrderItem> orderItemList, Address address, User user) {
        this.orderItemList = orderItemList;
        this.address = address;
        this.user = user;
    }

    public LinkedList<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(LinkedList<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "orderItemList=" + orderItemList +
                ", address=" + address +
                ", user=" + user +
                '}';
    }
}
