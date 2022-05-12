package com.revature.p2backend;



import com.revature.p2backend.beans.dao.*;

import com.revature.p2backend.entities.*;
import com.revature.p2backend.beans.services.StorageManager;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "com.revature.p2backend.beans")
public class P2BackendApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(P2BackendApplication.class, args);

        StorageManager storageManager = context.getBean(StorageManager.class);
        storageManager.addEntity(User.class);
        storageManager.addEntity(Address.class);
        storageManager.addEntity(OrderItem.class);
        storageManager.addEntity(Orders.class);
        storageManager.addEntity(Product.class);

        context.start();

        Session session = storageManager.getSession();

        UserDao userDao = context.getBean(UserDao.class);
        AddressDao addressDao = context.getBean(AddressDao.class);
        ProductDao productDao = context.getBean(ProductDao.class);
        OrderItemDao orderItemDao = context.getBean(OrderItemDao.class);
        OrdersDao ordersDao = context.getBean(OrdersDao.class);

        Product product = new Product("watch", "watch", 500.25, 100,Category.BRACELETS);
        Product product1 = new Product("earring", "earring", 125.39, 50,Category.EARRINGS);
        Product product2 = new Product("necklace", "necklace", 199.99, 60, Category.NECKLACES);

        Cart cart = new Cart();
        cart.addProduct(product);
        cart.addProduct(product1);
        cart.addProduct(product2);

        Double cartTotal = 0.0;

        User fatemeh = new User("Fatemeh","Goudarzi","FatemehGoudarzi","FGoudarzi@gmail.com","123","123456789");

        userDao.save(fatemeh);
        Address a = new Address("1", "test", "test", "test", "test");
        addressDao.save(a);

        Orders orders = new Orders(String.valueOf(LocalDate.now()),a, fatemeh);
        for(Product p : cart.getCart()){
            OrderItem orderItem = new OrderItem(1, p, orders);
            orderItem.setItemTotalAmount(p.getPrice() * orderItem.getQuantity());
            orderItemDao.save(orderItem);
            System.out.println(orderItem);
            cartTotal += orderItem.getItemTotalAmount();
        }
        orders.setOrderTotal(cartTotal);
        ordersDao.save(orders);




        User f= new User("Anthony","Pilletti","APilletti","APilletti@gmail.com","123","987654321");

        System.out.println(fatemeh);
        System.out.println(f);
        userDao.save(f);
        System.out.println(f);

//		User u = new User();
//		u.setUsername("anthony01");
//		uDao.save(u);
//
//		User user = uDao.getUserByUserName("anthony01");
//		System.out.println(user.getUsername());
    }

}
