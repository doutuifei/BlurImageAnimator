package com.muzi.blurimageanimator.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.muzi.blurimageanimator.FrescoBlurView;
import com.muzi.blurimageanimator.R;

public class FrescoActivity extends AppCompatActivity {

    private FrescoBlurView frescoBlurView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);
        frescoBlurView = findViewById(R.id.frescoView);

        frescoBlurView.setImagePath(R.drawable.select_head_1);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(frescoBlurView, "ImageAlpha", 0, 1, 0);
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }
}
