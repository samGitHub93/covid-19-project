package com.example.mvvm_architecture_test.util;

import android.graphics.Typeface;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mvvm_architecture_test.recycler_view.PlagueDayHolder;
import com.example.mvvm_architecture_test.model.PlagueDay;
import com.example.mvvm_architecture_test.painter.Painter;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.List;

public class AdapterUtils {

    private TextView dateItemHolder, newContagionsItemHolder, contagionsVariationItemHolder,
            contagionsItemHolder, homeIsolationItemHolder, hospitalizedWithSymptomsItemHolder,
            intensiveCareItemHolder, totalHospitalizedItemHolder, deathsItemHolder, healedItemHolder,
            buffersItemHolder, totalPositiveMolecularTestsItemHolder, totalPositiveRapidTestsItemHolder,
            totalMolecularTestsItemHolder, totalRapidTestsItemHolder;

    public void buildAdapter(PlagueDayHolder holder, PlagueDay plagueDay){
        initializeItems(holder);
        setItemsText(plagueDay);
        List<TextView> row = buildRow();
        paintRow(row, plagueDay);
    }

    public void columnFilter(PlagueDayHolder plagueDayHolder, List<CheckBox> checkBoxes){
        if(checkBoxes!=null && !checkBoxes.isEmpty()) {
            if (!checkBoxes.get(1).isChecked())
                plagueDayHolder.newContagions.setVisibility(View.GONE);
            if (!checkBoxes.get(2).isChecked())
                plagueDayHolder.contagionsVariation.setVisibility(View.GONE);
            if (!checkBoxes.get(3).isChecked())
                plagueDayHolder.currentContagions.setVisibility(View.GONE);
            if (!checkBoxes.get(4).isChecked())
                plagueDayHolder.deaths.setVisibility(View.GONE);
            if (!checkBoxes.get(5).isChecked())
                plagueDayHolder.hospitalizedWithSymptoms.setVisibility(View.GONE);
            if (!checkBoxes.get(6).isChecked())
                plagueDayHolder.intensiveCare.setVisibility(View.GONE);
            if (!checkBoxes.get(7).isChecked())
                plagueDayHolder.totalHospitalized.setVisibility(View.GONE);
            if (!checkBoxes.get(8).isChecked())
                plagueDayHolder.homeIsolation.setVisibility(View.GONE);
            if (!checkBoxes.get(9).isChecked())
                plagueDayHolder.healed.setVisibility(View.GONE);
            if (!checkBoxes.get(10).isChecked())
                plagueDayHolder.buffers.setVisibility(View.GONE);
            if (!checkBoxes.get(11).isChecked())
                plagueDayHolder.totalPositiveMolecularTests.setVisibility(View.GONE);
            if (!checkBoxes.get(12).isChecked())
                plagueDayHolder.totalPositiveRapidTests.setVisibility(View.GONE);
            if (!checkBoxes.get(13).isChecked())
                plagueDayHolder.totalMolecularTests.setVisibility(View.GONE);
            if (!checkBoxes.get(14).isChecked())
                plagueDayHolder.totalRapidTests.setVisibility(View.GONE);
        }
    }

    private void initializeItems(@NonNull PlagueDayHolder holder){
        dateItemHolder = holder.date;
        newContagionsItemHolder = holder.newContagions;
        contagionsVariationItemHolder = holder.contagionsVariation;
        contagionsItemHolder = holder.currentContagions;
        homeIsolationItemHolder = holder.homeIsolation;
        hospitalizedWithSymptomsItemHolder = holder.hospitalizedWithSymptoms;
        intensiveCareItemHolder = holder.intensiveCare;
        totalHospitalizedItemHolder = holder.totalHospitalized;
        deathsItemHolder = holder.deaths;
        healedItemHolder = holder.healed;
        buffersItemHolder = holder.buffers;
        totalPositiveMolecularTestsItemHolder = holder.totalPositiveMolecularTests;
        totalPositiveRapidTestsItemHolder = holder.totalPositiveRapidTests;
        totalMolecularTestsItemHolder = holder.totalMolecularTests;
        totalRapidTestsItemHolder = holder.totalRapidTests;
    }

    private void setItemsText(PlagueDay dayOfPlague){
        dateItemHolder.setText(dayOfPlague.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        dateItemHolder.setTypeface(null, Typeface.BOLD);
        newContagionsItemHolder.setText(String.valueOf(dayOfPlague.getNewContagions()));
        contagionsVariationItemHolder.setText(String.valueOf(dayOfPlague.getContagionsVariation()));
        contagionsItemHolder.setText(String.valueOf(dayOfPlague.getContagions()));
        homeIsolationItemHolder.setText(String.valueOf(dayOfPlague.getHomeIsolation()));
        hospitalizedWithSymptomsItemHolder.setText(String.valueOf(dayOfPlague.getHospitalizedWithSymptoms()));
        intensiveCareItemHolder.setText(String.valueOf(dayOfPlague.getIntensiveCare()));
        totalHospitalizedItemHolder.setText(String.valueOf(dayOfPlague.getTotalHospitalized()));
        deathsItemHolder.setText(String.valueOf(dayOfPlague.getDeaths()));
        healedItemHolder.setText(String.valueOf(dayOfPlague.getHealed()));
        buffersItemHolder.setText(String.valueOf(dayOfPlague.getBuffers()));
        totalPositiveMolecularTestsItemHolder.setText(String.valueOf(dayOfPlague.getTotalPositiveMolecularTest()));
        totalPositiveRapidTestsItemHolder.setText(String.valueOf(dayOfPlague.getTotalPositiveRapidTest()));
        totalMolecularTestsItemHolder.setText(String.valueOf(dayOfPlague.getMolecularTestBuffers()));
        totalRapidTestsItemHolder.setText(String.valueOf(dayOfPlague.getRapidTestBuffers()));
    }

    private List<TextView> buildRow() {
        return Arrays.asList(dateItemHolder, newContagionsItemHolder, contagionsVariationItemHolder, contagionsItemHolder,
                homeIsolationItemHolder, hospitalizedWithSymptomsItemHolder, intensiveCareItemHolder, totalHospitalizedItemHolder,
                deathsItemHolder, healedItemHolder, buffersItemHolder, totalPositiveMolecularTestsItemHolder, totalPositiveRapidTestsItemHolder,
                totalMolecularTestsItemHolder, totalRapidTestsItemHolder);
    }

    private void paintRow(List<TextView> row, PlagueDay day){
        for(TextView textView : row) Painter.paintTextView(textView, day);
    }
}
