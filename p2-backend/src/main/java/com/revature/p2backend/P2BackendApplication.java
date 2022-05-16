package com.revature.p2backend;




import com.revature.p2backend.beans.dao.*;
import com.revature.p2backend.beans.controllers.OrdersController;
import com.revature.p2backend.beans.services.OrdersService;
import com.revature.p2backend.entities.*;
import com.revature.p2backend.beans.utilities.StorageManager;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;

@SpringBootApplication(scanBasePackages = "com.revature.p2backend.beans")
public class P2BackendApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(P2BackendApplication.class, args);

        StorageManager storageManager = context.getBean(StorageManager.class);
        storageManager.addAnnotatedClass(User.class);
        storageManager.addAnnotatedClass(Address.class);
        storageManager.addAnnotatedClass(OrderItem.class);
        storageManager.addAnnotatedClass(Orders.class);
        storageManager.addAnnotatedClass(Product.class);

        context.start();


        User fatemeh = new User("Fatemeh","Goudarzi","FatemehGoudarzi","FGoudarzi@gmail.com","123","123456789");
        UserDao userDao = context.getBean(UserDao.class);
        Address address = new Address("123", "test", "test", "test", "test");
        Address billingAddress= new Address("8111", "billing address rd", "billing town", "billing state", "81111");

        userDao.save(fatemeh);
        AddressDao addressDao = context.getBean(AddressDao.class);
        addressDao.save(address);


        OrdersController ordersController = context.getBean(OrdersController.class);

        Orders orderWithBillingAddy = new Orders(String.valueOf(LocalDate.now()), address, billingAddress, fatemeh);
        ordersController.createNewOrder(orderWithBillingAddy);
        System.out.println(orderWithBillingAddy);



    }
}
