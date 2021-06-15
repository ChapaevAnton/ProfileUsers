package com.example.profileusers.profile;

import java.io.File;
import java.util.ArrayList;

public class PhotoGalleryModel {

    private ArrayList<File> fileList = new ArrayList<>();


    public ArrayList<File> getFile(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (File file : listFile) {
                if (file.isDirectory()) {
                    getFile(file);
                }
                else {
                    if (file.getName().endsWith(".png")
                            || file.getName().endsWith(".jpg")
                            || file.getName().endsWith(".jpeg")
                            || file.getName().endsWith(".gif")
                            || file.getName().endsWith(".bmp")
                            || file.getName().endsWith(".webp"))
                    {
                        String temp = file.getPath().substring(0, file.getPath().lastIndexOf('/'));
                        if (!fileList.contains(temp))
                            fileList.add(new File(temp));
                    }
                }
            }
        }
        return fileList;
    }


}
