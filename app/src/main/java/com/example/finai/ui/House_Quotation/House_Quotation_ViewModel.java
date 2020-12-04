package com.example.finai.ui.House_Quotation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class House_Quotation_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public House_Quotation_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to Fin-AI");
    }

    public LiveData<String> getText() {
        return mText;
    }
}