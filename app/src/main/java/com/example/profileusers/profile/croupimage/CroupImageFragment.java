package com.example.profileusers.profile.croupimage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.profileusers.MainActivity;
import com.example.profileusers.R;
import com.example.profileusers.databinding.CroupImageFragmentBinding;
import com.example.profileusers.profile.UserProfileFragment;

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
                requireActivity().getSupportFragmentManager().setFragmentResult(UserProfileFragment.PHOTO_FILE_PATH_REQUEST, event.getContent());
                MainActivity.croupImageToUserProfile(requireActivity());
            }
        });

        viewModel.getResultEventRotatePhoto().observe(getViewLifecycleOwner(), event -> {
            if (event.isHandled()) {
                binding.croupImageView.rotateImage(90);
            }
        });


    }


}

