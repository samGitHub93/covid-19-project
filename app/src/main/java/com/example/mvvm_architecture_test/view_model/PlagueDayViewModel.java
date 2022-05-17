package com.example.mvvm_architecture_test.view_model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_architecture_test.enumerator.TableType;
import com.example.mvvm_architecture_test.model.PlagueDay;
import com.example.mvvm_architecture_test.service.PlagueDayService;
import com.example.mvvm_architecture_test.service.IService;
import com.example.mvvm_architecture_test.util.TableDataUtils;

import java.util.ArrayList;
import java.util.List;

public class PlagueDayViewModel extends AndroidViewModel implements IViewModel<List<PlagueDay>,TableType> {

    private final IService<List<PlagueDay>> plagueDayService;
    private LiveData<List<PlagueDay>> liveData;

    public PlagueDayViewModel(Application application){
        super(application);
        this.plagueDayService = new PlagueDayService(application);
    }

    @Override
    public LiveData<List<PlagueDay>> getUpgradedData(TableType tableType) {
        System.out.println("Up");
        plagueDayService.updateData();
        MutableLiveData<List<PlagueDay>> mutableLiveData = plagueDayService.getData();
        List<PlagueDay> data = tableDataMapper(mutableLiveData, tableType);
        mutableLiveData.setValue(data);
        liveData = new MutableLiveData<>();
        liveData = mutableLiveData;
        return liveData;
    }

    @Override
    public LiveData<List<PlagueDay>> getNotUpgradedData(TableType tableType) {
        System.out.println("Not Up");
        if(liveData == null || liveData.getValue() == null || liveData.getValue().isEmpty()) plagueDayService.updateData();
        MutableLiveData<List<PlagueDay>> mutableLiveData = plagueDayService.getData();
        List<PlagueDay> data = tableDataMapper(mutableLiveData, tableType);
        mutableLiveData.setValue(data);
        liveData = new MutableLiveData<>();
        liveData = mutableLiveData;
        return liveData;
    }

    private List<PlagueDay> tableDataMapper(LiveData<List<PlagueDay>> mutableLiveData, TableType tableType){
        List<PlagueDay> list = new ArrayList<>();
        switch (tableType){
            case DAYS:
                list = TableDataUtils.toDays(mutableLiveData.getValue());
                break;
            case WEEKS:
                list = TableDataUtils.toWeeks(mutableLiveData.getValue());
                break;
            case MONTHS:
                list = TableDataUtils.toMonths(mutableLiveData.getValue());
                break;
        }
        return list;
    }
}
