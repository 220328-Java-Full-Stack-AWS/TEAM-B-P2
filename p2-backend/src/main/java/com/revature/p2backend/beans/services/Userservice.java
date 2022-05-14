package com.revature.p2backend.beans.services;


import com.revature.p2backend.beans.dao.UserDao;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Userservice {

private final UserDao userDao;

//this is the constructor and it has @Autowired
    //because it wants to search the dependency which is the userDao
    //bean when want to instantiate the UserService bean

    @Autowired
    public Userservice(UserDao userDao){
    this.userDao = userDao;
}

//public User save(User user) {
    public User createUser (User user){
        userDao.save(user);
        return user;
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
    public List<User> readAll(){
        List list = userDao.getAll();
        return list;
    }

//public User getById(Integer id) {
    public User readById(Integer id){
        User user = userDao.getById(id);
        return user;
    }

//public User getByUsername(String userName) {
    public User readByUsername(String userName){
        User user = userDao.getByUsername(userName);
        return user;
    }

//public User update(User user) {
    public User update(User user){
        User userUpdated = userDao.update(user);
        return userUpdated;
    }

//public User delete(User user) {
    public User delete(User user){
        User userDeleted = userDao.delete(user);
        return userDeleted;
    }








}
