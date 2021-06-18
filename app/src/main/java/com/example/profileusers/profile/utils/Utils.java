package com.example.profileusers.profile.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Utils {

    @BindingAdapter({"app:loadImageGallery"})
    public static void loadImage(ImageView imageView, String photo) {
        Glide.with(imageView.getContext()).load(photo).centerCrop().into(imageView);
    }

}
