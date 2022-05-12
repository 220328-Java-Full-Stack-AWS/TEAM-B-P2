package com.revature.p2backend.beans.controllers;

import com.revature.p2backend.beans.services.UserService;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void register(@RequestBody User user){
        userService.register(user);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/usernameOrId")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@RequestHeader("usernameOrId") String usernameOrId, @RequestHeader("mode") String mode) {
        switch(mode){
            case "username":
                return userService.getUserByUsername(usernameOrId);
            case "id":
                return userService.getUserById(Integer.parseInt(usernameOrId));
            default:
                return null;
        }
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAccount(@RequestBody User user){ userService.deleteAccount(user); }

    @PutMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCredentials(@RequestBody User user) {
        userService.updateCredentials(user);
    }
}
