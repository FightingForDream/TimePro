package com.atguigu.time.module_home.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.atguigu.time.R;
import com.atguigu.time.bean.CityList;

import java.util.List;

/**
 * Created by Huanzi on 2016/4/20.
 */
public class GvCityItemAdapter extends BaseAdapter{
    private Activity mActivity;
    private List<CityList.PEntity> pEntities;

    public GvCityItemAdapter(Activity mActivity, List<CityList.PEntity> pEntities) {
        this.mActivity = mActivity;
        this.pEntities = pEntities;
    }

    public GvCityItemAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void setpEntities(List<CityList.PEntity> pEntities) {
        this.pEntities = pEntities;
    }

    @Override
    public int getCount() {
        return pEntities == null ? 0 : pEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return pEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        GvCityViewHolder holder = null;
        if (convertView == null){
            holder = new GvCityViewHolder();
            convertView = View.inflate(mActivity, R.layout.gv_item_city, null);
            //holder.mCityTextView = convertView;
            holder.mCityTextView = (TextView) convertView.findViewById(R.id.tv_city_name);

            convertView.setTag(holder);
        }else {
            holder = (GvCityViewHolder)convertView.getTag();
        }
        holder.mCityTextView.setText(pEntities.get(position).getN());
        return convertView;
    }

    static class GvCityViewHolder{
        private TextView mCityTextView;
    }
}
