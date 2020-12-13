package com.example.finai.ui.House_Quotation;

import android.content.Context;
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

import com.example.finai.R;
import com.example.finai.ui.House_Quotation.House_Quotation_ViewModel;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class House_Quotation_Fragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = inflater.getContext();
        House_Quotation_ViewModel houseQuotationModel = new ViewModelProvider(this).get(House_Quotation_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_house_quotation, container, false);
        //final TextView textView = root.findViewById(R.id.Home_Message);

        //db values
        View num_bedrooms = root.findViewById(R.id.bedrooms); //int
        View num_bathrooms = root.findViewById(R.id.bathrooms); //float
        View num_floors = root.findViewById(R.id.numberofFloorsForm); //float
        View sqft_living_past = root.findViewById(R.id.SqftAbovePastForm); //int
        View sqft_lot_past = root.findViewById(R.id.SqftLotPastForm); //int
        View sqft_living_present = root.findViewById(R.id.sqftLivingForm); //int
        View sqft_lot_present = root.findViewById(R.id.sqftLot); //int
        View sqft_above = root.findViewById(R.id.SqftAboveForm); //int
        View sqft_basement = root.findViewById(R.id.SqftBasementForm); //int
        View zip_code = root.findViewById(R.id.zipcodeForm); //int
        View latitude = root.findViewById(R.id.LatitudeForm); //float
        View longitude = root.findViewById(R.id.LongitudeForm); //float
        View waterfront = root.findViewById(R.id.WaterfrontGroup); //select
        View view = root.findViewById(R.id.ViewsForm); //select
        View condition = root.findViewById(R.id.ConditionForm); //select
        View grade = root.findViewById(R.id.GradeForm); //select
        View year_built = root.findViewById(R.id.yearBuiltForm); //Int
        View year_renovated = root.findViewById(R.id.yearRenovatedForm); //int
        //

        Button submit = root.findViewById(R.id.submit);
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage(R.string.houseQuotatationdialogue_title).setTitle(R.string.houseQuotatationdialogue_title);
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle(R.string.houseQuotatationdialogue_title);
                alert.show();
            }
        });

        houseQuotationModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}