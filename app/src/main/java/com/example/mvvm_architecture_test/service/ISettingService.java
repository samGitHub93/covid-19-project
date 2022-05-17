package com.example.mvvm_architecture_test.service;

public interface ISettingService<T> {
    T getSetting();
    void saveSetting(T list);
}
