package com.example.profileusers.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CroupImageViewModel extends AndroidViewModel {

    //конструктор
    public CroupImageViewModel(@NonNull Application application) {
        super(application);
    }

    //фотография path
    private MutableLiveData<String> photoPathString = new MutableLiveData<>();

    public LiveData<String> getPhotoPathString() {
        return photoPathString;
    }
    public void setPhotoPathString(String photoPath){
        photoPathString.setValue(photoPath);
    }
}
