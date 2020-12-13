package com.example.finai.ui.Loan_Suitability;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.finai.R;

import android.app.AlertDialog;

public class Loan_Suitability_Fragment extends Fragment {

    private Loan_Suitability_ViewModel Loan_Suitability_ViewModel;

    boolean loanSuitabilityResult;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = inflater.getContext();
        Loan_Suitability_ViewModel = new ViewModelProvider(this).get(Loan_Suitability_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_loan_suitability, container, false);
        //final TextView textView = root.findViewById(R.id.Home_Message);

        //db values
        View gender = root.findViewById(R.id.GenderGroup);
        //TODO View married = root.findViewById(R.id.)
        View dependents = root.findViewById(R.id.Dependents);
        View education = root.findViewById(R.id.EducationGroup);
        View Self_Employed = root.findViewById(R.id.SelfEmployedGroup);
        View ApplicantIncome = root.findViewById(R.id.ApplicantIncome);
        View CoapplicantIncome = root.findViewById(R.id.CoApplicantIncome);
        View LoanAmount = root.findViewById(R.id.LoanAmount);
        View Loan_Amount_Term = root.findViewById(R.id.LoanAmountTerm);
        View Credit_History = root.findViewById(R.id.CreditHistoryGroup);
        View Property_Area = root.findViewById(R.id.PropertyAreaGroup);
        //

        Button submit = root.findViewById(R.id.SubmitLoanSuitability);
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loanSuitabilityResult) { builder.setMessage(R.string.loanSuitabilitydialogue_messageYes).setTitle(R.string.loanSuitabilitydialogue_title);}
                else if (!loanSuitabilityResult) {builder.setMessage(R.string.loanSuitabilitydialogue_messageNo).setTitle(R.string.loanSuitabilitydialogue_title);}
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle(R.string.loanSuitabilitydialogue_title);
                alert.show();
            }

        });

        Loan_Suitability_ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}