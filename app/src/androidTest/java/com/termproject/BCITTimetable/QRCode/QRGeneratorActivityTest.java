package com.termproject.BCITTimetable.QRCode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.ImageView;

import com.termproject.BCITTimetable.R;

import org.junit.Test;

import java.io.File;
import java.nio.ByteBuffer;

import static org.junit.Assert.*;

public class QRGeneratorActivityTest {

    @Test
    public void QRGen() {

        String expected = "true";
        String result;
        String url_expected = "https://timetables.bcitsitecentre.ca/energy/instructor/77/3287";
        String url_test = "https://timetables.bcitsitecentre.ca/energy/instructor/77/3287";

        String filepath_expected = "/storage/emulated/0/qrimageexpected.jpg";
        String filepath_result = "/storage/emulated/0/qrimage.jpg";

        QRGeneratorActivity qRGeneratorActivity = new QRGeneratorActivity();

        Bitmap myBitmapexpected = qRGeneratorActivity.QRGen(url_expected, "qrimageexpected.jpg");
        //File imgFile = new File(filepath_expected);
        //Bitmap myBitmapexpected = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


        Bitmap myBitmapresult = qRGeneratorActivity.QRGen(url_test, "qrimage.jpg");
        //File imgFile2 = new File(filepath_result);
        //Bitmap myBitmapresult = BitmapFactory.decodeFile(imgFile2.getAbsolutePath());

         if (myBitmapresult.sameAs(myBitmapexpected)) {
            result = "true";
        } else {
            result = "false";
    }

        assertEquals(expected, result);

    }
}