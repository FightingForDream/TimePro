package com.atguigu.time.module_tickey.base;

import android.app.Activity;
import android.view.View;

/**
 * 作者：徐阿海 on 2016/4/9:11:48
 * QQ:845514573
 * 功能：热映和即将上映页的公共基类
 */
public abstract class MovieTabBasePager {
    public boolean mInit;
    public View mRootView;
    public Activity mActivity;



    public MovieTabBasePager(Activity activity) {
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
