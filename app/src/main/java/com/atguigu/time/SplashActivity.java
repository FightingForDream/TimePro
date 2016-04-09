package com.atguigu.time;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.atguigu.time.utils.CacheUtils;

public class SplashActivity extends Activity {
    private RelativeLayout rl_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_welcome = (RelativeLayout) findViewById(R.id.rl_welcome);
        startAnimation();
    }

    /**
     * 闪屏页的动画
     */
    private void startAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setFillAfter(true);
        animation.setDuration(3000);
        rl_welcome.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boolean isShow = CacheUtils.getBoolean(SplashActivity.this, GuideActivity.GuideActivity_IsShow);
                if (isShow) {
                    //曾经进入过主页面
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                    startActivity(intent);
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
