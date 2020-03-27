package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnComputingAndAcademic = findViewById(R.id.btnComputingAndAcademic);
        Button btnEnergy = findViewById(R.id.btnEnergy);


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

    }
}
