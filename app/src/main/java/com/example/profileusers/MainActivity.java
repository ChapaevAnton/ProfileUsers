package com.example.profileusers;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.profileusers.profile.UserProfileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment userProfileFragment = fragmentManager.findFragmentById(R.id.main_fragment_container);
        if (userProfileFragment == null) {
            userProfileFragment = new UserProfileFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.main_fragment_container, userProfileFragment)
                    .commit();
        }

    }
}
