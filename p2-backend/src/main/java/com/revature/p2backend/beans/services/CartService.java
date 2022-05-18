package com.revature.p2backend.beans.services;

import com.revature.p2backend.Dto.CartDto;
import com.revature.p2backend.beans.dao.*;
import com.revature.p2backend.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CartService {
    private OrdersDao ordersDao;
    private OrderItemDao orderItemDao;
    private AddressDao addressDao;
    private UserDao userDao;
    private ProductDao productDao;

    @Autowired
    public CartService(OrdersDao ordersDao, OrderItemDao orderItemDao, AddressDao addressDao, UserDao userDao, ProductDao productDao){
        this.ordersDao = ordersDao;
        this. orderItemDao = orderItemDao;
        this.addressDao = addressDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }

    public User getUser(Integer id){
       User user = userDao.getById(id);
       return user;
    }

//    public Address getAddress(Integer id){
//        Address address = addressDao.getById(id);
//        return address;
//    }

    public Product getProduct(Integer id){
        Product product = productDao.getById(id);
        return product;
    }

    public void checkout(CartDto cartDto){
        Double orderTotal = 0.0;
        User user = getUser(cartDto.getUser().getId());
        //Address address = getAddress(cartDto.getAddress().getAddressId());
        Orders orders = new Orders(String.valueOf(LocalDate.now()), cartDto.getAddress(), user);
        ordersDao.save(orders);
        for(OrderItem incomingOrderItem : cartDto.getOrderItemList()){
            Product product = getProduct(incomingOrderItem.getProductId().getProductId());
            OrderItem orderItem = new OrderItem(incomingOrderItem.getQuantity(), product, orders);
            Double itemTotal = incomingOrderItem.getQuantity() * product.getPrice();
            orderItem.setItemTotalAmount(itemTotal);
            product.setInventory(product.getInventory() - orderItem.getQuantity());
            orderTotal += (itemTotal);
            orders.setOrderItems(orderItem);
            orderItemDao.save(orderItem);
            System.out.println(orderItem);
        }
        orders.setOrderTotal(orderTotal);
        ordersDao.update(orders);
    }

}