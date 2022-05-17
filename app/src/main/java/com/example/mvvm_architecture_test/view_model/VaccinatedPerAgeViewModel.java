package com.example.mvvm_architecture_test.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_architecture_test.enumerator.VaccineModelType;
import com.example.mvvm_architecture_test.model.VaccinatedPerAge;
import com.example.mvvm_architecture_test.service.IService;
import com.example.mvvm_architecture_test.service.VaccinatedPerAgeService;

import java.util.List;

public class VaccinatedPerAgeViewModel extends AndroidViewModel implements IViewModel<List<VaccinatedPerAge>, VaccineModelType> {

    private final IService<List<VaccinatedPerAge>> service;
    private LiveData<List<VaccinatedPerAge>> liveData;

    public VaccinatedPerAgeViewModel(@NonNull Application application) {
        super(application);
        this.service = new VaccinatedPerAgeService(application);
    }

    @Override
    public LiveData<List<VaccinatedPerAge>> getUpgradedData(VaccineModelType vaccineModelType) {
        System.out.println("Up");
        service.updateData();
        MutableLiveData<List<VaccinatedPerAge>> mutableVaccinablePopulation = service.getData();
        liveData = new MutableLiveData<>();
        liveData = mutableVaccinablePopulation;
        return liveData;
    }

    @Override
    public LiveData<List<VaccinatedPerAge>> getNotUpgradedData(VaccineModelType vaccineModelType) {
        System.out.println("Not up");
        MutableLiveData<List<VaccinatedPerAge>> mutableVaccinablePopulation = service.getData();
        liveData = new MutableLiveData<>();
        liveData = mutableVaccinablePopulation;
        return liveData;
    }
}
