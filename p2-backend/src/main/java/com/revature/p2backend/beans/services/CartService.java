package com.revature.p2backend.beans.services;

import com.revature.p2backend.beans.dao.CartDao;
import com.revature.p2backend.beans.dao.OrdersDao;
import com.revature.p2backend.entities.Cart;
import com.revature.p2backend.entities.Orders;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    //TODO convert this into a service and add appropriate methods
    /*
    examples of methods: create, view
    Keep in mind, Orders does have a relationships.
    Please feel free to create OrderItemDao methods if needed.
    Also, we should think if we just want a new order created
    everytime a user logs in.
     */
    private final CartDao cartDao;

    @Autowired
    public CartService(CartDao cartDao){
        this.cartDao = cartDao;
    }

    public void createCart(Cart cart){
        cartDao.save(cart);
    }

    public List<Cart> getAllCart(){
        return cartDao.getAll();
    }

    public Cart getCartById(Integer id){
        return cartDao.getById(id);
    }

    public void updateCart(Cart cart){
        cartDao.update(cart);
    }

    public void deleteCart(Cart cart){
        cartDao.delete(cart);
    }

    public List<Cart> getCartByUser(User user){
        return cartDao.getCartByUser(user);
    }
}
