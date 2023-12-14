package com.example.mad_proj_bcsf20m525.Activities.Fragments;// FirstFragment.java

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.mad_proj_bcsf20m525.R;

public class FirstFragmentActivity extends Fragment {

    public FirstFragmentActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_first_fragment, container, false);

        Button btnNext = view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace current fragment with SecondFragment
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new SecondFragmentActivity())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
