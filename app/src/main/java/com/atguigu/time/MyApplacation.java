package com.atguigu.time;

import android.app.Application;

import org.xutils.x;

/**
 * Created by vence on 16/4/8 18:00
 * 邮箱 ：vence0815@foxmail.com
 */
public class MyApplacation extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.isDebug();
    }
}
