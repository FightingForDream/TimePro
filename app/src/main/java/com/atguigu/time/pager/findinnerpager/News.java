package com.atguigu.time.pager.findinnerpager;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.time.R;
import com.atguigu.time.adapter.FindPagerNewsListAdapter;
import com.atguigu.time.api.Url;
import com.atguigu.time.base.BaseFindInnerPager;
import com.atguigu.time.bean.FindPageTopBean;
import com.atguigu.time.bean.FindPagerNewsList;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 发现————新闻页面
 * @author xpl
 * created at 2016/4/10 9:36
 */
public class News extends BaseFindInnerPager {

    private View headView;
    private static final String TAG = News.class.getSimpleName();

    @ViewInject(R.id.iv_find_head_news)
    private ImageView iv_find_head_news;

    @ViewInject(R.id.tv_head_find_news)
    private TextView tv_head_find_news;

    private ListView lv_base_find_inner;

    //新闻顶部数据
    private FindPageTopBean.NewsEntity news;

    //新闻listview数据集合
    private List<FindPagerNewsList.NewsListEntity> newsList;

    public News(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity,R.layout.base_find_inner_page,null);
        lv_base_find_inner = (ListView) view.findViewById(R.id.lv_base_find_inner);
        headView = View.inflate(mActivity, R.layout.find_news_head,null);
        x.view().inject(this, headView);
        lv_base_find_inner.addHeaderView(headView);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getTopDataForNet();
        getDataForNet();
    }
    /**
     * 联网请求ListView数据
     * @author xpl
     * created at 2016/4/10 21:10
     */
    private void getDataForNet(){
        RequestParams params1 = new RequestParams(Url.FIND_NEWSLIST);
        x.http().get(params1, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "联网成功--" + result);
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError---" + ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled---" + cex);
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished");
            }
        });
    }
    /**
     * 联网获取头部数据
     * @author xpl
     * created at 2016/4/10 16:46
     */
    private void getTopDataForNet() {

        RequestParams params = new RequestParams(Url.TOP_Find);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "联网成功--" + result);
                processHeadData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError---" + ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled---" + cex);
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished");
            }
        });
    }

    /**
     * 解析newslist数据
     * @param json
     */
    private void processData(String json) {
        Gson gson = new Gson();
        FindPagerNewsList findPagerNewsList = gson.fromJson(json, FindPagerNewsList.class);

        newsList = findPagerNewsList.getNewsList();
        lv_base_find_inner.setAdapter(new FindPagerNewsListAdapter(mActivity,newsList));

    }
    /**
     * 解析头部数据
     */
    private void processHeadData(String json) {
        Gson gson = new Gson();
        FindPageTopBean findPageTopBean = gson.fromJson(json, FindPageTopBean.class);
        news = findPageTopBean.getNews();
        //装载头部数据
        if(findPageTopBean != null){
            x.image().bind(iv_find_head_news,news.getImageUrl());
            tv_head_find_news.setText(news.getTitle());
        }
    }
}
