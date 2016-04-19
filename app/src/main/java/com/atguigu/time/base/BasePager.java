package com.atguigu.time.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by vence on 16/4/8 16:18
 * 邮箱 ：vence0815@foxmail.com
 */
public abstract class BasePager extends Activity{

    public boolean mInit;
    public View mRootView;
    public Activity mActivity;


    public BasePager(Activity activity) {
        mActivity = activity;
        mRootView = getView();
    }

    /**
     * 获取当前详情页面视图, 强制子类实现
     */
    protected abstract View getView();

    /**
     * 初始化方法, 留给子类实现, 在页面加载后调用
     */
    public void initData() {
    }


}
