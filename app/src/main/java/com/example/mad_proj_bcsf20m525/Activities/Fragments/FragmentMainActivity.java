package com.example.mad_proj_bcsf20m525.Activities.Fragments;// MainActivity.java

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mad_proj_bcsf20m525.R;

public class FragmentMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);

        // Load the FirstFragment into the container
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new FirstFragmentActivity())
                    .commit();
        }
    }
}
