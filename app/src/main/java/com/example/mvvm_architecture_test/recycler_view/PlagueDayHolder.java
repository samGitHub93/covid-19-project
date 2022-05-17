package com.example.mvvm_architecture_test.recycler_view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_architecture_test.R;

public class PlagueDayHolder extends RecyclerView.ViewHolder {

    public TextView date;
    public TextView newContagions;
    public TextView contagionsVariation;
    public TextView currentContagions;
    public TextView homeIsolation;
    public TextView hospitalizedWithSymptoms;
    public TextView intensiveCare;
    public TextView totalHospitalized;
    public TextView deaths;
    public TextView healed;
    public TextView buffers;
    public TextView totalPositiveMolecularTests;
    public TextView totalPositiveRapidTests;
    public TextView totalMolecularTests;
    public TextView totalRapidTests;

    public PlagueDayHolder(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.date);
        newContagions = itemView.findViewById(R.id.new_contagions);
        contagionsVariation = itemView.findViewById(R.id.contagions_variation);
        currentContagions = itemView.findViewById(R.id.current_contagions);
        homeIsolation = itemView.findViewById(R.id.home_isolation);
        hospitalizedWithSymptoms = itemView.findViewById(R.id.hospitalized_with_symptoms);
        intensiveCare = itemView.findViewById(R.id.intensive_care);
        totalHospitalized = itemView.findViewById(R.id.total_hospitalized);
        deaths = itemView.findViewById(R.id.deaths);
        healed = itemView.findViewById(R.id.healed);
        buffers = itemView.findViewById(R.id.buffers);
        totalPositiveMolecularTests = itemView.findViewById(R.id.total_positive_molecular_tests);
        totalPositiveRapidTests = itemView.findViewById(R.id.total_positive_rapid_tests);
        totalMolecularTests = itemView.findViewById(R.id.total_molecular_tests);
        totalRapidTests = itemView.findViewById(R.id.total_rapid_tests);
    }
}
