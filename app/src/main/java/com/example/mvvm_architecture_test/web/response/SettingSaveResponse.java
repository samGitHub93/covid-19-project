package com.example.mvvm_architecture_test.web.response;

import com.example.mvvm_architecture_test.exception.NotWebException;

public class SettingSaveResponse implements IResponse<Boolean, NotWebException> {

    private boolean success;
    private NotWebException exception;

    @Override
    public Boolean getSuccess() {
        return success;
    }

    @Override
    public NotWebException getError() {
        return exception;
    }

    @Override
    public void setSuccess(Boolean success) {
        this.success = success;
        this.exception = null;
    }

    @Override
    public void setError(NotWebException e) {
        this.exception = e;
        this.success = false;
    }
}
