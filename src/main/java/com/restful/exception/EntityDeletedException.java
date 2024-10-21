package com.restful.exception;

public class EntityDeletedException extends RuntimeException {
    public EntityDeletedException(String message) {
        super(message);
    }
}
