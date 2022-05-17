package com.example.mvvm_architecture_test.util;

import android.graphics.Typeface;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.mvvm_architecture_test.R;
import com.example.mvvm_architecture_test.model.PlagueDay;
import com.example.mvvm_architecture_test.painter.Painter;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;

public class FragmentUtils {

    private TextView date, newContagions, contagionsVariation,
            contagions, homeIsolation, hospitalizedWithSymptoms,
            intensiveCare, totalHospitalized, deaths, healed, buffers,
            totalPositiveMolecularTests, totalPositiveRapidTests,
            totalMolecularTests, totalRapidTests;
    private TextView newContagionsTitle, contagionsVariationTitle,
            contagionsTitle, homeIsolationTitle, hospitalizedWithSymptomsTitle,
            intensiveCareTitle, totalHospitalizedTitle, deathsTitle, healedTitle, buffersTitle,
            totalPositiveMolecularTestsTitle, totalPositiveRapidTestsTitle,
            totalMolecularTestsTitle, totalRapidTestsTitle;
    private final View view;
    private final PlagueDay today;

    public FragmentUtils(View view, PlagueDay today){
        this.view = view;
        this.today = today;
    }

    public void buildFragment(List<CheckBox> checkBoxes){
        bindTitleRow();
        bindFirstRow();
        writeFirstRowValues();
        paintFirstRow();
        if(checkBoxes!=null && !checkBoxes.isEmpty()){
            columnTitleFilter(checkBoxes);
            columnFirstRowFilter(checkBoxes);
        }
    }

    private void bindTitleRow(){
        newContagionsTitle = view.findViewById(R.id.new_contagions_title);
        contagionsVariationTitle = view.findViewById(R.id.contagions_variation_title);
        contagionsTitle = view.findViewById(R.id.contagions_title);
        homeIsolationTitle = view.findViewById(R.id.home_isolation_title);
        hospitalizedWithSymptomsTitle = view.findViewById(R.id.hospitalized_with_symptoms_title);
        intensiveCareTitle = view.findViewById(R.id.intensive_care_title);
        totalHospitalizedTitle = view.findViewById(R.id.total_hospitalized_title);
        deathsTitle = view.findViewById(R.id.deaths_title);
        healedTitle = view.findViewById(R.id.healed_title);
        buffersTitle = view.findViewById(R.id.buffers_title);
        totalPositiveMolecularTestsTitle = view.findViewById(R.id.total_positive_molecular_tests_title);
        totalPositiveRapidTestsTitle = view.findViewById(R.id.total_positive_rapid_tests_title);
        totalMolecularTestsTitle = view.findViewById(R.id.total_molecular_tests_title);
        totalRapidTestsTitle = view.findViewById(R.id.total_rapid_tests_title);
    }

    private void bindFirstRow() {
        date = view.findViewById(R.id.date);
        newContagions = view.findViewById(R.id.new_contagions);
        contagionsVariation = view.findViewById(R.id.contagions_variation);
        contagions = view.findViewById(R.id.contagions);
        homeIsolation = view.findViewById(R.id.home_isolation);
        hospitalizedWithSymptoms = view.findViewById(R.id.hospitalized_with_symptoms);
        intensiveCare = view.findViewById(R.id.intensive_care);
        totalHospitalized = view.findViewById(R.id.total_hospitalized);
        deaths = view.findViewById(R.id.deaths);
        healed = view.findViewById(R.id.healed);
        buffers = view.findViewById(R.id.buffers);
        totalPositiveMolecularTests = view.findViewById(R.id.total_positive_molecular_tests);
        totalPositiveRapidTests = view.findViewById(R.id.total_positive_rapid_tests);
        totalMolecularTests = view.findViewById(R.id.total_molecular_tests);
        totalRapidTests = view.findViewById(R.id.total_rapid_tests);
    }

    private void writeFirstRowValues(){
        date.setText(today.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        newContagions.setText(String.valueOf(today.getNewContagions()));
        contagionsVariation.setText(String.valueOf(today.getContagionsVariation()));
        contagions.setText(String.valueOf(today.getContagions()));
        homeIsolation.setText(String.valueOf(today.getHomeIsolation()));
        hospitalizedWithSymptoms.setText(String.valueOf(today.getHospitalizedWithSymptoms()));
        intensiveCare.setText(String.valueOf(today.getIntensiveCare()));
        totalHospitalized.setText(String.valueOf(today.getTotalHospitalized()));
        deaths.setText(String.valueOf(today.getDeaths()));
        healed.setText(String.valueOf(today.getHealed()));
        buffers.setText(String.valueOf(today.getBuffers()));
        totalPositiveMolecularTests.setText(String.valueOf(today.getTotalPositiveMolecularTest()));
        totalPositiveRapidTests.setText(String.valueOf(today.getTotalPositiveRapidTest()));
        totalMolecularTests.setText(String.valueOf(today.getMolecularTestBuffers()));
        totalRapidTests.setText(String.valueOf(today.getRapidTestBuffers()));
    }

    private void paintFirstRow(){
        Painter.paintTextView(date, today);
        date.setTypeface(null, Typeface.BOLD);
        Painter.paintTextView(newContagions, today);
        Painter.paintTextView(contagionsVariation, today);
        Painter.paintTextView(contagions, today);
        Painter.paintTextView(homeIsolation, today);
        Painter.paintTextView(hospitalizedWithSymptoms, today);
        Painter.paintTextView(intensiveCare, today);
        Painter.paintTextView(totalHospitalized, today);
        Painter.paintTextView(deaths, today);
        Painter.paintTextView(healed, today);
        Painter.paintTextView(buffers, today);
        Painter.paintTextView(totalPositiveMolecularTests, today);
        Painter.paintTextView(totalPositiveRapidTests, today);
        Painter.paintTextView(totalMolecularTests, today);
        Painter.paintTextView(totalRapidTests, today);
    }

    private void columnTitleFilter(List<CheckBox> checkBoxes){
        if(!checkBoxes.get(1).isChecked())
            newContagionsTitle.setVisibility(View.GONE);
        if(!checkBoxes.get(2).isChecked())
            contagionsVariationTitle.setVisibility(View.GONE);
        if(!checkBoxes.get(3).isChecked())
            contagionsTitle.setVisibility(View.GONE);
        if(!checkBoxes.get(4).isChecked())
            deathsTitle.setVisibility(View.GONE);
        if(!checkBoxes.get(5).isChecked())
            hospitalizedWithSymptomsTitle.setVisibility(View.GONE);
        if(!checkBoxes.get(6).isChecked())
            intensiveCareTitle.setVisibility(View.GONE);
        if(!checkBoxes.get(7).isChecked())
            totalHospitalizedTitle.setVisibility(View.GONE);
        if(!checkBoxes.get(8).isChecked())
            homeIsolationTitle.setVisibility(View.GONE);
        if(!checkBoxes.get(9).isChecked())
            healedTitle.setVisibility(View.GONE);
        if(!checkBoxes.get(10).isChecked())
            buffersTitle.setVisibility(View.GONE);
        if(!checkBoxes.get(11).isChecked())
            totalPositiveMolecularTestsTitle.setVisibility(View.GONE);
        if(!checkBoxes.get(12).isChecked())
            totalPositiveRapidTestsTitle.setVisibility(View.GONE);
        if(!checkBoxes.get(13).isChecked())
            totalMolecularTestsTitle.setVisibility(View.GONE);
        if(!checkBoxes.get(14).isChecked())
            totalRapidTestsTitle.setVisibility(View.GONE);
    }

    private void columnFirstRowFilter(List<CheckBox> checkBoxes){
        if(!checkBoxes.get(1).isChecked())
            newContagions.setVisibility(View.GONE);
        if(!checkBoxes.get(2).isChecked())
            contagionsVariation.setVisibility(View.GONE);
        if(!checkBoxes.get(3).isChecked())
            contagions.setVisibility(View.GONE);
        if(!checkBoxes.get(4).isChecked())
            deaths.setVisibility(View.GONE);
        if(!checkBoxes.get(5).isChecked())
            hospitalizedWithSymptoms.setVisibility(View.GONE);
        if(!checkBoxes.get(6).isChecked())
            intensiveCare.setVisibility(View.GONE);
        if(!checkBoxes.get(7).isChecked())
            totalHospitalized.setVisibility(View.GONE);
        if(!checkBoxes.get(8).isChecked())
            homeIsolation.setVisibility(View.GONE);
        if(!checkBoxes.get(9).isChecked())
            healed.setVisibility(View.GONE);
        if(!checkBoxes.get(10).isChecked())
            buffers.setVisibility(View.GONE);
        if(!checkBoxes.get(11).isChecked())
            totalPositiveMolecularTests.setVisibility(View.GONE);
        if(!checkBoxes.get(12).isChecked())
            totalPositiveRapidTests.setVisibility(View.GONE);
        if(!checkBoxes.get(13).isChecked())
            totalMolecularTests.setVisibility(View.GONE);
        if(!checkBoxes.get(14).isChecked())
            totalRapidTests.setVisibility(View.GONE);
    }
}
