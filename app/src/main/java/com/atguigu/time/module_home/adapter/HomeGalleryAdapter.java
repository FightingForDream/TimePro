package com.atguigu.time.module_home.adapter;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.atguigu.time.R;
import com.atguigu.time.base.BaseActivity;
import com.atguigu.time.bean.HomeHotMovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Huanzi, Xiaopei on 2016/4/9.
 */
public class HomeGalleryAdapter extends BaseAdapter{
    private BaseActivity activity;
    private HomeHotMovieBean hotMovieBean;
    private List<HomeHotMovieBean.MoviesBean> moviesBean;

    public HomeGalleryAdapter(BaseActivity activity, HomeHotMovieBean hotMovieBean) {
        this.activity = activity;
        this.hotMovieBean = hotMovieBean;
        moviesBean = hotMovieBean.getMovies();
    }

    @Override
    public int getCount() {
        return moviesBean == null ? 0 : moviesBean.size();
    }

    @Override
    public Object getItem(int position) {
        return moviesBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(activity, R.layout.home_gallery, null);
            holder.dv_home_gallery_thumbnail = (SimpleDraweeView) convertView.findViewById(R.id.dv_home_gallery_thumbnail);
            holder.tv_home_gallery_title = (TextView) convertView.findViewById(R.id.tv_home_gallery_title);
            holder.btn_home_gallery_buy = (Button) convertView.findViewById(R.id.btn_home_gallery_buy);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        setData(holder, position);
        return convertView;
    }

    /**
     * 设置Gallery视图数据
     */
    public void setData(ViewHolder holder, int position){
        holder.tv_home_gallery_title.setText(moviesBean.get(position).getTitleCn());

        /*String url = "http://uus-img6.android.d.cn/content_pic/201603/behpic/icon/629/2-69629/icon-1458809775520.png";
        if(moviesBean.get(position).getImg() != null){
            url = moviesBean.get(position).getImg();
        }*/
        Uri uri = Uri.parse(moviesBean.get(position).getImg());
        holder.dv_home_gallery_thumbnail.setImageURI(uri);
    }

    static class ViewHolder{
        private SimpleDraweeView dv_home_gallery_thumbnail;
        private TextView tv_home_gallery_title;
        private Button btn_home_gallery_buy;
    }
}
