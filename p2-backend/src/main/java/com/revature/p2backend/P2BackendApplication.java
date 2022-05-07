package com.revature.p2backend;

import com.revature.p2backend.entities.Order;
import com.revature.p2backend.entities.OrderItem;
import com.revature.p2backend.entities.User;
import com.revature.p2backend.utilities.StorageManager;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class  P2BackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(P2BackendApplication.class, args);

		StorageManager storageManager = new StorageManager();
		storageManager.addAnnotadtedClass(User.class);
        storageManager.addAnnotadtedClass(Order.class);
		storageManager.addAnnotadtedClass(OrderItem.class);
		Session session = storageManager.inintializeDatasource();






	}
}