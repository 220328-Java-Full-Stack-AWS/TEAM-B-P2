package com.revature.p2backend.dao;

import com.revature.p2backend.models.User;
import com.revature.p2backend.Config;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoImp implements UserDAO{

    Config config = new Config();
    SessionFactory sessionFactory = config.connect().buildSessionFactory();
    Session session = sessionFactory.openSession();
    @Override
    public User getUserByUserName(String username) {
        return null;
    }

    @Override
    public void register(String... args) {

        User u = new User(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
        session.save(u);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
