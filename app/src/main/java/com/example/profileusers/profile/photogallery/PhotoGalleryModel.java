package com.example.profileusers.profile.photogallery;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhotoGalleryModel {

    private final List<Photo> listPhotos = new ArrayList<>();


    public List<Photo> getListPhotos(@NonNull File root) throws IOException {
        Log.d("TEST", "searchFilesPaths: load start ");

        List<File> listFiles;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("TEST", "searchFilesPathsApi26: run");
            listFiles = searchFilesPathsApi26(root);

        } else {
            Log.d("TEST", "searchFilesPaths: run");
            listFiles = searchFilesPaths(root);
        }


        assert listFiles != null;
        for (File file : listFiles) {
            listPhotos.add(new Photo(file.getAbsolutePath()));
            Log.d("TEST", file.getAbsolutePath());
        }

        Log.d("TEST", "searchFilesPaths: load stop");
        Log.d("TEST", String.valueOf(listPhotos.size()));

        return listPhotos;
    }


    //поиск файлов по фильтру для >= API26
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<File> searchFilesPathsApi26(@NonNull File path) throws IOException {

        return Files.walk(path.toPath()).map(Path::toFile).filter(this::fileFiler).collect(Collectors.toList());

    }

    //поиск файлов по фильтру
    private List<File> searchFilesPaths(@NonNull File root) {

        List<File> fileList = new ArrayList<>();

        if (!root.isDirectory()) {
            return fileList;
        }

        File[] directoryFiles = root.listFiles();

        if (directoryFiles == null) {
            return fileList;
        }

        for (File file : directoryFiles) {
            if (file.isDirectory()) {
                fileList.addAll(searchFilesPaths(file));
            } else {
                if (fileFiler(file)) {
                    fileList.add(file);
                }
            }
        }
        return fileList;
    }

    private boolean fileFiler(@NonNull File file) {
        return file.getName().toLowerCase().endsWith(".jpg")
                || file.getName().toLowerCase().endsWith(".jpeg")
                || file.getName().toLowerCase().endsWith(".png")
                || file.getName().toLowerCase().endsWith(".gif")
                || file.getName().toLowerCase().endsWith(".bmp")
                || file.getName().toLowerCase().endsWith(".webp");
    }

}
