package com.atguigu.time.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.time.R;
import com.atguigu.time.bean.Mall;

import org.xutils.x;

import java.util.List;

/**
 * Created by SkyWalker on 2016/4/9.
 */
public class MallListAdapter extends BaseAdapter {

    private Activity mActivity;
    private Mall dataBean;

    //玩具模型  数码配件 服饰配件 家居生活
    private List<Mall.CategoryBean> categoryBean;

    public MallListAdapter(Activity mActivity, Mall dataBean) {
        this.dataBean = dataBean;
        this.mActivity = mActivity;
        this.categoryBean = dataBean.getCategory();
    }

    @Override
    public int getCount() {
        return categoryBean.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mActivity, R.layout.type_shop_mall_pager, null);
            //玩具模型标题
            viewHolder.tv_item_name = (TextView) convertView.findViewById(R.id.tv_item_name);
            //大图
            viewHolder.iv_big = (ImageView) convertView.findViewById(R.id.iv_big);
            //小图
            viewHolder.iv_small1 = (ImageView) convertView.findViewById(R.id.iv_small1);
            viewHolder.iv_small2 = (ImageView) convertView.findViewById(R.id.iv_small2);
            viewHolder.iv_small3 = (ImageView) convertView.findViewById(R.id.iv_small3);
            //商品名称
            viewHolder.tv_name1 = (TextView) convertView.findViewById(R.id.tv_item_name1);
            viewHolder.tv_name2 = (TextView) convertView.findViewById(R.id.tv_item_name2);
            viewHolder.tv_name3 = (TextView) convertView.findViewById(R.id.tv_item_name3);

            //价格
            viewHolder.tv_price1 = (TextView) convertView.findViewById(R.id.tv_price1);
            viewHolder.tv_price2 = (TextView) convertView.findViewById(R.id.tv_price2);
            viewHolder.tv_price3 = (TextView) convertView.findViewById(R.id.tv_price3);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_item_name.setText(categoryBean.get(position).getName());

        x.image().bind(viewHolder.iv_big, categoryBean.get(position).getImage());

        x.image().bind(viewHolder.iv_small1, categoryBean.get(position).getSubList().get(0).getImage());
        x.image().bind(viewHolder.iv_small2, categoryBean.get(position).getSubList().get(1).getImage());
        x.image().bind(viewHolder.iv_small3, categoryBean.get(position).getSubList().get(2).getImage());

        viewHolder.tv_name1.setText(categoryBean.get(position).getSubList().get(0).getTitle());
        viewHolder.tv_name2.setText(categoryBean.get(position).getSubList().get(1).getTitle());
        viewHolder.tv_name3.setText(categoryBean.get(position).getSubList().get(2).getTitle());

        return convertView;
    }


    class ViewHolder {
        TextView tv_item_name;
        ImageView iv_big;
        ImageView iv_small1;
        ImageView iv_small2;
        ImageView iv_small3;
        TextView tv_price1;
        TextView tv_price2;
        TextView tv_price3;
        TextView tv_name1;
        TextView tv_name2;
        TextView tv_name3;
    }
}
