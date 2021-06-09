package com.example.profileusers.profile;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.profileusers.R;


public class UserProfile extends Fragment {

   public UserProfile(){
       super(R.layout.user_profile_fragment);
   }

    @Override
    public void onViewCreated(@NonNull  View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int someInt = requireArguments().getInt("some_int");
    }
}
