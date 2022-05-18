package com.revature.p2backend.beans.services;


import com.revature.p2backend.Cart;
import com.revature.p2backend.beans.dao.OrdersDao;
import com.revature.p2backend.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {
    //TODO convert this into a service and add appropriate methods
    /*
    examples of methods: create, view
    Keep in mind, Orders does have a relationships.
    Please feel free to create OrderItemDao methods if needed.
    Also, we should think if we just want a new order created
    everytime a user logs in.
     */
    private final OrdersDao ordersDao;
    private final UserService userService;
    private final AddressService addressService;
    private final ProductService productService;


    @Autowired
    public OrdersService(OrdersDao ordersDao , UserService userService, AddressService addressService, ProductService productService){
        this.ordersDao = ordersDao;
        this.userService = userService;
        this.addressService = addressService;
        this.productService = productService;
    }

    public void createOrder(Orders orders){
        ordersDao.save(orders);
    }

    public void createNewOrder(Cart cart){
        User user = userService.getUserByUserId(cart.getUserId());
        Address address = addressService.getAddressById(cart.getAddressId());
        Orders orders = new Orders();
        List<OrderItem> orderItems = buildOrders(cart, orders);
        orders.setOrderItems(orderItems);
        orders.setCreationDate(cart.getCreationDate());
        orders.setOrderTotal(cart.getOrderTotal());
        orders.setUser(user);
        orders.setAddress(address);
        ordersDao.save(orders);
    }

    private List<OrderItem>  buildOrders(Cart cart, Orders orders) {
        List<OrderItem> orderItems = new ArrayList<>();
        cart.getCartItems().forEach(cartItem -> {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(orders);
            Product product = productService.getByProductId(cartItem.getProductId());
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setItemTotalAmount(cartItem.getItemTotalAmount());
            orderItems.add(orderItem);

        });
        return orderItems;
    }

    public List<Orders> getAllOrders(){
        return ordersDao.getAll();
    }

    public Orders getOrdersById(Integer id){
        return ordersDao.getById(id);
    }

    public void updateOrder(Orders orders){
        ordersDao.update(orders);
    }

    public void deleteOrder(Orders orders){
        ordersDao.delete(orders);
    }

    public List<Orders> getOrdersByUser(User user){
        return ordersDao.getOrdersByUser(user);
    }
}
