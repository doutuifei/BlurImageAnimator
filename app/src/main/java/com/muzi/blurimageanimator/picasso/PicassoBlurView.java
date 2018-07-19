package com.muzi.blurimageanimator.picasso;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.muzi.blurimageanimator.R;
import com.muzi.library.BlurView;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * 作者: lipeng
 * 时间: 2018/7/19
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class PicassoBlurView extends BlurView<ImageView, String> {

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
    public void blurImage(Context context, ImageView blurView, String imagePath, int blueRadius) {
        Picasso.get()
                .load(imagePath)
                .transform(new BlurTransformation(context, blueRadius))
                .transform(new CropCircleTransformation())
                .into(blurView);
    }

    @Override
    public void normalImage(Context context, ImageView normalView, String imagePath) {
        Picasso.get()
                .load(imagePath)
                .transform(new CropCircleTransformation())
                .into(normalView);
    }

}
