package com.example.profileusers.profile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.profileusers.R;

public class UserProfileFragment extends Fragment {
    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_PICK_CODE = 1001;
    private static final int RESULT_OK = -1;
    private ImageView userPhoto;
    private Button selectPhotoButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.user_profile_fragment, container, false);
        if (fragmentView != null) {
            userPhoto = fragmentView.findViewById(R.id.user_photo);
            selectPhotoButton = fragmentView.findViewById(R.id.select_photo_button);

            selectPhotoButton.setOnClickListener(view -> {
                //check permissions
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if ((fragmentView
                            .getContext()
                            .checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        selectPhoto();
                    }
                } else {
                    selectPhoto();
                }
            });
        }
        return fragmentView;
    }

    void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                selectPhoto();
            else
                Toast.makeText(getContext(), "Permission denied...!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            if (data != null)
                userPhoto.setImageURI(data.getData());
        }
    }
}