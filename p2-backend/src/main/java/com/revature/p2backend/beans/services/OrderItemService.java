package com.revature.p2backend.beans.services;

import com.revature.p2backend.beans.dao.OrderItemDao;
import com.revature.p2backend.entities.Address;
import com.revature.p2backend.entities.OrderItem;
import com.revature.p2backend.entities.Orders;
import com.revature.p2backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemDao orderItemDao;
    private final OrdersService ordersService;

    @Autowired
    public OrderItemService(OrderItemDao orderItemDao, OrdersService ordersService) {
        this.orderItemDao = orderItemDao;
        this.ordersService = ordersService;
    }

    public OrderItem craeteOrderItem(OrderItem orderItem){
        return orderItemDao.save(orderItem);
    }

    public void checkout(List<OrderItem> orderItemList, Address address, User user){
        Orders orders = new Orders();
        orders.setAddress(address);
        orders.setUser(user);
        ordersService.createOrder(orders);
        Double orderTotal = null;
        for(OrderItem orderItem : orderItemList) {
            orderTotal += (orderItem.getQuantity() * orderItem.getProductId().getPrice());
            //orderItem.getProductId().getInventory()--;
            craeteOrderItem(orderItem);
        }
        orders.setOrderTotal(orderTotal);
        ordersService.updateOrder(orders);

    }

    public List<OrderItem> getAllOrderItems(){
        return orderItemDao.getAll();
    }


    public OrderItem getOrderItemById(Integer id){
        return orderItemDao.getById(id);
    }


    public void deleteOrderItem(OrderItem orderItem){
        orderItemDao.delete(orderItem);
    }


    public OrderItem updateOrderItem(OrderItem orderItem){
        return orderItemDao.update(orderItem);
    }

    public List<OrderItem> getOrderItemsByOrder(Orders order){
        return orderItemDao.getOrderItemsByOrder(order);
    }


}
