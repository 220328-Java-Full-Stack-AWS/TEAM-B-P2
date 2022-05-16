package com.revature.p2backend.beans.controllers;

import com.revature.p2backend.beans.services.CartService;
import com.revature.p2backend.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Product addItemToCart(@RequestBody Product product){
        System.out.println("test");
        //cartService.addProduct(product, quantity);
        System.out.println(cartService.getCart());
        return product;
    }

}
