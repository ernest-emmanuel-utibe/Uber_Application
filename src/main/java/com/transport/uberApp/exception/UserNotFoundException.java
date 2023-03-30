package com.transport.uberApp.exception;

public class UserNotFoundException extends BusinessLogicException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
