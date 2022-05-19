package com.revature.p2backend.beans.controllers;

import com.revature.p2backend.Dto.AuthDto;
import com.revature.p2backend.beans.services.UserService;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *This class is specifically for the get request for logging into the site
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * This is the UserService we will use and call throughout this class. It will
     * give us access to all the user service methods with in the UserService class.
     * The UserService is also marked as a "service" bean. We have not Autowired this,
     * as it is not a good practice.
     */
    private final UserService userService;

    /**
     * This is a constructor and is better practice to Autowire here. This initializes the
     * user service without having to initialize it to null or as a new object which would make it tightly coupled.
     * @param userService
     */
    @Autowired
    public AuthController(UserService userService){
        this.userService = userService;
    }

    /**
     * This is a response entity which allows us to return different responses to the front end.
     * In this methode, if the user login is bad, it sends back the response status of UNAUTHORIZED
     * which is response status 401.
     * @param authDto
     * @return
     */
    @GetMapping
    ResponseEntity<User> userLogin(@RequestBody AuthDto authDto){
        try{
            User user = userService.authenticateUser(authDto);
            System.out.println("Successful login");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            //e.printStackTrace();
            System.out.println("Username or password incorrect");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
