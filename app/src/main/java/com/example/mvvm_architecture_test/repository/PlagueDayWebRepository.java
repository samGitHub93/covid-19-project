package com.example.mvvm_architecture_test.repository;

import android.app.Application;

import com.example.mvvm_architecture_test.exception.UnableToReachDataException;
import com.example.mvvm_architecture_test.exception.WebException;
import com.example.mvvm_architecture_test.model.PlagueDay;
import com.example.mvvm_architecture_test.web.callback_response.ICallbackResponse;
import com.example.mvvm_architecture_test.web.callback_response.WebCallbackResponse;
import com.example.mvvm_architecture_test.web.request.WebRequest;
import com.example.mvvm_architecture_test.web.request.PlagueDayWebRequest;
import com.example.mvvm_architecture_test.web.response.IResponse;
import com.example.mvvm_architecture_test.web.response.WebDataResponse;

import java.util.List;

public class PlagueDayWebRepository implements IWebRepository<List<PlagueDay>> {

    private final ICallbackResponse<List<PlagueDay>, WebException> callbackResponse;
    private final IResponse<List<PlagueDay>, WebException> response;
    private final WebRequest<List<PlagueDay>> request;

    public PlagueDayWebRepository(Application application){
        request = new PlagueDayWebRequest();
        response = new WebDataResponse<>();
        callbackResponse = new WebCallbackResponse<>(application.getApplicationContext());
    }

    @Override
    public IResponse<List<PlagueDay>, WebException> getAll() {
        List<PlagueDay> plagueDayList = request.getAll();
        prepareWebResponse(plagueDayList);
        return response;
    }

    private void prepareWebResponse(List<PlagueDay> plagueDayList){
        if (plagueDayList.isEmpty()) response.setError(new UnableToReachDataException());
        else response.setSuccess(plagueDayList);
        callbackResponse.onComplete(response);
    }
}
