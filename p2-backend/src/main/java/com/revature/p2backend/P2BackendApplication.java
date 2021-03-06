package com.revature.p2backend;




import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.p2backend.beans.controllers.AddressController;
import com.revature.p2backend.beans.dao.*;
import com.revature.p2backend.beans.controllers.OrdersController;
import com.revature.p2backend.beans.services.AddressService;
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



    }
}
