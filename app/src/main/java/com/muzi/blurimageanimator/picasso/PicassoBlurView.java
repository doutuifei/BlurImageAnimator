package com.muzi.blurimageanimator.picasso;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.muzi.blurimageanimator.R;
import com.muzi.blurimageanimator.blur.BlurView;
import com.squareup.picasso.Picasso;

/**
 * 作者: lipeng
 * 时间: 2018/7/19
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class PicassoBlurView extends BlurView<ImageView, Integer> {

    public PicassoBlurView(@NonNull Context context) {
        super(context);
    }

    public PicassoBlurView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PicassoBlurView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected ImageView getImageView(Context context) {
        return (ImageView) LayoutInflater.from(context).inflate(R.layout.view_imageview, null, false);
    }

    @Override
    public void blurImage(Context context, ImageView blurView, Integer imagePath, int blueRadius) {
        Picasso.get()
                .load(imagePath)
                .transform(new PicassoBlurTransformation(context, blueRadius))
                .transform(new PicassoCircleTransform())
                .into(blurView);
    }

    @Override
    public void normalImage(Context context, ImageView normalView, Integer imagePath) {
        Picasso.get()
                .load(imagePath)
                .transform(new PicassoCircleTransform())
                .into(normalView);
    }

}
