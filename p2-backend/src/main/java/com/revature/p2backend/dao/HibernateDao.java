package com.revature.p2backend.dao;

import java.util.List;

public interface HibernateDao<T> {
    public void save(T t);

    public List<T> getAll();

    public T getById(Integer id);

    public void update(T t);

    public void delete(T t);
}
