package com.example.profileusers.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.profileusers.R;


public class UserProfileFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.user_profile_fragment, container, false);
        if (fragmentView != null) {
            ImageView userPhoto = fragmentView.findViewById(R.id.user_photo);
            Button selectPhotoButton = fragmentView.findViewById(R.id.select_photo_button);

            selectPhotoButton.setOnClickListener(view -> {

            });
        }
        return fragmentView;
    }

}
