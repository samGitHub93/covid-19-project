package com.example.mvvm_architecture_test.repository;

import android.app.Application;

import com.example.mvvm_architecture_test.exception.NotWebException;
import com.example.mvvm_architecture_test.exception.ReadStorageException;
import com.example.mvvm_architecture_test.exception.WriteStorageException;
import com.example.mvvm_architecture_test.model.PlagueDay;
import com.example.mvvm_architecture_test.web.callback_response.ICallbackResponse;
import com.example.mvvm_architecture_test.web.callback_response.SettingGetCallbackResponse;
import com.example.mvvm_architecture_test.web.callback_response.SettingSaveCallbackResponse;
import com.example.mvvm_architecture_test.web.callback_response.StorageGetCallbackResponse;
import com.example.mvvm_architecture_test.web.callback_response.StorageSaveCallbackResponse;
import com.example.mvvm_architecture_test.web.request.NotWebRequest;
import com.example.mvvm_architecture_test.web.request.SettingRequest;
import com.example.mvvm_architecture_test.web.request.PlagueDayStorageRequest;
import com.example.mvvm_architecture_test.web.request.PlagueDayWebRequest;
import com.example.mvvm_architecture_test.web.response.IResponse;
import com.example.mvvm_architecture_test.web.response.SettingGetResponse;
import com.example.mvvm_architecture_test.web.response.SettingSaveResponse;
import com.example.mvvm_architecture_test.web.response.StorageGetResponse;
import com.example.mvvm_architecture_test.web.response.StorageSaveResponse;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class PlagueDayNotWebRepository implements INotWebRepository<List<PlagueDay>> {

    private final ICallbackResponse<List<PlagueDay>, NotWebException> storageGetCallbackResponse;
    private final ICallbackResponse<Boolean, NotWebException> storageSaveCallbackResponse;
    private final ICallbackResponse<List<String>, NotWebException> settingGetCallbackResponse;
    private final ICallbackResponse<Boolean, NotWebException> settingSaveCallbackResponse;
    private final IResponse<List<PlagueDay>, NotWebException> storageGetResponse;
    private final IResponse<Boolean, NotWebException> storageSaveResponse;
    private final IResponse<List<String>, NotWebException> settingGetResponse;
    private final IResponse<Boolean, NotWebException> settingSaveResponse;
    private final NotWebRequest<List<PlagueDay>, Boolean, URL> storageRequest;
    private final NotWebRequest<List<String>, Boolean, List<String>> settingRequest;

    public PlagueDayNotWebRepository(Application application){
        storageRequest = new PlagueDayStorageRequest(application.getApplicationContext());
        settingRequest = new SettingRequest(application.getApplicationContext());
        storageGetResponse = new StorageGetResponse<>();
        settingGetResponse = new SettingGetResponse();
        storageSaveResponse = new StorageSaveResponse();
        settingSaveResponse = new SettingSaveResponse();
        settingGetCallbackResponse = new SettingGetCallbackResponse(application.getApplicationContext());
        settingSaveCallbackResponse = new SettingSaveCallbackResponse(application.getApplicationContext());
        storageGetCallbackResponse = new StorageGetCallbackResponse<>(application.getApplicationContext());
        storageSaveCallbackResponse = new StorageSaveCallbackResponse(application.getApplicationContext());
    }

    @Override
    public IResponse<List<PlagueDay>, NotWebException> getAll() {
        List<PlagueDay> plagueDayList = storageRequest.getAll();
        prepareStorageGetResponse(plagueDayList);
        return storageGetResponse;
    }

    @Override
    public void saveBackup() {
        try {
            boolean backupSuccess = storageRequest.save(new URL(PlagueDayWebRequest.getURL()));
            prepareStorageSaveResponse(backupSuccess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IResponse<List<String>, NotWebException> getSetting() {
        List<String> columns = settingRequest.getAll();
        buildGetResponse(columns);
        return settingGetResponse;
    }

    public void saveSetting(List<String> columns) {
        boolean saveResponse = settingRequest.save(columns);
        buildSaveResponse(saveResponse);
    }

    private void prepareStorageGetResponse(List<PlagueDay> plagueDayList){
        if (plagueDayList.isEmpty()) storageGetResponse.setError(new ReadStorageException());
        else storageGetResponse.setSuccess(plagueDayList);
        storageGetCallbackResponse.onComplete(storageGetResponse);
    }

    private void prepareStorageSaveResponse(Boolean backupSuccess){
        if (!backupSuccess) storageSaveResponse.setError(new WriteStorageException());
        else storageSaveResponse.setSuccess(true);
        storageSaveCallbackResponse.onComplete(storageSaveResponse);
    }

    private void buildGetResponse(List<String> columns){
        if(columns.isEmpty()) settingGetResponse.setError(new ReadStorageException());
        else settingGetResponse.setSuccess(columns);
        settingGetCallbackResponse.onComplete(settingGetResponse);
    }

    private void buildSaveResponse(Boolean saveResponse){
        if(!saveResponse) settingSaveResponse.setError(new WriteStorageException());
        else settingSaveResponse.setSuccess(true);
        settingSaveCallbackResponse.onComplete(settingSaveResponse);
    }
}
