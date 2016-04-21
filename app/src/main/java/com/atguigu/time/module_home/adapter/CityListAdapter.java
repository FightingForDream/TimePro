package com.atguigu.time.module_home.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.time.R;
import com.atguigu.time.bean.CityList;

import java.util.List;
import java.util.Map;

/**
 * Created by Huanzi on 2016/4/20.
 */
public class CityListAdapter extends BaseAdapter{
    /**上下文*/
    private Activity mActivity;
    /**数据集*/
    private List<CityList.PEntity> pEntities;
    private Map<Integer, List<CityList.PEntity>> cityMap;
    /**GridView装配器*/
    private GvCityItemAdapter gvCityItemAdapter;

    /**标识是否新的字母开头*/
    private boolean isNewAlphabetic = true;
    private String firstAlphabetic;

    public CityListAdapter(Activity mActivity, List<CityList.PEntity> pEntities, Map<Integer, List<CityList.PEntity>> cityMap) {
        this.mActivity = mActivity;
        this.pEntities = pEntities;
        this.cityMap = cityMap;
        gvCityItemAdapter = new GvCityItemAdapter(mActivity);
    }

    @Override
    public int getCount() {
        return cityMap == null ? 0 : cityMap.size();
    }

    @Override
    public Object getItem(int position) {
        return cityMap.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        CityHolder holder = null;
        //CityTypeHolder typeHolder = null;
        /*if(position % 2 == 0){
            if (convertView == null){
                typeHolder = new CityTypeHolder();
                convertView = View.inflate(mActivity, R.layout.lv_item_city_type, null);
                //holder.mCityTextView = convertView;
                typeHolder.mCityTextView = (TextView) convertView.findViewById(R.id.tv_city_type);

                convertView.setTag(typeHolder);
            }else {
                typeHolder = (CityTypeHolder)convertView.getTag();
            }
            typeHolder.mCityTextView.setText(pEntities.get(position).getPinyinShort().substring(0, 1));
        }else {
            if (convertView == null){
                holder = new CityHolder();
                convertView = View.inflate(mActivity, R.layout.lv_item_city_list, null);
                //holder.mCityTextView = convertView;
                holder.mCityListGridView = (GridView) convertView.findViewById(R.id.gv_cityList_item);

                convertView.setTag(holder);
            }else {
                holder = (CityHolder)convertView.getTag();
            }
            gvCityItemAdapter.setpEntities(pEntities.subList(0, 10));
            holder.mCityListGridView.setAdapter(gvCityItemAdapter);
        }*/
        if (convertView == null){
            holder = new CityHolder();
            convertView = View.inflate(mActivity, R.layout.lv_item_city_list, null);
            holder.mCityTextView = (TextView) convertView.findViewById(R.id.tv_city_alphabetic);
            holder.mCityListGridView = (GridView) convertView.findViewById(R.id.gv_cityList_item);

            convertView.setTag(holder);
        }else {
            holder = (CityHolder)convertView.getTag();
        }
        //if(position % 2 == 0){
            //holder.mCityTextView.setVisibility(View.VISIBLE);
            //holder.mCityListGridView.setVisibility(View.GONE);
            holder.mCityTextView.setText(cityMap.get(position).get(0).getPinyinShort().substring(0, 1).toUpperCase());
            gvCityItemAdapter.setpEntities(cityMap.get(position));
            holder.mCityListGridView.setAdapter(gvCityItemAdapter);
        /*}else {
            holder.mCityTextView.setVisibility(View.GONE);
            //holder.mCityListGridView.setVisibility(View.VISIBLE);
            gvCityItemAdapter.setpEntities(cityMap.get(position));
            holder.mCityListGridView.setAdapter(gvCityItemAdapter);
        }*/

        return convertView;
    }

    static class CityHolder{
        private TextView mCityTextView;
        private GridView mCityListGridView;
    }

    /*static class CityTypeHolder{
        private TextView mCityTextView;
    }*/
}
