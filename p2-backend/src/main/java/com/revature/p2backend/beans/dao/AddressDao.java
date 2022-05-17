package com.revature.p2backend.beans.dao;

import com.revature.p2backend.beans.utilities.StorageManager;
import com.revature.p2backend.entities.Address;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

//To make this class a bean
@Repository
public class AddressDao implements HibernateDao<Address> {

    //we need this to get the session object to
    //make a connection with database
    //then with that session we can start a transaction and
    //and then make a connection with adress objects and database
    //to make those objects persistent
    private final StorageManager storageManager;
    private boolean running = false;
    private Session session;



    @Autowired
    public  AddressDao(StorageManager storageManager){

        this.storageManager = storageManager;
    }

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
        TypedQuery<Address> query = session.createQuery(hql);
        return query.getResultList();
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
