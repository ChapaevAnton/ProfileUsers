package com.example.profileusers.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.profileusers.R;
import com.example.profileusers.databinding.CroupImageFragmentBinding;
import com.theartofdev.edmodo.cropper.CropImage;

public class CroupImageFragment extends Fragment {


    private CroupImageFragmentBinding binding;

    @Override
    public void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startCropFragment();
    }

    //    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
//
//        binding = DataBindingUtil.inflate(inflater, R.layout.croup_image_fragment,container,false);
//        binding.setLifecycleOwner(getViewLifecycleOwner());
//        return binding.getRoot();
//    }




    private void startCropFragment() {

        CropImage
                .activity()
                .start(requireContext(), this);
    }

}

