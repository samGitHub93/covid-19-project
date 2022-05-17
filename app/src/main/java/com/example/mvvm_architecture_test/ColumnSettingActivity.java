package com.example.mvvm_architecture_test;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvm_architecture_test.service.SettingService;
import com.example.mvvm_architecture_test.util.ColumnUtils;

import java.util.ArrayList;
import java.util.List;

public class ColumnSettingActivity extends AppCompatActivity {

    private SettingService settingService;
    private CheckBox date, newCases, casesVariation, totalCases, newDeaths,
    hospitalized, intensiveCare, totalHospitalized, homeIsolation, newHealed,
    newBuffers, totalPositiveMolecularTests, totalPositiveRapidTests,
    totalMolecularTests, totalRapidTests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column_setting);
        settingService = new SettingService(getApplication());
        getIntent().getIntExtra("tab_number", 0);
        bindCheckBoxes();
        initCheckBoxes();
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.column_setting_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }else if(item.getItemId() == R.id.save){
            ColumnUtils.setCheckBoxes(initList());
            settingService.saveSetting(ColumnUtils.getCheckBoxes());
            ColumnUtils.setChanged(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initCheckBoxes(){
        List<CheckBox> columns = settingService.getSetting();
        for(int i=0; i<columns.size(); i++){
            if(columns.get(i).isChecked()){
                switchColumnTrue(i);
            }else{
                switchColumnFalse(i);
            }
        }
    }

    private List<CheckBox> initList(){
        List<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(date);
        checkBoxes.add(newCases);
        checkBoxes.add(casesVariation);
        checkBoxes.add(totalCases);
        checkBoxes.add(newDeaths);
        checkBoxes.add(hospitalized);
        checkBoxes.add(intensiveCare);
        checkBoxes.add(totalHospitalized);
        checkBoxes.add(homeIsolation);
        checkBoxes.add(newHealed);
        checkBoxes.add(newBuffers);
        checkBoxes.add(totalPositiveMolecularTests);
        checkBoxes.add(totalPositiveRapidTests);
        checkBoxes.add(totalMolecularTests);
        checkBoxes.add(totalRapidTests);
        return checkBoxes;
    }

    private void switchColumnFalse(int i) {
        switch (i){
            case 0:
                date.setChecked(true);
                break;
            case 1:
                newCases.setChecked(false);
                break;
            case 2:
                casesVariation.setChecked(false);
                break;
            case 3:
                totalCases.setChecked(false);
                break;
            case 4:
                newDeaths.setChecked(false);
                break;
            case 5:
                hospitalized.setChecked(false);
                break;
            case 6:
                intensiveCare.setChecked(false);
                break;
            case 7:
                totalHospitalized.setChecked(false);
                break;
            case 8:
                homeIsolation.setChecked(false);
                break;
            case 9:
                newHealed.setChecked(false);
                break;
            case 10:
                newBuffers.setChecked(false);
                break;
            case 11:
                totalPositiveMolecularTests.setChecked(false);
                break;
            case 12:
                totalPositiveRapidTests.setChecked(false);
                break;
            case 13:
                totalMolecularTests.setChecked(false);
                break;
            case 14:
                totalRapidTests.setChecked(false);
                break;
        }
    }

    private void switchColumnTrue(int i) {
        switch (i){
            case 0:
                date.setChecked(true);
                break;
            case 1:
                newCases.setChecked(true);
                break;
            case 2:
                casesVariation.setChecked(true);
                break;
            case 3:
                totalCases.setChecked(true);
                break;
            case 4:
                newDeaths.setChecked(true);
                break;
            case 5:
                hospitalized.setChecked(true);
                break;
            case 6:
                intensiveCare.setChecked(true);
                break;
            case 7:
                totalHospitalized.setChecked(true);
                break;
            case 8:
                homeIsolation.setChecked(true);
                break;
            case 9:
                newHealed.setChecked(true);
                break;
            case 10:
                newBuffers.setChecked(true);
                break;
            case 11:
                totalPositiveMolecularTests.setChecked(true);
                break;
            case 12:
                totalPositiveRapidTests.setChecked(true);
                break;
            case 13:
                totalMolecularTests.setChecked(true);
                break;
            case 14:
                totalRapidTests.setChecked(true);
                break;
        }
    }

    private void bindCheckBoxes(){
        date = findViewById(R.id.date_column);
        newCases = findViewById(R.id.new_cases_column);
        casesVariation = findViewById(R.id.cases_variation_column);
        totalCases = findViewById(R.id.total_cases_column);
        newDeaths = findViewById(R.id.new_deaths_column);
        hospitalized = findViewById(R.id.hospitalized_column);
        intensiveCare = findViewById(R.id.intensive_care_column);
        totalHospitalized = findViewById(R.id.total_hospitalized_column);
        homeIsolation = findViewById(R.id.home_isolation_column);
        newHealed = findViewById(R.id.new_healed_column);
        newBuffers = findViewById(R.id.new_buffers_column);
        totalPositiveMolecularTests = findViewById(R.id.total_positive_molecular_tests_column);
        totalPositiveRapidTests = findViewById(R.id.total_positive_rapid_tests_column);
        totalMolecularTests = findViewById(R.id.total_molecular_tests_column);
        totalRapidTests = findViewById(R.id.total_rapid_tests_column);
    }
}
