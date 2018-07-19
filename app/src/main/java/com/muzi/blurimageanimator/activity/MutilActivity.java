package com.muzi.blurimageanimator.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.muzi.blurimageanimator.R;
import com.muzi.blurimageanimator.view.SelectBlurView;

public class MutilActivity extends AppCompatActivity {

    private SelectBlurView blurView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutil);
        blurView = findViewById(R.id.blurView);
        blurView.startAnimator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        blurView.closeAnimator(true);
    }
}
