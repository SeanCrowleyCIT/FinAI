package com.example.finai.ui.Loan_Suitability;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.finai.R;
import com.example.finai.ui.Loan_Suitability.Loan_Suitability_ViewModel;

public class Loan_Suitability_Fragment extends Fragment {

    private Loan_Suitability_ViewModel Loan_Suitability_ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Loan_Suitability_ViewModel =
                new ViewModelProvider(this).get(Loan_Suitability_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_loan_suitability, container, false);
        //final TextView textView = root.findViewById(R.id.Home_Message);
        Loan_Suitability_ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}