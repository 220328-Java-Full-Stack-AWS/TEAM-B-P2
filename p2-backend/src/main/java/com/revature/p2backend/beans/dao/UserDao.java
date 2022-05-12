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
    /**
     * This is the StorageManager we will use and call throughout this class. It will
     * give us access to all the StorageManager methods with in the StorageManager class.
     * The StorageManager is also marked as a "service" bean. We have not Autowired this class
     * declaration, as it is not a good practice.
     */
    private final StorageManager storageManager;
    private boolean running = false;//Used to tell if the bean is running
    private Session session;//session that becomes usable upon start, see below
    /**
     * This is a constructor and is better practice to Autowire here. This initializes the
     * StorageManager without having to initialize it to null or as a new object which would make it tightly coupled.
     * @param storageManager
     */
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
        session.persist(user);
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
    public User update(User user) {

        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET " +
                "firstName = :first_name, lastName = :last_name, password = :password,  phoneNumber = :phone " +
                "where id = :user_id");
        //query.setParameter("username", getById(user.getId()).getUserName());
        query.setParameter("user_id", user.getId());
        query.setParameter("first_name", user.getFirstName());
        query.setParameter("last_name", user.getLastName());
        //query.setParameter("email", getById(user.getId()).getEmail());
        //query.setParameter("credit_card", user.getCreditCard());
        query.setParameter("password", user.getPassword());
        query.setParameter("phone", user.getPhoneNumber());
        query.executeUpdate();
        tx.commit();
        return user;
    }

    public User getUserByUserName(String username) {
        TypedQuery<User> query = session.createQuery("FROM User WHERE userName = :username ", User.class);
        query.setParameter("username", username);
        User user = query.getSingleResult();
        session.persist(user);
        return user;
    }

    /**
     * This class will be to check if the username is unique and be returning a boolean
     * @param username
     * @return
     */
    public Boolean isUsernameUnique(String username) {

        TypedQuery<User> query = session.createQuery("FROM User WHERE userName = :username", User.class);
        query.setParameter("username", username);
        if(query.getResultList().isEmpty()){
            return true;
        }
        else{
            return false;
        }

    }

    public Boolean isUserEmailUnique(String email){
        TypedQuery<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
        query.setParameter("email", email);
        if(query.getResultList().isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This method will start the component and establish a usable session
     * for the rest of the class.
     */
    @Override
    public void start() {
        this.session = storageManager.getSession();
        running = true;
    }
    /**
     * Will destroy the connection when complete.
     */
    @Override
    public void stop() {
        running = false;
        session.close();
    }
    /**
     * This will return a boolean if this component/bean is running.
     * @return
     */
    @Override
    public boolean isRunning() {
        return running;
    }
}
