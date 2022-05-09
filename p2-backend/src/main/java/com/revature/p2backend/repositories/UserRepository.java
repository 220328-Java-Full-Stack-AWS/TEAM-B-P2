package com.revature.p2backend.repositories;

import com.revature.p2backend.entities.User;
import com.revature.p2backend.exceptions.Myexceptions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

public class UserRepository implements HibernateRepository<User> {

    private Session session;
    String tableName;

    public UserRepository(Session session) {
        this.session = session;
        this.tableName = "users";
    }


    @Override
    public void save(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    public void updateBySession(User user) {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
    }

    public void deleteBySession(User user) {
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
    }


    @Override
    public List<User> getAll() {
        String sql = "select * from p2.users";
        Query query = session.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        List<User> userList = new LinkedList<>();

        for (Object[] result : results) {
            User user = new User();
            user.setId((Integer) result[0]);
            user.setAddress((String) result[1]);
            user.setCity((String) result[2]);
            user.setEmailAddress((String) result[3]);
            user.setFirstName((String) result[4]);
            user.setLastName((String) result[5]);
            user.setPassword((String) result[6]);
            user.setPhoneNumber((String) result[7]);
            user.setState((String) result[8]);
            user.setUserName((String) result[9]);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public User getById(Integer id) {

        String hql = "FROM User WHERE id = :id";
        TypedQuery<User> query = session.createQuery(hql, User.class);

        query.setParameter("id", id);

        User user = query.getSingleResult();

        return user;


    }

    public User getByUsername(String userName) {

        String hql = "FROM User WHERE userName = :userName";
        TypedQuery<User> query = session.createQuery(hql, User.class);
        query.setParameter("userName", userName);
        User user = query.getSingleResult();
        return user;
    }

    @Override
    public void update(User user) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET " +
                "userName = :userName, password = :password, firstName = :firstName, lastName = :lastName, " +
                "emailAddress = :emailAddress, phoneNumber = :phoneNumber, address = :address, city = :city, " +
                "state = :state where id = :id");

        query.setParameter("userName", user.getUserName());
        query.setParameter("password", user.getPassword());
        query.setParameter("firstName", user.getFirstName());
        query.setParameter("lastName", user.getLastName());
        query.setParameter("emailAddress", user.getEmailAddress());
        query.setParameter("phoneNumber", user.getPhoneNumber());
        query.setParameter("address", user.getAddress());
        query.setParameter("city", user.getCity());
        query.setParameter("state", user.getState());
        query.setParameter("id", user.getId());
        query.executeUpdate();
        tx.commit();
    }

    @Override
    public void delete(User user) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("DELETE User WHERE id = :id");
        query.setParameter("id", user.getId());
        query.executeUpdate();
        tx.commit();
    }
}
