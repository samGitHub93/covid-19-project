package com.example.mvvm_architecture_test.service;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_architecture_test.exception.NotWebException;
import com.example.mvvm_architecture_test.exception.WebException;
import com.example.mvvm_architecture_test.model.VaccinablePopulation;
import com.example.mvvm_architecture_test.repository.INotWebRepository;
import com.example.mvvm_architecture_test.repository.IWebRepository;
import com.example.mvvm_architecture_test.repository.VaccinablePopulationNotWebRepository;
import com.example.mvvm_architecture_test.repository.VaccinablePopulationWebRepository;
import com.example.mvvm_architecture_test.web.response.IResponse;
import com.example.mvvm_architecture_test.web.response.StorageGetResponse;
import com.example.mvvm_architecture_test.web.response.WebDataResponse;

import java.util.ArrayList;
import java.util.List;

public class VaccinablePopulationService implements IService<List<VaccinablePopulation>>{

    private IResponse<List<VaccinablePopulation>, WebException> webResponse;
    private IResponse<List<VaccinablePopulation>, NotWebException> notWebResponse;
    private final IWebRepository<List<VaccinablePopulation>> webRepository;
    private final INotWebRepository<List<VaccinablePopulation>> notWebRepository;

    public VaccinablePopulationService(Application application) {
        webResponse = new WebDataResponse<>();
        notWebResponse = new StorageGetResponse<>();
        webRepository = new VaccinablePopulationWebRepository(application);
        notWebRepository = new VaccinablePopulationNotWebRepository(application);
    }

    @Override
    public void updateData() {
        webResponse = webRepository.getAll();
        if(webResponse.getSuccess() != null)
            saveBackup();
        else
            notWebResponse = notWebRepository.getAll();
    }

    @Override
    public MutableLiveData<List<VaccinablePopulation>> getData() {
        MutableLiveData<List<VaccinablePopulation>> data = new MutableLiveData<>();
        if(webResponse.getSuccess() != null) {
            data.setValue(webResponse.getSuccess());
        }else if(notWebResponse.getSuccess() != null){
            data.setValue(notWebResponse.getSuccess());
        }else data.setValue(new ArrayList<>());
        return data;
    }

    private void saveBackup(){
        notWebRepository.saveBackup();
    }
}
