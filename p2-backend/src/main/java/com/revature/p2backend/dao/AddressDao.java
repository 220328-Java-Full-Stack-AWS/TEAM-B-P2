package com.revature.p2backend.dao;

import com.revature.p2backend.entities.Address;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class AddressDao implements HibernateDao<Address>{
    private Session session;

    public AddressDao(Session session){
        this.session = session;
    }
    @Override
    public void save(Address address) {
        Transaction tx = session.beginTransaction();
        session.save(address);
        tx.commit();
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
    public void update(Address address) {
        Transaction tx = session.beginTransaction();
        String hql = "UPDATE Address SET street = :street," +
                " houseNumber = :houseNumber, city = :city," +
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
    }


    @Override
    public void delete(Integer id) {
        Transaction tx = session.beginTransaction();
        String hql = "DELETE FROM Address WHERE id = :id";
        TypedQuery<Address> query = session.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
        tx.commit();
    }

}
