package com.atguigu.time.adapter;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.time.bean.HomeHeaderBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 首页ViewPagerAdapter
 * Created by Huanzi on 2016/4/9.
 */
public class HomeHeaderAdapter extends PagerAdapter {
    private HomeHeaderBean viewPagerBean;
    private List<HomeHeaderBean.AdvListBean> advList;
    private List<SimpleDraweeView> views;
    private Activity activity;

    public HomeHeaderAdapter(Activity activity, List<SimpleDraweeView> views) {
        this.activity = activity;
        this.views = views;
        //this.advList = viewPagerBean.getAdvList();
    }

    @Override
    public int getCount() {
        return views == null ? 0 : views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //View.inflate(activity, R.layout.home_viewpager_item, null);
        container.addView(views.get(position));
        String url = (String) views.get(position).getTag();
        views.get(position).setImageURI(Uri.parse(url));
        //activity.showTips(Uri.parse(url) + "");
        //Log.e("instantiateItem", Uri.parse(url) + "");
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}
