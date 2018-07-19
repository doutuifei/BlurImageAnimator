# 图片高斯模糊动画

## 前言
* 这里只讲高斯模糊动画的处理，不讨论高斯模糊的处理方式。想看高斯模糊处理的可以参考这个[Android 图片高斯模糊解决方案](https://www.jianshu.com/p/02da487a2f43)
* 通过```Bitmap```多次处理高斯模糊做动画，容易卡顿或者OOM。
* 优点：解耦，易扩展

## 预览
![image](https://github.com/mzyq/BlurImageAnimator/blob/aedcf4336444ab3e447f92eb60edc01df6385eaf/images/simple.gif)


## 使用
> 思路：底部放一张最大高斯模糊后的图片，使用```FrameLayout```在上面放一个没有模糊处理的图片。通过改变上层图片的```setAlpha(float alpha)```做到动画效果。
初始状态为显示高斯模糊图片。

* 代码中用```Fresco```做的模糊处理，具体用什么框架模糊处理随意，只需要修改以下代码即可

1. 继承```BlurView```，并重写以下三个方法

```java

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

```

[可参考FrescoBlurView.java](https://github.com/mzyq/BlurImageAnimator/blob/c78deb3a71a6d24a5df618f05a6253068148f884/app/src/main/java/com/muzi/blurimageanimator/view/FrescoBlurView.java)

2. 设置需要加载的图片路径
```java
   public void setImagePath(Object imagePath) {}
```

3. 改变模糊程度/改变图片透明度
```java
   public void setImageAlpha(float alpha) {}
```

4. 动画：支持ValueAnimator和ObjectAnimator

  * ObjectAnimator动画

    ```java
    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(blurCircleImageView, "ImageAlpha", 0, 1, 0);
    objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
    objectAnimator.setDuration(2000);
    objectAnimator.start();
    ```

  * ValueAnimator动画

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

5. 其他方法

 * 设置最大模糊半径，默认为16

 ```java
   public int getBlurRadius() {
           return super.getBlurRadius();
       }
 ```

 * 显示模糊图片，默认显示模糊图片

 ```java
   public boolean showBlueView() {
           return super.showBlueView();
       }
 ```