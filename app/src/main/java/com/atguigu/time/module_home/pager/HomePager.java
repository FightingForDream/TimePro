package com.atguigu.time.module_home.pager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.atguigu.time.R;

import com.atguigu.time.api.Url;
import com.atguigu.time.api.VolleyManager;
import com.atguigu.time.base.BasePager;
import com.atguigu.time.bean.HomeContentBean;
import com.atguigu.time.bean.HomeHeaderBean;
import com.atguigu.time.bean.HomeHotMovieBean;
import com.atguigu.time.module_home.activity.HotMovieDetailActivity;
import com.atguigu.time.module_home.activity.SearchItemActivity;
import com.atguigu.time.module_home.activity.base.BaseActivity;
import com.atguigu.time.module_home.adapter.HomeContentAdapter;
import com.atguigu.time.module_home.adapter.HomeHeaderAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huanzi on 2016/4/7.
 */
public class HomePager extends BasePager {
    private static final int START_SCROLL_PAGE = 0;
    private static final int SCROLL_PAGE_DELAY = 4000;
    /**加载数据进度条*/
    private ProgressBar pb_home_loading;
    /**头部视图*/
    private View mHeaderView;
    /**---------------------------头部ViewPager-----------------------*/
    private ViewPager home_view_pager;
    private HomeHeaderBean homeHeaderBean;
    private List<SimpleDraweeView> posterViewList;
    private HomeHeaderAdapter viewPagerAdapter;
    private List<HomeHeaderBean.TopPostersBean> posterList;
    private LinearLayout ll_home_viewpager_dots;
    private List<ImageView> dotList;
    private int currentPage;
    /**搜索按钮*/
    private TextView tv_home_search;
    /**ListView内容*/
    private ListView lv_home_content;
    private HomeContentAdapter adapter;
    private HomeContentBean contentBean;

    //private Gallery home_gallery;
    //private HomeGalleryAdapter galleryAdapter;
    private HomeHotMovieBean hotMovieBean;

    private LinearLayout ll_home_gallery;


    /**-----------------------------广告模块----------------------------*/
    /**广告ViewPager*/
    private ViewPager advViewPager;
    private List<HomeHeaderBean.AdvListBean> advList;
    private List<SimpleDraweeView> advViews;
    private HomeHeaderAdapter advAdapter;


    /*---------------------------电影商城模块--------------------------*/
    /**电影商城数据*/
    private HomeHeaderBean.AreaSecondBean areaSecondBean;
    /**电影商城标题组件*/
    private TextView tv_shop_title01;
    private TextView tv_shop_title02;
    private TextView tv_shop_title03;
    private TextView tv_shop_title04;
    private TextView tv_shop_title05;
    private TextView tv_shop_title06;
    /**电影商城子标题组件*/
    private TextView tv_shop_item01;
    private TextView tv_shop_item02;
    private TextView tv_shop_item03;
    private TextView tv_shop_item04;
    private TextView tv_shop_item05;
    private TextView tv_shop_item06;
    /**电影商城图片组件*/
    private SimpleDraweeView sdv_shop_item01;
    private SimpleDraweeView sdv_shop_item02;
    private SimpleDraweeView sdv_shop_item03;
    private SimpleDraweeView sdv_shop_item04;
    private SimpleDraweeView sdv_shop_item05;
    private SimpleDraweeView sdv_shop_item06;


    /*--------------------------时光精选模块------------------------------*/
    private int pageIndex;
    /*private HomeContentBean.DataBean moreDataBean;*/


    /*--------------------------底部刷新模块------------------------------*/
    private View mFooterView;
    private boolean isLoadingData;
    private ImageView iv_home_footer_loading;
    private TextView tv_home_footer_text;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case START_SCROLL_PAGE:
                    //currentPage++;
                    home_view_pager.setCurrentItem((currentPage + 1) % 5);
                    mHandler.sendEmptyMessageDelayed(START_SCROLL_PAGE, SCROLL_PAGE_DELAY);
                    break;
            }
        }
    };

    /**
     * 构造函数，初始化上下文，调用initView初始化视图
     *
     * @param mActivity 上下文
     */
    public HomePager(Activity mActivity) {
        super(mActivity);
    }

    /**
     * 视图组件初始化
     * @return mRootView ListView内容
     */
    @Override
    public View getView() {
        //加载ListView内容视图
        mRootView = View.inflate(mActivity, R.layout.pager_home, null);
        //加载ListView 头部视图
        mHeaderView = View.inflate(mActivity, R.layout.pager_home_header, null);
        //加载底部下拉刷新视图
        mFooterView = View.inflate(mActivity, R.layout.page_home_footer, null);
        //初始化视图组件
        findViewById();
        //设置监听
        setListener();
        //添加头部
        lv_home_content.addHeaderView(mHeaderView);
        //添加底部
        lv_home_content.addFooterView(mFooterView);

        return mRootView;
    }
    public void findViewById(){
        lv_home_content = (ListView) mRootView.findViewById(R.id.lv_home_content);
        pb_home_loading = (ProgressBar) mRootView.findViewById(R.id.pb_home_loading);
        //home_gallery = (Gallery) headerView.findViewById(R.id.home_gallery);      //采用HorizontalScrollView替换掉Gallery
        ll_home_gallery = (LinearLayout) mHeaderView.findViewById(R.id.ll_home_gallery);
        //顶部ViewPager
        home_view_pager = (ViewPager) mHeaderView.findViewById(R.id.home_view_pager);
        //广告ViewPager
        advViewPager = (ViewPager) mHeaderView.findViewById(R.id.vp_home_adv);
        //购物商城组件初始化
        tv_shop_title01 = (TextView) mHeaderView.findViewById(R.id.tv_shop_title01);
        tv_shop_title02 = (TextView) mHeaderView.findViewById(R.id.tv_shop_title02);
        tv_shop_title03 = (TextView) mHeaderView.findViewById(R.id.tv_shop_title03);
        tv_shop_title04 = (TextView) mHeaderView.findViewById(R.id.tv_shop_title04);
        tv_shop_title05 = (TextView) mHeaderView.findViewById(R.id.tv_shop_title05);
        tv_shop_title06 = (TextView) mHeaderView.findViewById(R.id.tv_shop_title06);

        tv_shop_item01 = (TextView) mHeaderView.findViewById(R.id.tv_shop_item01);
        tv_shop_item02 = (TextView) mHeaderView.findViewById(R.id.tv_shop_item02);
        tv_shop_item03 = (TextView) mHeaderView.findViewById(R.id.tv_shop_item03);
        tv_shop_item04 = (TextView) mHeaderView.findViewById(R.id.tv_shop_item04);
        tv_shop_item05 = (TextView) mHeaderView.findViewById(R.id.tv_shop_item05);
        tv_shop_item06 = (TextView) mHeaderView.findViewById(R.id.tv_shop_item06);

        sdv_shop_item01 = (SimpleDraweeView) mHeaderView.findViewById(R.id.sdv_shop_item01);
        sdv_shop_item02 = (SimpleDraweeView) mHeaderView.findViewById(R.id.sdv_shop_item02);
        sdv_shop_item03 = (SimpleDraweeView) mHeaderView.findViewById(R.id.sdv_shop_item03);
        sdv_shop_item04 = (SimpleDraweeView) mHeaderView.findViewById(R.id.sdv_shop_item04);
        sdv_shop_item05 = (SimpleDraweeView) mHeaderView.findViewById(R.id.sdv_shop_item05);
        sdv_shop_item06 = (SimpleDraweeView) mHeaderView.findViewById(R.id.sdv_shop_item06);

        tv_home_search = (TextView) mHeaderView.findViewById(R.id.tv_home_search);

        iv_home_footer_loading = (ImageView) mFooterView.findViewById(R.id.iv_home_footer_loading);
        tv_home_footer_text = (TextView) mFooterView.findViewById(R.id.tv_home_footer_text);

        ll_home_viewpager_dots = (LinearLayout) mHeaderView.findViewById(R.id.ll_home_viewpager_dots);
    }

    /**
     * 设置监听
     */
    public void setListener(){
        //搜索按钮设置监听
        tv_home_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it  = new Intent(mActivity, SearchItemActivity.class);
                mActivity.startActivity(it);
            }
        });
        //ListView设置滑动监听
        lv_home_content.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                //Log.e("TAG", "mfooterView.isShown");
                if (mFooterView.isShown() && !isLoadingData) {
                    pageIndex++;
                    loadMoreData();
                    Log.e("TAG", "mfooterView.isShown");
                } else {
                    Log.e("TAG", "mfooterView false");
                }
            }
        });

        //头部ViewPager设置滑动监听
        home_view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dotList.get(position).setEnabled(true);
                dotList.get(currentPage % 5).setEnabled(false);
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
       /* //按下取消handler滑动,松开继续滑动
        home_view_pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mHandler.removeMessages(START_SCROLL_PAGE);
                        break;
                    case MotionEvent.ACTION_UP:
                        mHandler.sendEmptyMessageDelayed(START_SCROLL_PAGE, SCROLL_PAGE_DELAY);
                        break;
                }
                return false;
            }
        });*/
    }
    /**
     * ListView滑动监听
     */
    //private AbsListView.OnScrollListener scrollChangeListener =
    /**
     * 请求数据
     */
    @Override
    public void initData() {
        //请求时光精选数据
        getMtimeChosenData();
        //请求Gallery数据
        getTicketOnSaleData();
        //请求Header数据
        getAdsData();
    }

    /**
     * 下拉加载更多数据
     */
    public void loadMoreData(){
        isLoadingData = true;
        if (pageIndex == 0 || pageIndex > 3){
            Toast.makeText(mActivity, "已经到尾了...", Toast.LENGTH_SHORT).show();
            iv_home_footer_loading.setImageResource(R.drawable.actor_detail_like_base);
            tv_home_footer_text.setText("木有数据了");
            return;
        }
        String url = Url.HOME_CONTENT + "?pageIndex=" + pageIndex;
        //请求ListView内容
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String str) {
                HomeContentBean data = new Gson().fromJson(str, HomeContentBean.class);
                contentBean.getData().addAll(data.getData());
                adapter.notifyDataSetChanged();
                //adapter = new HomeContentAdapter(mActivity, contentBean);
                //lv_home_content.setAdapter(adapter);
                Toast.makeText(mActivity, "加载数据成功", Toast.LENGTH_SHORT).show();
                //pb_home_loading.setVisibility(View.GONE);
                isLoadingData = false;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //mActivity.showTips("加载数据出错！");
                Log.e("ERROR", volleyError.getMessage());
                Toast.makeText(mActivity, "加载数据出错！", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyManager.getInstance(mActivity).getQueue().add(request);
    }

    /**
     * 加载时光精选内容
     */
    public void getMtimeChosenData(){
        //请求ListView内容
        StringRequest request = new StringRequest(Request.Method.GET, Url.HOME_CONTENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String str) {
                contentBean = new Gson().fromJson(str, HomeContentBean.class);
                adapter = new HomeContentAdapter(mActivity, contentBean);
                lv_home_content.setAdapter(adapter);
                Toast.makeText(mActivity, "加载数据成功", Toast.LENGTH_SHORT).show();
                pb_home_loading.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //mActivity.showTips("加载数据出错！");
                Log.e("ERROR", volleyError.getMessage());
                Toast.makeText(mActivity, "加载数据出错！", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyManager.getInstance(mActivity).getQueue().add(request);
    }

    /**
     * 加载正在售票内容
     */
    public void getTicketOnSaleData(){
        StringRequest galleryRequest = new StringRequest(Request.Method.GET, Url.HOME_GALLERY, new Response.Listener<String>() {
            @Override
            public void onResponse(String str) {
                hotMovieBean = new Gson().fromJson(str, HomeHotMovieBean.class);
                //galleryAdapter = new HomeGalleryAdapter(mActivity, hotMovieBean);
                //home_gallery.setAdapter(galleryAdapter);
                for (int i = 0; i < hotMovieBean.getMovies().size(); i++){
                    View view = View.inflate(mActivity, R.layout.home_gallery, null);
                    SimpleDraweeView dv_home_gallery_thumbnail = (SimpleDraweeView) view.findViewById(R.id.dv_home_gallery_thumbnail);
                    //String url = "http://uus-img6.android.d.cn/content_pic/201603/behpic/icon/629/2-69629/icon-1458809775520.png";
                    /*if(hotMovieBean.getMovies().get(i).getImg() != null){
                        url = hotMovieBean.getMovies().get(i).getImg();
                    }*/
                    Uri uri = Uri.parse(hotMovieBean.getMovies().get(i).getImg());
                    dv_home_gallery_thumbnail.setImageURI(uri);
                    TextView tv_home_gallery_title = (TextView) view.findViewById(R.id.tv_home_gallery_title);
                    tv_home_gallery_title.setText(hotMovieBean.getMovies().get(i).getTitleCn());
                    Button btn_home_gallery_buy = (Button) view.findViewById(R.id.btn_home_gallery_buy);

                    TextView home_gallery_rank = (TextView) view.findViewById(R.id.home_gallery_rank);
                    home_gallery_rank.setText(hotMovieBean.getMovies().get(i).getRatingFinal() + ""); //设置评分
                    TextView home_gallery_type = (TextView) view.findViewById(R.id.home_gallery_type); //设置类型
                    if (hotMovieBean.getMovies().get(i).isIs3D()){
                        home_gallery_type.setText("3D");
                    }else if(hotMovieBean.getMovies().get(i).isIsDMAX()){
                        home_gallery_type.setText("IMAX 2D");
                    }else if(hotMovieBean.getMovies().get(i).isIsIMAX3D()){
                        home_gallery_type.setText("IMAX 3D");
                    }else {
                        home_gallery_type.setVisibility(View.GONE);
                    }

                    ll_home_gallery.addView(view);
                    view.setOnClickListener(showHotMovieDetailListener);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //mActivity.showTips("加载数据出错！");
            }
        });
        VolleyManager.getInstance(mActivity).getQueue().add(galleryRequest);
    }

    /**
     * 加载广告、卖品数据
     */
    public void getAdsData(){
        StringRequest viewPagerRequest = new StringRequest(Request.Method.GET, Url.HOME_HEADER, new Response.Listener<String>() {
            @Override
            public void onResponse(String str) {
                posterViewList = new ArrayList<SimpleDraweeView>();
                homeHeaderBean = new Gson().fromJson(str, HomeHeaderBean.class);
                posterList = homeHeaderBean.getTopPosters();
                /*LinearLayout headerView = (LinearLayout) View.inflate(mActivity, R.layout.home_viewpager_item, null);
                for (int i = 0; i < posterList.size(); i++){
                    SimpleDraweeView view = (SimpleDraweeView) View.inflate(mActivity, R.layout.home_viewpager_item, null);

                    view.setTag(posterList.get(i).getImg());
                    headerScrollViews.add(view);
                }
                viewPagerAdapter = new HomeHeaderAdapter(mActivity, headerScrollViews);
                home_view_pager.setAdapter(viewPagerAdapter);*/
                getTopPosterView(posterList);
                //装配广告数据
                advList = homeHeaderBean.getAdvList();
                advViews = new ArrayList<SimpleDraweeView>();
                for (int i = 0; i < advList.size(); i++){
                    SimpleDraweeView view = (SimpleDraweeView) View.inflate(mActivity, R.layout.home_viewpager_drawee, null);

                    view.setTag(advList.get(i).getImg());
                    advViews.add(view);
                }
                advAdapter = new HomeHeaderAdapter(mActivity, advViews, homeHeaderBean);
                advViewPager.setAdapter(advAdapter);
                //装配电影商城购物模块
                areaSecondBean = homeHeaderBean.getAreaSecond();

                tv_shop_title01.setText(areaSecondBean.getSubFirst().getTitle());
                tv_shop_title02.setText(areaSecondBean.getSubSecond().getTitle());
                tv_shop_item03.setText(areaSecondBean.getSubThird().getTitle());
                //tv_shop_item04.setText(areaSecondBean.getSubFourth().getTitle());
                tv_shop_title05.setText(areaSecondBean.getSubThird().getTitle());
                tv_shop_title06.setText(areaSecondBean.getSubFifth().getTitle());


                tv_shop_item01.setText(areaSecondBean.getSubFirst().getTitleSmall());
                tv_shop_item02.setText(areaSecondBean.getSubSecond().getTitleSmall());
                //tv_shop_item03.setText(areaSecondBean.getSubThird().getTitleSmall());
                //tv_shop_item04.setText(areaSecondBean.getSubFourth().getTitleSmall());
                tv_shop_item05.setText(areaSecondBean.getSubThird().getTitleSmall());
                tv_shop_item06.setText(areaSecondBean.getSubFifth().getTitleSmall());


                String url1 = areaSecondBean.getSubFirst().getImage();
                if (url1.isEmpty()){
                    url1 = areaSecondBean.getSubFirst().getImage2();
                }
                String url2 = areaSecondBean.getSubSecond().getImage();
                if (url2.isEmpty()){
                    url2 = areaSecondBean.getSubSecond().getImage2();
                }
                String url3 = areaSecondBean.getSubThird().getImage();
                if (url3.isEmpty()){
                    url3 = areaSecondBean.getSubThird().getImage2();
                }
                String url4 = areaSecondBean.getSubFirst().getImage();
                if (url4.isEmpty()){
                    url4 = areaSecondBean.getSubFirst().getImage2();
                }
                String url5 = areaSecondBean.getSubFifth().getImage();
                if (url5.isEmpty()){
                    url5 = areaSecondBean.getSubFifth().getImage2();
                }
                String url6 = areaSecondBean.getSubFirst().getImage();
                if (url6.isEmpty()){
                    url6 = areaSecondBean.getSubFirst().getImage2();
                }

                sdv_shop_item01.setImageURI(Uri.parse(url1));
                sdv_shop_item02.setImageURI(Uri.parse(url2));
                sdv_shop_item03.setImageURI(Uri.parse(areaSecondBean.getSubFourth().getImage()));
                sdv_shop_item04.setImageURI(Uri.parse(areaSecondBean.getSubFourth().getImage2()));
                sdv_shop_item05.setImageURI(Uri.parse(url3));
                sdv_shop_item06.setImageURI(Uri.parse(url5));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //mActivity.showTips("加载数据出错！");
            }
        });
        VolleyManager.getInstance(mActivity).getQueue().add(viewPagerRequest);
    }

    /**
     * 生成TopPoster数据
     */
    public void getTopPosterView(List<HomeHeaderBean.TopPostersBean> posterList){
        dotList = new ArrayList<ImageView>();
        //LinearLayout headerView = (LinearLayout) View.inflate(mActivity, R.layout.home_viewpager_item, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.rightMargin = 10;
        for (int i = 0; i < posterList.size(); i++){
            SimpleDraweeView sdv = (SimpleDraweeView) View.inflate(mActivity, R.layout.home_viewpager_drawee, null);
            ImageView dot = (ImageView) View.inflate(mActivity, R.layout.home_viewpager_dot, null);
            dot.setLayoutParams(params);
            ll_home_viewpager_dots.addView(dot);
            dot.setEnabled(false);
            dotList.add(dot);
            sdv.setTag(posterList.get(i).getImg());
            posterViewList.add(sdv);
        }
        dotList.get(0).setEnabled(true);
        viewPagerAdapter = new HomeHeaderAdapter(mActivity, posterViewList, homeHeaderBean);
        home_view_pager.setAdapter(viewPagerAdapter);
        setViewPagerAutoScroll();
    }


    private View.OnClickListener showHotMovieDetailListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent it = new Intent(mActivity, HotMovieDetailActivity.class);
            mActivity.startActivity(it);
        }
    };
    /**
     * 设置ViewPager轮播
     */
    public void setViewPagerAutoScroll(){
        Message msg = Message.obtain();
        msg.what = START_SCROLL_PAGE;
        mHandler.sendEmptyMessageDelayed(START_SCROLL_PAGE, SCROLL_PAGE_DELAY);
    }
}
