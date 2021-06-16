package com.example.profileusers.profile;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PhotoGalleryViewModel extends AndroidViewModel {

    public PhotoGalleryViewModel(@NonNull Application application) {
        super(application);
        loadListPhoto();
    }

    private final PhotoGalleryModel model = new PhotoGalleryModel();
    private final MutableLiveData<List<Photo>> itemsListPhotos = new MutableLiveData<>();
    private final List<Photo> listPhotos = new ArrayList<>();


    public LiveData<List<Photo>> getItemsListPhotos() {
        return itemsListPhotos;
    }

    public void onPhotoItemClicked() {

    }

    private void loadListPhoto() {
        File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.d("TEST", root.getAbsolutePath());
        listPhotos.addAll(model.getListPhotos(root));
        itemsListPhotos.setValue(listPhotos);
    }

}
