package com.example.finai.ui.Loan_Suitability;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Loan_Suitability_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Loan_Suitability_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to Fin-AI");
    }

    public LiveData<String> getText() {
        return mText;
    }
}