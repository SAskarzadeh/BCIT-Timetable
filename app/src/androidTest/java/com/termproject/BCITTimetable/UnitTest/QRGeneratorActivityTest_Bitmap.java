package com.termproject.BCITTimetable.UnitTest;

import android.graphics.Bitmap;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class QRGeneratorActivityTest_Bitmap {

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
        Bitmap myBitmapresult = qRGeneratorActivity.QRGen(url_test, "qrimage.jpg");

        if (myBitmapresult.sameAs(myBitmapexpected)) {
            result = "true";
        } else {
            result = "false";
        }
        assertEquals(expected, result);

    }
}