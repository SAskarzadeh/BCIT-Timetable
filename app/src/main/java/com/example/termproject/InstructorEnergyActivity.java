package com.example.termproject;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class InstructorEnergyActivity extends Instructor_Programs_EnergyActivity {

    private static final String TAG = "InstructorActivity";
    private Object JsonObjectRequest;
    private ListView mlistView;
    private RequestQueue mQueue;
    Button btnReturn;
    Button btnQR;
    EditText theFilter;
    private ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_list);
        mlistView = findViewById(R.id.listView);
        btnReturn = findViewById(R.id.btnReturn2Main);
        btnQR = findViewById(R.id.btnQR);
        theFilter = findViewById(R.id.searchFilter);

        mQueue = Volley.newRequestQueue(this);
        Log.d(TAG, "onCreate: started.");
        jsonParse("https://timetables.bcitsitecentre.ca/api/Instructor/TimetableFilter?schoolID=4&termSchoolID=77");

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QRDisplayed.class);
                startActivity(intent);
            }
        });
    }

    public void jsonParse(String inputURL){
        String url = inputURL;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(final JSONArray response) {

                final ArrayList<String> arrayList = new ArrayList<String>();

                for(int i = 0; i < response.length(); i++) {
                    try {
                        arrayList.add(response.getJSONObject(i).getString("name"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                System.out.println(arrayList);

                 arrayAdapter = new ArrayAdapter(InstructorEnergyActivity.this, R.layout.row,arrayList);
                System.out.println(arrayAdapter);
                mlistView.setAdapter(arrayAdapter);

                theFilter.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        (InstructorEnergyActivity.this).arrayAdapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                //OnitemClickListner
                mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                     public void onItemClick(AdapterView<?> parent, View view,  final int position, long id) {
                        setContentView(R.layout.activity_web_viewer);
                        try {
                            System.out.println(response.getJSONObject(position).getString("instructorID"));
                            WebView webView = new WebView(InstructorEnergyActivity.this);
                            WebSettings webSettings = webView.getSettings();
                            webSettings.setJavaScriptEnabled(true);

                            webView = (WebView) findViewById(R.id.webViewer);
                            webView.getSettings().setJavaScriptEnabled(true);
                            webView.loadUrl("https://timetables.bcitsitecentre.ca/energy/instructor/77/"+response.getJSONObject(position).getString("instructorID"));
                            webView.loadUrl("javascript:document.");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        btnReturn = (Button) findViewById(R.id.btnReturn2Main);

                        btnReturn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        });

                        btnQR = (Button) findViewById(R.id.btnQR);

                        btnQR.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                try {

                                    String url = "https://timetables.bcitsitecentre.ca/energy/instructor/77/"+response.getJSONObject(position).getString("instructorID");
                                    QRGenerator.QRGen(url);
                                    Intent intent = new Intent(getApplicationContext(), QRDisplayed.class);
                                    //intent.putExtra("BitmapImage", bitmap);
                                    startActivity(intent);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }



                            }
                        });
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Response",error.toString());
            }
        });
        mQueue.add(request);
    }


}
