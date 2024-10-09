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
     * <h4>1. Query Methods (Derived Query Methods):</h4>
     * <p>The JPA module supports defining a query manually as a String or having it being derived from the method name.</p>
     *
     * <p>Derived queries with the predicates IsStartingWith, StartingWith, StartsWith, IsEndingWith, EndingWith, EndsWith, IsNotContaining, NotContaining, NotContains,
     * IsContaining, Containing, Contains the respective arguments for these queries will get sanitized. This means if the arguments actually contain characters
     * recognized by LIKE as wildcards these will get escaped so they match only as literals.</p>
     *
     * <p>
     * @Note - you have to use the same datatype when working with the Jpa Query Methods, If you change the data type then you will get Illegal Argument Exception
     * </p>
     *
     * <a href="https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.named-queries">Jpa Query methods (derived methods)</a>
     *
     * <h4>Custom Queries:</h4>
     *
     * For more complex queries, you can use @Query annotation to write your JPQL and SQL queries
     */

    List<Product> findByProductNameAndOrderdBy(String productName, String orderdBy);

    @Query(nativeQuery = true, value="select * from products where quantity > :quantity")
    List<Product> getQuantityMoreThan(@Param("quantity") int quantity);
}
