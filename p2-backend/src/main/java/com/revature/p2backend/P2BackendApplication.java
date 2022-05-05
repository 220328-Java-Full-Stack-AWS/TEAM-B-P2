package com.revature.p2backend;


import com.revature.p2backend.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class  P2BackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(P2BackendApplication.class, args);

		//not this one, this is for xml file
		//config.configure().buildSessionFactory();
		//this is for hibernate.properties
		//look at the notes for 05/04/2022
		Configuration config = new Configuration();

		//This line he added after creating the entity class
		//(Uesr.class) is class annotated class

		//config.configure().addAnnotatedClass(User.class);//This is for xml config
		config.addAnnotatedClass(User.class);//This is for .properties config

		//this line creates the user table
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();

		//Now that we run the application and it v=created the table for us
		// we can make rows in it

		//Here we are doing what exactly we were doing with jdbc in p1
		User mohammad = new User("mohammadf", "MOHAMMAD", "mohammad", "foroutan");
		//so this mohammad object is in transient state because it is not
		//associated with the session
		User somayyeh = new User("somayyehsh", "SOMAYYEH", "somayyeh", "shamloo");
		session.save(mohammad);
		session.save(somayyeh);
		mohammad.setLastName("foroutanyazdian");
		session.update(mohammad);

		session.flush();


	}
}