package com.muzi.blurimageanimator.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.muzi.blurimageanimator.R;
import com.muzi.blurimageanimator.picasso.PicassoBlurView;

public class PicassoActivity extends AppCompatActivity {

    private PicassoBlurView picassoBlurView;
    private String path = "http://p1.qzone.la/upload/20150218/x5alew4n.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        picassoBlurView = findViewById(R.id.picassonBlur);
        picassoBlurView.setImagePath(path);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(picassoBlurView, "ImageAlpha", 0, 1, 0);
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }
}
