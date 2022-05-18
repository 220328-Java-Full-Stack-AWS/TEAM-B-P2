package com.revature.p2backend.beans.controllers;

import com.revature.p2backend.Dto.FlatOrderDto;
import com.revature.p2backend.beans.services.OrdersService;
import com.revature.p2backend.entities.Orders;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService){
        this.ordersService = ordersService;
    }

    @GetMapping("/byCurrentUser")
    @ResponseStatus(HttpStatus.OK)
    public List<Orders> getByUser(@RequestBody User user){
        return ordersService.getOrdersByUser(user);
    }

    @GetMapping("/byOrderId")
    @ResponseStatus(HttpStatus.OK)
    public Orders getById(@RequestHeader("id") String id){
        return ordersService.getOrdersById(Integer.parseInt(id));
    }

    @CrossOrigin
    @PostMapping("/newOrder")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createNewOrder(@RequestBody Orders orders){
        ordersService.createOrder(orders);
    }

    @CrossOrigin
    @PostMapping("/newFlatOrder")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createNewFlatOrder(@RequestBody FlatOrderDto flatOrderDto){
        ordersService.createFlatOrder(flatOrderDto);
    }


    @PutMapping("/updateOrder")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateOrder(@RequestBody Orders orders){
        ordersService.updateOrder(orders);
    }
}
