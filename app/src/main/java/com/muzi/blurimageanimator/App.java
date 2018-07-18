package com.muzi.blurimageanimator;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 作者: lipeng
 * 时间: 2018/7/18
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
