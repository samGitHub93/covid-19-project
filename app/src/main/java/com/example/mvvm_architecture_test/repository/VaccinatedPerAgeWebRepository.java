package com.example.mvvm_architecture_test.repository;

import android.app.Application;

import com.example.mvvm_architecture_test.exception.UnableToReachDataException;
import com.example.mvvm_architecture_test.exception.WebException;
import com.example.mvvm_architecture_test.model.VaccinatedPerAge;
import com.example.mvvm_architecture_test.web.callback_response.ICallbackResponse;
import com.example.mvvm_architecture_test.web.callback_response.WebCallbackResponse;
import com.example.mvvm_architecture_test.web.request.VaccinatedPerAgeWebRequest;
import com.example.mvvm_architecture_test.web.request.WebRequest;
import com.example.mvvm_architecture_test.web.response.IResponse;
import com.example.mvvm_architecture_test.web.response.WebDataResponse;

import java.util.List;

public class VaccinatedPerAgeWebRepository implements IWebRepository<List<VaccinatedPerAge>>{

    private final ICallbackResponse<List<VaccinatedPerAge>, WebException> callbackResponse;
    private final IResponse<List<VaccinatedPerAge>, WebException> response;
    private final WebRequest<List<VaccinatedPerAge>> request;

    public VaccinatedPerAgeWebRepository(Application application) {
        request = new VaccinatedPerAgeWebRequest();
        response = new WebDataResponse<>();
        callbackResponse = new WebCallbackResponse<>(application.getApplicationContext());
    }

    @Override
    public IResponse<List<VaccinatedPerAge>, WebException> getAll() {
        List<VaccinatedPerAge> vaccinatedPerAgeList = request.getAll();
        prepareWebResponse(vaccinatedPerAgeList);
        return response;
    }

    private void prepareWebResponse(List<VaccinatedPerAge> vaccinatedPerAgeList){
        if (vaccinatedPerAgeList == null || vaccinatedPerAgeList.isEmpty()) response.setError(new UnableToReachDataException());
        else response.setSuccess(vaccinatedPerAgeList);
        callbackResponse.onComplete(response);
    }
}
