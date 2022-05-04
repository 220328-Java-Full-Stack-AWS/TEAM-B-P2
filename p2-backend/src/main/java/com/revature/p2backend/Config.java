package com.revature.p2backend;

import com.revature.p2backend.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Config {
    public static Configuration connect(){
        Configuration config = new Configuration();

        config.addAnnotatedClass(User.class);
        return config;
    }

    public Config() {
    }
}
