package com.atguigu.time;

import android.app.Application;

import com.atguigu.time.service.WriteLog;
import com.baidu.mapapi.SDKInitializer;

import org.xutils.x;

/**
 * Created by vence on 16/4/8 18:00
 * 邮箱 ：vence0815@foxmail.com
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);

        WriteLog.getInstance().init(); // 初始化日志
        SDKInitializer.initialize(getApplicationContext());
    }
}
