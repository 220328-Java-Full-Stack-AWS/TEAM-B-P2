package com.revature.p2backend;



import com.revature.p2backend.beans.dao.AddressDao;
import com.revature.p2backend.beans.dao.OrderItemDao;
import com.revature.p2backend.beans.dao.OrdersDao;
import com.revature.p2backend.beans.dao.UserDao;

import com.revature.p2backend.entities.*;
import com.revature.p2backend.utilities.StorageManager;
import org.hibernate.Session;
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

        Product product = new Product("watch", "watch", 500.25, 100,Category.BRACELETS);
        Product product1 = new Product("earring", "earring", 125.39, 50,Category.EARRINGS);
        Product product2 = new Product("necklace", "necklace", 199.99, 60, Category.NECKLACES);

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

        AddressDao addressDao = new AddressDao(session);
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
        OrdersDao ordersDao = new OrdersDao(session);
        ordersDao.save(orders);




        User f= new User("Fatemeh","Goudarzi","FatemehGoudarzi","FGoudarzi@gmail.com","123","123456789");

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
