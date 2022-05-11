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

    public Integer save(User user){
            if(!userDao.isUsernameUnique(user.getUserName())){
                return 1;
            }
            else if (!userDao.isUserEmailUnique(user.getEmail())) {
                return 2;
            }
            else {
                userDao.save(user);
                return 0;
            }

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

    public User getUserByUserId(User user){
        return userDao.getById(user.getId());
    }

}
