package com.revature.p2backend.beans.controllers;

import com.revature.p2backend.beans.dao.AddressDao;
import com.revature.p2backend.beans.services.AddressService;
import com.revature.p2backend.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;


    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/currentaddress")
    @ResponseStatus(HttpStatus.OK)
    public Address viewAddress(@RequestBody Address address) {
        System.out.println("Address view function");
        return addressService.getAddressById(address.getAddressId());
    }

    @PutMapping("/update")
    public Address updateAddress(@RequestBody Address address) {
        System.out.println("updating address.");
        return addressService.update(address);
    }



}
