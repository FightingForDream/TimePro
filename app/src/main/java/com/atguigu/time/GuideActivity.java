package com.atguigu.time;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.atguigu.time.adapter.GuideAdapter;
import com.atguigu.time.bean.City;
import com.atguigu.time.utils.CacheUtils;

public class GuideActivity extends Activity {

    private ViewPager vp_guide;
    private Button btn_start_main;

    //引导图片
    private int[] drawableIds = {R.drawable.lead_bg1, R.drawable.lead_bg2,
            R.drawable.lead_bg3, R.drawable.lead_bg4};

    //底部图片
    private int[] bottomViewIds = {R.drawable.lead_bg1_iv, R.drawable.lead_bg2_iv,
            R.drawable.lead_bg3_iv, R.drawable.lead_bg4_iv};

    public static final String GuideActivity_IsShow = "1";

    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initData() {
        city = (City) getIntent().getSerializableExtra("City");
        vp_guide.setAdapter(new GuideAdapter(this, drawableIds, bottomViewIds));

        vp_guide.addOnPageChangeListener(new MyPagerChangerListener());
        btn_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CacheUtils.putBoolean(GuideActivity.this, GuideActivity_IsShow, true);
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                intent.putExtra("City",city);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        setContentView(R.layout.activity_guide);
        vp_guide = (ViewPager)findViewById(R.id.vp_guide);
        btn_start_main = (Button) findViewById(R.id.btn_start_main);
    }


    class MyPagerChangerListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == drawableIds.length - 1) {
                btn_start_main.setVisibility(View.VISIBLE);
            } else {
                btn_start_main.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
