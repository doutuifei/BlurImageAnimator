package com.muzi.blurimageanimator;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.muzi.blurimageanimator.view.BlurCircleImageView;

public class SimpleActivity extends AppCompatActivity {

    private BlurCircleImageView blurCircleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        blurCircleImageView = findViewById(R.id.blurCircleImageView);
        blurCircleImageView.setImageResource(R.drawable.select_head_1);


        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(blurCircleImageView, "ImageAlpha", 0, 1, 0);
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimator.setDuration(2000);
        objectAnimator.start();


//        ValueAnimator animator = ValueAnimator.ofFloat(0, 1, 0);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float alpha= (float) animation.getAnimatedValue();
//                blurCircleImageView.setImageAlpha(alpha);
//            }
//        });
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.setDuration(2000);
//        animator.start();
    }

}
