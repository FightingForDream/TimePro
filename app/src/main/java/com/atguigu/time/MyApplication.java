package com.atguigu.time;

import android.app.Application;
import android.content.Context;

import com.atguigu.time.api.VolleyManager;
import com.atguigu.time.service.WriteLog;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.xutils.x;

/**
 * Created by vence on 16/4/8 18:00
 * 邮箱 ：vence0815@foxmail.com
 */
public class MyApplication extends Application {

    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);

        WriteLog.getInstance().init(); // 初始化日志
        SDKInitializer.initialize(getApplicationContext());

        Fresco.initialize(this);  //初始化Fresco
        VolleyManager.getInstance(this); //初始化VolleyManager

        sContext = getApplicationContext();//得到一个全局的context
    }

    public static Context getContext() {
        return sContext;
    }
}
