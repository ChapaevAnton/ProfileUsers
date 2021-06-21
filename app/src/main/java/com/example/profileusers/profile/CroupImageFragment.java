package com.example.profileusers.profile;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import com.example.profileusers.R;
import com.example.profileusers.databinding.CroupImageFragmentBinding;

public class CroupImageFragment extends Fragment {


    private CroupImageFragmentBinding binding;
    private String photoPath;
    private CroupImageViewModel viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(requireActivity()).get(CroupImageViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getResultFragmentPhotoGallery();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.croup_image_fragment, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        CropImageView cropImageView = binding.croupImageView;
//        Button croupButton = binding.croupButton;
//        Button rotateButton = binding.rotateButton;
//
//        cropImageView.setImageUriAsync(Uri.fromFile(new File("/storage/emulated/0/Download/TEMP/1612098568_71665.jpg")));
//        cropImageView.setOnCropImageCompleteListener(new CropImageView.OnCropImageCompleteListener() {
//            @Override
//            public void onCropImageComplete(CropImageView view, CropImageView.CropResult result) {
//                Log.d("TEST", "onCropImageComplete");
//            }
//        });
//        cropImageView.setOnSetImageUriCompleteListener(new CropImageView.OnSetImageUriCompleteListener() {
//            @Override
//            public void onSetImageUriComplete(CropImageView view, Uri uri, Exception error) {
//                Log.d("TEST", "onSetImageUriComplete");
//            }
//        });

//        croupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.resultImage.setImageBitmap(cropImageView.getCroppedImage());
//                Uri uri = cropImageView.getImageUri();
//                String pathFile = cropImageView.getImageUri().getPath();
//                Log.d("TEST", "onClick");
//                Log.d("TEST URI", uri.toString());
//                Log.d("TEST pathFile", pathFile);
//
//            }
//        });
//
//        rotateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cropImageView.rotateImage(90);
//            }
//        });
//
    }


    private void getResultFragmentPhotoGallery() {
        requireActivity().getSupportFragmentManager().setFragmentResultListener(
                UserProfileFragment.PHOTO_FILE_PATH_REQUEST, this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        photoPath = result.getString(UserProfileFragment.PHOTO_FILE_PATH_REQUEST);
                        Log.d("TEST", "PHOTO_FILE_PATH_REQUEST CROUP:" + photoPath);
                        viewModel.setPhotoPathString(photoPath);
                    }
                });
    }

}

