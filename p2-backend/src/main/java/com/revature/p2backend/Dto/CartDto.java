package com.revature.p2backend.Dto;

import com.revature.p2backend.entities.Address;
import com.revature.p2backend.entities.OrderItem;
import com.revature.p2backend.entities.User;

import java.util.LinkedList;
import java.util.List;

public class CartDto {
    private List<OrderItem> orderItemList;
    private Address shippingAddress;
    private User user;

    public CartDto() {
    }

    public CartDto(Address shippingAddress, User user) {
        this.shippingAddress = shippingAddress;
        this.user = user;
        this.orderItemList = new LinkedList<>();
    }

    public CartDto(List<OrderItem> orderItemList, User user) {
        this.orderItemList = orderItemList;
        this.user = user;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(LinkedList<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
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
                ", address=" + shippingAddress +
                ", user=" + user +
                '}';
    }
}