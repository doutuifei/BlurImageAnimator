package com.muzi.blurimageanimator.view;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.muzi.blurimageanimator.R;

/**
 * 作者: lipeng
 * 时间: 2018/7/18
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class BlurCircleImageView extends FrameLayout {

    private final int ITERATIONS = 3;
    private final int BLUR_RADIUS = 10;
    private float curreAlpha = 0f;

    private SimpleDraweeView blurDraweeView, normalDraweeView;

    public BlurCircleImageView(Context context) {
        this(context, null, 0);
    }

    public BlurCircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlurCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.view_blur_circle_image, this, true);
        blurDraweeView = findViewById(R.id.image_blur);
        normalDraweeView = findViewById(R.id.image_normal);
    }

    public void setImageResource(@DrawableRes int resId) {
        setBlurResource(blurDraweeView, resId, ITERATIONS, BLUR_RADIUS);
        setBlurResource(normalDraweeView, resId, 0, 0);
    }

    public void showBlurView(boolean isShow) {
        if (isShow) {
            normalDraweeView.setAlpha(0);
        } else {
            normalDraweeView.setAlpha(1);
        }
    }

    public void setImageAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (alpha != curreAlpha) {
            curreAlpha = alpha;
            normalDraweeView.setAlpha(alpha);
        }
    }

    /**
     * 高斯模糊图片
     *
     * @param draweeView
     * @param uri
     * @param iterations 迭代次数，越大越魔化，必须>0
     * @param blurRadius 模糊半径，必须>0
     */
    private void setBlurImageUri(SimpleDraweeView draweeView, Uri uri, int iterations, int blurRadius) {
        try {

            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(iterations, blurRadius))
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(draweeView.getController())
                    .setImageRequest(request)
                    .build();
            draweeView.setController(controller);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载drawable图片
     *
     * @param draweeView
     * @param resId
     * @param iterations
     * @param blurRadius
     */
    private void setBlurResource(SimpleDraweeView draweeView, int resId, int iterations, int blurRadius) {
        Uri uri = Uri.parse("res:///" + resId);
        if (blurRadius > 0) {
            setBlurImageUri(draweeView, uri, iterations, blurRadius);
        } else {
            draweeView.setImageURI(uri);
        }
    }

}