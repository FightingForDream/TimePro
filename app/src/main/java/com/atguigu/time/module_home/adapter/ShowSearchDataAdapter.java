package com.atguigu.time.module_home.adapter;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.time.R;
import com.atguigu.time.module_home.bean.SearchItemBean;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Huanzi on 2016/4/19.
 */
public class ShowSearchDataAdapter extends BaseAdapter{
    private Activity mActivity;
    private SearchItemBean searchItemBean;

    public ShowSearchDataAdapter(Activity mActivity, SearchItemBean searchItemBean) {
        this.mActivity = mActivity;
        this.searchItemBean = searchItemBean;
    }

    @Override
    public int getCount() {
        return searchItemBean.getSuggestions() == null ? 0 : searchItemBean.getSuggestions().size();
    }

    @Override
    public Object getItem(int position) {
        return searchItemBean.getSuggestions().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //Log.e("TAG", "---------------" + position);
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            //Log.e("TAG", "XXXXXXXXXXXXXXXXX" + position);
            convertView = View.inflate(mActivity, R.layout.item_search_return_data, null);
            holder.sdv_search_data_cover = (SimpleDraweeView) convertView.findViewById(R.id.sdv_search_data_cover);
            holder.tv_search_data_title = (TextView) convertView.findViewById(R.id.tv_search_data_title);
            holder.tv_search_data_titleEn = (TextView) convertView.findViewById(R.id.tv_search_data_titleEn);
            holder.tv_search_data_type = (TextView) convertView.findViewById(R.id.tv_search_data_type);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        //Log.e("TAG", "~~~~~~~~~~~~~~~~~~~~~" + position);
        holder.sdv_search_data_cover.setImageURI(Uri.parse(searchItemBean.getSuggestions().get(position).getCover()));
        holder.tv_search_data_title.setText(searchItemBean.getSuggestions().get(position).getTitlecn());
        holder.tv_search_data_titleEn.setText(searchItemBean.getSuggestions().get(position).getTitleen());
        holder.tv_search_data_type.setText(searchItemBean.getSuggestions().get(position).getMovieType());
        return convertView;
    }

    static class ViewHolder{
        public SimpleDraweeView sdv_search_data_cover;
        public TextView tv_search_data_title;
        public TextView tv_search_data_titleEn;
        public TextView tv_search_data_type;
    }
}
