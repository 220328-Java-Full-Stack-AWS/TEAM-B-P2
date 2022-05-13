package com.revature.p2backend.beans.controllers;

import com.revature.p2backend.beans.dao.AddressDao;
import com.revature.p2backend.beans.services.AddressService;
import com.revature.p2backend.entities.Address;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
