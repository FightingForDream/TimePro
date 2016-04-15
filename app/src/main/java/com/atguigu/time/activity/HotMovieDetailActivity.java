package com.atguigu.time.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.atguigu.time.R;
import com.atguigu.time.base.BaseActivity;
import com.atguigu.time.view.XScrollView;

public class HotMovieDetailActivity extends BaseActivity implements XScrollView.OnScrollStateChangedListener{
    /**影片演员信息容器*/
    private LinearLayout ll_film_detail_actors;
    /**影片简介组件*/
    private TextView tv_film_brief;
    private ImageButton ib_show_more_brief;
    private XScrollView film_detail_scrollview;
    private Toolbar toolbar;
    private RelativeLayout rl_film_detail_header;
    private Button btn_film_detail_header_buy;
    private Button btn_film_detail_buy;
    private ViewTreeObserver mObserver;

    //private int mLineHeight;
    private int mTotalLines;
    private static final int DEFAULT_LINES = 3;

    private boolean isExpanded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_movie_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        //getSupportActionBar().setSplitBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /*mObserver = getWindow().getDecorView().getViewTreeObserver();
        mObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mLines = tv_film_brief.getLineCount();
                //Log.e("TAG", mLines + "");
                //tv_film_brief.setLines(2);
            }
        });*/
        findViewById();
        initView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG", "onTouchEvent");
        return super.onTouchEvent(event);
    }

    public void findViewById(){
        ll_film_detail_actors = (LinearLayout) findViewById(R.id.ll_film_detail_actors);
        tv_film_brief = (TextView) findViewById(R.id.tv_film_brief);
        ib_show_more_brief = (ImageButton) findViewById(R.id.ib_show_more_brief);
        //ib_show_more_brief.setOnClickListener(showMoreFilmBriefListener);

        film_detail_scrollview = (XScrollView) findViewById(R.id.film_detail_scrollview);
        btn_film_detail_header_buy = (Button) findViewById(R.id.btn_film_detail_header_buy);
        btn_film_detail_buy = (Button) findViewById(R.id.btn_film_detail_buy);

        rl_film_detail_header = (RelativeLayout) findViewById(R.id.rl_film_detail_header);

        //mLines = tv_film_brief.getLineCount();
        //tv_film_brief.setLines(3);
        //Log.e("TAG", tv_film_brief.getLineHeight() + "--LineHeight");
    }

    /**
     * 初始化视图组件
     */
    public void initView(){

        for (int i = 0; i < 5; i++){
            View item = View.inflate(this, R.layout.item_film_detail_actors, null);
            if (i == 0){
                TextView tv_film_actor_title = (TextView) item.findViewById(R.id.tv_film_actor_title);
                tv_film_actor_title.setVisibility(View.VISIBLE);
            }

            ll_film_detail_actors.addView(item);
        }

        String text = "Instantly translate your text from one language to another with Bing Translator. " +
                "Powered by Microsoft Translator, the site provides free translation to and.";
        tv_film_brief.setText(text);
        //mLineHeight = tv_film_brief.getLineHeight();
        tv_film_brief.post(new Runnable() {
            @Override
            public void run() {
                mTotalLines = tv_film_brief.getLineCount() + 1;
                //Log.e("TAG", mTotalLines + "--LineCount");
                //ib_show_more_brief.setVisibility(tv_film_brief.getLineCount() > 2 ? View.VISIBLE : View.GONE);
                //tv_film_brief.setLines(3);
                tv_film_brief.setHeight(DEFAULT_LINES * tv_film_brief.getLineHeight() - 2);
            }
        });
        ib_show_more_brief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchTextViewState(tv_film_brief, ib_show_more_brief);
            }
        });
        toolbar.setTitle("最新电影");
        toolbar.getBackground().setAlpha(0);
        /*film_detail_scrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float deltaY = 0;
                switch (motionEvent.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        deltaY = motionEvent.getRawY();
                        preY = motionEvent.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(film_detail_scrollview.getScrollY() < 20){
                            toolbar.getBackground().setAlpha(0);
                            break;
                        }
                        if (film_detail_scrollview.getScrollY() > 255){
                            toolbar.getBackground().setAlpha(255);
                            break;
                        }
                        if (btn_film_detail_buy.getScrollY() > 200){
                            btn_film_detail_header_buy.setVisibility(View.VISIBLE);
                        }else {
                            btn_film_detail_header_buy.setVisibility(View.GONE);
                        }
                        *//*if(film_detail_scrollview.getScrollY() < 5){
                            toolbar.getBackground().setAlpha(0);
                            break;
                        }*//*
                        deltaY = motionEvent.getRawY() - preY;

                        if (deltaY < 0 ){
                            //preAlpha -= deltaY / 255;
                            preAlpha += Math.abs(deltaY);
                            //deltaY = 0;
                        }else{
                            preAlpha -= Math.abs(deltaY);
                        }
                        Log.e("TAG", film_detail_scrollview.getScrollY() + "-----deltaY");
                        Log.e("TAG", deltaY + "-----deltaY");
                        Log.e("TAG", preAlpha + "-----preAlpha");
                        *//*if (deltaY > 255){
                            deltaY = 255;
                        }*//*
                        if (preAlpha < 0){
                            preAlpha = 0;
                        }
                        if (preAlpha > 255){
                            preAlpha = 255;
                        }
                        //preAlpha += deltaY / 255 / 5;
                        toolbar.getBackground().setAlpha((int) preAlpha);
                        preY = motionEvent.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if(film_detail_scrollview.getScrollY() < 20){
                            toolbar.getBackground().setAlpha(0);
                        }
                        //preY = motionEvent.getRawY();
                        break;
                }
                return false;
            }
        });*/
        film_detail_scrollview.setOnScrollStateChangedListener(this);
    }

    @Override
    public void OnScrollStateChanged(int left, int top, int preLeft, int preTop) {
        int deltaY = top - preTop;
        //Log.e("TAG", deltaY + "-----deltaY");
        if (top > 100 && deltaY > 0){
            toolbarAlpha += Math.abs(deltaY);
            changeToolbarBackground();
            //return;
        }
        if (top < 255 && deltaY < 0){
            toolbarAlpha -= Math.abs(deltaY);
            changeToolbarBackground();
        }
        showOrHideView(deltaY);
        //Log.e("TAG", film_detail_scrollview.getScrollY() + "-----deltaY");
        //Log.e("TAG", toolbarAlpha + "-----preAlpha");
    }

    /**滑动渐变*/
    public void changeToolbarBackground(){
        if (toolbarAlpha < 0){
            toolbarAlpha = 0;
        }
        if (toolbarAlpha > 255){
            toolbarAlpha = 255;
        }
        toolbar.getBackground().setAlpha((int) toolbarAlpha);
    }

    /**
     * 视图固定显隐
     */
    public void showOrHideView(int deltaY){
        totalScrollY += deltaY;
        /*RelativeLayout.LayoutParams upParams = (RelativeLayout.LayoutParams) btn_film_detail_header_buy.getLayoutParams();
        RelativeLayout.LayoutParams downParams = (RelativeLayout.LayoutParams) btn_film_detail_buy.getLayoutParams();
        int upTop = upParams.topMargin;
        int downTop = downParams.topMargin;*/
        int upTop = btn_film_detail_header_buy.getTop();
        int downTop = btn_film_detail_buy.getTop() - film_detail_scrollview.getScrollY();
        /*Log.e("TAG", film_detail_scrollview.getScrollY() + "-----------getScrollY");
        Log.e("TAG", deltaY + "-----------deltaY");
        Log.e("TAG", upTop + "-----------upTop");
        Log.e("TAG", downTop + "-----------downTop");*/
        if (downTop <= upTop){
            btn_film_detail_header_buy.setVisibility(View.VISIBLE);
            btn_film_detail_buy.setVisibility(View.INVISIBLE);
            rl_film_detail_header.setBackgroundColor(getResources().getColor(R.color.white));
        }else {
            btn_film_detail_header_buy.setVisibility(View.INVISIBLE);
            btn_film_detail_buy.setVisibility(View.VISIBLE);
            rl_film_detail_header.setBackgroundColor(getResources().getColor(R.color.transparent));
        }
    }

    private int toolbarAlpha = 0, totalScrollY;

    public void switchTextViewState(final TextView textView, ImageButton arrowBtn){
        textView.clearAnimation();//清楚动画效果
        final int deltaY;//要移动的高度
        final int startHeight = textView.getHeight();//起始高度
        int lineHeight = textView.getLineHeight(); //行高
        int durationMillis = 350;//动画持续时间
        if (isExpanded) {
            /**
             * 折叠动画
             * 从实际高度缩回起始高度
             */
            deltaY = lineHeight * DEFAULT_LINES - startHeight - 2;
            RotateAnimation collapseAnimate = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            collapseAnimate.setDuration(durationMillis);
            collapseAnimate.setFillAfter(true);
            arrowBtn.startAnimation(collapseAnimate);
            //tv_film_brief.setLines(3);
            //tv_film_brief.setHeight(mLines * mLineHeight);
            //isExpanded = false;

        } else {
            /**
             * 展开动画
             * 从起始高度增长至实际高度
             */
            deltaY = lineHeight * mTotalLines - startHeight;
            RotateAnimation expandAnimate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            expandAnimate.setDuration(durationMillis);
            expandAnimate.setFillAfter(true);
            arrowBtn.startAnimation(expandAnimate);
            //tv_film_brief.setLines(mLines);
            //tv_film_brief.setHeight(defaultLines * mLineHeight);
            //isExpanded = true;
        }

        Animation animation = new Animation() {
            protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                textView.setHeight((int) (startHeight + deltaY * interpolatedTime));
            }
        };
        animation.setDuration(durationMillis);
        textView.startAnimation(animation);
        isExpanded = !isExpanded;
    }

    /**
     * 点击显示跟多内容
     */
    private View.OnClickListener showMoreFilmBriefListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //tv_film_brief.getLineCount();
            tv_film_brief.setLines(mTotalLines);
        }
    };

    /**
     * 初始化数据
     */
    public void initData(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.film_detail_menu, menu);
        return true;
    }
}
