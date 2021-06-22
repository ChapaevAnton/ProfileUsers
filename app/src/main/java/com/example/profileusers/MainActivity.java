package com.example.profileusers;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.example.profileusers.profile.photogallery.PhotoGalleryFragmentDirections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    // UI NAVIGATOR
    public static void userProfileToPhotoGallery(Activity activity) {
        Navigation.findNavController(activity, R.id.main_fragment_container)
                .navigate(R.id.action_userProfileFragment_to_photoGalleryFragment, null, null);
    }

    public static void photoGalleryToCropImage(Activity activity, String photoPath) {
        Navigation.findNavController(activity, R.id.main_fragment_container)
                .navigate(PhotoGalleryFragmentDirections.actionPhotoGalleryFragmentToCropImageFragment(photoPath));
    }

    public static void croupImageToUserProfile(Activity activity){
        Navigation.findNavController(activity,R.id.main_fragment_container)
                .navigate(R.id.action_CropImageFragment_to_userProfileFragment,null,null);
    }

}
