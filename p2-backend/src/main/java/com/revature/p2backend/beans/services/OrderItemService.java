package com.revature.p2backend.beans.services;

import com.revature.p2backend.CartItem;
import com.revature.p2backend.beans.dao.OrderItemDao;
import com.revature.p2backend.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderItemService {


private final OrderItemDao orderItemDao;

//added
private final OrdersService orderService;
private final ProductService productService;

@Autowired
    public OrderItemService(OrderItemDao orderItemDao,OrdersService orderService, ProductService productService) {

    this.orderItemDao = orderItemDao;
    this.orderService=orderService;
    this.productService=productService;
    }

   public OrderItem craeteOrderItem(OrderItem orderItem){
    return orderItemDao.save(orderItem);
   }


    public OrderItem createOrderItem(CartItem cartItem){
        OrderItem item= new OrderItem();
        Orders order = orderService.getOrdersById(cartItem.getOrderId());
        Product product = productService.getByProductId(cartItem.getProductId());
        item.setItemTotalAmount(cartItem.getItemTotalAmount());
        item.setQuantity(cartItem.getQuantity());
        item.setOrders(order);
        item.setProduct(product);
        return orderItemDao.save(item);
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
