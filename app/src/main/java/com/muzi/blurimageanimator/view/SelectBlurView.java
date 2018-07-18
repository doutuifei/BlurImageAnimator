package com.muzi.blurimageanimator.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import com.muzi.blurimageanimator.R;

/**
 * 作者: lipeng
 * 时间: 2018/7/17
 * 邮箱: lipeng@moyi365.com
 * 功能: 图片动画
 */
public class SelectBlurView extends RelativeLayout {

    private long duration = 800;
    private long startDelay = 200;

    private ValueAnimator animator1;
    private ValueAnimator animator2;
    private ValueAnimator animator3;
    private ValueAnimator animator4;
    private ValueAnimator[] valueAnimators = new ValueAnimator[4];

    private int[] imagesResources = new int[]{
            R.drawable.select_head_1,
            R.drawable.select_head_2,
            R.drawable.select_head_3,
            R.drawable.select_head_4,
            R.drawable.select_head_5,
            R.drawable.select_head_6,
            R.drawable.select_head_7,
            R.drawable.select_head_8,
            R.drawable.select_head_9,
            R.drawable.select_head_10,
            R.drawable.select_head_11,
            R.drawable.select_head_12,
            R.drawable.select_head_13,
            R.drawable.select_head_14,
            R.drawable.select_head_15,
            R.drawable.select_head_16,
            R.drawable.select_head_17,
            R.drawable.select_head_18,
            R.drawable.select_head_19,
            R.drawable.select_head_20
    };
    private BlurCircleImageView[] ivHeads = new BlurCircleImageView[11];

    public SelectBlurView(Context context) {
        this(context, null, 0);
    }

    public SelectBlurView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectBlurView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_select_blur, this, true);

        ivHeads[0] = findViewById(R.id.image1);
        ivHeads[1] = findViewById(R.id.image2);
        ivHeads[2] = findViewById(R.id.image3);
        ivHeads[3] = findViewById(R.id.image4);
        ivHeads[4] = findViewById(R.id.image5);
        ivHeads[5] = findViewById(R.id.image6);
        ivHeads[6] = findViewById(R.id.image7);
        ivHeads[7] = findViewById(R.id.image8);
        ivHeads[8] = findViewById(R.id.image9);
        ivHeads[9] = findViewById(R.id.image10);
        ivHeads[10] = findViewById(R.id.image11);

        getImages();
        initAnimator();
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setStartDelay(long startDelay) {
        this.startDelay = startDelay;
    }

    /**
     * 开始动画
     */
    public void startAnimator() {
        getImages();
        if (animator1 == null) {
            initAnimator();
        }
        animator1.start();
    }

    /**
     * 关闭动画
     */
    public void closeAnimator() {
        closeAnimator(false);
    }

    /**
     * 关闭动画，并移除Listeners
     *
     * @param removeListeners
     */
    public void closeAnimator(boolean removeListeners) {
        for (ValueAnimator animator : valueAnimators) {
            if (animator != null) {
                if (animator.isStarted() || animator.isRunning()) {
                    animator.cancel();
                }
                if (removeListeners) {
                    animator.removeAllListeners();
                    animator.removeAllUpdateListeners();
                }
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        closeAnimator(true);
    }

    /**
     * 随机抽选11张图片
     *
     * @return
     */
    private int[] getImages() {
        int[] arr = new int[11];
        for (int i = 0; i < arr.length; i++) {
            int index = (int) (Math.random() * 20);//生产一个0-19的随机数
            arr[i] = imagesResources[index];
            for (int j = 0; j < i; j++) {//（遍历数组中储存进去的值，i中有几个值则循环几次）
                if (arr[j] == arr[i]) {//把储存在数组中的值j 和 随机出的值i 做比较
                    i--; //数组的值下标-1，i的循环次数回到上次
                    break;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            ivHeads[i].setImageResource(arr[i]);
        }
        return arr;
    }

    /**
     * 初始化动画
     */
    private void initAnimator() {
        //1、3、4聚焦
        animator1 = getValueAnimator();
        animator1.setStartDelay(0);
        valueAnimators[0] = animator1;
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                ivHeads[0].setImageAlpha(alpha);
                ivHeads[2].setImageAlpha(alpha);
                ivHeads[3].setImageAlpha(alpha);
            }
        });
        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ivHeads[0].setImageAlpha(1);
                ivHeads[2].setImageAlpha(1);
                ivHeads[3].setImageAlpha(1);

                animator2.start();
            }
        });

        //1、3、4失焦，2、6、7、9聚焦
        animator2 = getValueAnimator();
        valueAnimators[1] = animator2;
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                ivHeads[0].setImageAlpha(1 - alpha);
                ivHeads[2].setImageAlpha(1 - alpha);
                ivHeads[3].setImageAlpha(1 - alpha);
                ivHeads[1].setImageAlpha(alpha);
                ivHeads[5].setImageAlpha(alpha);
                ivHeads[6].setImageAlpha(alpha);
                ivHeads[8].setImageAlpha(alpha);
            }
        });
        animator2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ivHeads[0].setImageAlpha(0);
                ivHeads[2].setImageAlpha(0);
                ivHeads[3].setImageAlpha(0);
                ivHeads[1].setImageAlpha(1);
                ivHeads[5].setImageAlpha(1);
                ivHeads[6].setImageAlpha(1);
                ivHeads[8].setImageAlpha(1);

                animator3.start();
            }
        });

        //2、6、7、9失焦,5、8、10、11聚焦
        animator3 = getValueAnimator();
        valueAnimators[2] = animator3;
        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                ivHeads[1].setImageAlpha(1 - alpha);
                ivHeads[5].setImageAlpha(1 - alpha);
                ivHeads[6].setImageAlpha(1 - alpha);
                ivHeads[8].setImageAlpha(1 - alpha);
                ivHeads[4].setImageAlpha(alpha);
                ivHeads[7].setImageAlpha(alpha);
                ivHeads[9].setImageAlpha(alpha);
                ivHeads[10].setImageAlpha(alpha);
            }
        });
        animator3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ivHeads[1].setImageAlpha(0);
                ivHeads[5].setImageAlpha(0);
                ivHeads[6].setImageAlpha(0);
                ivHeads[8].setImageAlpha(0);
                ivHeads[4].setImageAlpha(1);
                ivHeads[7].setImageAlpha(1);
                ivHeads[9].setImageAlpha(1);
                ivHeads[10].setImageAlpha(1);

                animator4.start();
            }
        });

        //5、8、10、11失焦
        animator4 = getValueAnimator();
        valueAnimators[3] = animator4;
        animator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                ivHeads[4].setImageAlpha(1 - alpha);
                ivHeads[7].setImageAlpha(1 - alpha);
                ivHeads[9].setImageAlpha(1 - alpha);
                ivHeads[10].setImageAlpha(1 - alpha);
            }
        });
        animator4.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ivHeads[4].setImageAlpha(0);
                ivHeads[7].setImageAlpha(0);
                ivHeads[9].setImageAlpha(0);
                ivHeads[10].setImageAlpha(0);

                animator1.start();
                animator1.setStartDelay(startDelay);
            }
        });
    }

    private ValueAnimator getValueAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1f);
        animator.setDuration(duration);
        animator.setStartDelay(startDelay);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }

}
