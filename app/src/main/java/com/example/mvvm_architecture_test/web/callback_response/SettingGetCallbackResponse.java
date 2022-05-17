package com.example.mvvm_architecture_test.web.callback_response;

import android.content.Context;
import android.widget.Toast;

import com.example.mvvm_architecture_test.exception.NotWebException;
import com.example.mvvm_architecture_test.web.response.IResponse;

import java.util.List;

public class SettingGetCallbackResponse implements ICallbackResponse<List<String>, NotWebException>{

    private final Context context;

    public SettingGetCallbackResponse(Context context){
        this.context = context;
    }

    @Override
    public void onComplete(IResponse<List<String>, NotWebException> response) {
        if (!response.getSuccess().isEmpty()) {
            Toast.makeText(context, "Settings loaded!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, response.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComplete(IResponse<List<String>, NotWebException> response, String message) {
        if (!response.getSuccess().isEmpty()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, response.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
