package com.atguigu.time.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.time.R;
import com.atguigu.time.bean.Mall;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by SkyWalker on 2016/4/11.
 * 商城点击圆形头像切换商品的gridView adapter
 */
public class MallGridViewAdapter extends BaseAdapter {

    private int iconId;
    private List<Mall.TopicBean> topic;
    private Context context;
    private LinearLayout ll_mall_circle_icon;

    private List<Mall.TopicBean.SubListBean> data;
    private ImageOptions imageOptions;

    public MallGridViewAdapter(Activity mActivity, int iconId, List<Mall.TopicBean> topic) {
        this.context = mActivity;
        this.topic = topic;
        this.iconId = iconId;
        data = topic.get(iconId).getSubList();

        imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))//图片大小
                .setRadius(DensityUtil.dip2px(5))//ImageView圆角半径
                .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.img_default)//加载中默认显示图片
                .setFailureDrawableId(R.drawable.img_default)//加载失败后默认显示图片
                .build();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.grid_view_item, null);
            holder=new Holder();
            holder.iv_shop_icon= (ImageView) convertView.findViewById(R.id.iv_shop_icon);
            holder.tv_shop_name= (TextView) convertView.findViewById(R.id.tv_shop_name);
            holder.tv_shop_price= (TextView) convertView.findViewById(R.id.tv_shop_price);

            convertView.setTag(holder);
        } else {
            holder= (Holder) convertView.getTag();
        }

        holder.tv_shop_name.setText(data.get(position).getTitle());
        x.image().bind(holder.iv_shop_icon,data.get(position).getImage(),imageOptions);

        return convertView;
    }

    class Holder {
        ImageView iv_shop_icon;
        TextView tv_shop_name;
        TextView tv_shop_price;
    }
}
