package com.revature.p2backend.beans.controllers;

import com.revature.p2backend.beans.services.OrderItemService;
import com.revature.p2backend.entities.OrderItem;
import com.revature.p2backend.entities.Orders;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orderitem")

public class OrderItemController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public OrderItem persistNewOrderItem(@RequestBody OrderItem orderItem){
        return orderItemService.craeteOrderItem(orderItem);
    }



    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<OrderItem> getAllOrderItems(){
        return orderItemService.getAllOrderItems();
    }


    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrderItem(@RequestBody OrderItem orderItem){
        orderItemService.deleteOrderItem(orderItem);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderItem updateOrderItem(@RequestBody OrderItem orderItem){
        return orderItemService.updateOrderItem(orderItem);
    }


    @GetMapping("byId")
    @ResponseStatus(HttpStatus.OK)
    public OrderItem getOrderItemById(@RequestBody Integer id){
        return orderItemService.getOrderItemById(id);
    }

    @GetMapping("byOrder")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderItem> getOrderItemByorder(@RequestBody Orders order){
        return orderItemService.getOrderItemsByOrder(order);
    }

}
