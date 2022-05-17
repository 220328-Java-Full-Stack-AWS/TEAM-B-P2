package com.revature.p2backend.beans.dao;

import com.revature.p2backend.beans.utilities.StorageManager;
import com.revature.p2backend.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDao implements HibernateDao<User> {


    private final StorageManager storageManager;
    private boolean running = false;
    private Session session;
    String tableName;

    //we use @Autowired here in the constructor because
    //we are doing injection dependency
    //remember not to it with fields
    @Autowired
    public UserDao(StorageManager storageManager){

        this.storageManager = storageManager;
    }

    @Override
    public User save(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        return user;
    }

    public User updateBySession(User user) {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        return user;
    }

    public void deleteBySession(User user) {
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
    }


    @Override
    public List<User> getAll() {

        TypedQuery<User> query = session.createQuery("FROM User", User.class);
        List<User> users = query.getResultList();

        return users;
    }

    @Override
    public User getById(Integer id) {

        String hql = "FROM User WHERE id = :id";
        TypedQuery<User> query = session.createQuery(hql, User.class);
        query.setParameter("id", id);
        User user = query.getSingleResult();

        return user;
    }

    public User getUserByUserName(String username) {
        TypedQuery<User> query = session.createQuery("FROM User WHERE userName = :username ", User.class);
        query.setParameter("username", username);
        User user = query.getSingleResult();
        session.persist(user);
        return user;
    }


    public User updateByhql(User user) {
        Transaction tx = session.beginTransaction();
        //we do not even look at what the column names are in DBeaver
        //so in these guys : userName = :userName
        //first userName is what we have in calss declaration
        //second userName is the one that we pass to the function
        Query query = session.createQuery("UPDATE User SET " +
                "userName = :userName, password = :password, firstName = :firstName, lastName = :lastName, " +
                "email = :email, creditCard = :creditCard , phoneNumber = :phoneNumber " +
                "where id = :id");
        //in these guys userName is the second userName in the above query
        query.setParameter("userName", user.getUserName());
        query.setParameter("password", user.getPassword());
        query.setParameter("firstName", user.getFirstName());
        query.setParameter("lastName", user.getLastName());
        query.setParameter("email", user.getEmail());
        query.setParameter("creditCard", user.getCreditCard());
        query.setParameter("phoneNumber", user.getPhoneNumber());
        query.setParameter("id", user.getId());
        query.executeUpdate();
        tx.commit();
        return user;
    }

    //ask why they did not use updateBysql
    //and also why they are using merge
    @Override
    public User update(User user) {
        Transaction tx = session.beginTransaction();
        session.merge(user);
        tx.commit();
        return user;
    }

    @Override
    public void delete(User user) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("DELETE User WHERE id = :id");
        query.setParameter("id", user.getId());
        query.executeUpdate();
        tx.commit();

    }

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

    public StorageManager getStorageManager() {
        return storageManager;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getTableName() {
        return tableName;
    }

    @Value("users")
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
