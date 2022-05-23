package com.revature.p2backend.beans.controllers;


import com.revature.p2backend.beans.services.AddressService;
import com.revature.p2backend.entities.Address;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;


    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/add")
    public Address createAddress(@RequestBody Address address) {
        System.out.println("Creating new address from address controller");
        return addressService.save(address);
    }

    @GetMapping("/currentaddress")
    @ResponseStatus(HttpStatus.OK)
    public Address viewAddress(@RequestBody Address address) {
        System.out.println("Address view function from address controller");
        return addressService.getAddressById(address.getAddressId());
    }

    @PostMapping("/currentaddress")
    @ResponseStatus(HttpStatus.OK)
    public Address viewAddressWithPost(@RequestBody Address address) {
        System.out.println("Address view function from address controller");
        return addressService.getAddressById(address.getAddressId());
    }


    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<Address> getAddressesByUser (@RequestBody User user){
        System.out.println("getting address with user" + user);
        return addressService.getAddressByUser(user);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Address> getAllAddress(@RequestBody Address address) {
        System.out.println("You're seeing all the addresses in the database.");
        return addressService.getAllAddress();
    }

    @PutMapping("/update")
    public Address updateAddress(@RequestBody Address address) {
        System.out.println("updating address from address controller");
        return addressService.update(address);
    }

    @DeleteMapping("/delete")
    public void deleteAddress(@RequestBody Address address) {
        System.out.println("deleting address from address controller");
        addressService.delete(address);

    }
}
