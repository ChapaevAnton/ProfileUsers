package com.example.profileusers.profile.utils;

import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

public class Utils {

    @BindingAdapter({"app:loadImageGallery"})
    public static void loadImage(ImageView imageView, String photo) {
        Glide.with(imageView.getContext()).load(photo).centerCrop().into(imageView);
    }

    @BindingAdapter({"app:loadImageGalleryAsync"})
    public static void loadImageAsync(CropImageView cropImageView, String photo) {
        cropImageView.setImageUriAsync(Uri.fromFile(new File(photo)));
    }
}
