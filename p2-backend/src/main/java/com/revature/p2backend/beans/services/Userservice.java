package com.revature.p2backend.beans.services;


import com.revature.p2backend.Dto.AuthDto;
import com.revature.p2backend.Dto.UserDto;
import com.revature.p2backend.beans.dao.UserDao;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

private final UserDao userDao;

//this is the constructor and it has @Autowired
    //because it wants to search the dependency which is the userDao
    //bean when want to instantiate the UserService bean

    @Autowired
    public UserService(UserDao userDao){
    this.userDao = userDao;
}

//public User save(User user) {
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


//public User updateBySession(User user) {
    public User updateBySession(User user){
        userDao.updateBySession(user);
        return user;
    }

//public void deleteBySession(User user) {
    public void deleteBySession(User user){
        userDao.deleteBySession(user);
    }

//public List<User> getAll() {
    public List<User> getAll(){
        List list = userDao.getAll();
        return list;
    }

//public User getById(Integer id) {
public User getUserByUserId(Integer id){

    return userDao.getById(id);
}

    public User getUserByUserId(User user){

        return userDao.getById(user.getId());
    }

//public User getUserByUserName(String username) {
    public User getByUsername(String userName){
        User user = userDao.getUserByUserName(userName);
        return user;
    }

//public User update(User user) {
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

//public User delete(User user) {
    public void delete(User user){
        userDao.delete(user);

    }








}
