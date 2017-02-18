package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Admin on 2017/2/19 0019.
 */

public class CrimePhotoActivity extends SingleFragmentActivity {
    private static final String EXTRA_PHOTO =
            "com.bignerdranch.android.criminalintent.crime_photo";

    public static Intent newIntent(Context packageContext, String path) {
        Intent intent = new Intent(packageContext, CrimePhotoActivity.class);
        intent.putExtra(EXTRA_PHOTO, path);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String path = (String) getIntent().getSerializableExtra(EXTRA_PHOTO);
        return CrimeFragmentPhoto.newInstance(path);
    }
}
