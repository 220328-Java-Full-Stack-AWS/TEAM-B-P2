package com.revature.p2backend.beans.services;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StorageManager implements Lifecycle {

    private final List<Class> entities;
    private final Configuration config;
    private SessionFactory sessionFactory;
    private Session session;
    private boolean running = false;

    public StorageManager() {
        config = new Configuration();
        sessionFactory = config.buildSessionFactory();
        entities = new LinkedList<>();
    }

    @Override
    public void start() {
        for (Class entity : entities) {
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void addEntity(Class entity) {
        entities.add(entity);
    }
}
