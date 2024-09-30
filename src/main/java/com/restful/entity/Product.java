package com.restful.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity @Table(name="products")
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(name="id")
    private UUID id;
    @Column(name="product_name")
    private String productName;
    @Column(name="quantity")
    private int quantity;
    @Column(name="orderd_by")
    private String orderdBy;
}
