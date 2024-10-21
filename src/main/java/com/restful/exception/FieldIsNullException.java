package com.restful.exception;

public class FieldIsNullException extends RuntimeException {
    public FieldIsNullException(String message) {
        super(message);
    }
}
