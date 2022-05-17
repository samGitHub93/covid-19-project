package com.example.mvvm_architecture_test.repository;

import android.app.Application;

import com.example.mvvm_architecture_test.exception.UnableToReachDataException;
import com.example.mvvm_architecture_test.exception.WebException;
import com.example.mvvm_architecture_test.model.VaccinablePopulation;
import com.example.mvvm_architecture_test.web.callback_response.ICallbackResponse;
import com.example.mvvm_architecture_test.web.callback_response.WebCallbackResponse;
import com.example.mvvm_architecture_test.web.request.VaccinablePopulationWebRequest;
import com.example.mvvm_architecture_test.web.request.WebRequest;
import com.example.mvvm_architecture_test.web.response.IResponse;
import com.example.mvvm_architecture_test.web.response.WebDataResponse;

import java.util.List;

public class VaccinablePopulationWebRepository implements IWebRepository<List<VaccinablePopulation>>{

    private final ICallbackResponse<List<VaccinablePopulation>, WebException> callbackResponse;
    private final IResponse<List<VaccinablePopulation>, WebException> response;
    private final WebRequest<List<VaccinablePopulation>> request;

    public VaccinablePopulationWebRepository(Application application) {
        request = new VaccinablePopulationWebRequest();
        response = new WebDataResponse<>();
        callbackResponse = new WebCallbackResponse<>(application.getApplicationContext());
    }

    @Override
    public IResponse<List<VaccinablePopulation>, WebException> getAll() {
        List<VaccinablePopulation> vaccinablePopulationList = request.getAll();
        prepareWebResponse(vaccinablePopulationList);
        return response;
    }

    private void prepareWebResponse(List<VaccinablePopulation> vaccinablePopulationList){
        if (vaccinablePopulationList == null || vaccinablePopulationList.isEmpty()) response.setError(new UnableToReachDataException());
        else response.setSuccess(vaccinablePopulationList);
        callbackResponse.onComplete(response);
    }
}
