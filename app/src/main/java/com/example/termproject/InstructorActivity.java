package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class InstructorActivity extends AppCompatActivity {

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
        jsonParse();

    }

    private void jsonParse(){
        String url = "https://timetables.bcitsitecentre.ca/api/Instructor/TimetableFilter?schoolID=4&termSchoolID=77";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(final JSONArray response) {

                ArrayList<String> arrayList = new ArrayList<String>();

                for(int i = 0; i < response.length(); i++) {
                    try {
                        arrayList.add(response.getJSONObject(i).getString("name"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(arrayList);
                ArrayAdapter arrayAdapter = new ArrayAdapter(InstructorActivity.this, android.R.layout.simple_list_item_1,arrayList);
                System.out.println(arrayAdapter);
                mlistView.setAdapter(arrayAdapter);

                //OnitemClickListner
                mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                        try {
                            System.out.println(response.getJSONObject(position).getString("instructorID"));
                         //  InstructorSchedule instructorSchedule = new InstructorSchedule(3287);
                        //   instructorSchedule.getInstructorSchedule();
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


   /* private void getInstructorSchedule(int idInstructor){
        String url = "https://timetables.bcitsitecentre.ca/api/Timetable/Instructor?instructorID="+idInstructor+"&termSchoolID=77";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject timeTableByWeek = response.getJSONObject("timetablesByWeek");
                    System.out.println(timeTableByWeek);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
