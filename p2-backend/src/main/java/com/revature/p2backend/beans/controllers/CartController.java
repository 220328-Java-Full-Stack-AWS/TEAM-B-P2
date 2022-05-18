package com.revature.p2backend.beans.controllers;

import com.revature.p2backend.Cart;
import com.revature.p2backend.beans.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class CartController {

    private final OrdersService ordersService;

    @Autowired
    public CartController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @CrossOrigin
    @PostMapping("/newOrder")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createNewOrder(@RequestBody Cart cart) {
        ordersService.createNewOrder(cart);
    }

}
