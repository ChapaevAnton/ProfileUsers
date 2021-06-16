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

    private final MutableLiveData<Photo> itemPhoto = new MutableLiveData<>();
    private final List<Photo> listPhotos = new ArrayList<>();
    private PhotoClickListener photoClickListener;

//    Photo[] photos = {
//            new Photo(R.drawable.image_item),
//            new Photo(R.drawable.image_item),
//            new Photo(R.drawable.image_item),
//            new Photo(R.drawable.image_item),
//            new Photo(R.drawable.image_item),
//            new Photo(R.drawable.image_item)};

    public LiveData<List<Photo>> getItemsListPhotos() {
        return itemsListPhotos;
    }

    public LiveData<Photo> getItemPhoto() {
        return itemPhoto;
    }

    public void setPhotoClickListener(PhotoClickListener photoClickListener) {
        this.photoClickListener = photoClickListener;
    }

    public void onPhotoItemClicked(Photo photo) {
        photoClickListener.onPhotoItemClick(photo);
    }

    private void loadListPhoto() {
        File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.d("TEST", root.getAbsolutePath());
        listPhotos.addAll(model.getListPhotos(root));
        itemsListPhotos.setValue(listPhotos);
    }

}
