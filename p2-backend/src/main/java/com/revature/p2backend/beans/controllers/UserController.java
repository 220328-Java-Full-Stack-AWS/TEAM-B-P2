package com.revature.p2backend.beans.controllers;

import com.revature.p2backend.Dto.UserDto;
import com.revature.p2backend.beans.services.UserService;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/auth")
    ResponseEntity<User> userLogin(@RequestBody UserDto userDto){
        try{
            userService.authenticateUser(userDto);
            System.out.println("Successful login");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            //e.printStackTrace();
            System.out.println("Username or password incorrect");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

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

}
