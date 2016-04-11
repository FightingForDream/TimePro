package com.atguigu.time.module_tickey.impletickbuy;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.time.MainActivity;
import com.atguigu.time.R;
import com.atguigu.time.module_tickey.afragment.HotMovieFragment;
import com.atguigu.time.module_tickey.afragment.WillOnMovieFragment;
import com.atguigu.time.pager.TicketBuyPager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * 作用：电影详情页面
 */
public class MoviePager extends TicketBuyPager {

    @ViewInject(R.id.movie_indicator)
    private TabLayout movie_indicator;

    @ViewInject(R.id.vp_movie)
    private ViewPager vp_movie;

    private RelativeLayout rl_loading;
    private TextView tv_loading;
    private ImageView iv_loading_failed, iv_loading,iv_ad;
    //引导图片
// 1   private int[] drawableIds = {R.drawable.lead_bg1, R.drawable.lead_bg2};

    /**
     * ViewPager中嵌套listView等
     * 第一步
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;


    public MoviePager(Activity activity) {
        super(activity);

    }

    @Override
    public View getView() {
        View view = View.inflate(mActivity, R.layout.moviepager, null);
        x.view().inject(this, view);

        //动画
        iv_loading = (ImageView) view.findViewById(R.id.iv_loading);
        iv_loading.setBackgroundResource(R.drawable.animation_list);

        AnimationDrawable animationDrawable = (AnimationDrawable) iv_loading.getBackground();
        animationDrawable.start();


        rl_loading = (RelativeLayout) view.findViewById(R.id.rl_loading);
        rl_loading.setVisibility(View.GONE);

        tv_loading = (TextView) view.findViewById(R.id.tv_loading);
        iv_loading = (ImageView) view.findViewById(R.id.iv_loading);
        iv_loading_failed = (ImageView) view.findViewById(R.id.iv_loading_failed);

        iv_ad = (ImageView) view.findViewById(R.id.iv_ad);//广告图片

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        System.out.println("影院详情页面数据被初始化了...");

        /**
         * 第二步
         */
        MainActivity activity = (MainActivity) mActivity;//因为MainActivity必须继承FragmentActivity才能得到下面的方法

        mSectionsPagerAdapter = new SectionsPagerAdapter(activity.getSupportFragmentManager());

        //设置适配器
        vp_movie.setAdapter(mSectionsPagerAdapter);

        //关联页签页面与ViewPager
        movie_indicator.setupWithViewPager(vp_movie);
        movie_indicator.setTabGravity(TabLayout.GRAVITY_CENTER);


//        vp_movie.addOnPageChangeListener(new MyPagerChangerListener());
    }

    /**
     * ViewPager添加ListView的Adapter
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //切换两个ListView页面
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    return new HotMovieFragment();
                }
                case 1: {
                    return new WillOnMovieFragment();
                }

            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "正在热映";
                case 1:
                    return "即将上映";
            }
            return null;
        }
    }


//    class MyPagerChangerListener implements ViewPager.OnPageChangeListener {
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//        }
//
//        @Override
//        public void onPageSelected(int position) {
////            if (position == drawableIds.length - 1) {
////                btn_start_main.setVisibility(View.VISIBLE);
////            } else {
////                btn_start_main.setVisibility(View.GONE);
////            }
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//
//        }
//    }


    /**
     * 填装热映和即将上映两个ListView的Adapter
     */
//    class MoviePagerAdapter extends PagerAdapter {
//        @Override
//        public CharSequence getPageTitle(int position) {
//            if(position == 0) {
//                return "正在热映";
//            }else {
//                return "即将上映";
//            }
//        }
//
//        @Override
//        public int getCount() {
//            return drawableIds.length;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view==object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            ImageView imageView = new ImageView(mActivity);
//            imageView.setBackgroundResource(drawableIds[position]);
//            container.addView(imageView);//图片加入到ViewPager中 ViewPager的父类是ViewGroup
//            return imageView;//可以
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//    }


}
