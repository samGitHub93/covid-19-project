package com.example.mvvm_architecture_test.service;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_architecture_test.exception.NotWebException;
import com.example.mvvm_architecture_test.exception.WebException;
import com.example.mvvm_architecture_test.model.PlagueDay;
import com.example.mvvm_architecture_test.repository.INotWebRepository;
import com.example.mvvm_architecture_test.repository.IWebRepository;
import com.example.mvvm_architecture_test.repository.PlagueDayNotWebRepository;
import com.example.mvvm_architecture_test.repository.PlagueDayWebRepository;
import com.example.mvvm_architecture_test.web.response.IResponse;
import com.example.mvvm_architecture_test.web.response.StorageGetResponse;
import com.example.mvvm_architecture_test.web.response.WebDataResponse;

import java.util.ArrayList;
import java.util.List;

public class PlagueDayService implements IService<List<PlagueDay>> {

    private IResponse<List<PlagueDay>, WebException> webResponse;
    private IResponse<List<PlagueDay>, NotWebException> notWebResponse;
    private final IWebRepository<List<PlagueDay>> webRepository;
    private final INotWebRepository<List<PlagueDay>> notWebRepository;

    public PlagueDayService(Application application){
        webResponse = new WebDataResponse<>();
        notWebResponse = new StorageGetResponse<>();
        webRepository = new PlagueDayWebRepository(application);
        notWebRepository = new PlagueDayNotWebRepository(application);
    }

    @Override
    public void updateData(){
        webResponse = webRepository.getAll();
        if(webResponse.getSuccess() != null)
            saveBackup();
        else
            notWebResponse = notWebRepository.getAll();
    }

    @Override
    public MutableLiveData<List<PlagueDay>> getData(){
        MutableLiveData<List<PlagueDay>> data = new MutableLiveData<>();
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
