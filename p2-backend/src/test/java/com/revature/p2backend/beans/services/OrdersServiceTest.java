package com.revature.p2backend.beans.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.revature.p2backend.beans.dao.OrdersDao;
import com.revature.p2backend.entities.Address;
import com.revature.p2backend.entities.Orders;
import com.revature.p2backend.entities.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrdersServiceTest {

    @MockBean
    private OrdersDao ordersDao;

    private Orders orders;

    private User user;

    @BeforeEach
    public void setUp() {

        User user = new User(
                "John",
                "Doe",
                "jdoe",
                "john@example.com",
                "password",
                "1234567890");
        orders = new Orders(
                "2020-01-01 00:00:00",
                new Address(
                        "123 Main St",
                        "Apt 1",
                        "City",
                        "State",
                        "12345"),
                new Address(
                        "123 Main St",
                        "Apt 1",
                        "City",
                        "State",
                        "12345"),
                user);
        orders.setId(1);
    }

    @Test
    void testCreateOrder(@Autowired OrdersService ordersService) {
        ordersService.createOrder(orders);
        verify(ordersDao).save(orders);

    }

    @Test
    void testGetAllOrders(@Autowired OrdersService ordersService) {
        List<Orders> ordersList = new ArrayList<Orders>();
        ordersList.add(orders);

        when(ordersDao.getAll()).thenReturn(ordersList);
        assertEquals(ordersList, ordersService.getAllOrders());
        assertEquals(1, ordersList.size());
    }

    @Test
    void testGetOrdersById(@Autowired OrdersService ordersService) {
        when(ordersDao.getById(1)).thenReturn(orders);
        assertEquals(orders, ordersService.getOrdersById(1));
    }

    @Test
    void testGetOrdersByUser(@Autowired OrdersService ordersService) {
        List<Orders> ordersList = new ArrayList<Orders>();
        ordersList.add(orders);

        when(ordersDao.getOrdersByUser(user)).thenReturn(ordersList);
        assertEquals(ordersList, ordersService.getOrdersByUser(user));
        assertEquals(1, ordersList.size());

    }

    @Test
    void testUpdateOrder(@Autowired OrdersService ordersService) {
        ordersService.updateOrder(orders);
        verify(ordersDao).update(orders);
    }

    @Test
    void testDeleteOrder(@Autowired OrdersService ordersService) {
        ordersService.deleteOrder(orders);
        verify(ordersDao).delete(orders);

    }
}
