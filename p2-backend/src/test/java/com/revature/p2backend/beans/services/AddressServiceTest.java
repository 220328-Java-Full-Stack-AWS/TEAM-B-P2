package com.revature.p2backend.beans.services;


import com.revature.p2backend.beans.dao.AddressDao;
import com.revature.p2backend.entities.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.criteria.CriteriaBuilder;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @MockBean
    AddressDao addressDao;


    //we want to test this
    //public Address save(Address address){
    //        addressDao.save(address);
    //        return address;
    //    }

    @Test
    public void saveTest(@Autowired AddressService sut) throws Exception {

        //arrange:we want to arrange the parameter and return
        //with this we are making the parameter
        Address addressParam = new Address("testNumber", "testStreet", "testCity", "testState", "testZipCode");
        //with this we are making the return for the mock
        Address addressReturn = new Address("testNumber", "testStreet", "testCity", "testState", "testZipCode");
        //with this we say when the mock calls its method return what we want you to return
        //which in this case is addressReturn
        when(addressDao.save(addressParam)).thenReturn(addressReturn);

        //act
        //With this we are calling the function and it is returning returnedAddress
        //we wanted the return to be addressReturn
        Address returnedAddress = sut.save(addressParam);

        //assert: now we assert that what is returned by the function(returnedAddress)
        //is equal to what we wanted the method to return(addressReturn)
        Assertions.assertEquals(addressReturn, returnedAddress);
    }

        //we want to test this:
        //public Address getAddressById(Integer id) {
        //
        //        return addressDao.getById(id);
        //    }

         @Test
         public void getAddressById(@Autowired AddressService sut) throws Exception{
          //arrange:we want to arrange the parameter and return
             // with this we are making the parameter
              Integer id = 1;
              //with this we are making the return
             Address addressReturn = new Address("numberTest", "streetTest", "cityTest", "stateTest", "zipCodeTest");
             //with this we say when the mock object calls its method return what we want you to return
             //which in this case is addressReturn
              when(addressDao.getById(id)).thenReturn(addressReturn);

              //act
             //With this we are calling the function and it is returning returnedAddress
             //we wanted the return to be addressReturn
             Address returnedAddress = sut.getAddressById(id);
             //assert: now we assert that what is returned by the function(returnedAddress)
             //is equal to what we wanted the method to return(addressReturn)
             Assertions.assertEquals(addressReturn, returnedAddress);

    }
             //we want to write test for this:
             //public Address update(Address address) {
    //
    //        addressDao.update(address);
    //        return address;
    //    }

    @Test
    public void updateTest(@Autowired AddressService sut) throws Exception{

        //arrange:we want to arrange the parameter and return
        // with this we are making the parameter
        Address addressParam = new Address("numberTest", "streetTest", "cityTest", "stateTest", "zipTest");
        //with this we are making the return
        //with this we say when the mock object calls its method return what we want you to return
        //which in this case is addressReturn
        Address adrressReturn = new Address("numberTest", "streetTest", "cityTest", "stateTest", "zipTest");
        when(addressDao.update(addressParam)).thenReturn(adrressReturn);
        //act
        //With this we are calling the function and it is returning returnedAddress
        //we wanted the return to be addressReturn
        Address returnedAddress = sut.update(addressParam);
        //assert: now we assert that what is returned by the function(returnedAddress)
        //is equal to what we wanted the method to return(addressReturn)

        Assertions.assertEquals(adrressReturn, returnedAddress );
    }


    //we want to write test for this:
    //public Address delete(Address address) {
    //
    //        addressDao.delete(address);
    //        return address;
    //    }

    @Test
    public void deleteTest(@Autowired AddressService sut)throws Exception{
        //arrange:we want to arrange the parameter and return
        //with this we are making the parameter
        Address addressParam = new Address("testNumber", "testStreet", "testCity", "testState", "testZipCode");
        //with this we are making the return
        Address addressReturn = new Address("testNumber", "testStreet", "testCity", "testState", "testZipCode");
        //with this we say when the other object calls its method return what we want you to return
        //which in this case is addressReturn
        when(addressDao.delete(addressParam)).thenReturn(addressReturn);
        //act
        //With this we are calling the function and it is returning returnedAddress
        //we wanted the return to be addressReturn
        Address returnedAddress = sut.delete(addressParam);
        //assert: now we assert that what is returned by the function(returnedAddress)
        //is equal to what we wanted the method to return(addressReturn)
        Assertions.assertEquals(addressReturn, returnedAddress);

    }

}





