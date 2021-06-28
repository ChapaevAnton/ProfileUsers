package com.example.profileusers.profile.photogallery;

import android.app.Application;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.profileusers.profile.UserProfileFragment;
import com.example.profileusers.profile.utils.Event;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhotoGalleryViewModel extends AndroidViewModel {

    public PhotoGalleryViewModel(@NonNull Application application) {
        super(application);
        loadListPhotoGallery();
    }

    private final PhotoGalleryModel model = new PhotoGalleryModel();
    private final MutableLiveData<List<Photo>> listPhotosGallery = new MutableLiveData<>();
    private final List<Photo> listPhotos = new ArrayList<>();
    private final MutableLiveData<Event> resultEventPhotoGallery = new MutableLiveData<>();
    private final MutableLiveData<Event> resultEventCropImage = new MutableLiveData<>();

    public LiveData<List<Photo>> getListPhotosGallery() {
        return listPhotosGallery;
    }

    public LiveData<Event> getResultEventPhotoGallery() {
        return resultEventPhotoGallery;
    }

    //обрезка фото
    public LiveData<Event> getResultEventCropImage() {
        return resultEventCropImage;
    }

    public void setResultEventPhotoGallery(Photo photo) {
        Bundle result = new Bundle();
        result.putString(UserProfileFragment.PHOTO_FILE_PATH_REQUEST, photo.getPhotoFilePath());
        resultEventPhotoGallery.setValue(new Event(result));

    }

    private void loadListPhotoGallery() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            Log.d("TEST", "MEDIA_MOUNTED: OK");
            Log.d("TEST", root.getAbsolutePath());
            try {
                listPhotos.addAll(model.getListPhotos(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
            listPhotosGallery.setValue(listPhotos);
        } else {
            Log.d("TEST", "MEDIA_MOUNTED: ERR");
        }

    }

    public void setResultEventCroupImage(Photo photo) {
        Bundle result = new Bundle();
        result.putString(UserProfileFragment.PHOTO_FILE_PATH_REQUEST, photo.getPhotoFilePath());
        resultEventCropImage.setValue(new Event(result));
    }

}
