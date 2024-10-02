package com.restful.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @PrePersist
    public void prePersist() {
        System.out.println("please wait, we are processing your data");
    }

    @PostPersist
    public void postPersist(){
        System.out.println("don't worry we got your data :)");
    }

    @PreUpdate
    public void preUpdate() {
        System.out.println("your data is ready to update");
    }

    @PostUpdate
    public void postUpdate() {
        System.out.println("Hurrai, your data is updated :)");
    }
}
