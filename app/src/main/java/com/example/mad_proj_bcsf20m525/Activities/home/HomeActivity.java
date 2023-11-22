package com.example.mad_proj_bcsf20m525.Activities.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_proj_bcsf20m525.Activities.calculator.CalculatorActivity;
import com.example.mad_proj_bcsf20m525.Activities.form.FormActivity;
import com.example.mad_proj_bcsf20m525.Activities.notes.NotesActivity;
import com.example.mad_proj_bcsf20m525.R;

public class HomeActivity extends AppCompatActivity {
    private Button calculatorButton;
    private Button todoButton;

    private Button notesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        calculatorButton = findViewById(R.id.calculatorButton);
        todoButton = findViewById(R.id.todoButton);
        notesButton = findViewById(R.id.notesButton);

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
    }
}
