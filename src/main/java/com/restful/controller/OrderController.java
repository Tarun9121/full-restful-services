package com.restful.controller;

import com.restful.entity.Order;
import com.restful.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public void addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> findById(@PathVariable("orderId") UUID orderId) {
        return orderService.findById(orderId);
    }

    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable("orderId") UUID orderId, @RequestBody Order updatedOrder) {
        return orderService.updateUser(orderId, updatedOrder);
    }
}
