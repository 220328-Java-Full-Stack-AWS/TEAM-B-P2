package com.revature.p2backend;


import com.revature.p2backend.entities.User;
import com.revature.p2backend.utilities.StorageManager;
import com.revature.p2backend.utilities.TransactionManager;

import org.hibernate.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class P2BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(P2BackendApplication.class, args);

		StorageManager storageManager = new StorageManager();
		storageManager.addAnnotatedClass(User.class);

		Session session = storageManager.initializeDatasource();

		TransactionManager transactionManager = new TransactionManager(session);

		//UserDao uDao = new UserDao(session);

		 Transaction tx = transactionManager.beginTransaction();
		 User fatemeh = new User("Fatemeh","Goudarzi","FatemehGoudarzi","FGoudarzi@gmail.com","123","123456789");
		 session.save(fatemeh);
		 transactionManager.commitTransaction(tx);

//		User u = new User();
//		u.setUsername("anthony01");
//		uDao.save(u);
//
//		User user = uDao.getUserByUserName("anthony01");
//		System.out.println(user.getUsername());
	}

}
