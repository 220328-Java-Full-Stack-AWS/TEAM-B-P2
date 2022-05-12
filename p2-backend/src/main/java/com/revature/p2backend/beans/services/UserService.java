package com.revature.p2backend.beans.services;

import com.revature.p2backend.beans.dao.UserDao;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public void register(User user){
        userDao.save(user);
    }

    public List<User> getAllUsers(){
        return userDao.getAll();
    }

    public User getUserById(Integer id){
        return userDao.getById(id);
    }

    public void deleteAccount(User user){
        userDao.delete(user);
    }

    public void updateCredentials(User user){
        userDao.update(user);
    }

    public User getUserByUsername(String username){
        return userDao.getUserByUserName(username);
    }
}
