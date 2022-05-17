package com.example.mvvm_architecture_test.fragment.fragment_main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_architecture_test.R;
import com.example.mvvm_architecture_test.view_model.ErrorViewModel;
import com.example.mvvm_architecture_test.view_model.IViewModel;

public class ErrorFragment extends Fragment implements IFragment {

    private ErrorViewModel errorViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View errorView = inflater.inflate(R.layout.error, container, false);
        errorViewModel = new ViewModelProvider(requireActivity()).get(ErrorViewModel.class);
        return errorView;
    }

    @Override
    public IViewModel getViewModel() {
        return errorViewModel;
    }
}
