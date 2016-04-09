package com.atguigu.time.pager;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atguigu.time.R;
import com.atguigu.time.base.BasePager;

import org.xutils.x;

/**
 * 商城页面
 */
public class ShopPager extends BasePager {

    //主布局 包含titleBar和listView
    private LinearLayout shopView;
    //下拉刷新页面
    private SwipeRefreshLayout srl_shop_mall;
    //扫描二维码
    private ImageView iv_mall_home_scan;
    //购物车
    private ImageView iv_mall_home_cart_icon;

    public ShopPager(Activity activity) {
        super(activity);
    }

    /**
     * 初始化页面布局
     *
     * @return
     */
    @Override
    protected View getView() {
        //加载主布局
        shopView = (LinearLayout) View.inflate(mActivity, R.layout.shop_mall_layout, null);
        x.view().inject(this,shopView);

        srl_shop_mall = (SwipeRefreshLayout) shopView.findViewById(R.id.srl_shop_mall);
        iv_mall_home_scan= (ImageView) shopView.findViewById(R.id.iv_mall_home_scan);
        iv_mall_home_cart_icon = (ImageView) shopView.findViewById(R.id.iv_mall_home_cart_icon);

        //头部ViewPager

        return shopView;
    }
}
