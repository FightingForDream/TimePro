package com.atguigu.time.module_tickey.impletickbuy;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.time.pager.TicketBuyPager;


/**
 * 作用：影院详情页面
 */
public class CinemaPager extends TicketBuyPager {

    private TextView textView;


    public CinemaPager(Activity activity) {
        super(activity);
    }

    @Override
    public View getView() {
        textView = new TextView(mActivity);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);

        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        System.out.println("影院详情页面数据被初始化了...");
        textView.setText("影院详情页面的内容");
    }
}
