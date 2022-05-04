package com.revature.p2backend;

import com.revature.p2backend.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class P2BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(P2BackendApplication.class, args);

		Configuration config = new Configuration();

		//config.configure().addAnnotatedClass(User.class);
		config.addAnnotatedClass(User.class);

		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();

		User u = new User("test","test","test","test","test","test","test");
		session.save(u);
	}

}
