package com.revature.p2backend.beans.services;

import com.revature.p2backend.Dto.AuthDto;
import com.revature.p2backend.Dto.UserDto;
import com.revature.p2backend.beans.dao.UserDao;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class performs actions/services for the user.
 */
@Service
public class UserService {
    /**
     * This is the UserDao we will use and call throughout this class. It will
     * give us access to all the UserDao methods with in the UserDao class.
     * The UserDao is also marked as a "repository" bean. We have not Autowired this class
     * declaration, as it is not a good practice.
     */
    private final UserDao userDao;

    /**
     * This is a constructor and is better practice to Autowire here. This initializes the
     * UserDao without having to initialize it to null or as a new object which would make it tightly coupled.
     * @param userDao
     */
    @Autowired
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    /**
     * This method will return an Integer to the UserController layer.
     * Based on that number it will return an appropriate response to the front
     * end. It takes in a user to send to the user dao to create a new user.
     * @param user
     * @return
     */
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

    /**
     * This method is to authenticate a user. It takes in an AuthDto,
     * which is just username and password. It finds the user based on
     * username and compares the password to match. If it is wrong, it throws
     * an exception which the AuthController catches and returns a null response.
     * @param authDto
     * @return
     * @throws Exception
     */
    public User authenticateUser(AuthDto authDto) throws Exception {
        User user = userDao.getUserByUserName(authDto.getUsername());
        if(user != null && user.getPassword().equals(authDto.getPassword())){
            return user;
        }
        else{
            throw new Exception("Bad username or password");
        }
    }

    /**
     * This simply returns a user based on user id. The user id comes from the
     * User controller in a request body.
     * @param user
     * @return
     */
    public User getUserByUserId(User user){
        return userDao.getById(user.getId());
    }
    public User getUserByUserId(Integer id){
        return userDao.getById(id);
    }
    /**
     * This method is to update a users information. Since it is only receiving partial
     * information, it takes in a UserDto (this does not include some typical user information)
     * The UserDto gets transferred to a User object to get sent back to the UserDao to make
     * the update.
     * @param userDto
     * @return
     */
    public User update(UserDto userDto){
        User user = userDao.getById(userDto.getId());
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.getUserName();
        user.getEmail();
        return userDao.update(user);
    }

}
