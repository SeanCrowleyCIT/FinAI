package com.example.finai.ui.Officer_Info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Officer_Info_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Officer_Info_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to Fin-AI");
    }

    public LiveData<String> getText() {
        return mText;
    }
}