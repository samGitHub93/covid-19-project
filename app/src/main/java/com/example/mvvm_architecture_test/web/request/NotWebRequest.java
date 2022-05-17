package com.example.mvvm_architecture_test.web.request;

public interface NotWebRequest<T, K, H> {
    T getAll();
    K save(H h);
}
