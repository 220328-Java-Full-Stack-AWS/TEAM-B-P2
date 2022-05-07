package com.revature.p2backend;

import com.revature.p2backend.entities.*;
import com.revature.p2backend.repositories.OrderRepository;
import com.revature.p2backend.repositories.UserRepository;
import com.revature.p2backend.utilities.StorageManager;
import com.revature.p2backend.utilities.TransactionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class  P2BackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(P2BackendApplication.class, args);

		StorageManager storageManager = new StorageManager();
		storageManager.addAnnotadtedClass(User.class);
		storageManager.addAnnotadtedClass(Order.class);
		storageManager.addAnnotadtedClass(OrderItem.class);
		storageManager.addAnnotadtedClass(Product.class);
		storageManager.addAnnotadtedClass(Category.class);
		Session session = storageManager.inintializeDatasource();



		User mohammad = new User("mohammadf", "MOHAMMAD", "mohammad", "foroutan",
				"mforo@hotmail.com", "546646",
				"63546", "Tampa", "FL");

		UserRepository userRepository = new UserRepository(session);

		userRepository.save(mohammad);
		mohammad.setUserName("mohammadfy");
		userRepository.update(mohammad);

		//userRepository.delete(mohammad);
		List<User> usersList = new ArrayList<>();
		usersList = userRepository.getAll();

		for (User u : usersList) {
			System.out.println("Username: " + u.getUserName());
		}

		User u =userRepository.getById(1);
		System.out.println(u.getCity());

		User u1 = userRepository.getByUsername("mohammadfy");
		System.out.println(u1.getEmailAddress());


		OrderRepository orderRepository = new OrderRepository(session);
		Order o1 = new Order(4500, mohammad);

		orderRepository.save(o1);
		o1.setTotal(7000.00);
		orderRepository.update(o1);

		List <Order> orderList = orderRepository.getAll();
		for (Order o : orderList) {
			System.out.println("prices: " + o.getTotal());
		}

		Order o2 = orderRepository.getById(1);
		System.out.println("user_id for that oredre: " + o2.getTotal());
	}

}
