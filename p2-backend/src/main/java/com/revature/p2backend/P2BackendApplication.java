package com.revature.p2backend;


import com.revature.p2backend.dao.OrderItemDao;
import com.revature.p2backend.dao.OrdersDao;
import com.revature.p2backend.dao.UserDao;
import com.revature.p2backend.entities.*;
import com.revature.p2backend.utilities.StorageManager;
import com.revature.p2backend.utilities.TransactionManager;

import org.hibernate.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class P2BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(P2BackendApplication.class, args);

        StorageManager storageManager = new StorageManager();
        storageManager.addAnnotatedClass(User.class);
        storageManager.addAnnotatedClass(Address.class);
        storageManager.addAnnotatedClass(OrderItem.class);
        storageManager.addAnnotatedClass(Orders.class);
        storageManager.addAnnotatedClass(Product.class);

        Product product = new Product("watch", 500.25, Category.BRACELETS);
        Product product1 = new Product("earring", 125.39, Category.EARRINGS);
        Product product2 = new Product("necklace", 199.99, Category.NECKLACES);

        Cart cart = new Cart();
        cart.addProduct(product);
        cart.addProduct(product1);
        cart.addProduct(product2);

        Double cartTotal = 0.0;

        Session session = storageManager.initializeDataSource();
        User fatemeh = new User("Fatemeh","Goudarzi","FatemehGoudarzi","FGoudarzi@gmail.com","123","123456789");
        UserDao userDao = new UserDao(session);
        userDao.save(fatemeh);
        OrderItemDao orderItemDao = new OrderItemDao(session);
        Orders orders = new Orders(String.valueOf(LocalDate.now()), "1234 Main st.", fatemeh);
        for(Product p : cart.getCart()){
            OrderItem orderItem = new OrderItem(1, p, orders);
            orderItem.setItemTotalAmount(p.getPrice() * orderItem.getQuantity());
            orderItemDao.save(orderItem);
            System.out.println(orderItem);
            cartTotal += orderItem.getItemTotalAmount();
        }
        orders.setOrderTotal(cartTotal);
        OrdersDao ordersDao = new OrdersDao(session);
        ordersDao.save(orders);



    }

}
