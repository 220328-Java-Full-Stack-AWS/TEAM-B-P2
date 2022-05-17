package com.revature.p2backend.beans.services;

import com.revature.p2backend.Dto.CartDto;
import com.revature.p2backend.beans.dao.*;
import com.revature.p2backend.entities.*;
import org.hibernate.criterion.Order;
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

//    public User getUser(Integer id){
//       User user = userDao.getById(id);
//       return user;
//    }
//
//    public Address getAddress(Integer id){
//        Address address = addressDao.getById(id);
//        return address;
//    }

    public void checkout(CartDto cartDto){
        Orders orders = new Orders(String.valueOf(LocalDate.now()), cartDto.getAddress(), cartDto.getUser());
        ordersDao.save(orders);
        for(OrderItem incomingOrderItem : cartDto.getOrderItemList()){
            Product product = productDao.getById(incomingOrderItem.getProductId().getProductId());

            OrderItem orderItem = new OrderItem(incomingOrderItem.getQuantity(), product, orders);
            orderItemDao.save(orderItem);
            orders.getOrderItems().add(incomingOrderItem);
            System.out.println(orderItem);
        }
        ordersDao.update(orders);
    }

}
