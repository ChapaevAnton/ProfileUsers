package com.example.profileusers.profile.croupimage;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.profileusers.profile.utils.Event;

public class CroupImageViewModel extends AndroidViewModel {

    //конструктор
    public CroupImageViewModel(@NonNull Application application) {
        super(application);
    }

    //rotate
    private MutableLiveData<Event> resultEventRotatePhoto = new MutableLiveData<>();
    //croup
    private MutableLiveData<Event> resultEventCroupPhoto = new MutableLiveData<>();

    //фотография path
    private MutableLiveData<String> photoPathStringToCroup = new MutableLiveData<>();

    public LiveData<String> getPhotoPathStringToCroup() {
        return photoPathStringToCroup;
    }
    public void setPhotoPathStringToCroup(String photoPath){
        photoPathStringToCroup.setValue(photoPath);
    }

    public LiveData<Event> getResultEventRotatePhoto() {
        return resultEventRotatePhoto;
    }

    public LiveData<Event> getResultEventCroupPhoto() {
        return resultEventCroupPhoto;
    }


    public void onRotatePhotoClicked(){
        resultEventRotatePhoto.setValue(new Event(new Bundle()));
    }

    public void onCroupPhotoClicked(){
        resultEventRotatePhoto.setValue(new Event(new Bundle()));
    }


}
