package com.atguigu.time.pager;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.time.R;
import com.atguigu.time.base.BasePager;
import com.atguigu.time.pager.impletickbuy.CinemaPager;
import com.atguigu.time.pager.impletickbuy.MoviePager;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vence on 16/4/8 18:17
 * 邮箱 ：vence0815@foxmail.com
 */
public class TicketBuyPager extends BasePager implements View.OnClickListener {

    private TextView tv_city,tv_movie,tv_cinema;

    private ImageView iv_search;
    private FrameLayout fl_tickbuy_content;

    private List<TicketBuyPager> tickBuyPagers;//电影和影院页的集合
    private int selectPosition;//记录选中的是电影or影院

    public TicketBuyPager(Activity activity) {
        super(activity);

    }

    @Override
    protected View getView() {
        View view = View.inflate(mActivity, R.layout.ticketbuypager, null);
        x.view().inject(this, view);//把view依赖注入到xUtils3框架中

        fl_tickbuy_content = (FrameLayout) view.findViewById(R.id.fl_tickbuy_content);

        tv_city = (TextView) view.findViewById(R.id.tv_city);
        tv_movie = (TextView) view.findViewById(R.id.tv_movie);
        tv_cinema = (TextView) view.findViewById(R.id.tv_cinema);
        iv_search = (ImageView) view.findViewById(R.id.iv_search);


        //默认选中电影页面
        tv_movie.setSelected(true);
        tv_cinema.setSelected(false);

        //监听
        tv_city.setOnClickListener(this);
        tv_movie.setOnClickListener(this);
        tv_cinema.setOnClickListener(this);
        iv_search.setOnClickListener(this);

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        tickBuyPagers = new ArrayList<>();
        tickBuyPagers.add(new MoviePager(mActivity));
        tickBuyPagers.add(new CinemaPager(mActivity));

    }

    /**
     * 转换电影或影院页面
     * @param position
     */
    public void switchPager(int position) {

        //得到页面
        TicketBuyPager ticketBuyPager = tickBuyPagers.get(position);

        View rootView = ticketBuyPager.mRootView;//代表不同的详情页面的视图
        ticketBuyPager.initData();//初始化数据

        fl_tickbuy_content.removeAllViews();//移除之前的视图
        fl_tickbuy_content.addView(rootView);//添加电影或影院视图
    }
    //titlebar上的空间的点击监听
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_city :
                Toast.makeText(mActivity, "城市列表", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_movie :
                Toast.makeText(mActivity, "电影页面", Toast.LENGTH_SHORT).show();
                tv_movie.setSelected(true);
                tv_cinema.setSelected(false);

                selectPosition = 0;
                switchPager(selectPosition);
                break;
            case R.id.tv_cinema :
                Toast.makeText(mActivity, "影院页面", Toast.LENGTH_SHORT).show();
                tv_movie.setSelected(false);
                tv_cinema.setSelected(true);
                selectPosition = 1;
                switchPager(selectPosition);
                break;
            case R.id.iv_search :
                Toast.makeText(mActivity, "搜索页面", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
