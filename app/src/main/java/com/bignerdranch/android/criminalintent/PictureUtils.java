package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Admin on 2017/2/19 0019.
 */

public class PictureUtils {
//    public static Bitmap getScaledBitmap(String path, Activity activity) {
//        Point size = new Point();
//        activity.getWindowManager().getDefaultDisplay().getSize(size);
//
//        Log.e("" + size.x, "" + size.y);
//        return getScaledBitmap(path, size.x, size.y);
//    }

    public static Bitmap getScaledBitmap(String path, int destWidth, int destHeight) {
        // Read in the dimensions of the image on disk
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        // Figure out how much to scale down by
        int inSampleSize = 1;
        if (srcHeight > destHeight || srcWidth > destWidth) {
            Log.e("" + srcWidth, "" + srcHeight);
            if (srcWidth < srcHeight) {
                inSampleSize = Math.round(srcHeight / destHeight);
            } else {
                inSampleSize = Math.round(srcWidth / destWidth);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        // Read in and create final bitmap
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
        Log.e("ExifInteface .........", "rotation =" + orientation);
        Matrix m = new Matrix();

        Bitmap bm = BitmapFactory.decodeFile(path, options);
        if ((orientation == ExifInterface.ORIENTATION_ROTATE_180)) {
            m.postRotate(180);
            Log.e("in orientation", "" + orientation);
            Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),bm.getHeight(), m, true);
            return bitmap;
        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {    // 竖着拍
            m.postRotate(90);
            Log.e("in orientation", "" + orientation);
            Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),bm.getHeight(), m, true);
            return bitmap;
        }
        else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
            m.postRotate(270);
            Log.e("in orientation", "" + orientation);
            Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),bm.getHeight(), m, true);
            return bitmap;
        }
        return bm;
    }
}
