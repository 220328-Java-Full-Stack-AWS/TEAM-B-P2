package com.revature.p2backend.beans.controllers;

import com.revature.p2backend.beans.services.Userservice;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Userservice userservice;


    @Autowired
    public UserController(Userservice userservice){

        this.userservice = userservice;
    }


@GetMapping
@ResponseStatus(HttpStatus.OK)
public List<User> getAllUsers(){
        return userservice.getAll();

}


@PostMapping()
@ResponseStatus(HttpStatus.OK)
public User createUser(@RequestBody @Valid User user){

        return userservice.save(user);
}

@PutMapping
@ResponseStatus(HttpStatus.OK)
public User updateUser(@RequestBody User user){
        return userservice.update(user);
    }


    @PostMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String testForCompleteUser(@RequestBody @Valid User user){

        return "success";
    }




    //http://localhost:8080/users/1
    //or http://localhost:8080/users/kplummer
    //http://localhost:8080/users/@PathVariable
    //in Headers @RequestHeader :  mode    usename
    //or in Headres @RequestHeader : mode      id
    //{} in GetMapping means that the value of username or password
    //should come after /users
    @GetMapping("/{usernameOrId}")
    @ResponseStatus(HttpStatus.OK)
    public User readUserByUsernameOrId(@PathVariable String usernameOrId,
                                       @RequestHeader("mode") String mode)
                                       throws Exception{

    switch (mode){
        case "username":
            return userservice.getByUsername(usernameOrId);
        case "id":
            return userservice.getById(Integer.parseInt(usernameOrId));
        default:
            throw new Exception("That is not a valid mode");
    }


    }

}
