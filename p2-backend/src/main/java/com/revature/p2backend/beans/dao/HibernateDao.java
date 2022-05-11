package com.revature.p2backend.beans.dao;

import org.springframework.context.Lifecycle;

import java.util.List;

public interface HibernateDao<T> extends Lifecycle {
    public T save(T t);

    public List<T> getAll();

    public T getById(Integer id);

    public void update(T t);

    public void delete(T t);
}
