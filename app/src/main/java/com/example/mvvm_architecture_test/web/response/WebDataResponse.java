package com.example.mvvm_architecture_test.web.response;

import com.example.mvvm_architecture_test.exception.WebException;

public class WebDataResponse<T> implements IResponse<T, WebException> {

    private T success;
    private WebException exception;

    @Override
    public T getSuccess() {
        return success;
    }

    @Override
    public WebException getError() {
        return exception;
    }

    @Override
    public void setSuccess(T success) {
        this.success = success;
        this.exception = null;
    }

    @Override
    public void setError(WebException e) {
        this.exception = e;
        this.success = null;
    }
}
