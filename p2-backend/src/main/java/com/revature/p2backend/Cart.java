package com.revature.p2backend;

import com.revature.p2backend.entities.Product;

import java.util.LinkedList;
import java.util.List;

public class Cart {

    List<Product> cart = new LinkedList<>();

    public void addProduct(Product product){
        cart.add(product);
    }

    public void removeProduct(Product product){
        cart.remove(product);
    }


    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }
}
