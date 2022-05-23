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


import java.time.LocalDate;

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
    @MockBean
    CartDto cartDto;
    @MockBean
    User user;



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
        public void checkOutMoreTest(@Autowired CartService sut) throws Exception{
          Double orderTotal = 0.0;

          Address address = new Address("numberTest", "streetTest", "cityTest", "stateTest", "zipCodeTest");
          User user = new User("firstNameTest", "lastNameTest", "userNameTest", "emailTest", "passwordTest", "phoneNumberTest");
          CartDto cartDto = new CartDto(address, user);

          Orders orders = new Orders(String.valueOf(LocalDate.now()), cartDto.getAddress(), user);
          ordersDao.save(orders);
         for(OrderItem incomingOrderItem : cartDto.getOrderItemList()){
            Product product = sut.getProduct(incomingOrderItem.getProductId().getProductId());
           OrderItem orderItem = new OrderItem(incomingOrderItem.getQuantity(), product, orders);
           Double itemTotal = incomingOrderItem.getQuantity() * product.getPrice();
          orderItem.setItemTotalAmount(itemTotal);
           product.setInventory(product.getInventory() - orderItem.getQuantity());
           orderTotal += (itemTotal);
           orders.setOrderItems(orderItem);
           orderItemDao.save(orderItem);
           System.out.println(orderItem);
       }  orders.setOrderTotal(orderTotal);
         when(ordersDao.update(orders)).thenReturn(orders);
         Orders orderRetuen = sut.checkout(cartDto);
         Assertions.assertEquals(orders, orderRetuen);

    }









}
