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
    private MutableLiveData<String> photoPathStringToCroup = new MutableLiveData<>();

    public LiveData<String> getPhotoPathStringToCroup() {
        return photoPathStringToCroup;
    }
    public void setPhotoPathStringToCroup(String photoPath){
        photoPathStringToCroup.setValue(photoPath);
    }
}
