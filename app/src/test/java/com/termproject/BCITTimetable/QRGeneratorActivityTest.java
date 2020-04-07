package com.termproject.BCITTimetable;

import android.os.Environment;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class QRGeneratorActivityTest {

    @Test
    public void QRGen() {
        String url = "http://timetables.bcitsitecentre.ca/energy/instructor/77/3287";

       // String filepath_expected = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "qrimage.jpg";


        QRGeneratorActivity qRGeneratorActivity = new QRGeneratorActivity();

        qRGeneratorActivity.QRGen(url);






    }
}