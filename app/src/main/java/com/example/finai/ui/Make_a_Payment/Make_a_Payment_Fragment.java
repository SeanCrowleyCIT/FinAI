package com.example.finai.ui.Make_a_Payment;

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
import com.example.finai.ui.Make_a_Payment.Make_a_Payment_Fragment;

public class Make_a_Payment_Fragment extends Fragment {

    private Make_a_Payment_ViewModel Make_a_Payment_ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Make_a_Payment_ViewModel =
                new ViewModelProvider(this).get(Make_a_Payment_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_make_a_payment, container, false);
        //final TextView textView = root.findViewById(R.id.Home_Message);
        Make_a_Payment_ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}