package com.example.mvvm_architecture_test.view_model;

import androidx.lifecycle.LiveData;

import com.example.mvvm_architecture_test.enumerator.TableType;
import com.example.mvvm_architecture_test.model.PlagueDay;

import java.util.List;

public interface IViewModel<K, T> {
    LiveData<K> getUpgradedData(T t);
    LiveData<K> getNotUpgradedData(T t);
}
