package com.revature.p2backend.beans.controllers;

import com.revature.p2backend.entities.OrderItem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderItemList")
public class OrderItemListController {

    @PutMapping("/updateOrderItemList")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateOrderItemList(@RequestBody OrderItem orderItem) {
        updateOrderItemList(orderItem);
    }

}
