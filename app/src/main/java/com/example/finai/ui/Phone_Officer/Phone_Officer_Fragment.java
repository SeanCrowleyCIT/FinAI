package com.example.finai.ui.Phone_Officer;

import android.content.Intent;
import android.net.Uri;
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
import com.example.finai.ui.Phone_Officer.Phone_Officer_Fragment;


public class Phone_Officer_Fragment extends Fragment {

    private Phone_Officer_ViewModel Phone_Officer_ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Phone_Officer_ViewModel =
                new ViewModelProvider(this).get(Phone_Officer_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_phone_officer, container, false);

        Uri number = Uri.parse("tel:5551234");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);

        //final TextView textView = root.findViewById(R.id.Home_Message);
        Phone_Officer_ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}