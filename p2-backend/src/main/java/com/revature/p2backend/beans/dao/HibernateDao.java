package com.revature.p2backend.beans.dao;

import org.springframework.context.Lifecycle;

import java.util.List;


//Here we want to make this interface a bean
//so we extends Lifecycle after this for all
//the classes that implements this interface
//do not need to extends Lifecyle
public interface HibernateDao<T> extends Lifecycle {

public T save(T t);

public List<T> getAll();

public T getById (Integer id);


    public T update(T t);

    public void delete(T t);


}
