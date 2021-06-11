package com.example.profileusers.profile;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class UserProfileViewModel extends AndroidViewModel {

    private final MutableLiveData<Event> selectPhoto = new MutableLiveData<>();
    private final MutableLiveData<Event> showPermission = new MutableLiveData<>();
    private final MutableLiveData<Uri> photo = new MutableLiveData<>();

    public LiveData<Uri> getPhoto() {
        return photo;
    }


    public LiveData<Event> getShowPermission() {
        return showPermission;
    }


    public LiveData<Event> getSelectPhoto() {
        return selectPhoto;
    }

    public UserProfileViewModel(Application application) {
        super(application);
    }


    public void onSelectPhotoClicked() {
        //TODO
        if (isPermission())
            selectPhoto.setValue(new Event(new Bundle()));
        else
            showPermission.setValue(new Event(new Bundle()));

    }

    public void loadInImageView(Uri uriPhoto) {
        photo.setValue(uriPhoto);
    }

    private boolean isPermission() {

        return ActivityCompat.checkSelfPermission(
                getApplication(), Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_DENIED;
    }


}
