package com.example.mvvm_architecture_test.repository;

import com.example.mvvm_architecture_test.exception.NotWebException;
import com.example.mvvm_architecture_test.web.response.IResponse;

public interface INotWebRepository<T> extends IRepository<T, NotWebException>{
    IResponse<T, NotWebException> getAll();
    void saveBackup();
}
