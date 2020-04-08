package com.termproject.BCITTimetable.UnitTest;

import android.graphics.Bitmap;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QRGeneratorActivityTest_URL {

    @Test
    public void QRGen() {

        String expected = "true";
        String url_expected = "https://timetables.bcitsitecentre.ca/energy/instructor/77/3287";
        String url_test = "https://timetables.bcitsitecentre.ca/energy/instructor/77/3287";
        String url_result;

        QRGeneratorActivity qRGeneratorActivity = new QRGeneratorActivity();

        Bitmap myBitmapresult = qRGeneratorActivity.QRGen(url_test, "qrimage.jpg");

        int w_result = myBitmapresult.getWidth();
        int h_result = myBitmapresult.getHeight();
        int[] pixels_result = new int[w_result * h_result];

        myBitmapresult.getPixels(pixels_result, 0, w_result, 0, 0, w_result, h_result);

        RGBLuminanceSource source = new RGBLuminanceSource(w_result, h_result, pixels_result);
        BinaryBitmap bBitmap = new BinaryBitmap(new HybridBinarizer(source));
        MultiFormatReader reader = new MultiFormatReader();

        Result result_URL1 = null;
        try {
            result_URL1 = reader.decode(bBitmap);

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        String result_URL = result_URL1.toString();

        assertEquals(url_expected, result_URL);

    }
}