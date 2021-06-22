package com.example.profileusers.profile;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.profileusers.profile.utils.Event;

public class UserProfileViewModel extends AndroidViewModel {

    private final MutableLiveData<Event> showPermissionWrite = new MutableLiveData<>();
    private final MutableLiveData<Event> showPhotoGallery = new MutableLiveData<>();

    private final MutableLiveData<String> photoPathStringToProfile = new MutableLiveData<>();

    //галерея фотографий
    public LiveData<Event> getShowPhotoGallery() {
        return showPhotoGallery;
    }

    //фотография path
    public LiveData<String> getPhotoPathStringToProfile() {
        return photoPathStringToProfile;
    }

    public void setPhotoPathStringToProfile(String photoPath) {
        photoPathStringToProfile.setValue(photoPath);
    }

    //разрешение

    public LiveData<Event> getShowPermissionWrite() {
        return showPermissionWrite;
    }

    public UserProfileViewModel(Application application) {
        super(application);
    }

    public void onSelectPhotoGalleryClicked() {

        if (isPermissionWrite()) {
            showPhotoGallery.setValue(new Event(new Bundle()));

        } else showPermissionWrite.setValue(new Event(new Bundle()));

    }


    private boolean isPermissionWrite() {
        return ActivityCompat.checkSelfPermission(
                getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_DENIED;
    }

}
