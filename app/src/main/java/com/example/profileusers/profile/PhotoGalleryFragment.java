package com.example.profileusers.profile;

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

import com.example.profileusers.R;
import com.example.profileusers.databinding.PhotoGalleryFragmentBinding;

public class PhotoGalleryFragment extends Fragment {

    private PhotoGalleryFragmentBinding binding;
    private PhotoGalleryViewModel viewModel;
    private PhotoGalleryAdapter photoGalleryAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(this).get(PhotoGalleryViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.loadListPhoto();
        photoGalleryAdapter = new PhotoGalleryAdapter();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.photo_gallery_fragment, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setUserprofile(viewModel);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.photoRecyclerView.setAdapter(photoGalleryAdapter);

        viewModel.getItemsListPhoto().observe(getViewLifecycleOwner(), listPhoto -> {
            photoGalleryAdapter.setItems(listPhoto);
        });

    }
}
