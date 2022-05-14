package com.revature.p2backend.beans.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


// so for making this class a bean we put the @Service
//on the above of the class and also put the class in service package
//because in main springboot configure classes only in bean package
@Service
public class StorageManager implements Lifecycle {

    //This sis for Hibernate
  //Configuration config = new Configuration();
    //config.addAnnotatedClass(User.class)
    //SessionFactory sessionFactory = config.buildSessionFactory();
    //Session session = sessionFactory.openSession();

private boolean running = false;
private final List<Class> entities;
private final Configuration config;
private SessionFactory sessionFactory;
private Session session;

//we do not have @Autowired here because in this class
    //we do not have any dependency injection
    //This is the constructor
public StorageManager(){

    config = new Configuration();
    sessionFactory = config.buildSessionFactory();
    entities = new LinkedList<>();
}

//Because we are implementing the Lifecyle
    //because this class ia a bean then we need to
    //implement the override methods of Lifecycle class


    //context will call this start method and all
    //other starts in other bean classes in main
    @Override
    public void start() {

    for(Class entity : entities){
        config.addAnnotatedClass(entity);
    }
       sessionFactory = config.buildSessionFactory();
       session = sessionFactory.openSession();
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

    //This method is used in main
    public void addEntity(Class entity) {

    entities.add(entity);
    }

    //The remaining methods are only getters and setters
    public void setRunning(boolean running) {
        this.running = running;
    }

    public List<Class> getEntities() {
        return entities;
    }

    public Configuration getConfig() {
        return config;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }





}
