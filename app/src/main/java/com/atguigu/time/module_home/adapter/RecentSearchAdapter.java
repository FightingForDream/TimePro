package com.atguigu.time.module_home.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.time.R;

import java.util.List;

/**
 * Created by Huanzi on 2016/4/19.
 */
public class RecentSearchAdapter extends BaseAdapter{
    private Activity mActivity;
    private List<String> hotSearchList;

    public RecentSearchAdapter(Activity mActivity, List<String> hotSearchList) {
        this.mActivity = mActivity;
        this.hotSearchList = hotSearchList;
    }

    @Override
    public int getCount() {
        return hotSearchList == null ? 0 : hotSearchList.size();
    }

    @Override
    public Object getItem(int position) {
        return hotSearchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(mActivity, R.layout.item_search_recent, null);
            holder.tv_search_recent = (TextView) convertView.findViewById(R.id.tv_search_recent);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tv_search_recent.setText(hotSearchList.get(position));
        return convertView;
    }

    static class ViewHolder{
        TextView tv_search_recent;
    }
}
