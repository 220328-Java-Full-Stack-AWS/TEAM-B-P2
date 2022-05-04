package com.revature.p2backend.dao;

import com.revature.p2backend.models.User;

import java.util.List;

public interface UserDAO {
    public User getUserByUserName(String username);

    public void register(String... args);

    public List<User> getAllUsers();
}
