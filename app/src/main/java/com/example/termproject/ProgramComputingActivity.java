package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class ProgramComputingActivity extends AppCompatActivity  {


    private static final String TAG = "InstructorActivity";
    private Object JsonObjectRequest;
    private ListView mlistView;
    private RequestQueue mQueue;
    private JsonArrayRequest request;
    private JsonArrayRequest request2;
    Button btnReturn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_list);
        mlistView = findViewById(R.id.listView_2);
        btnReturn = findViewById(R.id.btnReturn2Main);
        mQueue = Volley.newRequestQueue(this);
      //  mQueue2 = Volley.newRequestQueue(this);
        Log.d(TAG, "onCreate: started.");
        jsonParse("https://timetables.bcitsitecentre.ca/api/Department/Get?termSchoolID=75","departmentCode");

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void jsonParse(String inputURL, final String code){
        String url = inputURL;
        System.out.println(inputURL);
        request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(final JSONArray response) {

                ArrayList<String> arrayList = new ArrayList<String>();
                final ArrayList<Integer> arrayListID = new ArrayList<Integer>();

                for(int i = 0; i < response.length(); i++) {
                    try {
                        arrayList.add(response.getJSONObject(i).getString(code));
                        arrayListID.add(response.getJSONObject(i).getInt("departmentID"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(arrayList);

                ArrayAdapter arrayAdapter = new ArrayAdapter(ProgramComputingActivity.this, R.layout.row, arrayList);
                System.out.println(arrayAdapter);
                mlistView.setAdapter(arrayAdapter);

                //OnitemClickListner
                mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Intent intent = new Intent(getApplicationContext(),ProgramActivity.class);
                       // startActivity(intent);
                        try {
                            System.out.println(response.getJSONObject(position).getString(code));
                            System.out.println(arrayListID.get(position));
                            jsonParse2("https://timetables.bcitsitecentre.ca/api/Set/Get?departmentID="+arrayListID.get(position)+"&termSchoolID=75",response.getJSONObject(position).getString(code));
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


    private void jsonParse2(String inputURL, final String code){
        String url = inputURL;
        System.out.println(inputURL);
        request2 = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(final JSONArray response) {

                ArrayList<String> arrayListSetCode = new ArrayList<String>();
                final ArrayList<Integer> arrayListSetID = new ArrayList<Integer>();

                for(int i = 0; i < response.length(); i++) {
                    try {
                        arrayListSetCode.add(response.getJSONObject(i).getString("setCode"));
                        arrayListSetID.add(response.getJSONObject(i).getInt("setID"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(arrayListSetCode);

                ArrayAdapter arrayAdapter = new ArrayAdapter(ProgramComputingActivity.this, android.R.layout.simple_list_item_1,arrayListSetCode);
                System.out.println(arrayAdapter);
                mlistView.setAdapter(arrayAdapter);


                mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        WebView webView = new WebView(ProgramComputingActivity.this);
                        WebSettings webSettings = webView.getSettings();
                        webSettings.setJavaScriptEnabled(true);
                        setContentView(webView);
                        webView.loadUrl("https://timetables.bcitsitecentre.ca/computing-and-academic/set/75/"+arrayListSetID.get(position));
                        webView.loadUrl("javascript:document.");
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Response",error.toString());
            }
        });
        mQueue.add(request2);
    }
}
