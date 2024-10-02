package com.restful.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder
@Entity @Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(name="order_id")
    private UUID orderId;

    @Column(name="product_name")
    private String productName;

    @Column(name="person_name")
    private String personName;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdTimeStamp;

    private LocalDateTime updateTimeStamp;

    @PrePersist
    public void init() {
        System.out.println("we have got your data");
    }

    @PostPersist
    public void postPersist() {
        System.out.println("your data saved successfully");
    }

    @PreUpdate
    public void updateTime() {
        this.updateTimeStamp = LocalDateTime.now();
    }
}
