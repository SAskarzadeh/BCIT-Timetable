package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Instructor_Programs_ComputingActivity extends AppCompatActivity {

    Button btnInstructor;
    Button btnPrograms;
    Button btnReturn;
    public static final int REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computing_terms);
        btnInstructor = findViewById(R.id.btnInstructor);
        btnPrograms = findViewById(R.id.btnPrograms);
        btnReturn = findViewById(R.id.btnReturn2Main);



        btnInstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), InstructorComputingActivity.class);
                startActivity(intent);

            }
        });


        btnPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProgramComputingActivity.class);
                startActivity(intent);
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}


