package com.atguigu.time.pager;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.atguigu.time.R;
import com.atguigu.time.adapter.MallGridViewAdapter;
import com.atguigu.time.api.Url;
import com.atguigu.time.api_net.VolleyManager;
import com.atguigu.time.base.BasePager;
import com.atguigu.time.bean.InterestedShopBean;
import com.atguigu.time.bean.Mall;
import com.atguigu.time.module_mall.adapter.MallListAdapter;
import com.atguigu.time.utils.CacheUtils;
import com.atguigu.time.view.CircleImageView;
import com.atguigu.time.view.NoScrollGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * 商城页面
 */
public class ShopPager extends BasePager {

    private static final int AUTO = 1;
    //主布局 包含titleBar和listView
    private RelativeLayout shopView;
    //搜索框
    private TextView tv_search;
    //背景
    private ImageView background;
    //下拉刷新页面
    private PullToRefreshGridView srl_shop_mall;
    //主布局
    private RelativeLayout rl_shop_mall_layout;
    //扫描二维码
    private ImageView iv_scan;
    //购物车
    private ImageView iv_car;
    //顶部ViewPager
    private ViewPager vp_mall;
    //圆点
    private LinearLayout ll_mall_view_pager_points;
    //ListView
    private PullToRefreshListView lv_shop_mall;
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
    private HorizontalScrollView view_mall_topic_scroll;
    private LinearLayout ll_mall_circle_icon;
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
    //标识是否刷新
    private boolean isRefreshing = false;
    //商城连接
    private String mallUrl;
    //感兴趣商品链接
    private String mallMoreUrl;
    //感兴趣商品连接类
    private InterestedShopBean shopBean;
    //感兴趣的商品数据
    private List<InterestedShopBean.GoodsListBean> goods;
    private InterestedAdapter interestedAdapter;
    //标识数据都解析得到了
    private boolean prepareBaseData;
    //上拉加载没有更多
    private TextView tv_no_more;

    //标识是否已经发送轮播图自动播放的消息
    private boolean isAuto;
    //标识头部的ViewPager是否是拖拽引起的状态改变
    private boolean isDragging;

    private ImageOptions imageOptions = new ImageOptions.Builder()
            .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))//图片大小
            .setRadius(DensityUtil.dip2px(5))//ImageView圆角半径
            .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            .setLoadingDrawableId(R.drawable.img_default)//加载中默认显示图片
            .setFailureDrawableId(R.drawable.img_default)//加载失败后默认显示图片
            .build();
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
        shopView = (RelativeLayout) View.inflate(mActivity, R.layout.mall_pager_layout, null);
        lv_shop_mall = (PullToRefreshListView) shopView.findViewById(R.id.lv_shop_mall);
        iv_scan = (ImageView) shopView.findViewById(R.id.iv_scan);
        tv_search = (TextView) shopView.findViewById(R.id.tv_search);
        iv_car = (ImageView) shopView.findViewById(R.id.iv_car);
        x.view().inject(this, shopView);
        return shopView;
    }


    @Override
    public void initData() {
        super.initData();
        mallUrl = Url.MALL_URL;
        mallMoreUrl = Url.MALL_MORE_URL;
        value = CacheUtils.getString(mActivity, mallUrl);
//        if (!TextUtils.isEmpty(value)) {
//            processData(value);
//        }
        getDataFromNet();
    }


    /**
     * 从网络获取数据
     */
    private void getDataFromNet() {
        prepareBaseData = false;

        VolleyManager.newInstance().GsonGetRequest(null, mallUrl, Mall.class, new Response.Listener<Mall>() {
            @Override
            public void onResponse(Mall mallBean) {
                if (mallBean != null) {
                    dataBean = mallBean;
                    if (prepareBaseData) {
                        showUI();
                    } else {
                        prepareBaseData = true;
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mActivity, "请检查网络！", Toast.LENGTH_SHORT).show();
            }
        });

        VolleyManager.newInstance().GsonGetRequest(null, mallMoreUrl, InterestedShopBean.class, new Response.Listener<InterestedShopBean>() {
            @Override
            public void onResponse(InterestedShopBean interestedBean) {
                if (interestedBean != null) {
                    shopBean = interestedBean;
                    if (prepareBaseData) {
                        showUI();
                    } else {
                        prepareBaseData = true;
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mActivity, "请检查网络！", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void setData() {
        viewPagers = dataBean.getScrollImg();
        icons = dataBean.getNavigatorIcon();
        topic = dataBean.getTopic();

        vp_mall.setAdapter(new ViewPagerAdapter());
        gv_type.setAdapter(new GridViewAdapter());
        vp_mall.addOnPageChangeListener(new OnPageChangeListener());

        x.image().bind(iv_background, topic.get(0).getBackgroupImage());
        x.image().bind(iv_circle_icon1, topic.get(0).getUncheckImage());
        x.image().bind(iv_circle_icon2, topic.get(1).getUncheckImage());
        x.image().bind(iv_circle_icon3, topic.get(2).getUncheckImage());
        x.image().bind(iv_circle_icon4, topic.get(3).getUncheckImage());

        x.image().bind(iv_a, dataBean.getCellA().getImg());
        x.image().bind(iv_b, dataBean.getCellB().getImg());
        x.image().bind(iv_c1, dataBean.getCellC().getList().get(0).getImage());
        x.image().bind(iv_c2, dataBean.getCellC().getList().get(1).getImage());

        showTopicView(mActivity, 1, topic);

        if (mallListAdapter == null) {
            mallListAdapter = new MallListAdapter(mActivity, dataBean);
        }
        lv_shop_mall.setAdapter(mallListAdapter);

        interestedAdapter = new InterestedAdapter();
        goods = shopBean.getGoodsList();
        interest_grid_view.setAdapter(interestedAdapter);
    }

    /**
     * 点击圆形图片显示响应效果的方法
     *
     * @param mActivity
     * @param position
     * @param topic
     */
    private void showTopicView(Activity mActivity, int position, List<Mall.TopicBean> topic) {
        mall_no_scroll_grid_view.setAdapter(new MallGridViewAdapter(mActivity, position, topic));
        x.image().bind(iv_background, topic.get(position).getBackgroupImage());
        tv_english_title.setText(topic.get(position).getTitleEn());
        tv_chinese_title.setText(topic.get(position).getTitleCn());
    }

    /**
     * 准备好数据以后再去加载布局
     */
    private void showUI() {
        View topView = View.inflate(mActivity, R.layout.shop_mall_layout, null);
        lv_shop_mall.getRefreshableView().addHeaderView(topView);

        View footView = View.inflate(mActivity, R.layout.mall_interested, null);
        interest_grid_view = (NoScrollGridView) footView.findViewById(R.id.interest_grid_view);
        tv_no_more= (TextView) footView.findViewById(R.id.tv_no_more);
        lv_shop_mall.getRefreshableView().addFooterView(footView);

        vp_mall = (ViewPager) topView.findViewById(R.id.vp_mall);
        ll_mall_view_pager_points = (LinearLayout) topView.findViewById(R.id.ll_mall_view_pager_points);

        lv_shop_mall.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        gv_type = (NoScrollGridView) topView.findViewById(R.id.gv_type);
        iv_go_top = (ImageView) topView.findViewById(R.id.iv_go_top);

        ll_abc = (LinearLayout) topView.findViewById(R.id.ll_abc);
        iv_a = (ImageView) topView.findViewById(R.id.iv_a);
        iv_b = (ImageView) topView.findViewById(R.id.iv_b);
        iv_c1 = (ImageView) topView.findViewById(R.id.iv_c1);
        iv_c2 = (ImageView) topView.findViewById(R.id.iv_c2);
        iv_background = (ImageView) topView.findViewById(R.id.iv_background);

        ll_mall_circle_icon = (LinearLayout) topView.findViewById(R.id.ll_mall_circle_icon);
        view_mall_topic_scroll = (HorizontalScrollView) topView.findViewById(R.id.view_mall_topic_scroll);
        iv_circle_icon1 = (ImageView) topView.findViewById(R.id.iv_circle_icon1);
        iv_circle_icon2 = (ImageView) topView.findViewById(R.id.iv_circle_icon2);
        iv_circle_icon3 = (ImageView) topView.findViewById(R.id.iv_circle_icon3);
        iv_circle_icon4 = (ImageView) topView.findViewById(R.id.iv_circle_icon4);

        tv_english_title = (TextView) topView.findViewById(R.id.tv_english_title);
        tv_chinese_title = (TextView) topView.findViewById(R.id.tv_chinese_title);

        mall_no_scroll_grid_view = (NoScrollGridView) topView.findViewById(R.id.mall_no_scroll_grid_view);
        tv_more_shop = (TextView) topView.findViewById(R.id.tv_more_shop);

        setData();
        setListener();
    }

    private void setListener() {
        /**
         * 设置点击切换圆形图片并显示对应的商品内容
         */

        for (int i = 0; i < ll_mall_circle_icon.getChildCount(); i++) {
            final int position = i;
            ll_mall_circle_icon.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTopicView(mActivity, position, topic);
                }
            });
        }

        lv_shop_mall.setOnRefreshListener(new refreshListener());
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
        ImageView iv_item_shop_icon;
        TextView tv_item_tag;
        TextView tv_item_shop_name;
        TextView tv_item_shop_price;
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return viewPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mActivity);
            x.image().bind(imageView, viewPagers.get(position).getImage());
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private class InterestedAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return goods.size();
        }

        @Override
        public Object getItem(int position) {
            return goods.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            GridViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(mActivity, R.layout.interested_item, null);

                holder = new GridViewHolder();
                holder.iv_item_shop_icon = (ImageView) convertView.findViewById(R.id.iv_item_shop_icon);
                holder.tv_item_tag = (TextView) convertView.findViewById(R.id.tv_item_tag);
                holder.tv_item_shop_name = (TextView) convertView.findViewById(R.id.tv_item_shop_name);
                holder.tv_item_shop_price = (TextView) convertView.findViewById(R.id.tv_item_shop_price);
                convertView.setTag(holder);
            } else {
                holder = (GridViewHolder) convertView.getTag();
            }

            int price = goods.get(position).getMarketPrice();
            ImageLoader.getInstance().displayImage(goods.get(position).getImage(), holder.iv_item_shop_icon);

            holder.tv_item_tag.setText(goods.get(position).getIconText());
            Log.e("TAG", "goods.get(position).getIconText()" + goods.get(position).getIconText());
            holder.tv_item_shop_name.setText(goods.get(position).getName());
            holder.tv_item_shop_price.setText("￥" + price / 100);
            return convertView;
        }
    }

    //表示当前请求的页数
    private int currentPager = 2;

    private class refreshListener implements PullToRefreshBase.OnRefreshListener2<ListView> {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        }

        @Override
        public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {
            //请求的页
            int pager = shopBean.getPageCount();
            if (pager >=currentPager) {
                String url = getUrl(currentPager);
                VolleyManager.newInstance().GsonGetRequest(null, url, InterestedShopBean.class, new Response.Listener<InterestedShopBean>() {
                    @Override
                    public void onResponse(InterestedShopBean response) {
                        InterestedShopBean interestedShopBean = response;
                        List<InterestedShopBean.GoodsListBean> newPagerListData = interestedShopBean.getGoodsList();
                        if (newPagerListData != null && !newPagerListData.isEmpty()) {
                            goods.addAll(newPagerListData);
                            interestedAdapter.notifyDataSetChanged();
                            refreshView.setShowViewWhileRefreshing(true);
                            refreshView.onRefreshComplete();
                            currentPager += 1;
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mActivity, "刷新失败,请检查网络", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                refreshView.onRefreshComplete();
                tv_no_more.setVisibility(View.VISIBLE);
                lv_shop_mall.onRefreshComplete();
            }
        }
    }

    /**
     * 得到当前上拉加载更多的url
     */
    private String getUrl(int currentPager) {
        return Url.MALL_BASE_MORE_URL + currentPager + "&goodsIds=102314";
    }
}
