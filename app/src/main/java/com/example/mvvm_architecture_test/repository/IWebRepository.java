package com.example.mvvm_architecture_test.repository;

import com.example.mvvm_architecture_test.exception.WebException;
import com.example.mvvm_architecture_test.web.response.IResponse;

public interface IWebRepository<T> extends IRepository<T, WebException>{
    IResponse<T, WebException> getAll();
}
