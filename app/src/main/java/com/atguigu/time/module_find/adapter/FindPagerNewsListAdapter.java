package com.atguigu.time.module_find.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.time.R;
import com.atguigu.time.bean.FindPagerNewsList;

import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 发现页面新闻页面listview Adapter
 * @author xpl
 * created at 2016/4/10 21:25
 */
public class FindPagerNewsListAdapter extends BaseAdapter {
    private Activity mActivity;

    private List<FindPagerNewsList.NewsListEntity> newsList;

    public FindPagerNewsListAdapter(Activity mActivity, List<FindPagerNewsList.NewsListEntity> newsList) {
        this.mActivity = mActivity;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        int type = newsList.get(position).getType();
        if(convertView == null){
             holder = new ViewHolder();
            switch (type) {
                case 0 :
                case 2 :
                    convertView = View.inflate(mActivity, R.layout.item_find_news_type0, null);
                    holder.imageView = (ImageView) convertView.findViewById(R.id.item_iv_find_type0_image);
                    holder.title = (TextView) convertView.findViewById(R.id.item_tv_find_type0_title);
                    holder.content = (TextView) convertView.findViewById(R.id.item_tv_find_type0_content);
                    holder.time = (TextView) convertView.findViewById(R.id.item_tv_find_type0_time);
                    holder.count = (TextView) convertView.findViewById(R.id.item_tv_find_type0_count);
                    holder.imagePlay = (ImageView) convertView.findViewById(R.id.item_iv_find_type0_play);
                    if(type == 0){
                        holder.imagePlay.setVisibility(View.GONE);
                    }else{
                        holder.imagePlay.setVisibility(View.VISIBLE);
                    }
                    convertView.setTag(holder);
                    break;
                case 1 :
                    convertView = View.inflate(mActivity, R.layout.item_find_news_type1, null);
                    holder.title = (TextView) convertView.findViewById(R.id.item_tv_find_type1_title);
                    holder.image11 = (ImageView) convertView.findViewById(R.id.item_iv_find_type1_image1);
                    holder.image12 = (ImageView) convertView.findViewById(R.id.item_iv_find_type1_image2);
                    holder.image13 = (ImageView) convertView.findViewById(R.id.item_iv_find_type1_image3);
                    holder.time = (TextView) convertView.findViewById(R.id.item_tv_find_type1_time);
                    holder.count = (TextView) convertView.findViewById(R.id.item_tv_find_type1_count);
                    convertView.setTag(holder);
                    break;
            }
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //装配数据
        if (holder.imageView != null) {
            x.image().bind(holder.imageView, newsList.get(position).getImage());
        }
        holder.title.setText(newsList.get(position).getTitle());
        if (holder.content != null) {
            holder.content.setText(newsList.get(position).getTitle2());
        }
        holder.time.setText(getMilliToDate(newsList.get(position).getPublishTime())+"分钟前");
        holder.count.setText("评论 "+newsList.get(position).getCommentCount());
        if(newsList.get(position).getImages()!=null){
            x.image().bind(holder.image11,newsList.get(position).getImages().get(0).getUrl1());
            x.image().bind(holder.image12,newsList.get(position).getImages().get(1).getUrl1());
            x.image().bind(holder.image13,newsList.get(position).getImages().get(2).getUrl1());
        }
        return convertView;
    }

    static class ViewHolder{
        ImageView imagePlay;
        ImageView imageView;
        TextView title;
        TextView content;
        TextView time;
        TextView count;

        ImageView image11;
        ImageView image12;
        ImageView image13;
    }
    public static String getMilliToDate(long time) {
        Date date = new Date(Long.valueOf(time));
        SimpleDateFormat formatter = new SimpleDateFormat("mm");
        return formatter.format(date);
    }
}
