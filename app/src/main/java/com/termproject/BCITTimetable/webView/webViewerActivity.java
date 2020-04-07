package com.termproject.BCITTimetable.webView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.termproject.BCITTimetable.Main.MainActivity;
import com.termproject.BCITTimetable.R;

public class webViewerActivity extends AppCompatActivity {
    Button btnReturn;
    Button btnQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_viewer);
        btnReturn = findViewById(R.id.btnReturn2Main);
        btnQR = findViewById(R.id.btnQR);


        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
