package com.example.mad_proj_bcsf20m525;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_proj_bcsf20m525.Activities.LoginActivity;
import com.example.mad_proj_bcsf20m525.Activities.SignupActivity;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private Button proceedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        proceedButton = findViewById(R.id.proceedButton);

        spinner.setSelection(0);

        // This is an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.login_signup_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                boolean enableProceed = position != 0; // 0 means "Select an option"
                proceedButton.setEnabled(enableProceed);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedItemPosition = spinner.getSelectedItemPosition();
                if (selectedItemPosition == 1) {
                    // Navigate to the LoginActivity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else if (selectedItemPosition == 2) {
                    // Navigate to the SignupActivity
                    startActivity(new Intent(MainActivity.this, SignupActivity.class));
                }
            }
        });
    }
}
