package com.example.mvvm_architecture_test.exception;

public class WebException extends Exception {
    public WebException(){
        super("Web error.");
    }
    public WebException(String message){
        super(message);
    }
}
