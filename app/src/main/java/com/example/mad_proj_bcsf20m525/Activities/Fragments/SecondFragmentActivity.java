package com.example.mad_proj_bcsf20m525.Activities.Fragments;// SecondFragment.java

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.mad_proj_bcsf20m525.Activities.Fragments.FirstFragmentActivity;
import com.example.mad_proj_bcsf20m525.R;

public class SecondFragmentActivity extends Fragment {

    public SecondFragmentActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_second_fragment, container, false);

        Button btnPrevious = view.findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace current fragment with FirstFragment
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new FirstFragmentActivity())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
