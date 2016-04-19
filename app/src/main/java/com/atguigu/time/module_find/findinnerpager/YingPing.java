package com.atguigu.time.module_find.findinnerpager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.time.module_find.base.BaseFindInnerPager;

/**
 * 发现--影评页面
 * @author xpl
 * created at 2016/4/10 9:33
 */
public class YingPing extends BaseFindInnerPager {
    private TextView textView;

    public YingPing(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        textView = new TextView(mActivity);
        textView.setTextColor(Color.RED);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("发现--影评页面");

    }
}
