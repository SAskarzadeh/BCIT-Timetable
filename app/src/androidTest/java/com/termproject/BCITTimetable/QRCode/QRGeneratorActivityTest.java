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
        String filepath_result = "/storage/emulated/0/qrimage.jpg";
        String url = "https://timetables.bcitsitecentre.ca/energy/instructor/77/3342";


        File imgFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "qrimageexpected.jpg");
        Bitmap myBitmapexpected = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        QRGeneratorActivity qRGeneratorActivity = new QRGeneratorActivity();

        qRGeneratorActivity.QRGen(url);

        File imgFile2 = new File(filepath_result);

        Bitmap myBitmapresult = BitmapFactory.decodeFile(imgFile2.getAbsolutePath());
        if (myBitmapresult.sameAs(myBitmapexpected)) {

            result = "true";
        } else {
            result = "false";
    }

        assertEquals(expected, result);

    }
}