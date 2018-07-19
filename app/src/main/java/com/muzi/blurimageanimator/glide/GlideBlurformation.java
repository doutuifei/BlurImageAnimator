package com.muzi.blurimageanimator.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.muzi.blurimageanimator.utils.BlurBitmapUtil;

import java.security.MessageDigest;

/**
 * 作者: lipeng
 * 时间: 2018/7/19
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class GlideBlurformation extends BitmapTransformation {

    private Context context;
    private int blurRadius = 10;

    public GlideBlurformation(Context context) {
        this.context = context;
    }

    public GlideBlurformation(Context context, int blurRadius) {
        this.context = context;
        this.blurRadius = blurRadius;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return BlurBitmapUtil.instance().blurBitmap(context, toTransform, blurRadius, outWidth, outHeight);
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }

}

