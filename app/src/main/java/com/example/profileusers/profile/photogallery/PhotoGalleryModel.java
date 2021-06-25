package com.example.profileusers.profile.photogallery;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class PhotoGalleryModel {

    private final ArrayList<Photo> listPhotos = new ArrayList<>();

    public ArrayList<Photo> getListPhotos(File root) {
        Log.d("TEST", "searchFilesPaths: LOAD START ");
        for (File file : searchFilesPaths(root)) {
            listPhotos.add(new Photo(file.getAbsolutePath()));
        }
        Log.d("TEST", "searchFilesPaths: LOAD STOP");
        Log.d("TEST", String.valueOf(listPhotos.size()));
        return listPhotos;
    }


    // TODO: 25.06.2021 убрат вложенность if, и предсумотреть что возвращать если else
    private ArrayList<File> searchFilesPaths(File root) {
        ArrayList<File> fileList = new ArrayList<>();
        if (root.isDirectory()) {
            File[] directoryFiles = root.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        fileList.addAll(searchFilesPaths(file));
                    } else {
                        if (file.getName().toLowerCase().endsWith(".jpg")
                                || file.getName().toLowerCase().endsWith(".jpeg")
                                || file.getName().toLowerCase().endsWith(".png")
                                || file.getName().toLowerCase().endsWith(".gif")
                                || file.getName().toLowerCase().endsWith(".bmp")
                                || file.getName().toLowerCase().endsWith(".webp")) {
                            fileList.add(file);
                            Log.d("TEST", file.getAbsolutePath());
                        }
                    }
                }
            }
        }

        return fileList;
    }


}
