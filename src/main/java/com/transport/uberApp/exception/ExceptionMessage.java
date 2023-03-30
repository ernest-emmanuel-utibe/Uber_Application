package com.transport.uberApp.exception;

public enum ExceptionMessage {
    USER_WITH_ID_NOT_FOUND("user with id %d not found");
    private String message;


    ExceptionMessage(String message){
        this.message=message;
    }
    public String getMessage(){
        return message;
    }
}
