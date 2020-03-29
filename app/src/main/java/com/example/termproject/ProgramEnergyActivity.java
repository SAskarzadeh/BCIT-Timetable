package com.example.termproject;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URLDecoder;
import java.util.ArrayList;

public class ProgramEnergyActivity extends AppCompatActivity  {


    private static final String TAG = "InstructorActivity";
    private Object JsonObjectRequest;
    private ListView mlistView;
    private RequestQueue mQueue;
    private JsonArrayRequest request;
    private JsonArrayRequest request2;
    Button btnReturn;
    private Context mContext;
    private Activity mActivity;
    private static final int MY_PERMISSION_REQUEST_CODE = 123;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_list);
        mlistView = findViewById(R.id.listView_2);
        btnReturn = findViewById(R.id.btnReturn2Main);
        mQueue = Volley.newRequestQueue(this);
      //  mQueue2 = Volley.newRequestQueue(this);
        Log.d(TAG, "onCreate: started.");
        jsonParse("https://timetables.bcitsitecentre.ca/api/Department/Get?termSchoolID=77","departmentCode");

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

                ArrayAdapter arrayAdapter = new ArrayAdapter(ProgramEnergyActivity.this, R.layout.row, arrayList);
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
                            jsonParse2("https://timetables.bcitsitecentre.ca/api/Set/Get?departmentID="+arrayListID.get(position)+"&termSchoolID=77",response.getJSONObject(position).getString(code));
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

                ArrayAdapter arrayAdapter = new ArrayAdapter(ProgramEnergyActivity.this, R.layout.row,arrayListSetCode);
                System.out.println(arrayAdapter);
                mlistView.setAdapter(arrayAdapter);


                mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        // Get the application context
                        mContext = getApplicationContext();
                        mActivity = ProgramEnergyActivity.this;

                        //Runtime External storage permission for saving download files
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    == PackageManager.PERMISSION_DENIED) {
                                Log.d("permission", "permission denied to WRITE_EXTERNAL_STORAGE - requesting it");
                                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                requestPermissions(permissions, 1);
                            }
                        }

                        WebView webView = new WebView(ProgramEnergyActivity.this);
                        WebSettings webSettings = webView.getSettings();

                        webView.setWebViewClient(new WebViewClient());
                        //webSettings.setLoadsImagesAutomatically(true);
                        webSettings.setJavaScriptEnabled(true);
                        //webView.clearCache(true);

                        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

                        setContentView(webView);

                        String url ="https://timetables.bcitsitecentre.ca/energy/set/77/"+arrayListSetID.get(position);
                        webView.loadUrl(url);
                        //webView.loadUrl("javascript:document.");

                        //handle downloading
                        webView.setDownloadListener(new DownloadListener() {
                            @Override
                            public void onDownloadStart(String url, String userAgent, String      contentDisposition , String mimeType, long contentLength) {


                                DownloadManager.Request request = new      DownloadManager.Request(Uri.parse(url));
                                request.setMimeType(mimeType);
//------------------------COOKIE!!------------------------
                                String cookies = CookieManager.getInstance().getCookie(url);
                                request
                                        .addRequestHeader("cookie", cookies);
//------------------------COOKIE!!------------------------
                                request
                                        .addRequestHeader("User-Agent", userAgent);
                                request
                                        .setDescription("Downloading file...");
                                request
                                        .setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
                                request
                                        .allowScanningByMediaScanner();
                                request
                                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                request
                                        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimeType));
                                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                                dm
                                        .enqueue(request);
                                Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();
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
        mQueue.add(request2);

    }




    protected void checkPermission(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                if(shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    // show an alert dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                    builder.setMessage("Write external storage permission is required.");
                    builder.setTitle("Please grant permission");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(
                                    mActivity,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    MY_PERMISSION_REQUEST_CODE
                            );
                        }
                    });
                    builder.setNeutralButton("Cancel",null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else {
                    // Request permission
                    ActivityCompat.requestPermissions(
                            mActivity,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSION_REQUEST_CODE
                    );
                }
            }else {
                // Permission already granted
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch(requestCode){
            case MY_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // Permission granted
                }else {
                    // Permission denied
                }
            }
        }
    }



}
