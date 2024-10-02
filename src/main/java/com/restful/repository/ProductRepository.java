package com.restful.repository;

import com.restful.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    /**
     * JPA query methods:
     *
     * 1. Query Methods (Derived Query Methods):
     * The JPA module supports defining a query manually as a String or having it being derived from the method name.
     *
     * Derived queries with the predicates IsStartingWith, StartingWith, StartsWith, IsEndingWith, EndingWith, EndsWith, IsNotContaining, NotContaining, NotContains,
     * IsContaining, Containing, Contains the respective arguments for these queries will get sanitized. This means if the arguments actually contain characters
     * recognized by LIKE as wildcards these will get escaped so they match only as literals.
     *
     * not recommended
     * ref: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.named-queries
     *
     * Custom Queries:
     *
     * For more complex queries, you can use @Query annotation to write your JPQL and SQL queries
     */

    Product findByProductNameAndOrderdBy(String productName, String orderdBy);

    @Query(nativeQuery = true, value="select * from products where quantity > :quantity")
    List<Product> getQuantityMoreThan(@Param("quantity") int quantity);
}
