package com.revature.p2backend.dao;

import com.revature.p2backend.models.Address;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AddressDao implements HibernateDao<Address>{

    private Session session;
    String tableName;

    public AddressDao(Session session) {
        this.session = session;
        this.tableName = "addresses";
    }

    @Override
    public void save(Address address) {
        Transaction tx = session.beginTransaction();
        session.save(address);
        tx.commit();
    }

    @Override
    public List<Address> getAll() {
        return null;
    }
}
