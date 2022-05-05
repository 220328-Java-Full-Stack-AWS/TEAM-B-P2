package com.revature.p2backend.dao;

import com.revature.p2backend.models.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao implements HibernateDao<User> {

    private Session session;
    String tableName;

    public UserDao(Session session) {
        this.session = session;
        this.tableName = "users";
    }

    @Override
    public void save(User u) {
        Transaction tx = session.beginTransaction();
        session.save(u);
        tx.commit();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    public User getUserByUserName(String username) {

        TypedQuery<User> query = session.createQuery("FROM User WHERE username = :username ", User.class);
        query.setParameter("username", username);
        User user = query.getSingleResult();

        return user;
    }

}
