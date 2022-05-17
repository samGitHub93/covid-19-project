package com.example.mvvm_architecture_test.web.callback_response;

import android.content.Context;
import android.widget.Toast;

import com.example.mvvm_architecture_test.exception.NotWebException;
import com.example.mvvm_architecture_test.web.response.IResponse;

public class StorageSaveCallbackResponse implements ICallbackResponse<Boolean, NotWebException> {

    private final Context context;

    public StorageSaveCallbackResponse(Context context){
        this.context = context;
    }

    @Override
    public void onComplete(IResponse<Boolean, NotWebException> response) {
        if (response.getSuccess()) {
            Toast.makeText(context, "Backup saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, response.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComplete(IResponse<Boolean, NotWebException> response, String message) {
        if (response.getSuccess()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, response.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
