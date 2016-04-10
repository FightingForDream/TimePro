package com.atguigu.time.pager;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.atguigu.time.R;
import com.atguigu.time.activity.UserLoginActivity;
import com.atguigu.time.activity.UserRegistActivity;
import com.atguigu.time.base.BasePager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by vence on 16/4/8 18:17
 * 邮箱 ：vence0815@foxmail.com
 */
public class MinePager extends BasePager {
    /*
    用户登录按钮
     */
    @ViewInject(R.id.tv_login)
    private TextView tv_login;

    /*
    用户注册按钮
     */
    @ViewInject(R.id.tv_regist)
    private TextView tv_regist;


    public MinePager(Activity activity) {
        super(activity);
    }

    @Override
    protected View getView() {
        View view = View.inflate(mActivity, R.layout.mine_pager, null);
        x.view().inject(this, view);

        setListener();
        return view;
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();

    }

    /**
     * 设置各种监听
     */
    private void setListener() {
        tv_login.setOnClickListener(new MyclickListener());
        tv_regist.setOnClickListener(new MyclickListener());
    }


    class MyclickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_login:
                    mActivity.startActivity(new Intent(mActivity, UserLoginActivity.class));
                    break;
                case R.id.tv_regist:
                    mActivity.startActivity(new Intent(mActivity, UserRegistActivity.class));
                    break;
            }
        }
    }
}
