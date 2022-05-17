package com.revature.p2backend.beans.controllers;

import com.revature.p2backend.Dto.UserDto;
import com.revature.p2backend.beans.services.UserService;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){

        this.userService = userService;
    }

    /**
     * This method receives a request from the body (get) as the user id and then sends back
     * the user information.
     * @param user
     * @return
     */
    //TODO possibly figure out a way to not send the password back
    @GetMapping("/currentuser")
    @ResponseStatus(HttpStatus.OK)
    public User viewUser(@RequestBody User user){
        System.out.println("You are now viewing the user");
        return userService.getUserByUserId(user);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return userService.getAll();

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
    ResponseEntity<User> createUser(@RequestBody User user) {

        Integer result = userService.save(user);
        switch (result) {
            case 0:
                System.out.println("Successfully created new user");
                return new ResponseEntity<>(HttpStatus.OK);
            case 1:
                System.out.println("Username is not unique");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            case 2:
                System.out.println("User email is not unique");
                ;
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
     * @param userDto
     * @return
     */
    @PutMapping("/update")
    public User updateUser(@RequestBody UserDto userDto){
        System.out.println("successfully updated user");
        return userService.update(userDto);
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
            return userService.getByUsername(usernameOrId);
        case "id":
            return userService.getUserByUserId(Integer.parseInt(usernameOrId));
        default:
            throw new Exception("That is not a valid mode");
    }


    }

}
