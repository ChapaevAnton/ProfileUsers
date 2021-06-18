package com.example.profileusers.profile.photogallery;

public class Photo {

    private String photoFilePath;

    public Photo(String photoFilePath) {
        this.photoFilePath = photoFilePath;
    }

    public void setPhotoFilePath(String photoFilePath) {
        this.photoFilePath = photoFilePath;
    }

    public String getPhotoFilePath() {
        return photoFilePath;
    }
}
