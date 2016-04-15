package com.atguigu.time.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Huanzi on 2016/4/15.
 */
public class XScrollView extends ScrollView {
    private OnScrollStateChangedListener mOnScrollStateChangedListener;

    public XScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public XScrollView(Context context) {
        super(context);
    }

    public void setOnScrollStateChangedListener(OnScrollStateChangedListener mOnScrollStateChangedListener) {
        this.mOnScrollStateChangedListener = mOnScrollStateChangedListener;
    }

    /**
     * 监听ScrollView滑动
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        mOnScrollStateChangedListener.OnScrollStateChanged(l, t, oldl, oldt);
    }

    public interface OnScrollStateChangedListener{
        void OnScrollStateChanged(int left, int top, int preLeft, int preTop);
    }
}
