package com.example.termproject;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.OutputStream;

public class QRDisplayed extends AppCompatActivity {
    private ImageView imageView;

    Button Share_btn;
    Button Return_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_layout);
        Share_btn = findViewById(R.id.Share_btn);
        Return_btn = findViewById(R.id.Return_btn);

        File imgFile = new  File("/sdcard/qrimage.jpg");
        //Here File file = ur file path
        if(imgFile.exists())
        {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ImageView myImage = (ImageView) findViewById(R.id.imageView);
            myImage.setImageBitmap(myBitmap);
        }


        Share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String filePath = "/sdcard/qrimage.jpg";
                String caption = "QR";

                // Start server upload
                //upload(filePath);

                // Start sharing intent to send image to social media platform
                Bitmap icon = BitmapFactory.decodeFile(filePath);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");

                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, caption);
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        values);

                // Stream encoded image
                OutputStream outstream;
                try {
                    outstream = getContentResolver().openOutputStream(uri);
                    icon.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
                    outstream.close();
                } catch (Exception e) {
                    System.err.println(e.toString());
                }

                // Put encoded image in intent along with caption as the subject
                share.putExtra(Intent.EXTRA_STREAM, uri);
                share.putExtra(Intent.EXTRA_SUBJECT, caption);
                startActivity(Intent.createChooser(share, "Share Image"));

            }
        });

        Return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InstructorEnergyActivity.class);
                startActivity(intent);
            }
        });


    }

}
