package com.example.mvvm_architecture_test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvm_architecture_test.enumerator.AgeRange;
import com.example.mvvm_architecture_test.view_model_getter.VaccineViewModelGetter;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.List;

public class VaccineActivity extends AppCompatActivity {

    private VaccineViewModelGetter getter;

    @SuppressLint({"DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getter = new VaccineViewModelGetter(getApplication());
//        if(!getter.areListsEmpty()) {
            getDate();
            managePerAgeGraphic();
//        }else{
//            this.finish();
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    private void getDate(){
        TextView date = findViewById(R.id.vaccine_date);
        date.setText(" " + getter.getVaccineDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    private void managePerAgeGraphic(){
        TextView textView_05_1 = findViewById(R.id._05_1);
        TextView textView_05_2 = findViewById(R.id._05_2);
        TextView textView_12_1 = findViewById(R.id._12_1);
        TextView textView_12_2 = findViewById(R.id._12_2);
        TextView textView_20_1 = findViewById(R.id._20_1);
        TextView textView_20_2 = findViewById(R.id._20_2);
        TextView textView_30_1 = findViewById(R.id._30_1);
        TextView textView_30_2 = findViewById(R.id._30_2);
        TextView textView_40_1 = findViewById(R.id._40_1);
        TextView textView_40_2 = findViewById(R.id._40_2);
        TextView textView_50_1 = findViewById(R.id._50_1);
        TextView textView_50_2 = findViewById(R.id._50_2);
        TextView textView_60_1 = findViewById(R.id._60_1);
        TextView textView_60_2 = findViewById(R.id._60_2);
        TextView textView_70_1 = findViewById(R.id._70_1);
        TextView textView_70_2 = findViewById(R.id._70_2);
        TextView textView_80_1 = findViewById(R.id._80_1);
        TextView textView_80_2 = findViewById(R.id._80_2);
        makePerAgeGraphic(textView_05_1,textView_05_2,AgeRange._05_11);
        makePerAgeGraphic(textView_12_1,textView_12_2,AgeRange._12_19);
        makePerAgeGraphic(textView_20_1,textView_20_2,AgeRange._20_29);
        makePerAgeGraphic(textView_30_1,textView_30_2,AgeRange._30_39);
        makePerAgeGraphic(textView_40_1,textView_40_2,AgeRange._40_49);
        makePerAgeGraphic(textView_50_1,textView_50_2,AgeRange._50_59);
        makePerAgeGraphic(textView_60_1,textView_60_2,AgeRange._60_69);
        makePerAgeGraphic(textView_70_1,textView_70_2,AgeRange._70_79);
        makePerAgeGraphic(textView_80_1,textView_80_2,AgeRange._80_89);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void makePerAgeGraphic(TextView textView1, TextView textView2, AgeRange ageRange){
        int total = getter.getVaccinablePopulationPerAge(ageRange);
        List<Integer> dosesNumber = manageDosesNumber(ageRange);
        List<Double> dosesPercentage = manageDosesPercentage(dosesNumber.get(0), dosesNumber.get(1), total);
        if(ageRange==AgeRange._05_11){
            textView1.setText(String.format("%.2f", dosesPercentage.get(0)) + " %");
            textView1.setTextSize(10);
        }
        textView2.setText(String.format("%.2f", dosesPercentage.get(1)) + " %");
        textView2.setTextSize(10);
        float width1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, (float) (dosesPercentage.get(0)*2), getResources().getDisplayMetrics());
        float width2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, (float) (dosesPercentage.get(1)*2), getResources().getDisplayMetrics());
        float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, (float) 20, getResources().getDisplayMetrics());
        textView1.setLayoutParams(new RelativeLayout.LayoutParams(Math.round(width1), Math.round(height)));
        textView2.setLayoutParams(new RelativeLayout.LayoutParams(Math.round(width2), Math.round(height)));
    }

    private List<Integer> manageDosesNumber(AgeRange ageRange){
        int firstDose;
        int boosterDose;
        if(ageRange==AgeRange._80_89){
            firstDose = getter.getVaccinatedPerAge(ageRange).getFirstDose() + getter.getVaccinatedPerAge(AgeRange._OVER_90).getFirstDose();
            boosterDose = getter.getVaccinatedPerAge(ageRange).getBoosterDose() + getter.getVaccinatedPerAge(AgeRange._OVER_90).getBoosterDose();
        }else {
            firstDose = getter.getVaccinatedPerAge(ageRange).getFirstDose();
            boosterDose = getter.getVaccinatedPerAge(ageRange).getBoosterDose();
        }
        return Arrays.asList(firstDose,boosterDose);
    }

    private List<Double> manageDosesPercentage(int firstDose, int boosterDose, int total){
        double firstDosePercentage = (100.0*firstDose)/total;
        double boosterPercentage = (100.0*boosterDose)/total;
        return Arrays.asList(firstDosePercentage, boosterPercentage);
    }
}
