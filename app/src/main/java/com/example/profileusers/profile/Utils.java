package com.example.profileusers.profile;

import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class Utils {

    @BindingAdapter({"app:loadImage"})
    public static void loadImage(ImageView imageView, Uri photo){
        imageView.setImageURI(photo);
    }

}
