package com.muzi.blurimageanimator.view;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

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
public class FrescoBlurView extends BlurView {

    public FrescoBlurView(@NonNull Context context) {
        this(context, null, 0);
    }

    public FrescoBlurView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FrescoBlurView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected View getImageView(Context context) {
        SimpleDraweeView view = (SimpleDraweeView) LayoutInflater.from(context).inflate(R.layout.view_fresco_sample, null);
        return view;
    }

    @Override
    public void blurImage(View blurView, Object imagePath, int blueRadius) {
        Uri uri = Uri.parse("res:///" + imagePath);
        setBlurImageUri((SimpleDraweeView) blurView, uri, 3, blueRadius);
    }

    @Override
    public void normalImage(View blurView, Object imagePath) {
        Uri uri = Uri.parse("res:///" + imagePath);
        ((SimpleDraweeView) blurView).setImageURI(uri);
    }

    @Override
    public boolean showBlueView() {
        return super.showBlueView();
    }

    @Override
    public int getBlurRadius() {
        return super.getBlurRadius();
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


}
