package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import javax.xml.namespace.QName;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private Object JsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
        String url = "https://timetables.bcitsitecentre.ca/api/Instructor/TimetableFilter?schoolID=4&termSchoolID=77";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
           Log.d("Response",response.toString());
                for(int i = 0; i < response.length(); i++){
                    JSONObject jresponse = null;
                    try {
                        jresponse = response.getJSONObject(i);
                        Log.d("response2", String.valueOf(jresponse));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String fullname = null;
                    try {
                        fullname = jresponse.getString("name");
                        Log.d("response3",fullname);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mTextViewResult.append(fullname +"\n\n");
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

}
