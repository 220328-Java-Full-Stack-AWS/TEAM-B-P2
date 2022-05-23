package com.revature.p2backend.beans.controllers;

import com.revature.p2backend.Dto.UserDto;
import com.revature.p2backend.beans.services.UserService;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller is for the user and will send certain response status's back
 * based on what is received from the front end. It is used for viewing the current
 * user information (a get), to register a new user (post) and to update a user (put)
 * The update does not update the email or username.
 */
@RestController
@RequestMapping("/users")
public class UserController {
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
    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * This method receives a request from the body (get) as the user id and then sends back
     * the user information.
     * @paramuser
     * @return
     */
    //TODO possibly figure out a way to not send the password back
    @GetMapping("/currentuser")
    @ResponseStatus(HttpStatus.OK)
    public User viewUser(@RequestHeader("username") String username){
        System.out.println("You are now viewing the user");
        return userService.getUserByUsername(username);
    }


    /**
     * This is the create user method. It receives all the parts for a user
     * object, sends it to the service layer. The service layer responds back
     * with an integer. These checks are for uniqueness on username and email.
     * The UserService layer invokes the UserDao layer.
     * @param user
     * @return
     */
    @PostMapping("/register")
    ResponseEntity<User> createUser(@RequestBody User user){

        Integer result = userService.save(user);
        switch(result){
            case 0:
                System.out.println("Successfully created new user");
                return new ResponseEntity<>(HttpStatus.OK);
            case 1:
                System.out.println("Username is not unique");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            case 2:
                System.out.println("User email is not unique");;
            default:
                System.out.println("unable to create user");
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * The update method takes in a UserDto which is only partial information
     * as we do not want to allow for username and email updates. The UserDto gets
     * migrated to a User object in the UserService class and then sent back to the
     * UserDao to be updated.
     * @param user
     * @return
     */
    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        System.out.println("successfully updated user");
        return userService.update(user);
    }
}
