package com.atguigu.time.module_find.findinnerpager;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.time.module_find.base.BaseFindInnerPager;

/**
 * 发现————预告片页面
 * @author xpl
 * created at 2016/4/10 9:32
 */
public class Prevue extends BaseFindInnerPager {
    private TextView textView;

    public Prevue(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {

        textView =new TextView(mActivity);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("发现--预告片页面");

    }
}
