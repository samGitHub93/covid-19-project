package com.example.mvvm_architecture_test.service;

import androidx.lifecycle.MutableLiveData;

public interface IService<T> {
    void updateData();
    MutableLiveData<T> getData();
}
