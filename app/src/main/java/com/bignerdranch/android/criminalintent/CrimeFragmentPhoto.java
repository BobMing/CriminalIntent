package com.bignerdranch.android.criminalintent;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Admin on 2017/2/19 0019.
 */

public class CrimeFragmentPhoto extends Fragment {
    private ImageView mPhotoView;
    private static final String ARG_PHOTO_PATH = "photo";

    public static CrimeFragmentPhoto newInstance(String path) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PHOTO_PATH, path);

        CrimeFragmentPhoto fragment = new CrimeFragmentPhoto();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime_photo, container, false);

        String path = (String) getArguments().getSerializable(ARG_PHOTO_PATH);
        mPhotoView = (ImageView) v.findViewById(R.id.crime_photo);

        if (path == null) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(path, mPhotoView.getWidth(), mPhotoView.getHeight());
            mPhotoView.setImageBitmap(bitmap);
        }

        return v;
    }
}
