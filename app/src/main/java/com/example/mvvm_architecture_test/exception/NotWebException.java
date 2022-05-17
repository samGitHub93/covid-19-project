package com.example.mvvm_architecture_test.exception;

public class NotWebException extends Exception {
    public NotWebException(){
        super("Storage error.");
    }
    public NotWebException(String message){
        super(message);
    }
}
