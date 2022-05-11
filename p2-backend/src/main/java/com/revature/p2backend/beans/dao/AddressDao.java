package com.revature.p2backend.beans.dao;

import com.revature.p2backend.beans.utilities.StorageManager;
import com.revature.p2backend.entities.Address;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class AddressDao implements HibernateDao<Address>{
    private final StorageManager storageManager;
    private boolean running = false;

    private Session session;

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
        TypedQuery<Address> query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public Address getById(Integer id) {
        String hql = "FROM Address WHERE id = :id";
        TypedQuery<Address> query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Address update(Address address) {
        Transaction tx = session.beginTransaction();
        String hql = "UPDATE Address SET street = :street," +
                " number = :houseNumber, city = :city," +
                " zipCode = :zipCode WHERE id = :id";

        TypedQuery<Address> query = session.createQuery(hql);
        String street = address.getStreet();
        String houseNumber = address.getNumber();
        String city = address.getCity();
        String zipCode = address.getZipCode();
        query.setParameter("street", street);
        query.setParameter("houseNumber", houseNumber);
        query.setParameter("city", city);
        query.setParameter("zipCode", zipCode);
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
