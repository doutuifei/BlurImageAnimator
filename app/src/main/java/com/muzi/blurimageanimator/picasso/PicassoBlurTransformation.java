package com.muzi.blurimageanimator.picasso;

import android.content.Context;
import android.graphics.Bitmap;

import com.muzi.blurimageanimator.utils.BlurBitmapUtil;
import com.squareup.picasso.Transformation;

/**
 * 作者: lipeng
 * 时间: 2018/7/19
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class PicassoBlurTransformation implements Transformation {

    private Context context;
    private int blurRadius = 10;

    public PicassoBlurTransformation(Context context) {
        this.context = context;
    }

    public PicassoBlurTransformation(Context context, int blurRadius) {
        this.context = context;
        this.blurRadius = blurRadius;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        return BlurBitmapUtil.instance().blurBitmap(context, source, blurRadius, source.getWidth(), source.getHeight());
    }


    @Override
    public String key() {
        return "blur";
    }
}
