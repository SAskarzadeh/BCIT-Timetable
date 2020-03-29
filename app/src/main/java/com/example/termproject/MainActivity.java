package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.xml.namespace.QName;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private Object JsonObjectRequest;
    private ListView mlistView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlistView = findViewById(R.id.listView);
        mTextViewResult = findViewById(R.id.tvResult);
        Button buttonParse = findViewById(R.id.btnParse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });

    }
    private void jsonParse(){
        String url = "https://timetables.bcitsitecentre.ca/api/Department/Get?termSchoolID=77";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ArrayList<String> arrayList = new ArrayList<String>();

                for(int i = 0; i < response.length(); i++) {
                    try {
                        arrayList.add(response.getJSONObject(i).getString("departmentCode"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(arrayList);
                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,arrayList);
                System.out.println(arrayAdapter);
                mlistView.setAdapter(arrayAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Log.d("Error Response",error.toString());
            }
        });
        mQueue.add(request);
    }
    /*


    private void getInstructorSchedule(int idInstructor){
        String url = "https://timetables.bcitsitecentre.ca/api/Timetable/Instructor?instructorID="+idInstructor+"&termSchoolID=77";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                ArrayList<String> arrayList = new ArrayList<String>();

                for(int i = 0; i < response.length(); i++) {
                    try {
                        arrayList.add(response.getJSONObject(i).getString("name"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(arrayList);
                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,arrayList);
                System.out.println(arrayAdapter);
                mlistView.setAdapter(arrayAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Response",error.toString());
            }
        });
        mQueue.add(request);
    }
     */
}
