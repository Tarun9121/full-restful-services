package com.restful.dao;

import com.restful.entity.Order;
import com.restful.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MyCrudRepository {
    void save(Order order);
    Optional<Order> findById(UUID orderId);
    List<Order> findAll();
    Optional<Order> updateOrder(UUID orderId, Order order);
    ResponseEntity<String> removeOrder(UUID orderId);
}
