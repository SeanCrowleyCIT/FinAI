package com.example.finai.ui.Contact_Us;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.finai.MapsActivity;
import com.example.finai.R;
import com.example.finai.ui.home.HomeViewModel;

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

public class Contact_Us_Fragment extends Fragment {

    private Contact_Us_ViewModel Contact_Us_ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Contact_Us_ViewModel = new ViewModelProvider(this).get(Contact_Us_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contact_us, container, false);
        //final TextView textView = root.findViewById(R.id.Home_Message);

        Button activate = root.findViewById(R.id.mapButton);
        activate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(getActivity().getBaseContext(), MapsActivity.class);
                startActivity(map);
            }
        });

        Contact_Us_ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}