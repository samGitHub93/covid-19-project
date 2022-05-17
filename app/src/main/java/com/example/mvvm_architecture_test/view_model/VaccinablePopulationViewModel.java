package com.example.mvvm_architecture_test.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_architecture_test.enumerator.VaccineModelType;
import com.example.mvvm_architecture_test.model.VaccinablePopulation;
import com.example.mvvm_architecture_test.service.IService;
import com.example.mvvm_architecture_test.service.VaccinablePopulationService;

import java.util.List;

public class VaccinablePopulationViewModel extends AndroidViewModel implements IViewModel<List<VaccinablePopulation>, VaccineModelType> {

    private final IService<List<VaccinablePopulation>> service;
    private LiveData<List<VaccinablePopulation>> liveData;

    public VaccinablePopulationViewModel(@NonNull Application application) {
        super(application);
        this.service = new VaccinablePopulationService(application);
    }

    @Override
    public LiveData<List<VaccinablePopulation>> getUpgradedData(VaccineModelType vaccineModelType) {
        System.out.println("Up");
        service.updateData();
        MutableLiveData<List<VaccinablePopulation>> mutableVaccinablePopulation = service.getData();
        liveData = new MutableLiveData<>();
        liveData = mutableVaccinablePopulation;
        return liveData;
    }

    @Override
    public LiveData<List<VaccinablePopulation>> getNotUpgradedData(VaccineModelType vaccineModelType) {
        System.out.println("Not up");
        MutableLiveData<List<VaccinablePopulation>> mutableVaccinablePopulation = service.getData();
        liveData = new MutableLiveData<>();
        liveData = mutableVaccinablePopulation;
        return liveData;
    }
}
