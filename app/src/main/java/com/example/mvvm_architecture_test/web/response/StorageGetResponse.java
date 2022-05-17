package com.example.mvvm_architecture_test.web.response;

import com.example.mvvm_architecture_test.exception.NotWebException;
import com.example.mvvm_architecture_test.model.PlagueDay;

import java.util.ArrayList;
import java.util.List;

public class StorageGetResponse<T> implements IResponse<T, NotWebException> {

    private T success;
    private NotWebException exception;

    @Override
    public T getSuccess() {
        return success;
    }

    @Override
    public NotWebException getError() {
        return exception;
    }

    @Override
    public void setSuccess(T success) {
        this.success = success;
        this.exception = null;
    }

    @Override
    public void setError(NotWebException e) {
        this.exception = e;
        this.success = null;
    }
}
