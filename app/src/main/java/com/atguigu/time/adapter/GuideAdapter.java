package com.atguigu.time.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.time.R;

/**
 * Created by SkyWalker on 2016/4/9.
 * 引导页面的adapter
 */
public class GuideAdapter extends PagerAdapter {

    //ViewPager图片
    private int[] images;
    //底部图片 圆点
    private int[] bottoms;

    private Context context;

    public GuideAdapter(Context context ,int[] images, int[] bottoms) {
        this.images = images;
        this.bottoms = bottoms;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.guide_item, null);
        ImageView guideView = (ImageView) view.findViewById(R.id.iv_item_guide_bg);
        ImageView bottomView = (ImageView) view.findViewById(R.id.iv_item_guide_bottom);
        ImageView desView= (ImageView) view.findViewById(R.id.iv_des);
        guideView.setImageResource(images[position]);
        bottomView.setImageResource(bottoms[position]);

        if(position==images.length-1) {
            desView.setVisibility(View.VISIBLE);
        }else {
            desView.setVisibility(View.GONE);
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
