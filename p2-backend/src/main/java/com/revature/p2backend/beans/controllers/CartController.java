package com.revature.p2backend.beans.controllers;

import com.revature.p2backend.Dto.CartDto;
import com.revature.p2backend.beans.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void checkout(){
        System.out.println("Made it to checkout");
        //cartService.checkout(cartDto)
    }


}
