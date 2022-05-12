package com.revature.p2backend.beans.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a simple ping controller.
 *
 */
@RestController
@RequestMapping("/ping")
public class PingController {
    /**
     * This method returns and ok status when it is successfully hit from the front end.
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String ping() {
        return "pong!";
    }
}
