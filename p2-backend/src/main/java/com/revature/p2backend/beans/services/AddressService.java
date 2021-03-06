package com.revature.p2backend.beans.services;


import com.revature.p2backend.beans.dao.AddressDao;
import com.revature.p2backend.entities.Address;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    //Copy of AddressDao, Do not @Autowired
    private final AddressDao addressDao;

    @Autowired
    public AddressService(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public Address save(Address address){
        addressDao.save(address);
        return address;
    }

    public Address getAddressById(Integer id) {
        return addressDao.getById(id);
    }

    public List<Address> getAddressByUser(User user){
        return addressDao.getAddressByUser(user);
    }

    public Address update(Address address) {

        addressDao.update(address);
        return address;
    }

    public Address delete(Address address) {
        return addressDao.delete(address);
    }

    public List<Address> getAllAddress() {return addressDao.getAll();}
}
