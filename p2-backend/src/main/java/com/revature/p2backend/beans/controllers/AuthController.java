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

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    ResponseEntity<User> userLogin(@RequestBody AuthDto authDto){
        try{
            userService.authenticateUser(authDto);
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
