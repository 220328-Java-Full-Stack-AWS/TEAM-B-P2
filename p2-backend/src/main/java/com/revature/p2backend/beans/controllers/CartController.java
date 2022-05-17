package com.revature.p2backend.beans.controllers;


import com.revature.p2backend.beans.services.CartService;
import com.revature.p2backend.entities.Cart;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/byCurrentUser")
    @ResponseStatus(HttpStatus.OK)
    public List<Cart> getByUser(@RequestBody User user){
        return cartService.getCartByUser(user);
    }

    @GetMapping("/byCartId")
    @ResponseStatus(HttpStatus.OK)
    public Cart getById(@RequestHeader("id") String id){
        return cartService.getCartById(Integer.parseInt(id));
    }

    @PostMapping("/newCart")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createNewCart(@RequestBody Cart cart){
        cartService.createCart(cart);
    }

    @PutMapping("/updateCart")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCart(@RequestBody Cart cart){
        cartService.updateCart(cart);
    }

}
