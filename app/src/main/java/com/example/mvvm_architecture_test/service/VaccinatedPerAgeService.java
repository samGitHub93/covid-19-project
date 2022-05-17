package com.example.mvvm_architecture_test.service;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_architecture_test.exception.NotWebException;
import com.example.mvvm_architecture_test.exception.WebException;
import com.example.mvvm_architecture_test.model.VaccinatedPerAge;
import com.example.mvvm_architecture_test.repository.INotWebRepository;
import com.example.mvvm_architecture_test.repository.IWebRepository;
import com.example.mvvm_architecture_test.repository.VaccinatedPerAgeNotWebRepository;
import com.example.mvvm_architecture_test.repository.VaccinatedPerAgeWebRepository;
import com.example.mvvm_architecture_test.web.response.IResponse;
import com.example.mvvm_architecture_test.web.response.StorageGetResponse;
import com.example.mvvm_architecture_test.web.response.WebDataResponse;

import java.util.ArrayList;
import java.util.List;

public class VaccinatedPerAgeService implements IService<List<VaccinatedPerAge>> {

    private IResponse<List<VaccinatedPerAge>, WebException> webResponse;
    private IResponse<List<VaccinatedPerAge>, NotWebException> notWebResponse;
    private final IWebRepository<List<VaccinatedPerAge>> webRepository;
    private final INotWebRepository<List<VaccinatedPerAge>> notWebRepository;

    public VaccinatedPerAgeService(Application application) {
        webResponse = new WebDataResponse<>();
        notWebResponse = new StorageGetResponse<>();
        webRepository = new VaccinatedPerAgeWebRepository(application);
        notWebRepository = new VaccinatedPerAgeNotWebRepository(application);
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
    public MutableLiveData<List<VaccinatedPerAge>> getData() {
        MutableLiveData<List<VaccinatedPerAge>> data = new MutableLiveData<>();
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
