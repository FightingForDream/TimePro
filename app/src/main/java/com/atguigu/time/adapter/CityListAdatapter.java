package com.atguigu.time.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.time.R;
import com.atguigu.time.activity.CityListActivity;
import com.atguigu.time.bean.CityList;
import com.atguigu.time.view.NoScrollGridView;

import java.util.List;
import java.util.Map;

/**
 * Created by vence on 16/4/12 21:36
 * 邮箱 ：vence0815@foxmail.com
 */
public class CityListAdatapter extends BaseAdapter {
    private CityListActivity cityListActivity;
    private List<String> litters;
    private Map<String, List<CityList.PEntity>> maps;

    public CityListAdatapter(CityListActivity cityListActivity, List<String> litters, Map<String, List<CityList.PEntity>> maps) {
        this.cityListActivity = cityListActivity;
        this.litters = litters;
        this.maps = maps;
    }

    @Override
    public int getCount() {

        return litters.size();
    }

    @Override
    public Object getItem(int position) {
        return litters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder hoder = null;
        if (convertView == null) {
            hoder = new ViewHoder();
            convertView = View.inflate(cityListActivity, R.layout.city_list_inner_item, null);
            hoder.gv_inner = (NoScrollGridView) convertView.findViewById(R.id.gv_inner);
            hoder.tv_title = (TextView) convertView.findViewById(R.id.tv_titile);
            convertView.setTag(hoder);
        } else {
            hoder = (ViewHoder) convertView.getTag();
        }

        hoder.tv_title.setText(litters.get(position));
        hoder.gv_inner.setAdapter(new HotCityListAdapter(cityListActivity, maps.get(litters.get(position)),true));

        return convertView;
    }

    class ViewHoder {
        TextView tv_title;
        NoScrollGridView gv_inner;
    }

}

