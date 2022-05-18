package com.revature.p2backend.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.p2backend.entities.OrderItem;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties
public class FlatOrderDto {

    private String creationDate;
    private Double orderTotal;
    private Integer addressId;
    private Integer userId;


    private List<FlatOrderItemDto> orderItems;



    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<FlatOrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<FlatOrderItemDto> flatOrderItems) {
        this.orderItems = flatOrderItems;
    }


}
