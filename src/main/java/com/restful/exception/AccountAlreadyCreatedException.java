package com.restful.exception;

public class AccountAlreadyCreatedException extends RuntimeException {
    public AccountAlreadyCreatedException(String message)  {
        super(message);
    }
}
