package com.atguigu.time.pager;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
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
import com.atguigu.time.adapter.HomeContentAdapter;
import com.atguigu.time.adapter.HomeGalleryAdapter;
import com.atguigu.time.adapter.HomeHeaderAdapter;
import com.atguigu.time.api.Url;
import com.atguigu.time.api.VolleyManager;
import com.atguigu.time.base.BasePager;
import com.atguigu.time.bean.HomeContentBean;
import com.atguigu.time.bean.HomeHeaderBean;
import com.atguigu.time.bean.HomeHotMovieBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huanzi on 2016/4/7.
 */
public class HomePager extends BasePager {
    private ProgressBar pb_home_loading;
    /**头部视图*/
    private View headerView;
    /**ListView内容*/
    private ListView recyler_view_home;
    private HomeContentAdapter adapter;
    private HomeContentBean contentBean;

    //private Gallery home_gallery;
    //private HomeGalleryAdapter galleryAdapter;
    private HomeHotMovieBean hotMovieBean;

    private LinearLayout ll_home_gallery;
    /**头部ViewPager*/
    private ViewPager home_view_pager;
    private HomeHeaderBean homeHeaderBean;
    private List<SimpleDraweeView> headerViews;
    private HomeHeaderAdapter viewPagerAdapter;
    private List<HomeHeaderBean.TopPostersBean> posterList;
    /**-----------------------------广告模块----------------------------*/
    /**广告ViewPager*/
    private ViewPager advViewPager;
    private List<HomeHeaderBean.AdvListBean> advList;
    private List<SimpleDraweeView> advViews;
    private HomeHeaderAdapter advAdapter;
    /**---------------------------电影商城模块--------------------------*/
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
    /**----------------------------------------------------------------*/

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
     * @return
     */
    @Override
    public View getView() {
        mRootView = View.inflate(mActivity, R.layout.pager_home, null);
        recyler_view_home = (ListView) mRootView.findViewById(R.id.recyler_view_home);
        pb_home_loading = (ProgressBar) mRootView.findViewById(R.id.pb_home_loading);

        headerView = View.inflate(mActivity, R.layout.pager_home_header, null);
        //home_gallery = (Gallery) headerView.findViewById(R.id.home_gallery);      //采用HorizontalScrollView替换掉Gallery
        ll_home_gallery = (LinearLayout) headerView.findViewById(R.id.ll_home_gallery);
        //顶部ViewPager
        home_view_pager = (ViewPager) headerView.findViewById(R.id.home_view_pager);
        //广告ViewPager
        advViewPager = (ViewPager) headerView.findViewById(R.id.vp_home_adv);
        //购物商城组件初始化
        tv_shop_title01 = (TextView) headerView.findViewById(R.id.tv_shop_title01);
        tv_shop_title02 = (TextView) headerView.findViewById(R.id.tv_shop_title02);
        tv_shop_title03 = (TextView) headerView.findViewById(R.id.tv_shop_title03);
        tv_shop_title04 = (TextView) headerView.findViewById(R.id.tv_shop_title04);
        tv_shop_title05 = (TextView) headerView.findViewById(R.id.tv_shop_title05);
        tv_shop_title06 = (TextView) headerView.findViewById(R.id.tv_shop_title06);

        tv_shop_item01 = (TextView) headerView.findViewById(R.id.tv_shop_item01);
        tv_shop_item02 = (TextView) headerView.findViewById(R.id.tv_shop_item02);
        tv_shop_item03 = (TextView) headerView.findViewById(R.id.tv_shop_item03);
        tv_shop_item04 = (TextView) headerView.findViewById(R.id.tv_shop_item04);
        tv_shop_item05 = (TextView) headerView.findViewById(R.id.tv_shop_item05);
        tv_shop_item06 = (TextView) headerView.findViewById(R.id.tv_shop_item06);

        sdv_shop_item01 = (SimpleDraweeView) headerView.findViewById(R.id.sdv_shop_item01);
        sdv_shop_item02 = (SimpleDraweeView) headerView.findViewById(R.id.sdv_shop_item02);
        sdv_shop_item03 = (SimpleDraweeView) headerView.findViewById(R.id.sdv_shop_item03);
        sdv_shop_item04 = (SimpleDraweeView) headerView.findViewById(R.id.sdv_shop_item04);
        sdv_shop_item05 = (SimpleDraweeView) headerView.findViewById(R.id.sdv_shop_item05);
        sdv_shop_item06 = (SimpleDraweeView) headerView.findViewById(R.id.sdv_shop_item06);

        recyler_view_home.addHeaderView(headerView);
        return mRootView;
    }


    /**
     * 请求数据
     */
    @Override
    public void initData() {
        //请求ListView内容
        StringRequest request = new StringRequest(Request.Method.GET, Url.HOME_CONTENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String str) {
                contentBean = new Gson().fromJson(str, HomeContentBean.class);
                adapter = new HomeContentAdapter(mActivity, contentBean);
                recyler_view_home.setAdapter(adapter);
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
        //请求Gallery内容
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
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //mActivity.showTips("加载数据出错！");
            }
        });
        //请求Header内容
        StringRequest viewPagerRequest = new StringRequest(Request.Method.GET, Url.HOME_HEADER, new Response.Listener<String>() {
            @Override
            public void onResponse(String str) {
                headerViews = new ArrayList<SimpleDraweeView>();
                homeHeaderBean = new Gson().fromJson(str, HomeHeaderBean.class);
                posterList = homeHeaderBean.getTopPosters();
                for (int i = 0; i < posterList.size(); i++){
                    SimpleDraweeView view = (SimpleDraweeView) View.inflate(mActivity, R.layout.home_viewpager_item, null);

                    view.setTag(posterList.get(i).getImg());
                    headerViews.add(view);
                }
                viewPagerAdapter = new HomeHeaderAdapter(mActivity, headerViews);
                home_view_pager.setAdapter(viewPagerAdapter);
                //装配广告数据
                advList = homeHeaderBean.getAdvList();
                advViews = new ArrayList<SimpleDraweeView>();
                for (int i = 0; i < advList.size(); i++){
                    SimpleDraweeView view = (SimpleDraweeView) View.inflate(mActivity, R.layout.home_viewpager_item, null);

                    view.setTag(advList.get(i).getImg());
                    advViews.add(view);
                }
                advAdapter = new HomeHeaderAdapter(mActivity, advViews);
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

                sdv_shop_item01.setImageURI(Uri.parse(areaSecondBean.getSubFirst().getImage()));
                sdv_shop_item02.setImageURI(Uri.parse(areaSecondBean.getSubSecond().getImage()));
                sdv_shop_item03.setImageURI(Uri.parse(areaSecondBean.getSubFourth().getImage()));
                sdv_shop_item04.setImageURI(Uri.parse(areaSecondBean.getSubFourth().getImage2()));
                sdv_shop_item05.setImageURI(Uri.parse(areaSecondBean.getSubThird().getImage()));
                sdv_shop_item06.setImageURI(Uri.parse(areaSecondBean.getSubFifth().getImage()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //mActivity.showTips("加载数据出错！");
            }
        });
        VolleyManager.getInstance(mActivity).getQueue().add(request);
        VolleyManager.getInstance(mActivity).getQueue().add(galleryRequest);
        VolleyManager.getInstance(mActivity).getQueue().add(viewPagerRequest);
    }

    /**
     * 设置首页ListView数据
     */

}
