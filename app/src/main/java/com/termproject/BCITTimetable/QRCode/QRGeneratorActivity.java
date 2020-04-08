package com.termproject.BCITTimetable.QRCode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;

public class QRGeneratorActivity {

    public static void QRGen(String url, String ImageName) {

        Bitmap bitmap = null;
        String filepath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + ImageName;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200);
             bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565);
            for (int x = 0; x<200; x++){
                for (int y=0; y<200; y++){
                    bitmap.setPixel(x,y,bitMatrix.get(x,y)? Color.BLACK : Color.WHITE);
                }
            }

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, bytes);

            File  f= new File(filepath);
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            fo.close();
            //imageView.setImageBitmap(bitmap);

//            Bitmap myBitmapexpected = BitmapFactory.decodeFile(f.getAbsolutePath());
//
//            ByteBuffer buffer1 = ByteBuffer.allocate(myBitmapexpected.getHeight() * myBitmapexpected.getRowBytes());
//            myBitmapexpected.copyPixelsToBuffer(buffer1);
//            buff = buffer1.array();

        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//           return(bitmap);
//        }

    }

    }
