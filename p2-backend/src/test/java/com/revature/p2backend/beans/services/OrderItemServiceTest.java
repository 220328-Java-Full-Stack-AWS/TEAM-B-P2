package com.revature.p2backend.beans.services;

import com.revature.p2backend.beans.dao.OrderItemDao;
import com.revature.p2backend.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderItemServiceTest {

    @MockBean
    OrderItemDao orderItemDao;

    //we are making a test for this function
    //public OrderItem craeteOrderItem(OrderItem orderItem){
    //
    //        return orderItemDao.save(orderItem);
    //    }

    @Test
    public void createOrderItemTest(@Autowired OrderItemService sut) throws Exception {
        //arrange:we want to arrange the parameter and return
        //with this we are making the parameter
        Product productId = new Product("nameTest", "descriptionTest", 210.10, 15);
        Address address = new Address("numberTest", "streetTest", "cityTest", "stateTest", "zipCodeTest");
        User user = new User("firstNameTest", "lastNameTest", "userNameTest", "emailTest", "passwordTest", "phoneNumberTest");
        Orders orders = new Orders("dateTest", address, user);
        OrderItem orderItemParam = new OrderItem(9, productId, orders);
        //with this we are making the return
        OrderItem orderItemReturn = new OrderItem(9, productId, orders);
        //with this we say when the mock object calls its method return what we want you to return
        //which in this case is orderItemReturn
        when(orderItemDao.save(orderItemParam)).thenReturn(orderItemReturn);
        //act
        //With this we are calling the function and it is returning returnOrderItem
        //we wanted the return to be orderItemReturn
        OrderItem returnOrderItem = sut.craeteOrderItem(orderItemParam);
        //assert: now we assert that what is returned by the function(returnOrderItem)
        //is equal to what we wanted the method to return(orderItemReturn)

        Assertions.assertEquals(orderItemReturn, returnOrderItem);
    }

    //we are making test for this function:
    //public List<OrderItem> getAllOrderItems(){
    //
    //        return orderItemDao.getAll();
    //    }

    @Test
    public void getAllOrderItemsTest(@Autowired OrderItemService sut) throws Exception {
        //arrange:we want to arrange the parameter and return
        //with this we are making the return
        List<OrderItem> list = new ArrayList<>();
        //with this we say when the mock object calls its method return what we want you to return
        //which in this case is list
        when(orderItemDao.getAll()).thenReturn(list);
        //act
        //With this we are calling the function and it is returning returnList
        //we wanted the return to be list
        List<OrderItem> returnList = sut.getAllOrderItems();
        //assert: now we assert that what is returned by the function(returnList)
        //is equal to what we wanted the method to return(list)
        Assertions.assertEquals(list, returnList);
    }


    //we want to make test for this function:
    //public OrderItem getOrderItemById(Integer id){
    //
    //        return orderItemDao.getById(id);
    //    }

    @Test
    public void getOrderItemById(@Autowired OrderItemService sut) throws Exception {
        //arrange:we want to arrange the parameter and return
        //with this we are making the parameter
        Integer id = 13;
        //with this we are making the return
        Product productId = new Product("nameTest", "descriptionTest", 210.10, 15);
        Address address = new Address("numberTest", "streetTest", "cityTest", "stateTest", "zipCodeTest");
        User user = new User("firstNameTest", "lastNameTest", "userNameTest", "emailTest", "passwordTest", "phoneNumberTest");
        Orders orders = new Orders("dateTest", address, user);
        OrderItem orderItemReturn = new OrderItem(9, productId, orders);
        //with this we say when the mock object calls its method return what we want you to return
        //which in this case is orderItemReturn
        when(orderItemDao.getById(id)).thenReturn(orderItemReturn);
        //act
        //With this we are calling the function and it is returning returnOrderItem
        //we wanted the function to return orderItemReturn
        OrderItem returnOrderItem = sut.getOrderItemById(id);
        //assert: now we assert that what is returned by the function (returnOrderItem)
        //is equal to what we wanted the method to return(orderItemReturn)
        Assertions.assertEquals(orderItemReturn, returnOrderItem);

    }

    // we are making test for this function:
    //public OrderItem deleteOrderItem(OrderItem orderItem){
    //
    //        orderItemDao.delete(orderItem);
    //        return orderItem;
    //    }

    @Test
    public void deleteOrderItemTest(@Autowired OrderItemService sut) throws Exception {
        //arrange:we want to arrange the parameter and return
        //with this we are making the parameter
        Product productId = new Product("nameTest", "descriptionTest", 210.10, 15);
        Address address = new Address("numberTest", "streetTest", "cityTest", "stateTest", "zipCodeTest");
        User user = new User("firstNameTest", "lastNameTest", "userNameTest", "emailTest", "passwordTest", "phoneNumberTest");
        Orders orders = new Orders("dateTest", address, user);
        OrderItem orderItemParam = new OrderItem(9, productId, orders);
        //with this we are making the return
        OrderItem orderItemReturn = new OrderItem(9, productId, orders);
        //with this we say when the mock object calls its method return what we want you to return
        //which in this case is orderItemReturn
        when(orderItemDao.delete(orderItemParam)).thenReturn(orderItemReturn);
        //act
        //With this we are calling the function and it is returning returnedOrderItem
        //we wanted the return to be User
        OrderItem returnOrderItem = sut.deleteOrderItem(orderItemParam);
        //assert: now we assert that what is returned by the function(returnOrderItem)
        //is equal to what we wanted the method to return(orderItemReturn)

        Assertions.assertEquals(orderItemReturn, returnOrderItem);

    }

    //we are making tests for this function:
    //public OrderItem updateOrderItem(OrderItem orderItem){
    //
    //        return orderItemDao.update(orderItem);
    //    }

    @Test
    public void updateOrderItemTest(@Autowired OrderItemService sut) throws Exception {
        //arrange:we want to arrange the parameter and return
        //with this we are making the parameter
        Product productId = new Product("nameTest", "descriptionTest", 210.10, 15);
        Address address = new Address("numberTest", "streetTest", "cityTest", "stateTest", "zipCodeTest");
        User user = new User("firstNameTest", "lastNameTest", "userNameTest", "emailTest", "passwordTest", "phoneNumberTest");
        Orders orders = new Orders("dateTest", address, user);
        OrderItem orderItemParam = new OrderItem(9, productId, orders);
        //with this we are making the return
        OrderItem orderItemReturn = new OrderItem(9, productId, orders);
        //with this we say when the mock object calls its method return what we want you to return
        //which in this case is orderItemReturn
        when(orderItemDao.update(orderItemParam)).thenReturn(orderItemReturn);
        //act
        //With this we are calling the function and it is returning returOrderItem
        //we wanted the return to be User
        OrderItem returnOrderItem = sut.updateOrderItem(orderItemParam);
        //assert: now we assert that what is returned by the function(returnOrderItem)
        //is equal to what we wanted the method to return(orderItemReturn)

        Assertions.assertEquals(orderItemReturn, returnOrderItem);
    }


    //we are making tests for this function:
    //public List<OrderItem> getOrderItemsByOrder(Orders order){
    //
    //        return orderItemDao.getOrderItemsByOrder(order);
    //    }

    @Test
    public void getOrderItemsByOrderTest(@Autowired OrderItemService sut) throws Exception {
        //arrange:we want to arrange the parameter and return
        //with this we are making the parameter
        Address address = new Address("numberTest", "streetTest", "cityTest", "stateTest", "zipCodeTest");
        User user = new User("firstNameTest", "lastNameTest", "userNameTest", "emailTest", "passwordTest", "phoneNumberTest");
        Orders orders = new Orders("dateTest", address, user);
        //with this we are making the return
        List<OrderItem> list = new ArrayList<>();
        //with this we say when the mock object calls its method return what we want you to return
        //which in this case is list
        when(orderItemDao.getOrderItemsByOrder(orders)).thenReturn(list);
        //act
        //With this we are calling the function and it is returning listReturn
        //we wanted the return to be list
        List<OrderItem> listReturn = sut.getOrderItemsByOrder(orders);
        //assert: now we assert that what is returned by the function(listOrderItem)
        //is equal to what we wanted the method to return(list)
        Assertions.assertEquals(list, listReturn);

    }

}