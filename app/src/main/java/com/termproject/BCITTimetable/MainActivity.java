package com.termproject.BCITTimetable;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

// todo COMP Project: this is the up to date file as of 4:30 PM March 30, 2020

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnComputingAndAcademic = findViewById(R.id.btnComputingAndAcademic);
        Button btnEnergy = findViewById(R.id.btnEnergy);
        //Button btnSearch = findViewById(R.id.btnSearch);

        //Runtime External storage permission for saving download files
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to WRITE_EXTERNAL_STORAGE - requesting it");
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 1);
            }
        }

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
