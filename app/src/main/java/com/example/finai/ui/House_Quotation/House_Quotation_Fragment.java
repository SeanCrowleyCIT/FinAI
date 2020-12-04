package com.example.finai.ui.House_Quotation;

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
import com.example.finai.ui.House_Quotation.House_Quotation_ViewModel;

public class House_Quotation_Fragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        House_Quotation_ViewModel houseQuotationModel = new ViewModelProvider(this).get(House_Quotation_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_house_quotation, container, false);
        //final TextView textView = root.findViewById(R.id.Home_Message);
        houseQuotationModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}