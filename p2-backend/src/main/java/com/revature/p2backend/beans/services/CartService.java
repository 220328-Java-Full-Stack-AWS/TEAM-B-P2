package com.revature.p2backend.beans.services;

import com.revature.p2backend.Dto.CartDto;
import com.revature.p2backend.beans.dao.AddressDao;
import com.revature.p2backend.beans.dao.OrderItemDao;
import com.revature.p2backend.beans.dao.OrdersDao;
import com.revature.p2backend.beans.dao.UserDao;
import com.revature.p2backend.entities.Address;
import com.revature.p2backend.entities.OrderItem;
import com.revature.p2backend.entities.Orders;
import com.revature.p2backend.entities.User;
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

    @Autowired
    public CartService(OrdersDao ordersDao, OrderItemDao orderItemDao, AddressDao addressDao, UserDao userDao){
        this.ordersDao = ordersDao;
        this. orderItemDao = orderItemDao;
        this.addressDao = addressDao;
        this.userDao = userDao;
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
            OrderItem orderItem = new OrderItem(incomingOrderItem.getQuantity(), incomingOrderItem.getProductId(), orders);
            orderItemDao.save(orderItem);
            System.out.println(orderItem);
        }
    }

}
