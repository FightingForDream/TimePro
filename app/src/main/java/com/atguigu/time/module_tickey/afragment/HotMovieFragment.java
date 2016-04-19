package com.atguigu.time.module_tickey.afragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.time.module_tickey.activity.HotMovieActivity;
import com.atguigu.time.R;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：徐阿海 on 2016/4/10:17:35
 * QQ:845514573
 * 功能：显示热映
 */
@ContentView(R.layout.item_movie_hot_pager)
public class HotMovieFragment extends BaseFragment {

    //加载视图
    @ViewInject(R.id.rl_loading)
    private RelativeLayout rl_loading;
    @ViewInject(R.id.iv_loading_failed)
    private ImageView iv_loading_failed;
    @ViewInject(R.id.rl_icon)
    private RelativeLayout rl_icon;
    @ViewInject(R.id.tv_loading)
    private TextView tv_loading;

    private String[] imgSites = {
            "http://image.baidu.com/",
            "http://www.22mm.cc/",
            "http://www.moko.cc/",
            "http://eladies.sina.com.cn/photo/",
            "http://www.youzi4.com/"
    };

    @ViewInject(R.id.lv_movie_hot)
    private ListView hotListView;

    private HotListAdapter hotListAdapter;
    ImageOptions imageOptions;

    private SpannableStringBuilder ssb;//图文混排
//    Context myContext;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = new TextView(getActivity());
        CustomImageGetter imageGetter = new CustomImageGetter(getActivity(), CustomImageGetter.DEFAULT,CustomImageGetter.DEFAULT);

        StringBuilder sb = new StringBuilder("我是HTML:");
        sb.append("<img src=\"" + R.drawable.quot_film_on + "\">");
        textView.setText(Html.fromHtml(sb.toString(), imageGetter, null));


        imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .build();

        hotListAdapter = new HotListAdapter();
        hotListView.setAdapter(hotListAdapter);

        // 加载url请求返回的图片连接给listview
        // 这里只是简单的示例，并非最佳实践，图片较多时，最好上拉加载更多...
        for (String url : imgSites) {
            loadImgList(url);
        }
    }

    private class ImageItemHolder {
        @ViewInject(R.id.iv_icon)
        private ImageView iv_icon;


    }

    /**
     * ListView每一项的点击监听
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Event(value = R.id.lv_movie_hot, type = AdapterView.OnItemClickListener.class)
    private void onImageItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this.getActivity(), HotMovieActivity.class);
        intent.putExtra("url", hotListAdapter.getItem(position).toString());
        this.getActivity().startActivity(intent);
    }

    private void loadImgList(String url) {
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //加载成功把链接传给每一个item
                hotListAdapter.addSrc(getImgSrcList(result));
                hotListAdapter.notifyDataSetChanged();//通知listview更新数据
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * 填装ListView
     */
    private class HotListAdapter extends BaseAdapter {

        private final LayoutInflater mInflater;
        private ArrayList<String> imgSrcList;//所有item的集合

        public HotListAdapter() {
            super();
            mInflater = LayoutInflater.from(getActivity());
            imgSrcList = new ArrayList<String>();
        }

        public void addSrc(List<String> imgSrcList) {//每一个item的集合
            this.imgSrcList.addAll(imgSrcList);
        }

        public void addSrc(String imgUrl) {
            this.imgSrcList.add(imgUrl);
        }


        @Override
        public int getCount() {
            return imgSrcList.size();
        }

        @Override
        public Object getItem(int position) {
            return imgSrcList.get(position);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            ImageItemHolder holder = null;
            if (view == null) {
                view = mInflater.inflate(R.layout.item_movie_hot, null);
                holder = new ImageItemHolder();
                x.view().inject(holder, view);
                view.setTag(holder);
            } else {
                holder = (ImageItemHolder) view.getTag();
            }

//            holder.imgItem.setImageResource(imgSites[position]);

            x.image().bind(holder.iv_icon,
                    imgSrcList.get(position),
                    imageOptions,
                    new CustomBitmapLoadCallBack(holder));
            return view;
        }
    }



    public class CustomBitmapLoadCallBack implements Callback.ProgressCallback<Drawable> {
        private final ImageItemHolder holder;

        public CustomBitmapLoadCallBack(ImageItemHolder holder) {
            this.holder = holder;
        }

        @Override
        public void onWaiting() {
//            this.holder.imgPb.setProgress(0);
        }

        @Override
        public void onStarted() {

        }

        @Override
        public void onLoading(long total, long current, boolean isDownloading) {
//            this.holder.imgPb.setProgress((int) (current * 100 / total));
        }

        @Override
        public void onSuccess(Drawable result) {
//            this.holder.imgPb.setProgress(100);
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
        }

        @Override
        public void onCancelled(CancelledException cex) {

        }

        @Override
        public void onFinished() {

        }
    }

    /**
     * 得到网页中图片的地址
     */
    public static List<String> getImgSrcList(String htmlStr) {
        List<String> pics = new ArrayList<String>();

        String regEx_img = "<img.*?src=\"http://(.*?).jpg\""; // 图片链接地址
        Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        Matcher m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            String src = m_image.group(1);
            if (src.length() < 100) {
                pics.add("http://" + src + ".jpg");
            }
        }
        return pics;
    }

    public static class CustomImageGetter implements Html.ImageGetter {
        public static final int DEFAULT = -1;
        int mRight;
        int mBottom;
        final Context mContext;

        public CustomImageGetter(Context context, int right, int bottom) {
            mRight = right;
            mBottom = bottom;
            mContext = context;
        }

        @Override
        public Drawable getDrawable(String source) {
            int id = Integer.parseInt(source);
            Drawable d = mContext.getResources().getDrawable(id);
            if (null != d) {
                d.setBounds(0, 0, mRight == DEFAULT ? d.getIntrinsicWidth() : mRight,
                        mBottom == DEFAULT ? d.getIntrinsicHeight() : mBottom);
            }
            return d;
        }
    }
}
