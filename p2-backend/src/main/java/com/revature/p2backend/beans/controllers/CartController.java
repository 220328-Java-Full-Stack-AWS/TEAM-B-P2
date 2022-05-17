package com.revature.p2backend.beans.controllers;

import com.revature.p2backend.Dto.CartDto;
import com.revature.p2backend.beans.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public void checkout(@RequestBody CartDto cartDto){
        System.out.println("Made it to checkout");
        cartService.checkout(cartDto);
    }


}
