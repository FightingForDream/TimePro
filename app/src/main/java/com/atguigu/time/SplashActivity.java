package com.atguigu.time;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.atguigu.time.api.Url;
import com.atguigu.time.bean.City;
import com.atguigu.time.service.LocationService;
import com.atguigu.time.utils.CacheUtils;
import com.atguigu.time.utils.SpUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


public class SplashActivity extends Activity {
    private RelativeLayout rl_welcome;

    private LocationService locationService;
    public Vibrator mVibrator;
    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_welcome = (RelativeLayout) findViewById(R.id.rl_welcome);

        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);

        locationService.start();// 定位SDK
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

    @Override
    protected void onStart() {
        super.onStart();
        //获取locationService实例，建议应用中只始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }
    }

    /**
     * 在此方法中获取地理信息的经纬度
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            double latitude = location.getLatitude();//获取纬度
            double longitude = location.getLongitude();//获取经度
            //http://api.m.mtime.cn/GetCityByLongitudelatitude.api?longitude=116.386641&latitude=40.105512&cityName=
            Log.e("TAG", "latitude==" + latitude + "longitude==" + longitude);

            RequestParams params = new RequestParams(Url.GET_CITY + "longitude=" + longitude + "&latitude=" + latitude+"&cityName=");
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    city = new Gson().fromJson(result, City.class);
                    SpUtils.getInstance(SplashActivity.this).save("City", city.getCityId());
                    Log.e("TAG", city.toString());
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.e("TAG", "onError" + ex.toString());
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });

        }
    };

    @Override
    protected void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }
}
