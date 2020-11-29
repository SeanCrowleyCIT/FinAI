package com.example.finai.ui.Contact_Us;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Contact_Us_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Contact_Us_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to Fin-AI");
    }

    public LiveData<String> getText() {
        return mText;
    }
}