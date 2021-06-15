package com.example.profileusers.profile;

import android.app.Application;
import android.os.Environment;

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
    }

    private final PhotoGalleryModel model = new PhotoGalleryModel();
    private final MutableLiveData<List<Photo>> itemsListPhotos = new MutableLiveData<>();
    private final List<Photo> listPhotos = new ArrayList<>();

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

    public void loadListPhoto() {
        File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        if (listPhotos.isEmpty()) listPhotos.addAll(model.getListPhotos(root));
        itemsListPhotos.setValue(listPhotos);
    }

}
