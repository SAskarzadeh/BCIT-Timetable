package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

// todo COMP Project: this is the up to date file as 1:02PM March 30, 2020
// todo Amine Push Check Confirmed : 1:02PM March 30, 2020
// todo Sajjad Push Check Unconfirmed :
// todo Akash Push Check Unconfirmed :

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnComputingAndAcademic = findViewById(R.id.btnComputingAndAcademic);
        Button btnEnergy = findViewById(R.id.btnEnergy);
        //Button btnSearch = findViewById(R.id.btnSearch);


        btnComputingAndAcademic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Instructor_Programs_ComputingActivity.class);
                startActivity(intent);
            }
        });

        btnEnergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Instructor_Programs_EnergyActivity.class);
                startActivity(intent);
            }
        });

        /*btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Instructor_Programs_ComputingActivity.class);
                startActivity(intent);
            }
        });*/










    }
}
