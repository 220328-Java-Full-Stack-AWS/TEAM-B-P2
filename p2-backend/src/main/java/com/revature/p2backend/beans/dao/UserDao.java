package com.revature.p2backend.beans.dao;

import com.revature.p2backend.beans.services.StorageManager;
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
    private Session session;
    private boolean running = false;
    private String tableName;

    @Autowired
    public UserDao(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

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
    public void update(User user) {

        Transaction tx = session.beginTransaction();
        session.merge(user);
        tx.commit();
    }

    public User getUserByUserName(String username) {

        TypedQuery<User> query = session.createQuery("FROM User WHERE userName = :username ", User.class);
        query.setParameter("username", username);
        User user = query.getSingleResult();
        session.persist(user);
        return user;
    }

    @Override
    public void start() {
        this.session = storageManager.getSession();
        running = true;
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    public String getTableName() {
        return tableName;
    }

    @Value("users")
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
