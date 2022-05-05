package com.revature.p2backend;

import com.revature.p2backend.dao.UserDao;
import com.revature.p2backend.models.Address;
import com.revature.p2backend.models.User;
import com.revature.p2backend.utilities.StorageManager;
import com.revature.p2backend.utilities.TransactionManager;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class P2BackendApplication {


	public static void main(String[] args) {
		SpringApplication.run(P2BackendApplication.class, args);

		StorageManager connection = new StorageManager();
		connection.addAnnotatedClass(Address.class);
		connection.addAnnotatedClass(User.class);

		Session session = connection.initializeDataSource();

		TransactionManager transactionManager = new TransactionManager(session);

		UserDao uDao = new UserDao(session);

//		Transaction tx = transactionManager.beginTransaction();
//		User u = new User("anthony01", "password", "anthony", "pilletti", "email", "credit", "phone");
//		session.save(u);
//		transactionManager.commitTransaction(tx);

		User u = new User("anthony01", "password", "anthony", "pilletti", "email", "credit", "phone");
		uDao.save(u);

		User user = uDao.getUserByUserName("anthony01");
		System.out.println(user.getFirstName());
	}

}
