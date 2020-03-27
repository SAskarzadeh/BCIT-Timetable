package com.example.termproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class InstructorComputingActivity extends AppCompatActivity {

    private static final String TAG = "InstructorActivity";
    private Object JsonObjectRequest;
    private ListView mlistView;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_list);
        mlistView = findViewById(R.id.listView);
        mQueue = Volley.newRequestQueue(this);
        Log.d(TAG, "onCreate: started.");
        jsonParse("https://timetables.bcitsitecentre.ca/api/Instructor/TimetableFilter?schoolID=2&termSchoolID=75");

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
                ArrayAdapter arrayAdapter = new ArrayAdapter(InstructorComputingActivity.this, android.R.layout.simple_list_item_1,arrayList);
                System.out.println(arrayAdapter);
                mlistView.setAdapter(arrayAdapter);

                //OnitemClickListner
                mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            System.out.println(response.getJSONObject(position).getString("instructorID"));
                            WebView webView = new WebView(InstructorComputingActivity.this);
                            WebSettings webSettings = webView.getSettings();
                            webSettings.setJavaScriptEnabled(true);
                            setContentView(webView);
                            webView.loadUrl("https://timetables.bcitsitecentre.ca/computing-and-academic/instructor/75/"+response.getJSONObject(position).getString("instructorID"));
                            webView.loadUrl("javascript:document.");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
