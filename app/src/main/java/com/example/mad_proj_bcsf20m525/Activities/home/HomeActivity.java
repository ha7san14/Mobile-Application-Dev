package com.example.mad_proj_bcsf20m525.Activities.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_proj_bcsf20m525.Activities.API.ApiActivity;
import com.example.mad_proj_bcsf20m525.Activities.Fragments.FragmentMainActivity;
import com.example.mad_proj_bcsf20m525.Activities.PostAPI.PostApiActivity;
import com.example.mad_proj_bcsf20m525.Activities.calculator.CalculatorActivity;
import com.example.mad_proj_bcsf20m525.Activities.form.FormActivity;
import com.example.mad_proj_bcsf20m525.Activities.notes.NotesActivity;
import com.example.mad_proj_bcsf20m525.R;
import com.example.mad_proj_bcsf20m525.databinding.ActivityHomeBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button calculatorButton = binding.calculatorButton;
        Button todoButton = binding.todoButton;
        Button notesButton = binding.notesButton;
        Button apiButton = binding.apiButton;
        Button postapiButton = binding.postapiButton;
        Button fragmentButton = binding.fragmentButton;


        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });

        todoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        notesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });

        fragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FragmentMainActivity.class);
                startActivity(intent);
            }
        });

        apiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ApiActivity.class);
                startActivity(intent);
            }
        });

        postapiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PostApiActivity.class);
                startActivity(intent);
            }
        });
    }

    //OnKeyDown is a callback event
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showExitDialog();
            return true; // Handled the back press
        }
        return super.onKeyDown(keyCode, event); // Pass the key event to the superclass (the base implementation of the activity)
// to ensure that the system performs its default behavior for the back button press.
    }

    private void showExitDialog() {
        new MaterialAlertDialogBuilder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity(); // Finish all activities in the stack
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing, stay on the HomeActivity
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Dismiss the dialog
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
