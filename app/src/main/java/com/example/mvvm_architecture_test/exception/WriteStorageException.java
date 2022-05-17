package com.example.mvvm_architecture_test.exception;

public class WriteStorageException extends StorageException {
    public WriteStorageException(){
        super("Write error.");
    }
    public WriteStorageException(String message){
        super(message);
    }
}
