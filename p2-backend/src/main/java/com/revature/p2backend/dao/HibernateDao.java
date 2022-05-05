package com.revature.p2backend.dao;

import com.revature.p2backend.models.User;

import java.util.List;

public interface HibernateDao<T> {


    public void save(T t);

    public List<T> getAll();
}
