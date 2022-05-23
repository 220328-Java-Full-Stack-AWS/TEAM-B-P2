package com.revature.p2backend.beans.services;

import com.revature.p2backend.Dto.AuthDto;
import com.revature.p2backend.beans.dao.UserDao;
import com.revature.p2backend.entities.User;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @MockBean
    UserDao userDao;

    @Test
    public void getUserByGoodUsername(@Autowired UserService userService){
        String username = "username";
        User user = new User("first", "last", "username", "email", "password", "phone");
        when(userDao.getUserByUserName(username)).thenReturn(user);


        User returned = userService.getUserByUsername(username);

        Assertions.assertEquals(user, returned);
    }

    @Test
    public void authenticateUserSuccess(@Autowired UserService userService) throws Exception {
        AuthDto authDto = new AuthDto("username", "password");
        User user = new User("first", "last", "username", "email", "password", "phone");
        when(userDao.getUserByUserName(authDto.getUsername())).thenReturn(user);

        User returned = userService.authenticateUser(authDto);

        Assertions.assertEquals(user, returned);
    }

    @Test
    public void wrongUsernameTest(@Autowired UserService userService) throws Exception {
        try{
            AuthDto authDto = new AuthDto("username", "password");
            User user = new User("first", "last", "user", "email", "password", "phone");
            when(userDao.getUserByUserName(any())).thenReturn(user);

            userService.authenticateUser(authDto);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void wrongPasswordTest(@Autowired UserService userService) throws Exception {
        try{
            AuthDto authDto = new AuthDto("username", "pass");
            User user = new User("first", "last", "username", "email", "password", "phone");
            when(userDao.getUserByUserName(any())).thenReturn(user);

            userService.authenticateUser(authDto);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void successfulUserCreation(@Autowired UserService userService){
        User user = new User("first", "last", "username", "email", "password", "phone");
        user.setId(1);
        when(userDao.isUsernameUnique("username")).thenReturn(true);
        when(userDao.isUserEmailUnique("email")).thenReturn(true);

        Integer check = userService.save(user);

        Assertions.assertEquals(0, check);
    }

    @Test
    public void usernameAlreadyExists(@Autowired UserService userService){
        User user = new User("first", "last", "username", "email", "password", "phone");
        when(userDao.isUsernameUnique("username")).thenReturn(false);
        when(userDao.isUserEmailUnique("email")).thenReturn(true);


        Integer check = userService.save(user);

        Assertions.assertEquals(1, check);
    }

    @Test
    public void emailAlreadyExists(@Autowired UserService userService){
        User user = new User("first", "last", "username", "email", "password", "phone");
        when(userDao.isUsernameUnique("username")).thenReturn(true);
        when(userDao.isUserEmailUnique("email")).thenReturn(false);


        Integer check = userService.save(user);

        Assertions.assertEquals(2, check);
    }

    @Test
    public void updateSuccessful(@Autowired UserService userService){
        User user = new User("first", "last", "username", "email", "password", "phone");
        when(userDao.update(user)).thenReturn(user);
        user.setEmail("gmail");
        user.setUserName("anthonyuser");

        User returned = userService.update(user);

        Assertions.assertEquals(user, returned);
    }
}
