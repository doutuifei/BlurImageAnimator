package com.muzi.blurimageanimator.glide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.muzi.blurimageanimator.R;
import com.muzi.blurimageanimator.blur.BlurView;

/**
 * 作者: lipeng
 * 时间: 2018/7/19
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class GlideBlurView extends BlurView<ImageView, Integer> {

    public GlideBlurView(@NonNull Context context) {
        super(context);
    }

    public GlideBlurView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GlideBlurView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected ImageView getImageView(Context context) {
        return (ImageView) LayoutInflater.from(context).inflate(R.layout.view_imageview, null, false);
    }

    @Override
    public void blurImage(Context context, ImageView blurView, Integer imagePath, int blueRadius) {
        Glide.with(context)
                .load(imagePath)
                .apply(RequestOptions
                        .bitmapTransform(new GlideBlurformation(context, blueRadius))
                        .circleCropTransform())
                .into(blurView);
    }

    @Override
    public void normalImage(Context context, ImageView normalView, Integer imagePath) {
        Glide.with(context)
                .load(imagePath)
                .apply(RequestOptions.circleCropTransform())
                .into(normalView);
    }

}
