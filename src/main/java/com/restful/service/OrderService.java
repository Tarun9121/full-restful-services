package com.restful.service;

import com.restful.entity.Order;
import com.restful.repository.OrderRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public ResponseEntity<Order> findById(UUID orderId) {
        Optional<Order> existingOrder = orderRepository.findById(orderId);

        if(existingOrder.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(existingOrder.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public ResponseEntity<Order> updateUser(UUID orderId, Order updatedData) {
        Optional<Order> updated = orderRepository.updateOrder(orderId, updatedData);

        if(updated.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(updated.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
