# 图片高斯模糊动画

## 前言
* 这里只讲高斯模糊动画的处理，不讨论高斯模糊的处理方式。想看高斯模糊处理的可以参考这个[Android 图片高斯模糊解决方案](https://www.jianshu.com/p/02da487a2f43)
* 通过```Bitmap```多次处理高斯模糊做动画，容易卡顿或者OOM。

## 预览
![image](https://github.com/mzyq/BlurImageAnimator/blob/aedcf4336444ab3e447f92eb60edc01df6385eaf/images/simple.gif)

## 使用

1. 设置默认图片
```java
 BlurCircleImageView  blurCircleImageView = findViewById(R.id.blurCircleImageView);
 blurCircleImageView.setImageResource(R.drawable.select_head_1);
```

2. 动画处理

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
* 底部放一张最大高斯模糊后的图片，使用```FrameLayout```在上面放一个没有模糊处理的图片。通过改变上层图片的```setAlpha(float alpha)```做到动画效果。
初始状态为显示高斯模糊图片。
* 代码中用```Fresco```做的模糊处理，具体用什么框架模糊处理随意，只需要修改以下代码即可

```java
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
```

[BlurCircleImageView](https://github.com/mzyq/BlurImageAnimator/blob/5a8f877dea24d656ff1315bbd81f63f084d44d7e/app/src/main/java/com/muzi/blurimageanimator/view/BlurCircleImageView.java)


