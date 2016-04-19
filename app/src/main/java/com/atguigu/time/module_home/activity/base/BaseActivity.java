package com.atguigu.time.module_home.activity.base;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Huanzi on 2016/4/19.
 */
public class BaseActivity extends AppCompatActivity {
    /**
     * 封装Toast
     * @param msg 字符信息
     */
    public void showTip(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 封装打开Activity
     * @param tClass
     */
    public void openActivity(Class<?> tClass){
        Intent it = new Intent(this, tClass);
        startActivity(it);
    }
    /**
     * 封装打开Activity
     * @param tClass
     */
    public void openActivity(Class<?> tClass, Bundle bundle){
        Intent it = new Intent(this, tClass);
        if(bundle != null){
            it.putExtras(bundle);
        }
        startActivity(it);
    }
}
