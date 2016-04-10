package com.atguigu.time.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 作用：自定义ViewPager
 * 解决内部无法切换问题
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


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                //不能少呀
                getParent().requestDisallowInterceptTouchEvent(true);
                //1.记录坐标
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //2.记录结束点
                float endX = ev.getX();
                float endY = ev.getY();

                //3.计算偏移量
                float distenceX = endX - startX;
                float distenceY = endY - startY;

                //4.判断是水平方向滑动还是竖直方向滑动
                if(Math.abs(distenceX) > Math.abs(distenceY) ){//水平方向滑动
//                    1.当滑动到第一个页面，并且方向是从左到右的滑动
//                    endX - startX > 0 那么方向就是：从左往右滑动
                    if(getCurrentItem()==0&&distenceX >0){
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
//                    getParent().requestDisallowInterceptTouchEvent(false);
//
//                    2.当滑动到最后一个页面的时候，并且方向是从右到左滑动
//                    endX - startX < 0 那么方向就是：从右往左滑动
                    else if(getCurrentItem()==getAdapter().getCount()-1&&distenceX <0){
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
//                    getParent().requestDisallowInterceptTouchEvent(false);
                    else{
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
//
//                    3.其他情况
//                    getParent().requestDisallowInterceptTouchEvent(true);
                }else{
                    //竖直方向滑动
                    getParent().requestDisallowInterceptTouchEvent(false);

                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
