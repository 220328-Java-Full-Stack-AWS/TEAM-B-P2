package com.revature.p2backend;


import com.revature.p2backend.beans.dao.UserDao;
import com.revature.p2backend.entities.*;
import com.revature.p2backend.utilities.StorageManager;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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


        Session session = storageManager.initializeDataSource();


        //UserDao uDao = new UserDao(session);

        User fatemeh = new User("Fatemeh","Goudarzi","FatemehGoudarzi","FGoudarzi@gmail.com","123","123456789");
        UserDao userDao = new UserDao(session);
        userDao.save(fatemeh);

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
