package com.example.mvvm_architecture_test.repository;

import android.app.Application;

import com.example.mvvm_architecture_test.exception.NotWebException;
import com.example.mvvm_architecture_test.exception.ReadStorageException;
import com.example.mvvm_architecture_test.exception.WriteStorageException;
import com.example.mvvm_architecture_test.model.VaccinatedPerAge;
import com.example.mvvm_architecture_test.web.callback_response.ICallbackResponse;
import com.example.mvvm_architecture_test.web.callback_response.StorageGetCallbackResponse;
import com.example.mvvm_architecture_test.web.callback_response.StorageSaveCallbackResponse;
import com.example.mvvm_architecture_test.web.request.NotWebRequest;
import com.example.mvvm_architecture_test.web.request.VaccinatedPerAgeStorageRequest;
import com.example.mvvm_architecture_test.web.request.VaccinatedPerAgeWebRequest;
import com.example.mvvm_architecture_test.web.response.IResponse;
import com.example.mvvm_architecture_test.web.response.StorageGetResponse;
import com.example.mvvm_architecture_test.web.response.StorageSaveResponse;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class VaccinatedPerAgeNotWebRepository implements INotWebRepository<List<VaccinatedPerAge>>{

    private final ICallbackResponse<List<VaccinatedPerAge>, NotWebException> storageGetCallbackResponse;
    private final ICallbackResponse<Boolean, NotWebException> storageSaveCallbackResponse;
    private final IResponse<List<VaccinatedPerAge>, NotWebException> storageGetResponse;
    private final IResponse<Boolean, NotWebException> storageSaveResponse;
    private final NotWebRequest<List<VaccinatedPerAge>, Boolean, URL> storageRequest;

    public VaccinatedPerAgeNotWebRepository(Application application) {
        storageRequest = new VaccinatedPerAgeStorageRequest(application.getApplicationContext());
        storageGetResponse = new StorageGetResponse<>();
        storageSaveResponse = new StorageSaveResponse();
        storageGetCallbackResponse = new StorageGetCallbackResponse<>(application.getApplicationContext());
        storageSaveCallbackResponse = new StorageSaveCallbackResponse(application.getApplicationContext());
    }

    @Override
    public IResponse<List<VaccinatedPerAge>, NotWebException> getAll() {
        List<VaccinatedPerAge> vaccinatedPerAgeList = storageRequest.getAll();
        prepareStorageGetResponse(vaccinatedPerAgeList);
        return storageGetResponse;
    }

    @Override
    public void saveBackup() {
        try {
            boolean backupSuccess = storageRequest.save(new URL(VaccinatedPerAgeWebRequest.getUrl()));
            prepareStorageSaveResponse(backupSuccess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareStorageGetResponse(List<VaccinatedPerAge> vaccinatedPerAgeList){
        if (vaccinatedPerAgeList.isEmpty()) storageGetResponse.setError(new ReadStorageException());
        else storageGetResponse.setSuccess(vaccinatedPerAgeList);
        storageGetCallbackResponse.onComplete(storageGetResponse);
    }

    private void prepareStorageSaveResponse(Boolean backupSuccess){
        if (!backupSuccess) storageSaveResponse.setError(new WriteStorageException());
        else storageSaveResponse.setSuccess(true);
        storageSaveCallbackResponse.onComplete(storageSaveResponse);
    }
}
