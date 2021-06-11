package com.example.profileusers.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.profileusers.R;
import com.example.profileusers.databinding.UserProfileFragmentBinding;

public class UserProfileFragment extends Fragment {
    private static final int PERMISSION_CODE = 1000;
    private ImageView userPhoto;
    private Button selectPhotoButton;
    private ActivityResultLauncher<Intent> someActivityResultLauncher;
    private ActivityResultLauncher<String> mPermissionResult;

    private UserProfileFragmentBinding binding;
    //передать ссылку на ViewModel
    private UserProfileViewModel viewModel;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(requireActivity()).get(UserProfileViewModel.class);
    }

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
                                viewModel.loadInImageView(data.getData());
                        }
                    }
                });

        mPermissionResult = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {

                        if (result) {
                            Log.d("TEST", "Permission granted...!");
                            //Toast.makeText(getContext(), "Permission granted...!", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("TEST", "Permission denied...!");
                            //Toast.makeText(getContext(), "Permission denied...!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.user_profile_fragment, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setUserprofile(viewModel);


        return binding.getRoot();

//        View fragmentView = inflater.inflate(R.layout.user_profile_fragment, container, false);
//        if (fragmentView != null) {
//
//            userPhoto = fragmentView.findViewById(R.id.user_photo);
//            selectPhotoButton = fragmentView.findViewById(R.id.select_photo_button);
//
//            selectPhotoButton.setOnClickListener(view -> {
//                //check permissions
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    if ((fragmentView
//                            .getContext()
//                            .checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) == PackageManager.PERMISSION_DENIED) {
//
//                        //String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
//
//                        mPermissionResult.launch(Manifest.permission.READ_EXTERNAL_STORAGE, null);
//
//                        //ActivityCompat.requestPermissions(requireActivity(), permissions, PERMISSION_CODE);
//                        //requestPermissions(permissions, PERMISSION_CODE);
//
//                    } else {
//                        selectPhoto();
//                    }
//                } else {
//                    selectPhoto();
//                }
//            });
//        }
//        return fragmentView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getSelectPhoto().observe(getViewLifecycleOwner(), event -> {
            if (event.isHandled()) selectPhoto();
        });

        viewModel.getShowPermission().observe(getViewLifecycleOwner(), event -> {
           if(event.isHandled()) showPermission();
        });

    }

    void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        someActivityResultLauncher.launch(intent);

    }

    void showPermission(){
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(requireActivity(), permissions, PERMISSION_CODE);
    }

}