package com.atguigu.time;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.atguigu.time.base.BasePager;
import com.atguigu.time.pager.FindPager;
import com.atguigu.time.module_home.pager.HomePager;
import com.atguigu.time.pager.MinePager;
//import com.atguigu.time.pager.ShopPager;
import com.atguigu.time.pager.ShopPager;
import com.atguigu.time.pager.TicketBuyPager;
import com.atguigu.time.utils.SpUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private List<BasePager> mPagers;

    @ViewInject(R.id.rg_main)
    private RadioGroup rg_main;

    private int mPosition;
    public int cityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);

        cityId = SpUtils.getInstance(this).get("City", 290);
        Log.e("TAG", "cityId==" + cityId);
        initData();
    }

    //初始化数据
    private void initData() {
        mPagers = new ArrayList<>();
        mPagers.add(new HomePager(this));
        mPagers.add(new TicketBuyPager(this));
        mPagers.add(new ShopPager(this));
        mPagers.add(new FindPager(this));
        mPagers.add(new MinePager(this));

        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        rg_main.check(R.id.rb_home);//默认首页显示
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                default://默认首页
                    mPosition = 0;
                    break;
                case R.id.rb_payticket: //购票
                    mPosition = 1;
                    break;
                case R.id.rb_shop: // 购物
                    mPosition = 2;
                    break;
                case R.id.rb_find: // 发现
                    mPosition = 3;
                    break;
                case R.id.rb_mine: // 我的
                    mPosition = 4;
                    break;
            }
            setFragment();
        }

    }

    private void setFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_mian, new Fragment() {
            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                BasePager pager = getBasePager();
                if (null != pager)
                    return pager.mRootView;
                return null;
            }
        }).commit();

    }

    private BasePager getBasePager() {
        BasePager pager = mPagers.get(mPosition);
        if (null != pager && !pager.mInit) {
            pager.mInit = true;
            pager.initData();
        }
        return pager;
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            new AlertDialog.Builder(this)
                    .setMessage("确定退出吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }
        return true;
    }
}
