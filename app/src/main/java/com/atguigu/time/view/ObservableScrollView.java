package com.atguigu.time.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 自定义ScrollView
 */
public class ObservableScrollView extends ScrollView {

    private ScrollViewListener scrollViewLintener = null;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr, ScrollViewListener scrollViewLintener) {
        super(context, attrs, defStyleAttr);
        this.scrollViewLintener = scrollViewLintener;
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewLintener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewLintener != null) {
            scrollViewLintener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    public interface ScrollViewListener {
        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);

    }
}
