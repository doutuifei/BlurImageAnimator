package com.muzi.blurimageanimator.view;

import android.content.Context;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 作者: lipeng
 * 时间: 2018/7/18
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public abstract class BlurView extends FrameLayout {

    private final int MAX_BLUR_RADIUS = 10;

    private View blurView;
    private View normalView;
    private float curreAlpha = 0f;
    private int blueRadius;//模糊半径
    private Object imagePath;//图片地址

    public BlurView(@NonNull Context context) {
        this(context, null, 0);
    }

    public BlurView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlurView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        blurView = getImageView(getContext());
        normalView = getImageView(getContext());

        addView(blurView);
        addView(normalView);

        if (showBlueView()) {
            curreAlpha = 0f;
        } else {
            curreAlpha = 1f;
        }

        normalView.setAlpha(curreAlpha);

        blueRadius = getBlurRadius();
    }

    /**
     * 是否显示模糊后的图片
     *
     * @return
     */
    public boolean showBlueView() {
        return true;
    }

    /**
     * 最大模糊半径
     *
     * @return
     */
    public int getBlurRadius() {
        return MAX_BLUR_RADIUS;
    }

    /**
     * 设置图片
     *
     * @param imagePath
     */
    public void setImagePath(@NonNull Object imagePath) {
        this.imagePath = imagePath;
        if (imagePath != null) {
            blurImage(blurView, imagePath, blueRadius);
            normalImage(normalView, imagePath);
        }
    }

    /**
     * 动态改变透明度
     *
     * @param alpha
     */
    public void setImageAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (alpha != curreAlpha) {
            curreAlpha = alpha;
            normalView.setAlpha(alpha);
        }
    }

   protected abstract View getImageView(Context context);

    /**
     * 模糊图片加载
     *
     * @param blurView
     * @param imagePath
     * @param blueRadius
     */
   public abstract void blurImage(View blurView, Object imagePath, int blueRadius);

    /**
     * 正常图片加载
     *
     * @param blurView
     * @param imagePath
     */
    public abstract void normalImage(View blurView, Object imagePath);

}
