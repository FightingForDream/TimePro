package com.atguigu.time.module_home.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.time.bean.HomeHeaderBean;
import com.atguigu.time.module_home.activity.TopPosterActivity;
import com.atguigu.time.module_home.activity.base.BaseActivity;
import com.atguigu.time.utils.Constants;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 首页ViewPagerAdapter
 * Created by Huanzi on 2016/4/9.
 */
public class HomeHeaderAdapter extends PagerAdapter implements Constants{
    private HomeHeaderBean headerBean;
    //private List<HomeHeaderBean.TopPostersBean> posterList;
    private List<SimpleDraweeView> views;
    private Activity activity;

    public HomeHeaderAdapter(Activity activity, List<SimpleDraweeView> views, HomeHeaderBean headerBean) {
        this.activity = activity;
        this.views = views;
        this.headerBean = headerBean;
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
    public Object instantiateItem(ViewGroup container, final int position) {
        //View.inflate(activity, R.layout.home_viewpager_item, null);
        container.addView(views.get(position));
        String url = (String) views.get(position).getTag();
        SimpleDraweeView sdv = views.get(position);
        sdv.setImageURI(Uri.parse(url));
        //activity.showTips(Uri.parse(url) + "");
        //Log.e("instantiateItem", Uri.parse(url) + "");
        sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(POSTER_URL, headerBean.getTopPosters().get(position).getGotoPage().getUrl());
                //activity.openActivity(TopPosterActivity.class);
                Intent it = new Intent(activity, TopPosterActivity.class);
                it.putExtras(bundle);
                activity.startActivity(it);
            }
        });
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    private View.OnClickListener showHeaderAdDetailListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent it = new Intent(activity, TopPosterActivity.class);
            activity.startActivity(it);
            //activity.openActivity(TopPosterActivity.class);
        }
    };
}
