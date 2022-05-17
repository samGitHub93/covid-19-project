package com.example.mvvm_architecture_test.exception;

public class UnableToReachDataException extends WebException {
    public UnableToReachDataException(){
        super("Unable to reach data.");
    }
    public UnableToReachDataException(String message){
        super(message);
    }
}
