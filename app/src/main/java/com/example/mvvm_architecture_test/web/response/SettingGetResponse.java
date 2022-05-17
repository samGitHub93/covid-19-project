package com.example.mvvm_architecture_test.web.response;

import com.example.mvvm_architecture_test.exception.NotWebException;

import java.util.ArrayList;
import java.util.List;

public class SettingGetResponse implements IResponse<List<String>, NotWebException> {

    private List<String> success;
    private NotWebException exception;

    @Override
    public List<String> getSuccess() {
        return success;
    }

    @Override
    public NotWebException getError() {
        return exception;
    }

    @Override
    public void setSuccess(List<String> success) {
        this.success = success;
        this.exception = null;
    }

    @Override
    public void setError(NotWebException e) {
        this.exception = e;
        this.success = new ArrayList<>();
    }
}
