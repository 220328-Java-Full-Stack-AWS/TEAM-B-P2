package com.revature.p2backend.beans.services;

import com.revature.p2backend.Dto.UserDto;
import com.revature.p2backend.beans.dao.UserDao;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public User save(User user){
        return userDao.save(user);
    }

    public User authenticateUser(UserDto userDto) throws Exception {
        User user = userDao.getUserByUserName(userDto.getUsername());
        if(user != null && user.getPassword().equals(userDto.getPassword())){
            return user;
        }
        else{
            throw new Exception("Bad username or password");
        }
    }

}
