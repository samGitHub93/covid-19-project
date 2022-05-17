package com.example.mvvm_architecture_test.web.callback_response;

import com.example.mvvm_architecture_test.web.response.IResponse;

public interface ICallbackResponse<T, E> {
    void onComplete(IResponse<T, E> response);
    void onComplete(IResponse<T, E> response, String message);
}
