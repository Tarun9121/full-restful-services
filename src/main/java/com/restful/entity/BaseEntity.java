package com.restful.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import javax.persistence.MappedSuperclass;

@Data @NoArgsConstructor @AllArgsConstructor
public class BaseEntity {
    private String message;
    private HttpStatus httpStatus;
}
