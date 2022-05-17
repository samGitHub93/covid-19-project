package com.example.mvvm_architecture_test.exception;

public class ReadStorageException extends StorageException {
    public ReadStorageException(){
        super("Read error.");
    }
    public ReadStorageException(String message){
        super(message);
    }
}
