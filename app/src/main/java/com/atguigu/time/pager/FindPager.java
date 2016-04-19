package com.atguigu.time.pager;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.time.R;
import com.atguigu.time.module_find.base.BaseFindInnerPager;
import com.atguigu.time.base.BasePager;
import com.atguigu.time.module_find.findinnerpager.News;
import com.atguigu.time.module_find.findinnerpager.PaiHangBang;
import com.atguigu.time.module_find.findinnerpager.Prevue;
import com.atguigu.time.module_find.findinnerpager.YingPing;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现页面
 * @author xpl
 * created at 2016/4/9 19:35
 */
public class FindPager extends BasePager {

    //四个页面的集合
    private List<BaseFindInnerPager> baseFindInnerPagers;

    //tableLayout的标题
    private String[] title = new String[]{"新闻","预告片","排行榜","影评"};


    @ViewInject(R.id.tl_find_pager)
    private TabLayout tl_find_pager;

    @ViewInject(R.id.vp_find_pager)
    private ViewPager vp_find_pager;

    private int currentPosition = 0;

    public FindPager(Activity activity) {
        super(activity);
    }

    @Override
    protected View getView() {
        View view = View.inflate(mActivity, R.layout.find_pager,null);
        x.view().inject(this, view);
        return view;
    }
    @Override
    public void initData() {
        super.initData();
        if(baseFindInnerPagers == null){
            baseFindInnerPagers = new ArrayList<>();
            baseFindInnerPagers.add(new News(mActivity));//添加新闻页面
            baseFindInnerPagers.add(new Prevue(mActivity));//添加预告片页面
            baseFindInnerPagers.add(new PaiHangBang(mActivity));//添加排行榜页面
            baseFindInnerPagers.add(new YingPing(mActivity));//添加影评页面
        }
        //viewPager的适配器
        vp_find_pager.setAdapter(new MyViewPagerAdapter());
        //viewpager页面改变的监听
        vp_find_pager.addOnPageChangeListener(new FindPagerOnPageChangeListener());
        //关联viewpager
        tl_find_pager.setupWithViewPager(vp_find_pager);
        //设置为滚动模式
        tl_find_pager.setTabMode(TabLayout.MODE_SCROLLABLE);
        //定位当前的页面
        vp_find_pager.setCurrentItem(currentPosition);
        baseFindInnerPagers.get(currentPosition).initData();
    }
    class MyViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return baseFindInnerPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BaseFindInnerPager baseFindInnerPager = baseFindInnerPagers.get(position);
            View rootView = baseFindInnerPager.rootView;
            if(container == null) {
                Log.e("TAG", "===========================");
            }
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
    /**
     * ViewPager页面改变监听的实现类
     */
    class FindPagerOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            BaseFindInnerPager baseFindInnerPager = baseFindInnerPagers.get(position);
            baseFindInnerPager.initData();
            currentPosition = position;
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}