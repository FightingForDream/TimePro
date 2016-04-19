package com.atguigu.time.module_home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.atguigu.time.R;
import com.atguigu.time.utils.Constants;

public class TopPosterActivity extends AppCompatActivity implements Constants{
    /**显示Poster详情的WebView*/
    private WebView wv_poster_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_detail);

        wv_poster_detail = (WebView) findViewById(R.id.wv_poster_detail);
        Intent it = getIntent();
        String url = it.getExtras().getString(POSTER_URL);
        wv_poster_detail.loadUrl(url);


        wv_poster_detail.setWebViewClient(new WebViewClient());
        wv_poster_detail.getSettings().setJavaScriptEnabled(true);
    }
}
