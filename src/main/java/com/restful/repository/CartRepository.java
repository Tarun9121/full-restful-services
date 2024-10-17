package com.restful.repository;

import com.restful.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    @Query(value = "SELECT MAX(CAST(id AS SIGNED)) FROM cart;", nativeQuery = true)
    Long maxCartId();
}
