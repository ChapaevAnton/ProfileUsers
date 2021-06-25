package com.example.profileusers.profile.croupimage;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.profileusers.profile.UserProfileFragment;
import com.example.profileusers.profile.utils.Event;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;

public class CroupImageViewModel extends AndroidViewModel {

    //конструктор
    public CroupImageViewModel(@NonNull Application application) {
        super(application);
    }

    //rotate
    private final MutableLiveData<Event> resultEventRotatePhoto = new MutableLiveData<>();
    //croup
    private final MutableLiveData<Event> resultEventCroupPhoto = new MutableLiveData<>();

    //фотография path
    private final MutableLiveData<String> photoPathStringToCroup = new MutableLiveData<>();

    public LiveData<String> getPhotoPathStringToCroup() {
        return photoPathStringToCroup;
    }

    public void setPhotoPathStringToCroup(String photoPath) {
        photoPathStringToCroup.setValue(photoPath);
    }

    public LiveData<Event> getResultEventRotatePhoto() {
        return resultEventRotatePhoto;
    }

    public LiveData<Event> getResultEventCroupPhoto() {
        return resultEventCroupPhoto;
    }

    public void onRotatePhotoClicked() {
        resultEventRotatePhoto.setValue(new Event(new Bundle()));
    }

    public void onCroupPhotoClicked(Bitmap photoBitmapCroup) {
        // TODO: 22.06.2021 write bitmap to sdcard
        Executors.newSingleThreadExecutor().execute(() -> {
            String photoPathCroup = writePhotoBitmapToSdCard(photoBitmapCroup);
            if (photoPathCroup != null) {
                photoPathStringToCroup.postValue(photoPathCroup);
            }
        });

    }

    public void onSavePhotoClicked() {
        Bundle result = new Bundle();
        result.putString(UserProfileFragment.PHOTO_FILE_PATH_REQUEST, photoPathStringToCroup.getValue());
        resultEventCroupPhoto.postValue(new Event(result));
    }


    private String writePhotoBitmapToSdCard(Bitmap photoBitmapCroup) {
        //TODO Никогда не возвращать NULl
        String photoPathCroup = null;
        // TODO: 22.06.2021
        //TODO Лишняя вложенность
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            Log.d("TEST", "writePhotoBitmapToSdCard: " + photoPathStringToCroup.getValue());
            String photoPathString = photoPathStringToCroup.getValue();
            Path photoPath = Paths.get(photoPathString);
            String photoFileName = getFileNoExtension(photoPath.getFileName().toString());
            File photoFile = new File(photoPath.getParent().toString(), photoFileName + ".jpg");

            try (FileOutputStream writePhotoStream = new FileOutputStream(photoFile)) {

                if (photoBitmapCroup.compress(Bitmap.CompressFormat.JPEG, 90, writePhotoStream)) {
                    Log.d("TEST", "writePhotoBitmapToSdCard: write file OK");
                    Log.d("TEST", "writePhotoBitmapToSdCard: " + photoFile.canRead());
                    // TODO: 22.06.2021 path write file
                    Log.d("TEST", "writePhotoBitmapToSdCard: " + photoFile.getAbsolutePath());
                    photoPathCroup = photoFile.getAbsolutePath();
                } else Log.d("TEST", "writePhotoBitmapToSdCard: write file ERR");

            } catch (IOException err) {
                err.printStackTrace();
            }

        }
        return photoPathCroup;
    }
    //TODO SOLID Interrupt
    private String getFileNoExtension(String fileName) {
        //TODO Сергей немчиенский смотрит на тебя с неодобрением
        String newFileName = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("_yyyyMMdd_HH_mm_ss");
            localDateTime.format(formatter);
            int pos = fileName.lastIndexOf(".");
            if (pos == -1) newFileName = fileName;
            else
                newFileName = fileName.substring(0, pos).toLowerCase() + localDateTime.format(formatter);
        }
        return newFileName;
    }

}
