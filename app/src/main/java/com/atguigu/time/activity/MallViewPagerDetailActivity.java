package com.atguigu.time.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.atguigu.time.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 商城轮播图点击详情页面
 */
public class MallViewPagerDetailActivity extends Activity implements View.OnClickListener {
    //标题栏返回
    private ImageView iv_title_back;
    //标题栏logo
    private ImageView iv_title_logo;
    //WebView
    @ViewInject(R.id.web_view)
    private WebView web_view;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_view_pager_detail);
        x.view().inject(this);

        url=getIntent().getStringExtra("webViewUrl");
        web_view.loadUrl(url);
        WebSettings settings = web_view.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);//当加载的页面，是可以缩放的页面的时候，自动显示缩放按钮
        settings.setUseWideViewPort(true);//当加载的页面，是可以缩放的页面的时候，双击缩放

        web_view.setWebViewClient(new MyWebViewClient());
     //   setListener();
    }

    private void setListener() {
        iv_title_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }


    class MyWebViewClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }
}
