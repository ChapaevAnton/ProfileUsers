package com.example.profileusers.profile.croupimage;

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
import androidx.lifecycle.ViewModelProvider;

import com.example.profileusers.R;
import com.example.profileusers.databinding.CroupImageFragmentBinding;

public class CroupImageFragment extends Fragment {


    private CroupImageFragmentBinding binding;
    private String photoPath;
    private CroupImageViewModel viewModel;
    private CroupImageFragmentArgs args;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(requireActivity()).get(CroupImageViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //      getResultFragmentPhotoGallery();
        args = CroupImageFragmentArgs.fromBundle(requireArguments());
        photoPath = args.getPhotoPath();
        viewModel.setPhotoPathStringToCroup(photoPath);
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

        viewModel.getResultEventCroupPhoto().observe(getViewLifecycleOwner(), event -> {
            if (event.isHandled()) {
                croupPhoto();
            }
        });

        viewModel.getResultEventRotatePhoto().observe(getViewLifecycleOwner(), event -> {
            if (event.isHandled()) {
                rotatePhoto();
            }
        });

//        croupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.resultImage.setImageBitmap(cropImageView.getCroppedImage());
//
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

    private void croupPhoto() {
        Log.d("TEST", "croupPhoto: croup");
    }

    private void rotatePhoto() {
        Log.d("TEST", "rotatePhoto: rotate");
        binding.croupImageView.rotateImage(90);
    }

}

