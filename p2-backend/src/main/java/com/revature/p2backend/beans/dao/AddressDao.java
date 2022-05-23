package com.revature.p2backend.beans.dao;

import com.revature.p2backend.beans.utilities.StorageManager;
import com.revature.p2backend.entities.Address;
import com.revature.p2backend.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This Dao is for CRUD functionality on addresses. The
 * CRUD is implemented from the HibernateDao interface for
 * basic functionality. The Hibernate Dao implements
 * the lifecycle phases and forces methods Start, stop
 * and isRunning.
 */
@Repository
public class AddressDao implements HibernateDao<Address>{
    /**
     * This is the StorageManager we will use and call throughout this class. It will
     * give us access to all the StorageManager methods with in the StorageManager class.
     * The StorageManager is also marked as a "service" bean. We have not Autowired this class
     * declaration, as it is not a good practice.
     */
    private final StorageManager storageManager;
    private boolean running = false; //Used to tell if the bean is running

    private Session session; //session that becomes usable upon start, see below

    /**
     * This is a constructor and is better practice to Autowire here. This initializes the
     * StorageManager without having to initialize it to null or as a new object which would make it tightly coupled.
     * @param storageManager
     */
    @Autowired
    public AddressDao(StorageManager storageManager){
        this.storageManager = storageManager;
    }//make connection to the table address

    @Override
    public Address save(Address address) {
        Transaction tx = session.beginTransaction();
        session.save(address);
        tx.commit();
        return address;
    }

    @Override
    public List<Address> getAll() {
        String hql = "FROM Address";
        TypedQuery<Address> query = session.createQuery(hql, Address.class);
        List<Address> address = query.getResultList();
        return address;
    }

    @Override
    public Address getById(Integer id) {
        String hql = "FROM Address WHERE id = :id";
        TypedQuery<Address> query = session.createQuery(hql);
        query.setParameter("id", id);
        Address address = query.getSingleResult();
        session.persist(address);
        return address;
    }

    public List<Address> getAddressByUser(User user){

        TypedQuery<Address> query = session.createQuery("FROM Address WHERE userId = :user", Address.class);
        query.setParameter("user", user);
        List<Address> addresses = query.getResultList();
        //session.persist(addresses);
        return addresses;
    }

    @Override
    public Address update(Address address) {
        Transaction tx = session.beginTransaction();
        session.merge(address);
        tx.commit();
        return address;
    }

    @Override
    public void delete(Address address) {
        Transaction tx = session.beginTransaction();
        String hql = "DELETE FROM Address WHERE id = :id";
        TypedQuery<Address> query = session.createQuery(hql);
        query.setParameter("id", address.getAddressId());
        query.executeUpdate();
        tx.commit();
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
