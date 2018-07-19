package com.muzi.blurimageanimator.fresco;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.facebook.drawee.view.SimpleDraweeView;
import com.muzi.blurimageanimator.R;
import com.muzi.blurimageanimator.utils.BlurBitmapUtil;
import com.muzi.library.BlurView;

/**
 * 作者: lipeng
 * 时间: 2018/7/18
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class FrescoBlurView extends BlurView<SimpleDraweeView, Integer> {

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
    protected SimpleDraweeView getImageView(Context context) {
        SimpleDraweeView view = (SimpleDraweeView) LayoutInflater.from(context).inflate(R.layout.view_fresco_sample, null);
        return view;
    }

    @Override
    public void blurImage(Context context,SimpleDraweeView blurView, Integer imagePath, int blueRadius) {
        Uri uri = Uri.parse("res:///" + imagePath);
        BlurBitmapUtil.instance().frescoBlurImageUri(blurView, uri, 3, blueRadius);
    }

    @Override
    public void normalImage(Context context,SimpleDraweeView normalView, Integer imagePath) {
        Uri uri = Uri.parse("res:///" + imagePath);
        normalView.setImageURI(uri);
    }

    @Override
    public boolean showBlueView() {
        return super.showBlueView();
    }

    @Override
    public int getBlurRadius() {
        return super.getBlurRadius();
    }

}
