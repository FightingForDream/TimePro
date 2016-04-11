package com.atguigu.time.pager;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.time.R;
import com.atguigu.time.module_mall.adapter.MallListAdapter;
import com.atguigu.time.api.Url;
import com.atguigu.time.base.BasePager;
import com.atguigu.time.bean.Mall;
import com.atguigu.time.utils.CacheUtils;
import com.atguigu.time.view.CircleImageView;
import com.atguigu.time.view.NoScrollGridView;
import com.atguigu.time.view.ScrollListView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * 商城页面
 */
public class ShopPager extends BasePager {

    private static final int AUTO = 1;
    //主布局 包含titleBar和listView
    private LinearLayout shopView;
    //搜索框
    private EditText search_content;
    //下拉刷新页面
    private SwipeRefreshLayout srl_shop_mall;
    //主布局
    private RelativeLayout rl_shop_mall_layout;
    //扫描二维码
    private ImageView iv_mall_home_scan;
    //购物车
    private ImageView iv_mall_home_cart_icon;
    //顶部ViewPager
    private ViewPager vp_mall;
    //圆点
    private LinearLayout ll_mall_view_pager_points;
    //ListView
    private ScrollListView lv_shop_mall;
    //商城页面的json数据
    private String value;
    //商城json数据对应的实体类
    private Mall dataBean;
    //顶部轮播图的数据集合
    private List<Mall.ScrollImgBean> viewPagers;
    //点击切换商品的数据集合
    public List<Mall.TopicBean> topic;
    //圆形的类型gridView
    private NoScrollGridView gv_type;
    //圆形图数据集合
    private List<Mall.NavigatorIconBean> icons;
    //商城页面listView的adapter
    private MallListAdapter mallListAdapter;
    //回到顶部
    private ImageView iv_go_top;
    //商城页面的特惠  原创设计
    private LinearLayout ll_abc;
    private ImageView iv_a, iv_b, iv_c1, iv_c2;
    //功夫熊猫 星战圆形图片
    private ImageView iv_circle_icon1, iv_circle_icon2, iv_circle_icon3, iv_circle_icon4;
    //背景
    private ImageView iv_background;
    //英语名
    private TextView tv_english_title;
    //中文名
    private TextView tv_chinese_title;
    //点击以后商品展示  可切换
    private NoScrollGridView mall_no_scroll_grid_view;
    //更多商品
    private TextView tv_more_shop;
    //加载更多感兴趣商品
    private NoScrollGridView interest_grid_view;

    //商城连接
    private String mallUrl;
    //感兴趣商品链接
    private String mallMoreUrl;

    //标识是否已经发送轮播图自动播放的消息
    private boolean isAuto;
    //标识头部的ViewPager是否是拖拽引起的状态改变
    private boolean isDragging;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AUTO:
                    vp_mall.setCurrentItem((vp_mall.getCurrentItem() + 1) % viewPagers.size());
                    isAuto = true;
                    handler.removeMessages(AUTO);
                    handler.sendEmptyMessageDelayed(AUTO, 3000);
                    break;
                default:
                    break;
            }
        }
    };

    public ShopPager(Activity activity) {
        super(activity);
    }

    /**
     * 初始化页面布局
     *
     * @return
     */
    @Override
    protected View getView() {
        //加载主布局
        shopView = (LinearLayout) View.inflate(mActivity, R.layout.shop_mall_layout, null);
        lv_shop_mall = (ScrollListView) shopView.findViewById(R.id.lv_shop_mall);
        x.view().inject(this, shopView);

        vp_mall = (ViewPager) shopView.findViewById(R.id.vp_mall);
        ll_mall_view_pager_points = (LinearLayout) shopView.findViewById(R.id.ll_mall_view_pager_points);
        srl_shop_mall = (SwipeRefreshLayout) shopView.findViewById(R.id.srl_shop_mall);

        iv_mall_home_scan = (ImageView) shopView.findViewById(R.id.iv_mall_home_scan);
        iv_mall_home_cart_icon = (ImageView) shopView.findViewById(R.id.iv_mall_home_cart_icon);
        search_content = (EditText) shopView.findViewById(R.id.search_content);

        gv_type = (NoScrollGridView) shopView.findViewById(R.id.gv_type);

        iv_go_top = (ImageView) shopView.findViewById(R.id.iv_go_top);

        ll_abc = (LinearLayout) shopView.findViewById(R.id.ll_abc);
        iv_a = (ImageView) shopView.findViewById(R.id.iv_a);
        iv_b = (ImageView) shopView.findViewById(R.id.iv_b);
        iv_c1 = (ImageView) shopView.findViewById(R.id.iv_c1);
        iv_c2 = (ImageView) shopView.findViewById(R.id.iv_c2);
        iv_background = (ImageView) shopView.findViewById(R.id.iv_background);

        iv_circle_icon1 = (ImageView) shopView.findViewById(R.id.iv_circle_icon1);
        iv_circle_icon2 = (ImageView) shopView.findViewById(R.id.iv_circle_icon2);
        iv_circle_icon3 = (ImageView) shopView.findViewById(R.id.iv_circle_icon3);
        iv_circle_icon4 = (ImageView) shopView.findViewById(R.id.iv_circle_icon4);

        tv_english_title = (TextView) shopView.findViewById(R.id.tv_english_title);
        tv_chinese_title = (TextView) shopView.findViewById(R.id.tv_chinese_title);

        mall_no_scroll_grid_view = (NoScrollGridView) shopView.findViewById(R.id.mall_no_scroll_grid_view);
        tv_more_shop = (TextView) shopView.findViewById(R.id.tv_more_shop);

        interest_grid_view = (NoScrollGridView) shopView.findViewById(R.id.interest_grid_view);
        return shopView;
    }


    @Override
    public void initData() {
        super.initData();
        mallUrl = Url.MALL_URL;
        mallMoreUrl = Url.MALL_MORE_URL;
        value = CacheUtils.getString(mActivity, mallUrl);
        if (!TextUtils.isEmpty(value)) {
            processData(value);
        }
        getDataFromNet();
    }

    /**
     * 从网路获取数据
     */
    private void getDataFromNet() {
        RequestParams params = new RequestParams(mallUrl);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(mActivity, "网络请求成功", Toast.LENGTH_SHORT).show();
                processData(result);
                srl_shop_mall.setRefreshing(false);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(mActivity, "网络请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 解析json数据并且设置显示
     *
     * @param json
     */
    private void processData(String json) {

        Gson gson = new Gson();
        dataBean = gson.fromJson(json, Mall.class);

        viewPagers = dataBean.getScrollImg();
        icons = dataBean.getNavigatorIcon();
        topic = dataBean.getTopic();

        vp_mall.setAdapter(new ViewPagerAdapter());
        gv_type.setAdapter(new GridViewAdapter());
        vp_mall.addOnPageChangeListener(new OnPageChangeListener());

        x.image().bind(iv_a, dataBean.getCellA().getImg());
        x.image().bind(iv_b, dataBean.getCellB().getImg());
        x.image().bind(iv_c1, dataBean.getCellC().getList().get(0).getImage());
        x.image().bind(iv_c2, dataBean.getCellC().getList().get(1).getImage());


        srl_shop_mall.setOnRefreshListener(new RefreshListener());
        if (mallListAdapter == null) {
            mallListAdapter = new MallListAdapter(mActivity, dataBean);
        }
        lv_shop_mall.setAdapter(mallListAdapter);
    }


    class OnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                handler.removeMessages(AUTO);
                isDragging = true;
            } else {
                isDragging = false;
                handler.sendEmptyMessageDelayed(AUTO, 3000);
            }
            enableDisableSwipeRefresh(state == ViewPager.SCROLL_STATE_IDLE);
        }
    }

    protected void enableDisableSwipeRefresh(boolean enable) {
        if (srl_shop_mall != null) {
            srl_shop_mall.setEnabled(enable);
        }
    }

    class GridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return icons.size();
        }

        @Override
        public Object getItem(int position) {
            return icons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GridViewHolder holder = null;
            if (convertView == null) {
                holder = new GridViewHolder();
                convertView = View.inflate(mActivity, R.layout.mall_home_gr_item, null);
                holder.iv_mall_home_gv = (CircleImageView) convertView.findViewById(R.id.iv_mall_home_gv);
                holder.tv_mall_home_gv = (TextView) convertView.findViewById(R.id.tv_mall_home_gv);
                convertView.setTag(holder);
            } else {
                holder = (GridViewHolder) convertView.getTag();
            }

            x.image().bind(holder.iv_mall_home_gv, icons.get(position).getImage());
            holder.tv_mall_home_gv.setText(icons.get(position).getIconTitle());
            return convertView;
        }
    }

    static class GridViewHolder {
        CircleImageView iv_mall_home_gv;
        TextView tv_mall_home_gv;
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            Log.e("TAG", "getCount" + viewPagers.size());
            return viewPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            Log.e("TAG", "isViewFromObject" + view);
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.e("TAG", "instantiateItem");
            ImageView imageView = new ImageView(mActivity);
            x.image().bind(imageView, viewPagers.get(position).getImage());
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.e("TAG", "destroyItem");
            container.removeView((View) object);
        }
    }

    private class RefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            getDataFromNet();
        }
    }
}
