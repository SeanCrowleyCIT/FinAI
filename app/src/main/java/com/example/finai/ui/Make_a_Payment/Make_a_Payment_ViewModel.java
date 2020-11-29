package com.example.finai.ui.Make_a_Payment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Make_a_Payment_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Make_a_Payment_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to Fin-AI");
    }

    public LiveData<String> getText() {
        return mText;
    }
}