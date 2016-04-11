package com.atguigu.time.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by SkyWalker on 2016/3/25.
 * 屏蔽滑动的自定义ViewPager
 */
public class NoscrollViewPager extends ViewPager {
    public NoscrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoscrollViewPager(Context context) {
        super(context);
    }

    /**
     * 不做任何处理
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    /**
     * 如果该方法返回true就响应当前控件的onTouchEvent
     * 返回false就把事件传递给子视图
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
