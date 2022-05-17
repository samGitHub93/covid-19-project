package com.example.mvvm_architecture_test.web.response;

public interface IResponse<T,E> {
    T getSuccess();
    E getError();
    void setSuccess(T success);
    void setError(E e);
}
