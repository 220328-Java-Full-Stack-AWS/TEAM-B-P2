package com.revature.p2backend.beans.dao;

import com.revature.p2backend.beans.utilities.StorageManager;
import com.revature.p2backend.entities.User;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class UserDao implements HibernateDao<User> {
    private final StorageManager storageManager;
    private boolean running = false;
    private Session session;

    @Autowired
    public UserDao(StorageManager storageManager) {
        this.storageManager = storageManager;

    }

    @Override
    public User save(User u) {
        Transaction tx = session.beginTransaction();
        session.save(u);
        tx.commit();
        return u;
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
        return query.getSingleResult();
    }

    /**
     * This class will be to check if the username is unique and be returning a boolean
     * @param username
     * @return
     */
    public User isUsernameUnique(String username) {
        try{
            TypedQuery<User> query = session.createQuery("FROM User WHERE userName = :username", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        }
        catch(Exception e){
            System.out.println("Email does not exist");
            return null;
        }
    }

    public User isUserEmailUnique(String email){
        try {
            TypedQuery<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        }
        catch(Exception e){
            System.out.println("Email does not exist");
            return null;
        }
    }

    @Override
    public void start() {
        this.session = storageManager.getSession();
        running = true;
    }

    @Override
    public void stop() {
        running = false;
        session.close();
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
