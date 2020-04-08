package com.termproject.BCITTimetable.QRCode;

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
      // Bitmap myBitmapexpected = android.graphics.Bitmap@ffcd20b;
        File imgFile = new File(filepath_expected);
        //Bitmap myBitmapexpected = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        Bitmap myBitmapresult = qRGeneratorActivity.QRGen(url_test, "qrimage.jpg");
        //File imgFile2 = new File(filepath_result);
        //Bitmap myBitmapresult = BitmapFactory.decodeFile(imgFile2.getAbsolutePath());
       // int genid_expected = myBitmapexpected.getPixel(90, 130);
        //int genid_result = myBitmapresult.getPixel(90, 130);



        int w = myBitmapexpected.getWidth();
        int h = myBitmapexpected.getHeight();
        int w_result = myBitmapresult.getWidth();
        int h_result = myBitmapresult.getHeight();

        int[] pixels = new int[w * h];
        int[] pixels2 = new int[w * h];

        myBitmapexpected.getPixels(pixels, 0, w, 0, 0, w, h);
        myBitmapresult.getPixels(pixels2, 0, w, 0, 0, w, h);

        RGBLuminanceSource source = new RGBLuminanceSource(w_result, h_result, pixels2);
        BinaryBitmap bBitmap = new BinaryBitmap(new HybridBinarizer(source));
        MultiFormatReader reader = new MultiFormatReader();

        Result result_URL = null;

        try {
            result_URL = reader.decode(bBitmap);

        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        String a = pixels.toString();
        String b = pixels2.toString();

        //myBitmapexpected.getPixels(int[] pixels, int offset, int stride, int x, int y, int width, int height);
        System.out.println(w + "bankai");
        System.out.println(h + "sasuke");
        System.out.println(w_result);
        System.out.println(h_result);
        System.out.println(result_URL);



        for(int i=0; i< myBitmapresult.getWidth(); i++){
            for(int j=0; j< myBitmapresult.getHeight(); j++){
                pixels[i] = myBitmapresult.getPixel(i,j);
                pixels[i] = myBitmapexpected.getPixel(i,j);
            }
        }
        
        System.out.println(pixels);
        System.out.println(pixels2);

        if (myBitmapresult.sameAs(myBitmapexpected)) {
            result = "true";
        } else {
            result = "false";
    }

        assertEquals(expected, result);

    }
}