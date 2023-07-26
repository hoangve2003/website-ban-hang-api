package com.poly.rest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Order;
import com.poly.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class OrderRestController {
    @Autowired
    OrderService orderService;

    @PostMapping("/rest/orders")
    public Order create(@RequestBody JsonNode orderData) {
        return orderService.create(orderData);
    }
}
