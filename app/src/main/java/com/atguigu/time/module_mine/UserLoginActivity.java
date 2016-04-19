package com.atguigu.time.module_mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.time.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by vence on 16/4/10 16:24
 * 邮箱 ：vence0815@foxmail.com
 * 用户登录的界面
 */
public class UserLoginActivity extends Activity {

    /*
    跳转到注册按钮
     */
    @ViewInject(R.id.tv_to_register)
    private TextView tv_to_register;

    /*
    用户账号
     */
    @ViewInject(R.id.et_user_number)
    private EditText et_user_number;

    /*
    用户密码
     */
    @ViewInject(R.id.et_password)
    private EditText et_password;

    /*
    微博登录
     */
    @ViewInject(R.id.iv_weibo_login)
    private ImageView iv_weibo_login;

    /*
    qq登录
     */
    @ViewInject(R.id.iv_qq_login)
    private ImageView iv_qq_login;

    /*
    微信登录
     */
    @ViewInject(R.id.iv_weixin_login)
    private ImageView iv_weixin_login;

    /*
    回退操作
     */
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        x.view().inject(this);
        setListener();
    }

    /**
     * 设置各种监听
     */
    private void setListener() {
        tv_to_register.setOnClickListener(new MyonClickListener());
        iv_back.setOnClickListener(new MyonClickListener());
        iv_weibo_login.setOnClickListener(new MyonClickListener());
        iv_qq_login.setOnClickListener(new MyonClickListener());
        iv_weixin_login.setOnClickListener(new MyonClickListener());
    }

    /**
     * 点击登录的按钮
     *
     * @param v
     */
    public void login(View v) {

    }

    class MyonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_to_register://跳转到注册页面
                    Intent intent = new Intent(UserLoginActivity.this, UserRegistActivity.class);
                    startActivity(intent);
                    break;
                case R.id.iv_back://回退
                    finish();
                    break;
                case R.id.iv_weibo_login://微博登录
                    Toast.makeText(UserLoginActivity.this, "微博登录", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_qq_login://qq登录
                    Toast.makeText(UserLoginActivity.this, "qq登录", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_weixin_login://微信登录
                    Toast.makeText(UserLoginActivity.this, "微信登录", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}
