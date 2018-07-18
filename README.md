# 图片高斯模糊动画

## 预览

## 使用

* ObjectAnimator

```java
ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(blurCircleImageView, "ImageAlpha", 0, 1, 0);
objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
objectAnimator.setDuration(2000);
objectAnimator.start();
```

* ValueAnimator
```java
ValueAnimator animator = ValueAnimator.ofFloat(0, 1, 0);
animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float alpha= (float) animation.getAnimatedValue();
        blurCircleImageView.setImageAlpha(alpha);
    }
});
animator.setRepeatCount(ValueAnimator.INFINITE);
animator.setDuration(2000);
animator.start();
```

## 源码
> 底部放一张最大高斯模糊后的图片，使用```FrameLayout```在上面放一个没有模糊处理的图片。通过改变上层图片的```setAlpha(float alpha)```做到动画效果。
初始状态为显示高斯模糊图片。

