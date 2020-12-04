package com.example.finai.ui.Phone_Officer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Phone_Officer_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Phone_Officer_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to Fin-AI");
    }

    public LiveData<String> getText() {
        return mText;
    }
}