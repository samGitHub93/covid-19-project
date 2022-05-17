package com.example.mvvm_architecture_test.exception;

public class StorageException extends NotWebException {
    public StorageException(){
        super("Storage I/O error.");
    }
    public StorageException(String message){
        super(message);
    }
}
