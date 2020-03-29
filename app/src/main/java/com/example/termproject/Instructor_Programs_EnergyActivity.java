package com.example.termproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Instructor_Programs_EnergyActivity extends AppCompatActivity  {
    Button btnInstructor;
    Button btnPrograms;
    Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy);
        btnInstructor = findViewById(R.id.btnInstructor);
        btnPrograms = findViewById(R.id.btnPrograms);
        btnReturn = findViewById(R.id.btnReturn2Main);



            btnInstructor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplication(), InstructorEnergyActivity.class);
                    startActivity(intent);

                }
            });


            btnPrograms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ProgramEnergyActivity.class);
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
