package com.revature.p2backend;

import com.revature.p2backend.beans.dao.UserDao;
import com.revature.p2backend.entities.*;
import com.revature.p2backend.beans.services.StorageManager;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

//To enable auto configuration in Spring Boot
@SpringBootApplication(scanBasePackages = "com.revature.p2backend.beans")
public class  P2BackendApplication {

	public static void main(String[] args) {

	ConfigurableApplicationContext context =  SpringApplication.run(P2BackendApplication.class, args);

		//when we declared the StorageManager class @service, spring boot automatically
		//called the constructor and made a bean object already for us
		//now with context.getBean spring boot injects that bean object for us
		//and we can assign it in StorageManager storageManager
		StorageManager storageManager = context.getBean(StorageManager.class);
        storageManager.addEntity(User.class);
		storageManager.addEntity(Address.class);
		storageManager.addEntity(Category.class);
		storageManager.addEntity(OrderItem.class);
		storageManager.addEntity(Orders.class);
		storageManager.addEntity(Product.class);



		context.start();
		Session session = storageManager.getSession();

		UserDao userDao = context.getBean(UserDao.class);

		User mohammad = new User("mohammad", "foorutan", "mohammadf",
				"mohammadf@usf.edu", "MOHAMMAD", "813654646", "4545");

		userDao.save(mohammad);

		mohammad.setPhoneNumber("000000");
		userDao.update(mohammad);

		User somayyeh = new User("somayyeh", "shamloo", "somayyehsh",
				"somayyeh@gmail.com", "SOMAAYYEH", "564645", "546");

		userDao.save(somayyeh);

		List list = userDao.getAll();
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}

       User user1 = userDao.getById(2);
		System.out.println(user1.getEmail());

		User user2 = userDao.getByUsername("mohammadf");
		System.out.println(user2.getEmail());






	}



}
