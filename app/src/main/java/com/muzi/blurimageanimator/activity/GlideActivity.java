package com.muzi.blurimageanimator.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.muzi.blurimageanimator.R;
import com.muzi.blurimageanimator.glide.GlideBlurView;

public class GlideActivity extends AppCompatActivity {

    private GlideBlurView glideBlurView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        glideBlurView = findViewById(R.id.glideBlurView);
        glideBlurView.setImagePath(R.drawable.select_head_1);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(glideBlurView, "ImageAlpha", 0, 1, 0);
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }
}
