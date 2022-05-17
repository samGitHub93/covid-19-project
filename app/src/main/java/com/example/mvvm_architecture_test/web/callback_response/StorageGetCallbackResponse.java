package com.example.mvvm_architecture_test.web.callback_response;

import android.content.Context;
import android.widget.Toast;

import com.example.mvvm_architecture_test.exception.NotWebException;
import com.example.mvvm_architecture_test.web.response.IResponse;

public class StorageGetCallbackResponse<T> implements ICallbackResponse<T, NotWebException> {

    private final Context context;

    public StorageGetCallbackResponse(Context context){
        this.context = context;
    }

    @Override
    public void onComplete(IResponse<T, NotWebException> response) {
        if (response.getSuccess() != null) {
            Toast.makeText(context, "Backup read.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, response.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComplete(IResponse<T, NotWebException> response, String message) {
        if (response.getSuccess() != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, response.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
