package com.atguigu.time.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 作者：徐阿海 on 2016/3/25:22:34
 * QQ:845514573
 * 功能：让ViewPager不能滑动
 */
public class NoscrollViewPager extends ViewPager {
    public NoscrollViewPager(Context context) {
        super(context);
    }

    public NoscrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //让ViewPager不能滑动
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
    /**
     * 如果该方法，返回true就相应，当前控件的onTouchEvent被触发
     * 如果该方法返回false,把事件传递给子视图
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
