package com.revature.p2backend;

import com.revature.p2backend.beans.dao.AddressDao;
import com.revature.p2backend.beans.dao.OrdersDao;
import com.revature.p2backend.beans.dao.UserDao;
import com.revature.p2backend.entities.*;
import com.revature.p2backend.beans.utilities.StorageManager;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

		//From here I am testing the User class
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

		//From here I am testing the Address class
		AddressDao addressDao = context.getBean(AddressDao.class);

		Address mohammadAddress1 = new Address("18001", "Richmond", "Tampa", "FL", "33647");
		addressDao.save(mohammadAddress1);

		Address mohammadAddress2 = new Address("18002", "Richmond PL", "Tampa", "FL", "33647");
		addressDao.save(mohammadAddress2);

		Address somayyehAddress = new Address("18001", "Richmond", "Tampa", "FL", "33647");
		addressDao.save(somayyehAddress);

		List<Address> addressList = addressDao.getAll();

		for(int i = 0; i < addressList.size(); i++){
			System.out.println(addressList.get(i));
		}

		Address address = addressDao.getById(1);
		System.out.println(address.getCity());

		somayyehAddress.setStreet("Richmond PL");
		addressDao.update(somayyehAddress);

		mohammadAddress1.setStreet("Richmond PL");
		addressDao.update(mohammadAddress1);


		//Lests connect users with their addresses
		System.out.println("Hello, The address for mohammad is: " + mohammad.getAddresses());

		Set<Address> addressSetmohammd = new HashSet<>();
		addressSetmohammd.add(mohammadAddress1);
		addressSetmohammd.add(mohammadAddress2);
		mohammad.setAddresses(addressSetmohammd);
		System.out.println("Hell, The addresses for mohammad are:" + mohammad.getAddresses());

		//From here I am testing Orders

		OrdersDao ordersDao = context.getBean(OrdersDao.class);
		Orders orderMohammad1 = new Orders("05/15/2022", mohammadAddress1, mohammad);
		ordersDao.save(orderMohammad1);

		Orders orderMohammad2 = new Orders("05/17/2022", mohammadAddress2, mohammad);
		ordersDao.save(orderMohammad2);

		List mohammadOrders = ordersDao.getOrdersByUser(mohammad);
		for(int i=0; i < mohammadOrders.size(); i++){
			System.out.println(mohammadOrders.get(i));
		}


	     Orders order1 = ordersDao.getById(1);
		 System.out.println(order1);

		 List ordersList = ordersDao.getOrdersByUser(mohammad);
          for(int i=0; i<ordersList.size(); i++){
			  System.out.println(ordersList);
		  }

         orderMohammad1.setOrdetTotal(5000.50);
		  ordersDao.update(orderMohammad1);

		  Orders somayyehOrder = new Orders("05/15/2022",somayyehAddress, somayyeh);
		  ordersDao.save(somayyehOrder);

	}



}
