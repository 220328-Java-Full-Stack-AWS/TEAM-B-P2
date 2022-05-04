package com.revature.p2backend;

import com.revature.p2backend.Config;
import com.revature.p2backend.dao.UserDaoImp;
import com.revature.p2backend.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class P2BackendApplication {

	static UserDaoImp uDao = new UserDaoImp();
	public static void main(String[] args) {
		SpringApplication.run(P2BackendApplication.class, args);



		uDao.register("anthony01", "password", "anthony", "pilletti", "email", "credit", "phone");

	}

}
