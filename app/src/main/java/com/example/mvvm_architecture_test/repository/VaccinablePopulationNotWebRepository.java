package com.example.mvvm_architecture_test.repository;

import android.app.Application;

import com.example.mvvm_architecture_test.exception.NotWebException;
import com.example.mvvm_architecture_test.exception.ReadStorageException;
import com.example.mvvm_architecture_test.exception.WriteStorageException;
import com.example.mvvm_architecture_test.model.VaccinablePopulation;
import com.example.mvvm_architecture_test.web.callback_response.ICallbackResponse;
import com.example.mvvm_architecture_test.web.callback_response.StorageGetCallbackResponse;
import com.example.mvvm_architecture_test.web.callback_response.StorageSaveCallbackResponse;
import com.example.mvvm_architecture_test.web.request.NotWebRequest;
import com.example.mvvm_architecture_test.web.request.VaccinablePopulationStorageRequest;
import com.example.mvvm_architecture_test.web.request.VaccinablePopulationWebRequest;
import com.example.mvvm_architecture_test.web.response.IResponse;
import com.example.mvvm_architecture_test.web.response.StorageGetResponse;
import com.example.mvvm_architecture_test.web.response.StorageSaveResponse;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class VaccinablePopulationNotWebRepository implements INotWebRepository<List<VaccinablePopulation>>{

    private final ICallbackResponse<List<VaccinablePopulation>, NotWebException> storageGetCallbackResponse;
    private final ICallbackResponse<Boolean, NotWebException> storageSaveCallbackResponse;
    private final IResponse<List<VaccinablePopulation>, NotWebException> storageGetResponse;
    private final IResponse<Boolean, NotWebException> storageSaveResponse;
    private final NotWebRequest<List<VaccinablePopulation>, Boolean, URL> storageRequest;

    public VaccinablePopulationNotWebRepository(Application application) {
        storageRequest = new VaccinablePopulationStorageRequest(application.getApplicationContext());
        storageGetResponse = new StorageGetResponse<>();
        storageSaveResponse = new StorageSaveResponse();
        storageGetCallbackResponse = new StorageGetCallbackResponse<>(application.getApplicationContext());
        storageSaveCallbackResponse = new StorageSaveCallbackResponse(application.getApplicationContext());
    }

    @Override
    public IResponse<List<VaccinablePopulation>, NotWebException> getAll() {
        List<VaccinablePopulation> vaccinablePopulationList = storageRequest.getAll();
        prepareStorageGetResponse(vaccinablePopulationList);
        return storageGetResponse;
    }

    @Override
    public void saveBackup() {
        try {
            boolean backupSuccess = storageRequest.save(new URL(VaccinablePopulationWebRequest.getUrl()));
            prepareStorageSaveResponse(backupSuccess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareStorageGetResponse(List<VaccinablePopulation> vaccinablePopulationList){
        if (vaccinablePopulationList.isEmpty()) storageGetResponse.setError(new ReadStorageException());
        else storageGetResponse.setSuccess(vaccinablePopulationList);
        storageGetCallbackResponse.onComplete(storageGetResponse);
    }

    private void prepareStorageSaveResponse(Boolean backupSuccess){
        if (!backupSuccess) storageSaveResponse.setError(new WriteStorageException());
        else storageSaveResponse.setSuccess(true);
        storageSaveCallbackResponse.onComplete(storageSaveResponse);
    }
}
