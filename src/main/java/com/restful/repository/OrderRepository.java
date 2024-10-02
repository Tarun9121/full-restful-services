package com.restful.repository;
import com.restful.dao.MyCrudRepository;

import com.restful.entity.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OrderRepository implements MyCrudRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Order order) {
        entityManager.persist(order);
    }

    @Override
    public Optional<Order> findById(UUID orderId) {
        Order existingData = entityManager.find(Order.class, orderId);
        return Optional.of(existingData);
    }

    @Override
    public List<Order> findAll() {
        TypedQuery<Order> totalList = entityManager.createQuery("From Order", Order.class);
        return totalList.getResultList();
    }

    @Transactional
    @Override
    public Optional<Order> updateOrder(UUID orderId, Order data) {
        Optional<Order> existingData = findById(orderId);

        if(existingData.isPresent()) {
            data.setOrderId(orderId);
            return Optional.of(entityManager.merge(data));
        }
        else {
            return Optional.ofNullable(null);
        }
    }

    @Override
    public ResponseEntity<String> removeOrder(UUID orderId) {
        Optional<Order> exisingOrder = findById(orderId);

        if(exisingOrder.isPresent()) {
            entityManager.remove(exisingOrder.get());

            return ResponseEntity.status(HttpStatus.OK).body("the data deleted successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("there is no data");
        }
    }
}
