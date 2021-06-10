package com.example.profileusers.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.profileusers.R;
import com.example.profileusers.databinding.UserProfileFragmentBinding;

public class UserProfileFragment extends Fragment {
    //private static final int PERMISSION_CODE = 1000;
    //private static final int IMAGE_PICK_CODE = 1001;
    //private static final int RESULT_OK = -1;
    private ImageView userPhoto;
    private Button selectPhotoButton;
    private ActivityResultLauncher<Intent> someActivityResultLauncher;
    private ActivityResultLauncher<String> mPermissionResult;

    private UserProfileFragmentBinding binding;
    //передать ссылку на ViewModel
    private UserProfileViewModel viewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            if (data != null)
                                userPhoto.setImageURI(data.getData());
                        }
                    }
                });
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

                        //String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

                        mPermissionResult.launch(Manifest.permission.READ_EXTERNAL_STORAGE,null);

                        //ActivityCompat.requestPermissions(requireActivity(), permissions, PERMISSION_CODE);
                        //requestPermissions(permissions, PERMISSION_CODE);

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
        someActivityResultLauncher.launch(intent);

        //("deprecation")
        //startActivityForResult(intent, IMAGE_PICK_CODE);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSION_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                selectPhoto();
//            else
//                Toast.makeText(getContext(), "Permission denied...!", Toast.LENGTH_SHORT).show();
//        }
//    }
    //https://stackoverflow.com/questions/62671106/onactivityresult-method-is-deprecated-what-is-the-alternative
    //("deprecation")
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
//            if (data != null)
//                userPhoto.setImageURI(data.getData());
//        }
//    }

}