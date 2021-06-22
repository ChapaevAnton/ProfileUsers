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

import com.example.profileusers.profile.utils.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    //фотография
    private Bitmap photoBitmapCroup;

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

    public void setPhotoBitmapCroup(Bitmap photoBitmap) {
        photoBitmapCroup = photoBitmap;
    }


    public void onRotatePhotoClicked() {
        resultEventRotatePhoto.setValue(new Event(new Bundle()));
    }

    public void onCroupPhotoClicked() {
        // TODO: 22.06.2021 write bitmap to sdcard
        writePhotoBitmapToSdCard();
        resultEventCroupPhoto.setValue(new Event(new Bundle()));
    }


    private void writePhotoBitmapToSdCard() {

        Thread writePhoto = new Thread(() -> {
            // TODO: 22.06.2021
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                Log.d("TEST", "writePhotoBitmapToSdCard: " + photoPathStringToCroup.getValue());
                String photoPathString = photoPathStringToCroup.getValue();
                Path photoPath = Paths.get(photoPathString);
                String photoFileName = getFileNoExtension(photoPath.getFileName().toString());
                File photoFile = new File(photoPath.getParent().toString(), photoFileName + ".jpg");

                try (FileOutputStream writePhotoStream = new FileOutputStream(photoFile)) {

                    photoBitmapCroup.compress(Bitmap.CompressFormat.JPEG, 90, writePhotoStream);

                } catch (IOException err) {
                    err.printStackTrace();
                }

                Log.d("TEST", "writePhotoBitmapToSdCard: " + photoPath.getFileName());
                Log.d("TEST", "writePhotoBitmapToSdCard: " + photoPath.getParent());
                Log.d("TEST", "writePhotoBitmapToSdCard: " + photoFile.getAbsolutePath());
                Log.d("TEST", "writePhotoBitmapToSdCard: " + getFileNoExtension(photoPath.getFileName().toString()));

            }
        });

        writePhoto.start();
    }

    private String getFileNoExtension(String fileName) {
        String newFileName = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("_yyyyMMdd_HHmmss");
            localDateTime.format(formatter);

            int pos = fileName.lastIndexOf(".");

            if (pos == -1) newFileName = fileName;
            else
                newFileName = fileName.substring(0, pos).toLowerCase() + localDateTime.format(formatter);
        }

        return newFileName;
    }

}
