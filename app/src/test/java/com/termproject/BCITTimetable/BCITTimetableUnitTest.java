package com.termproject.BCITTimetable;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class BCITTimetableUnitTest {


        private  static String filepath_expected;
        private static File filepath_result;
        private static String url = "https://timetables.bcitsitecentre.ca/energy/instructor/77/3287";


        @Test
        public void QRGen(){


            filepath_expected="/storage/emulated/0/qrimage.jpg";

            //QRGeneratorActivity.QRGen(url);
            //filepath_result = QRGeneratorActivity.f;
            File expected = new File(filepath_expected);
            assertEquals(expected, filepath_result);
        }


}
