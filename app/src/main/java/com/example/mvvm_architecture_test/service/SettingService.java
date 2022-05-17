package com.example.mvvm_architecture_test.service;

import android.app.Application;
import android.content.Context;
import android.widget.CheckBox;

import com.example.mvvm_architecture_test.model.PlagueDay;
import com.example.mvvm_architecture_test.repository.INotWebRepository;
import com.example.mvvm_architecture_test.repository.PlagueDayNotWebRepository;

import java.util.ArrayList;
import java.util.List;

public class SettingService implements ISettingService<List<CheckBox>>{

    private final Context context;
    private final INotWebRepository<List<PlagueDay>> notWebRepository;

    public SettingService(Application application){
        this.context = application.getApplicationContext();
        this.notWebRepository = new PlagueDayNotWebRepository(application);
    }

    @Override
    public List<CheckBox> getSetting(){
        List<String> columnStrings = ((PlagueDayNotWebRepository) notWebRepository).getSetting().getSuccess();
        return stringToCheckBox(columnStrings);
    }

    @Override
    public void saveSetting(List<CheckBox> checkBoxes){
        List<String> columnStrings = checkBoxToString(checkBoxes);
        ((PlagueDayNotWebRepository) notWebRepository).saveSetting(columnStrings);
    }

    private List<CheckBox> stringToCheckBox(List<String> columnStrings){
        List<CheckBox> checkBoxes = new ArrayList<>();
        for(String columnString : columnStrings){
            CheckBox checkBox = new CheckBox(context);
            checkBox.setChecked(columnString.equals("true"));
            checkBoxes.add(checkBox);
        }
        return checkBoxes;
    }

    private List<String> checkBoxToString(List<CheckBox> checkBoxes){
        List<String> columnStrings = new ArrayList<>();
        for(CheckBox checkBox : checkBoxes){
            if(checkBox.isChecked()){
                String string = "true";
                columnStrings.add(string);
            }else{
                String string = "false";
                columnStrings.add(string);
            }
        }
        return columnStrings;
    }
}
