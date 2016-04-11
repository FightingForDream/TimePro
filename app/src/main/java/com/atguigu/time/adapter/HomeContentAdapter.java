package com.atguigu.time.adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.time.R;
import com.atguigu.time.bean.HomeContentBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Huanzi, Xiaopei on 2016/4/8.
 */
public class HomeContentAdapter extends BaseAdapter{
    private static final int JIAN_XUN = 51;
    private static final int CAI_DIAN_YING = 64;
    private static final int OU_MEI_XIN_PIAN = -1;
    private static final int YING_PING = 336;

    private static final int TYPE_JIAN_XUN = 0;
    private static final int TYPE_CAI_DIAN_YING = 1;
    private static final int TYPE_OU_MEI_XIN_PIAN = 2;
    private static final int TYPE_YING_PING = 3;

    private static final int TU_JI = 1;
    private static final int TOU_TIAO = 0;

    private Activity activity;
    private HomeContentBean entity;
    private List<HomeContentBean.DataBean> dataList;

    private String isNewType ="";

    public HomeContentAdapter(Activity activity, HomeContentBean entity) {
        this.activity = activity;
        this.entity = entity;
        this.dataList = entity.getData();
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position).getType() == JIAN_XUN){
            return TYPE_JIAN_XUN;
        }else if (dataList.get(position).getType() == CAI_DIAN_YING){
            return TYPE_CAI_DIAN_YING;
        }else if (dataList.get(position).getType() == OU_MEI_XIN_PIAN){
            return TYPE_OU_MEI_XIN_PIAN;
        }else if (dataList.get(position).getType() == YING_PING){
            return TYPE_YING_PING;
        }
        return dataList.get(position).getType();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        int type = getItemViewType(position);
        int dataType = dataList.get(position).getDataType();
        //int flag = type + dataType;
        //ViewHolder holder = null;
        JianXunHolder jianXunHolder = null;
        CaidianyingHolder caidianyingHolder = null;
        OumeixinpianHolder oumeixinpianHolder = null;
        YingpingHolder yingpingHolder = null;
          //  holder = new ViewHolder();
            //isNewType += type + ",";
        switch (type){
            case TYPE_JIAN_XUN:
                if (convertView == null) {
                    jianXunHolder = new JianXunHolder();
                    convertView = View.inflate(activity, R.layout.pager_item_jianxun, null);
                    jianXunHolder.tv_jianxun_tag = (TextView) convertView.findViewById(R.id.tv_jianxun_tag);
                    jianXunHolder.tv_jianxun_title = (TextView) convertView.findViewById(R.id.tv_jianxun_title);
                    jianXunHolder.tv_jianxun_subtitle = (TextView) convertView.findViewById(R.id.tv_jianxun_subtitle);
                    jianXunHolder.tv_jianxun_time = (TextView) convertView.findViewById(R.id.tv_jianxun_time);
                    jianXunHolder.tv_jianxun_comment = (TextView) convertView.findViewById(R.id.tv_jianxun_comment);

                    //if (dataType == TU_JI){
                    jianXunHolder.ll_item_tuji = (LinearLayout) convertView.findViewById(R.id.ll_item_tuji);
                    jianXunHolder.sdv_tuji_01 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_tuji_01);
                    jianXunHolder.sdv_tuji_02 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_tuji_02);
                    jianXunHolder.sdv_tuji_03 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_tuji_03);
                    //}else {
                    jianXunHolder.sdv_jianxun_thumbnail = (SimpleDraweeView) convertView.findViewById(R.id.sdv_jianxun_thumbnail);
                    //}
                    convertView.setTag(jianXunHolder);
                }else {
                    jianXunHolder = (JianXunHolder) convertView.getTag();
                }
                setJianxunData(jianXunHolder, position, type, dataType);
                break;
            case TYPE_CAI_DIAN_YING:
                if(convertView == null) {
                    caidianyingHolder = new CaidianyingHolder();
                    convertView = View.inflate(activity, R.layout.pager_item_caidianying, null);
                    caidianyingHolder.tv_caidianying_tag = (TextView) convertView.findViewById(R.id.tv_caidianying_tag);
                    caidianyingHolder.tv_caidianying_title = (TextView) convertView.findViewById(R.id.tv_caidianying_title);
                    caidianyingHolder.tv_caidianying_subtitle = (TextView) convertView.findViewById(R.id.tv_caidianying_subtitle);
                    caidianyingHolder.sdv_caidianying_thumbnail = (SimpleDraweeView) convertView.findViewById(R.id.sdv_caidianying_thumbnail);

                    convertView.setTag(caidianyingHolder);
                }else {
                    caidianyingHolder = (CaidianyingHolder) convertView.getTag();
                }
                setCaidianyingData(caidianyingHolder, position, type, dataType);
                break;
            case TYPE_OU_MEI_XIN_PIAN:
                if (convertView == null) {
                    oumeixinpianHolder = new OumeixinpianHolder();
                    convertView = View.inflate(activity, R.layout.pager_item_oumeixinpian, null);
                    oumeixinpianHolder.tv_oumeixinpian_tag = (TextView) convertView.findViewById(R.id.tv_oumeixinpian_tag);
                    oumeixinpianHolder.tv_oumeixinpian_title = (TextView) convertView.findViewById(R.id.tv_oumeixinpian_title);
                    oumeixinpianHolder.tv_oumeixinpian_subtitle = (TextView) convertView.findViewById(R.id.tv_oumeixinpian_subtitle);
                    oumeixinpianHolder.tv_oumeixinpian_content = (TextView) convertView.findViewById(R.id.tv_oumeixinpian_content);
                    oumeixinpianHolder.sdv_oumeixinpian_thumbnail = (SimpleDraweeView) convertView.findViewById(R.id.sdv_oumeixinpian_thumbnail);

                    convertView.setTag(oumeixinpianHolder);
                }else {
                    oumeixinpianHolder = (OumeixinpianHolder) convertView.getTag();
                }
                setOumeixinpianData(oumeixinpianHolder, position, type, dataType);
                break;
            case TYPE_YING_PING:
                if (convertView == null) {
                    yingpingHolder = new YingpingHolder();
                    convertView = View.inflate(activity, R.layout.pager_item_yingping, null);
                    yingpingHolder.tv_yingping_tag = (TextView) convertView.findViewById(R.id.tv_yingping_tag);
                    yingpingHolder.tv_yingping_title = (TextView) convertView.findViewById(R.id.tv_yingping_title);
                    yingpingHolder.tv_yingping_subtitle = (TextView) convertView.findViewById(R.id.tv_yingping_subtitle);
                    yingpingHolder.sdv_yingping_avatar = (SimpleDraweeView) convertView.findViewById(R.id.sdv_yingping_avatar);
                    yingpingHolder.tv_yingping_username = (TextView) convertView.findViewById(R.id.tv_yingping_username);
                    yingpingHolder.tv_yingping_film = (TextView) convertView.findViewById(R.id.tv_yingping_film);
                    yingpingHolder.tv_yingping_rate = (TextView) convertView.findViewById(R.id.tv_yingping_rate);

                    yingpingHolder.sdv_yingping_thumbnail = (SimpleDraweeView) convertView.findViewById(R.id.sdv_yingping_thumbnail);

                    convertView.setTag(yingpingHolder);
                }else {
                    yingpingHolder = (YingpingHolder) convertView.getTag();
                }
                setYingpingData(yingpingHolder, position, type, dataType);
                break;
        }
        /*convertView = View.inflate(activity, R.layout.pager_item_jianxun, null);
        holder.tv_home_item_tag = (TextView) convertView.findViewById(R.id.tv_home_item_tag);
        holder.tv_home_item_title = (TextView) convertView.findViewById(R.id.tv_home_item_title);
        holder.tv_home_item_subtitle = (TextView) convertView.findViewById(R.id.tv_home_item_subtitle);
        holder.tv_home_item_time = (TextView) convertView.findViewById(R.id.tv_home_item_time);
        holder.tv_home_item_comment = (TextView) convertView.findViewById(R.id.tv_home_item_comment);
        holder.iv_home_item_thumbnail = (SimpleDraweeView) convertView.findViewById(R.id.iv_home_item_thumbnail);*/
    /*
        convertView.setTag(holder);
    }else {
        holder = (ViewHolder) convertView.getTag();
    }*/

        return convertView;
    }

    public void setJianxunData(JianXunHolder holder, int position, int type, int dataType) {
        holder.tv_jianxun_tag.setText(dataList.get(position).getTag());   //设置类型，如简讯
        holder.tv_jianxun_title.setText(dataList.get(position).getTitle());   //设置标题
        holder.tv_jianxun_subtitle.setText(dataList.get(position).getContent());  //设置内容
        holder.tv_jianxun_comment.setText("评论" + dataList.get(position).getCommentCount()); //设置评论数

        if (dataType == TU_JI){
            holder.ll_item_tuji.setVisibility(View.VISIBLE);
            holder.sdv_tuji_02.setVisibility(View.VISIBLE);
            holder.sdv_tuji_03.setVisibility(View.VISIBLE);
            holder.tv_jianxun_time.setText(dataList.get(position).getPublishTime() + "");  //设置发布时间
            holder.sdv_tuji_01.setImageURI(Uri.parse(dataList.get(position).getImages().get(1).getUrl1()));
            holder.sdv_tuji_02.setImageURI(Uri.parse(dataList.get(position).getImages().get(2).getUrl1()));
            holder.sdv_tuji_03.setImageURI(Uri.parse(dataList.get(position).getImages().get(3).getUrl1()));
            holder.sdv_jianxun_thumbnail.setVisibility(View.GONE);
        }else if(dataType == TOU_TIAO){
            holder.ll_item_tuji.setVisibility(View.VISIBLE);
            holder.tv_jianxun_time.setText(dataList.get(position).getTime());  //设置发布时间
            holder.sdv_tuji_01.setImageURI(Uri.parse(dataList.get(position).getImg1()));
            holder.sdv_tuji_02.setVisibility(View.GONE);
            holder.sdv_tuji_03.setVisibility(View.GONE);
            holder.sdv_jianxun_thumbnail.setVisibility(View.GONE);
        }else {
            holder.sdv_jianxun_thumbnail.setVisibility(View.VISIBLE);
            holder.ll_item_tuji.setVisibility(View.GONE);
            holder.tv_jianxun_time.setText(dataList.get(position).getTime());  //设置发布时间
            Uri uri = Uri.parse(dataList.get(position).getImg1());
            holder.sdv_jianxun_thumbnail.setImageURI(uri);
        }
    }

    public void setCaidianyingData(CaidianyingHolder holder, int position, int type, int dataType) {
        holder.tv_caidianying_tag.setText(dataList.get(position).getTag());
        holder.tv_caidianying_title.setText(dataList.get(position).getTitle());
        holder.tv_caidianying_subtitle.setText(dataList.get(position).getMemo());
        holder.sdv_caidianying_thumbnail.setImageURI(Uri.parse(dataList.get(position).getPic1Url()));
    }

    public void setOumeixinpianData(OumeixinpianHolder holder, int position, int type, int dataType) {
        holder.tv_oumeixinpian_tag.setText(dataList.get(position).getTag());
        holder.tv_oumeixinpian_title.setText(dataList.get(position).getTitle());
        holder.tv_oumeixinpian_subtitle.setText(dataList.get(position).getTitlesmall());
        holder.tv_oumeixinpian_content.setText(dataList.get(position).getTitlesmall());
        ;
        holder.sdv_oumeixinpian_thumbnail.setImageURI(Uri.parse(dataList.get(position).getPic1Url()));
    }

    public void setYingpingData(YingpingHolder holder, int position, int type, int dataType) {
        holder.tv_yingping_tag.setText(dataList.get(position).getTag());
        holder.tv_yingping_title.setText(dataList.get(position).getTitle());
        holder.tv_yingping_subtitle.setText(dataList.get(position).getSummaryInfo());
        holder.sdv_yingping_avatar.setImageURI(Uri.parse(dataList.get(position).getUserImage()));
        holder.tv_yingping_username.setText(dataList.get(position).getNickname() + "-评");
        holder.tv_yingping_film.setText("《" + dataList.get(position).getRelatedObj().getTitle() + "》");
        holder.tv_yingping_rate.setText(dataList.get(position).getRelatedObj().getRating() + ".0");

        holder.sdv_yingping_thumbnail.setImageURI(Uri.parse(dataList.get(position).getRelatedObj().getImage()));
    }

    /*public void setData(ViewHolder holder, int position, int type, int dataType){
        switch (type){
            case JIAN_XUN:
                holder.tv_jianxun_tag.setText(dataList.get(position).getTag());   //设置类型，如简讯
                holder.tv_jianxun_title.setText(dataList.get(position).getTitle());   //设置标题
                holder.tv_jianxun_subtitle.setText(dataList.get(position).getContent());  //设置内容
                holder.tv_jianxun_time.setText(dataList.get(position).getPublishTime() + "");  //设置发布时间
                holder.tv_jianxun_comment.setText(dataList.get(position).getCommentCount() + ""); //设置评论数

                if (dataType == TU_JI){
                    holder.ll_item_tuji.setVisibility(View.VISIBLE);
                    holder.sdv_tuji_01.setImageURI(Uri.parse(dataList.get(position).getImages().get(1).getUrl1()));
                    holder.sdv_tuji_02.setImageURI(Uri.parse(dataList.get(position).getImages().get(2).getUrl1()));
                    holder.sdv_tuji_03.setImageURI(Uri.parse(dataList.get(position).getImages().get(3).getUrl1()));
                    holder.sdv_jianxun_thumbnail.setVisibility(View.GONE);
                }else {
                    holder.sdv_jianxun_thumbnail.setVisibility(View.VISIBLE);
                    holder.ll_item_tuji.setVisibility(View.GONE);

                    Uri uri = Uri.parse(dataList.get(position).getImg1());
                    holder.sdv_jianxun_thumbnail.setImageURI(uri);
                }
                break;
            case CAI_DIAN_YING:
                holder.tv_caidianying_tag.setText(dataList.get(position).getTag());
                holder.tv_caidianying_title.setText(dataList.get(position).getTitle());
                holder.tv_caidianying_subtitle.setText(dataList.get(position).getMemo());
                holder.sdv_caidianying_thumbnail.setImageURI(Uri.parse(dataList.get(position).getPic1Url()));
                break;
            case OU_MEI_XIN_PIAN:
                holder.tv_oumeixinpian_tag.setText(dataList.get(position).getTag());
                holder.tv_oumeixinpian_title.setText(dataList.get(position).getTitle());
                holder.tv_oumeixinpian_subtitle.setText(dataList.get(position).getTitlesmall());
                holder.tv_oumeixinpian_content.setText(dataList.get(position).getTitlesmall());;
                holder.sdv_oumeixinpian_thumbnail.setImageURI(Uri.parse(dataList.get(position).getPic1Url()));
                break;
            case YING_PING:
                holder.tv_yingping_tag.setText(dataList.get(position).getTag());
                holder.tv_yingping_title.setText(dataList.get(position).getTitle());
                holder.tv_yingping_subtitle.setText(dataList.get(position).getSummaryInfo());
                holder.sdv_yingping_avatar.setImageURI(Uri.parse(dataList.get(position).getUserImage()));
                holder.tv_yingping_username.setText(dataList.get(position).getNickname());
                holder.tv_yingping_film.setText(dataList.get(position).getRelatedObj().getTitle());
                holder.tv_yingping_rate.setText(dataList.get(position).getRelatedObj().getRating());

                holder.sdv_yingping_thumbnail.setImageURI(Uri.parse(dataList.get(position).getRelatedObj().getImage()));
                break;
        }
    }*/

    static class JianXunHolder{
        /**------------------------简讯模块-----------------------*/
        public TextView tv_jianxun_tag;
        public TextView tv_jianxun_title;
        public TextView tv_jianxun_subtitle;
        public TextView tv_jianxun_time;
        public TextView tv_jianxun_comment;
        public SimpleDraweeView sdv_jianxun_thumbnail;

        public LinearLayout ll_item_tuji;
        public SimpleDraweeView sdv_tuji_01;
        public SimpleDraweeView sdv_tuji_02;
        public SimpleDraweeView sdv_tuji_03;
    }
    static class CaidianyingHolder{
        /**------------------------猜电影模块---------------------*/
        public TextView tv_caidianying_tag;
        public TextView tv_caidianying_title;
        public TextView tv_caidianying_subtitle;
        public SimpleDraweeView sdv_caidianying_thumbnail;
    }
    static class OumeixinpianHolder{
        /**------------------------欧美新片模块---------------------*/
        public TextView tv_oumeixinpian_tag;
        public TextView tv_oumeixinpian_title;
        public TextView tv_oumeixinpian_subtitle;
        public TextView tv_oumeixinpian_content;
        public SimpleDraweeView sdv_oumeixinpian_thumbnail;
    }
    static class YingpingHolder{
        /**------------------------影评模块---------------------*/
        public TextView tv_yingping_tag;
        public TextView tv_yingping_title;
        public TextView tv_yingping_subtitle;
        public SimpleDraweeView sdv_yingping_avatar;
        public TextView tv_yingping_username;
        public TextView tv_yingping_film;
        public TextView tv_yingping_rate;
        public SimpleDraweeView sdv_yingping_thumbnail;
    }
    static class ToutiaoHolder{
        /**------------------------影评模块---------------------*/
        public TextView tv_yingping_tag;
        public TextView tv_yingping_title;
        public TextView tv_yingping_subtitle;
        public SimpleDraweeView sdv_yingping_avatar;
        public TextView tv_yingping_username;
        public TextView tv_yingping_film;
        public TextView tv_yingping_rate;
        public SimpleDraweeView sdv_yingping_thumbnail;
    }
    /*public String parseTime(long time){
        new Date().getTime() - time;
    }*/
}
