package com.example.mvvm_architecture_test.recycler_view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_architecture_test.R;
import com.example.mvvm_architecture_test.util.ColumnUtils;
import com.example.mvvm_architecture_test.model.PlagueDay;
import com.example.mvvm_architecture_test.util.AdapterUtils;

import java.util.List;

public class PlagueDayAdapter extends RecyclerView.Adapter<PlagueDayHolder> {

    private final List<PlagueDay> plagueDays;
    private final AdapterUtils adapterUtils;

    public PlagueDayAdapter(List<PlagueDay> plagueDays) {
        this.plagueDays = plagueDays;
        adapterUtils = new AdapterUtils();
    }

    @NonNull
    @Override
    public PlagueDayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlagueDayHolder plagueDayHolder = new PlagueDayHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false));
        adapterUtils.columnFilter(plagueDayHolder, ColumnUtils.getCheckBoxes());
        return  plagueDayHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlagueDayHolder holder, int position) {
        PlagueDay plagueDay = plagueDays.get(position + 1);
        adapterUtils.buildAdapter(holder, plagueDay);
    }

    @Override
    public int getItemCount() {
        return plagueDays.size() - 1;
    }
}
