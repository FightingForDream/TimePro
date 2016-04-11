package com.atguigu.time.module_find.base;

import android.app.Activity;
import android.view.View;

/**
 * 发现页面中的各个页面的基类
 * @author xpl
 * created at 2016/4/10 9:19
 */
public abstract class BaseFindInnerPager {

    /**
     * 上下文，在创建BasePager时，通过构造器传入
     */
    public Activity mActivity;

    //根View,代表BasePager长什么样子
    public View rootView;

    public BaseFindInnerPager(Activity activity) {
        mActivity = activity;
        rootView = initView();
    }

    public abstract View initView() ;

    public void initData() {

    }
}
