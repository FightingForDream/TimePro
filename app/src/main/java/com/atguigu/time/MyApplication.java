package com.atguigu.time;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.atguigu.time.api.VolleyManager;
import com.atguigu.time.service.WriteLog;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;


public class MyApplication extends Application {

    private static Context sContext;
    //Volley的请求队列
    public static RequestQueue queue;

    public static RequestQueue getHttpQueue() {
        return queue;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);


        WriteLog.getInstance().init(); // 初始化日志
        SDKInitializer.initialize(getApplicationContext());

        Fresco.initialize(this);  //初始化Fresco
        VolleyManager.getInstance(this); //初始化VolleyManager

        queue= Volley.newRequestQueue(getApplicationContext());
        sContext = getApplicationContext();//得到一个全局的context

        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);

        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);
    }


    public static Context getContext() {
        return sContext;
    }
}
