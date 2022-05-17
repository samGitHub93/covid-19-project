package com.example.mvvm_architecture_test.fragment.fragment_main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mvvm_architecture_test.MainActivity;
import com.example.mvvm_architecture_test.R;
import com.example.mvvm_architecture_test.enumerator.TableType;
import com.example.mvvm_architecture_test.model.PlagueDay;
import com.example.mvvm_architecture_test.recycler_view.PlagueDayAdapter;
import com.example.mvvm_architecture_test.util.ColumnUtils;
import com.example.mvvm_architecture_test.util.FragmentUtils;
import com.example.mvvm_architecture_test.view_model.PlagueDayViewModel;

import java.util.List;

public class WeekFragment extends Fragment implements DataFragment {

    private PlagueDayViewModel weekViewModel;
    private List<PlagueDay> plagueDays;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private View weeksView;
    private Observer<List<PlagueDay>> weekObserver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        weeksView = inflater.inflate(R.layout.table_fragment, container, false);
        weekViewModel = new ViewModelProvider(requireActivity()).get(PlagueDayViewModel.class);
        refreshUI();
        return weeksView;
    }

    @Override
    public PlagueDayViewModel getViewModel() {
        return weekViewModel;
    }

    @Override
    public void refreshUI() {
        weekObserver = plagueDays -> {
            this.plagueDays = plagueDays;
            if(!plagueDays.isEmpty()) updateUI();
            else MainActivity.setCurrentFragment(new ErrorFragment());
        };
        weekViewModel.getNotUpgradedData(TableType.WEEKS).observe(getViewLifecycleOwner(), weekObserver);
    }

    @Override
    public void refreshData() {
        weekObserver = plagueDays -> {
            this.plagueDays = plagueDays;
            if(!plagueDays.isEmpty()) updateUI();
            else MainActivity.setCurrentFragment(new ErrorFragment());
        };
        weekViewModel.getUpgradedData(TableType.WEEKS).observe(getViewLifecycleOwner(), weekObserver);
        MainActivity.setCurrentFragment(new WeekFragment());
    }

    private void updateUI(){
        FragmentUtils fragmentUtils = new FragmentUtils(weeksView, plagueDays.get(0));
        fragmentUtils.buildFragment(ColumnUtils.getCheckBoxes());
        LinearLayout listLayout = weeksView.findViewById(R.id.list_layout);
        swipeRefreshLayout = createSwipeRefreshLayout(getContext());
        recyclerView = createRecyclerView(getContext());
        swipeRefreshLayout.addView(recyclerView);
        listLayout.addView(swipeRefreshLayout);
    }

    private SwipeRefreshLayout createSwipeRefreshLayout(Context context){
        swipeRefreshLayout = new SwipeRefreshLayout(context);
        swipeRefreshLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        swipeRefreshLayout.setOnRefreshListener(refreshListener());
        return swipeRefreshLayout;
    }

    private RecyclerView createRecyclerView(Context context) {
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new PlagueDayAdapter(plagueDays));
        return recyclerView;
    }

    private SwipeRefreshLayout.OnRefreshListener refreshListener() {
        return () -> {
            swipeRefreshLayout.setRefreshing(false);
            refreshData();
            MainActivity.setCurrentFragment(new WeekFragment());
        };
    }
}
