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

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import com.example.profileusers.MainActivity;
import com.example.profileusers.R;
import com.example.profileusers.databinding.UserProfileFragmentBinding;

public class UserProfileFragment extends Fragment {

    private static final String PHOTO_TRANSFER = "photo_transfer";
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
         getChildFragmentManager().setFragmentResultListener(PHOTO_TRANSFER, this, new FragmentResultListener() {
             @Override
             public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String photoPath = result.getString(PHOTO_TRANSFER);
             }
         });


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
        binding.setViewModel(viewModel);
        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getSelectPhoto().observe(getViewLifecycleOwner(), event -> {
            if (event.isHandled()) selectPhoto();
        });

        viewModel.getShowPermission().observe(getViewLifecycleOwner(), event -> {
            if (event.isHandled()) showPermission();
        });

        viewModel.getShowPhotoGallery().observe(getViewLifecycleOwner(), event -> {
            if (event.isHandled()) MainActivity.userProfileToPhotoGallery(requireActivity());
        });

    }


    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        someActivityResultLauncher.launch(intent);

    }


    private void showPermission() {
        //String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        //ActivityCompat.requestPermissions(requireActivity(), permissions, PERMISSION_CODE);
        //альтернативный вариант
        mPermissionResult.launch(Manifest.permission.READ_EXTERNAL_STORAGE, null);
    }


}