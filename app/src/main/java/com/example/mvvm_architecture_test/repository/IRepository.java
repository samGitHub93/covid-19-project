package com.example.mvvm_architecture_test.repository;

import com.example.mvvm_architecture_test.web.response.IResponse;

public interface IRepository<T, E> {
    IResponse<T, E> getAll();
}
