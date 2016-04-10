package com.atguigu.time.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.time.R;
import com.atguigu.time.activity.CityListActivity;
import com.atguigu.time.bean.CityList;

import java.util.List;

/**
 * Created by vence on 16/4/10 09:43
 * 邮箱 ：vence0815@foxmail.com
 */
public class CityListAdapter extends BaseAdapter {
    private Context context;
    List<CityList.PEntity> cityList;

    public CityListAdapter(CityListActivity cityListActivity, List<CityList.PEntity> cityList) {
        this.context = cityListActivity;
        this.cityList = cityList;
    }


    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHoder();
            convertView = View.inflate(context, R.layout.city_list_item, null);
            viewHolder.tv_litter = (TextView) convertView.findViewById(R.id.tv_gird_hot_city);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHoder) convertView.getTag();
        }

        CityList.PEntity pEntity = cityList.get(position);

        viewHolder.tv_litter.setText(pEntity.getN());
        return convertView;
    }

    class ViewHoder {
        TextView tv_litter;
    }
}
