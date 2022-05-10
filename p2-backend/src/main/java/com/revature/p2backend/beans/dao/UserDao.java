package com.revature.p2backend.beans.dao;

import com.revature.p2backend.entities.User;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao implements HibernateDao<User> {

    private Session session;
    String tableName;

    public UserDao(Session session) {
        this.session = session;
        this.tableName = "users";
    }//make connection to the table users

    @Override
    public void save(User u) {
        Transaction tx = session.beginTransaction();
        session.save(u);
        tx.commit();
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> query = session.createQuery("FROM User", User.class);
        List<User> users = query.getResultList();

        return users;
    }

    @Override
    public User getById(Integer id) {
        TypedQuery<User> query = session.createQuery("FROM User where id = :user_id");
        query.setParameter("user_id", id);
        User user = query.getSingleResult();
        return user;
    }

    @Override
    public void delete(User user) {

        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("DELETE User where id = :user_id");
        query.setParameter("user_id", user.getId());
        query.executeUpdate();
        tx.commit();
    }

    @Override
    public void update(User user) {

        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET " +
                "userName = :username, password = :password, firstName = :first_name, lastName = :last_name, email = :email, creditCard = :credit_card, phoneNumber = :phone " +
                "where id = :user_id");
        query.setParameter("username", user.getUserName());
        query.setParameter("password", user.getPassword());
        query.setParameter("first_name", user.getFirstName());
        query.setParameter("last_name", user.getLastName());
        query.setParameter("email", user.getEmail());
        query.setParameter("credit_card", user.getCreditCard());
        query.setParameter("phone", user.getPhoneNumber());
        query.setParameter("user_id", user.getId());
        query.executeUpdate();
        tx.commit();
    }

    public User getUserByUserName(String username) {

        TypedQuery<User> query = session.createQuery("FROM User WHERE userName = :username ", User.class);
        query.setParameter("username", username);
        User user = query.getSingleResult();

        return user;
    }


}
