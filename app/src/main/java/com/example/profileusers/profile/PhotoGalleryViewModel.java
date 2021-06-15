package com.example.profileusers.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.profileusers.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhotoGalleryViewModel extends AndroidViewModel {

    public PhotoGalleryViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<List<Photo>> itemsListPhoto = new MutableLiveData<>();

    Photo[] photos = {
            new Photo(R.drawable.image_item),
            new Photo(R.drawable.image_item),
            new Photo(R.drawable.image_item),
            new Photo(R.drawable.image_item),
            new Photo(R.drawable.image_item),
            new Photo(R.drawable.image_item)};

    private List<Photo> listPhoto = new ArrayList<>(Arrays.asList(photos));

    public LiveData<List<Photo>> getItemsListPhoto() {
        return itemsListPhoto;
    }


    public void loadListPhoto() {
        itemsListPhoto.setValue(listPhoto);
    }


}
