package com.atguigu.time.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by SkyWalker on 2016/3/27.
 * 自定义的ViewPager
 * 解决内部无法切换的问题
 */
public class HorizontalScrollViewPager extends ViewPager {
    public HorizontalScrollViewPager(Context context) {
        super(context);
    }

    public HorizontalScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private float startX;
    private float startY;

    /**
     * 1.竖直方向滑动--不处理 getParent().requestDisallowInterceptTouchEvent(false);
     * <p/>
     * 2.水平方向滑动 2.1当水平方向滑动，并且滑动的页面是第0个的时候，并且滑动的方向是左到右（endX - startX>0）
     * getParent().requestDisallowInterceptTouchEvent(false);
     * 2.2当水平方向滑动，并且滑动的页面是最后一个的时候，并且滑动的方向是右到左（endX - startX <0）
     * getParent().requestDisallowInterceptTouchEvent(false);
     * <p/>
     * 2.3当滑动的时候在中间页面的时候 getParent().requestDisallowInterceptTouchEvent(true);
     * <p/>
     * 在按下事件中 getParent().requestDisallowInterceptTouchEvent(true);
     * <p/>
     * 判断竖直方向滑动和水平方向滑动
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //一旦触碰的时候就请求把事件传递给自己
                //不能少！
                getParent().requestDisallowInterceptTouchEvent(true);
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = ev.getX();
                float endY = ev.getY();

                //计算偏移量
                float distanceX = endX - startX;
                float distanceY = endY - startY;

                //判断是水平方向滑动还是竖直方向滑动
                if (Math.abs(distanceX) > Math.abs(distanceY)) {//水平滑动
                    //1.当滑动到第一个页面，并且方向是从左到右的滑动
                    if (getCurrentItem() == 0 && distanceX > 0) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        //2.当滑动到最后一个页面的时候，并且方向是从右到左滑动
                    } else if (getCurrentItem() == getAdapter().getCount() - 1 && distanceX < 0) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }else {
                        //3.其他情况
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                } else {
                    //竖直方向
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
