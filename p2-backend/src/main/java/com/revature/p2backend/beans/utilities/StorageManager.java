package com.revature.p2backend.beans.utilities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * This is marked as service to interact with the
 * rest of the beans. It gets us the configuration
 * to add our annotated classes (entities) as tables
 * in the database. It also allows us to get a session
 * when the other beans are made aware of this class.
 */
@Service
public class StorageManager implements Lifecycle {
    private boolean running = false;
    private final List<Class> annotatedEntities;
    private SessionFactory sessionFactory;
    private final Configuration config;
    private Session session;


    public StorageManager() {
        config = new Configuration();
        sessionFactory = config.buildSessionFactory();
        annotatedEntities = new LinkedList<>();
    }

    @Override
    public void start() {
        for(Class entity : annotatedEntities){
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

    public void addAnnotatedClass(Class c){
        annotatedEntities.add(c);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        return session;
    }


}
