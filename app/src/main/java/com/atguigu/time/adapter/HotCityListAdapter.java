package com.atguigu.time.adapter;

import android.content.Intent;
import android.view.Gravity;
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
public class HotCityListAdapter extends BaseAdapter {
    private boolean isCenter=false;
    private CityListActivity context;
    List<CityList.PEntity> cityList;

    public HotCityListAdapter(CityListActivity cityListActivity, List<CityList.PEntity> cityList,boolean isCenter) {
        this.context = cityListActivity;
        this.cityList = cityList;
        this.isCenter=isCenter;
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

        final CityList.PEntity pEntity = cityList.get(position);
        if(isCenter) {
            viewHolder.tv_litter.setGravity(Gravity.CENTER);
        }else{
            viewHolder.tv_litter.setGravity(Gravity.LEFT);
        }

        viewHolder.tv_litter.setText(pEntity.getN());
        viewHolder.tv_litter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //带结果的回调
                Intent intent = new Intent();
                intent.putExtra("cityID", pEntity.getId());
                context.setResult(1, intent);
                context.finish();
            }
        });
        return convertView;
    }

    class ViewHoder {
        TextView tv_litter;
    }
}
