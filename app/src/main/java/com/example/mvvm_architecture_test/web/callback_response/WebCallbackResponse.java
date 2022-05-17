package com.example.mvvm_architecture_test.web.callback_response;

import android.content.Context;
import android.widget.Toast;

import com.example.mvvm_architecture_test.exception.WebException;
import com.example.mvvm_architecture_test.web.response.IResponse;

public class WebCallbackResponse<T> implements ICallbackResponse<T, WebException> {

    private final Context context;

    public WebCallbackResponse(Context context){
        this.context = context;
    }

    @Override
    public void onComplete(IResponse<T, WebException> response) {
        if (response.getSuccess()!=null) {
            Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, response.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComplete(IResponse<T, WebException> response, String message) {
        if (response.getSuccess()!=null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, response.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
