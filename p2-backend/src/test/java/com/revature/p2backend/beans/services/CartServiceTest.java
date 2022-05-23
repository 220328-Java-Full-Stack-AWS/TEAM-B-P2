package com.revature.p2backend.beans.services;


import com.revature.p2backend.Dto.CartDto;
import com.revature.p2backend.beans.dao.*;
import com.revature.p2backend.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @MockBean
    OrdersDao ordersDao;
    @MockBean
    OrderItemDao orderItemDao;
    @MockBean
    AddressDao addressDao;
    @MockBean
    UserDao userDao;
    @MockBean
    ProductDao productDao;


    //we are creating a test for
    //public User getUser(Integer id){
    //       User user = userDao.getById(id);
    //       return user;
    //    }

@Test
    public void getUserTest(@Autowired CartService sut) throws Exception{
    //arrange:we want to arrange the parameter and return
    //with this we are making the parameter
    Integer id = 7;
    //with this we are making the return for the mock
    User user = new User("firstNameTest", "lastNameTest", "userNameTest", "emailTest", "passwordTest", "phoneNumberTest");
    //with this we say when the mock object calls its method return what we want you to return
    //which in this case is user
    when(userDao.getById(id)).thenReturn(user);
    //act
    //With this we are calling the function and it is returning returnedUser
    //we wanted the return to be User
    User returnedUser = sut.getUser(id);
    //assert: now we assert that what is returned by the function(returnedUser)
    //is equal to what we wanted the method to return(user)
    Assertions.assertEquals(user, returnedUser);

}

    //we want to test this function:
    //public Product getProduct(Integer id){
    //        Product product = productDao.getById(id);
    //        return product;
    //    }

    @Test
    public void getProduct(@Autowired CartService sut) throws Exception{
        //arrange:we want to arrange the parameter and return
        //with this we are making the parameter
        Integer id = 5;
        //with this we are making the return for the mock
        Product product = new Product("nameTest", "descriptionTest", 1000.23, 65);
        //with this we say when the mock object calls its method return what we want you to return
        //which in this case is product
        when(productDao.getById(id)).thenReturn(product);
        //act
        //With this we are calling the function and it is returning returnProduct
        //we wanted the return to be product
        Product returnProduct = sut.getProduct(id);
        //assert: now we assert that what is returned by the function(returnProduct)
        //is equal to what we wanted the method to return(product)
        Assertions.assertEquals(product, returnProduct);

    }

    @Test
    public void checkoutTest(@Autowired CartService sut) throws Exception{
        //arrange:we want to arrange the parameter and return
        //with this we are making the parameter

        Address address = new Address("numberTest", "streetTest", "cityTest", "stateTest", "zipCodeTest");
        //User user = new User("firstNameTest", "lastNameTest", "userNameTest", "emailTest", "passwordTest", "phoneNumberTest");
        User user = new User();
        CartDto cartDto = new CartDto(address, user);
        //with this we are making the return
        Orders orders = new Orders("creationDateTest", address, user);
        //with this we say when the mock object calls its method return what we want you to return
        //which in this case is orders
        when(ordersDao.update(orders)).thenReturn(orders);
        //act
        //With this we are calling the function and it is returning orderReturn
        //we wanted the return to be orders
        Orders orderRetuen = sut.checkout(cartDto);
        //assert: now we assert that what is returned by the function(orderReturn)
        //is equal to what we wanted the method to return(orders)
        Assertions.assertEquals(orders, orderRetuen);
}

}
