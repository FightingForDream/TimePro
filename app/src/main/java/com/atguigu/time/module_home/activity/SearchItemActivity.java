package com.atguigu.time.module_home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.atguigu.time.R;
import com.atguigu.time.api.Url;
import com.atguigu.time.api.VolleyManager;
import com.atguigu.time.module_home.adapter.HotSearchAdapter;
import com.atguigu.time.module_home.adapter.RecentSearchAdapter;
import com.atguigu.time.module_home.adapter.ShowSearchDataAdapter;
import com.atguigu.time.module_home.bean.SearchItemBean;
import com.atguigu.time.utils.SpUtils;
import com.google.gson.Gson;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnTextChanged;

public class SearchItemActivity extends AppCompatActivity {
    public static final String KEY_RECENT_SEARCH = "recent_search";
    /**显示最近搜索信息*/
    private ListView lv_search_recent;

    private View mHeaderView;
    private GridView gv_search_hot;
    private List<String> hotSearchList;
    private HotSearchAdapter hotSearchAdapter;
    private RecentSearchAdapter recentSearchAdapter;
    private List<String> recentSearchList;

    private ImageButton ib_search_barcode_scan;
    private Button btn_search;
    private AutoCompleteTextView actv_search_text;

    /**搜索返回显示ListView*/
    private ListView lv_search_data;
    private ShowSearchDataAdapter searchDataAdapter;
    private SearchItemBean searchItemBean;
    private LinearLayout ll_search_data_container;

    private String recentSearchStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);

        intiView();
        findViewById();
        setListener();
        initData();
    }

    public void intiView(){
        lv_search_recent = (ListView) findViewById(R.id.lv_search_recent);
        mHeaderView = View.inflate(this, R.layout.item_search_header, null);
        lv_search_recent.addHeaderView(mHeaderView);
    }

    public void findViewById(){
        gv_search_hot = (GridView) mHeaderView.findViewById(R.id.gv_search_hot);
        ib_search_barcode_scan = (ImageButton) findViewById(R.id.ib_search_barcode_scan);
        btn_search = (Button) findViewById(R.id.btn_search);
        actv_search_text = (AutoCompleteTextView) findViewById(R.id.actv_search_text);

        lv_search_data = (ListView) findViewById(R.id.lv_search_data);

        ll_search_data_container = (LinearLayout) findViewById(R.id.ll_search_data_container);
    }

    public void setListener(){
        btn_search.setOnClickListener(submitSearchListener);
        ib_search_barcode_scan.setOnClickListener(scanBarcodeListener);
        actv_search_text.addTextChangedListener(ajaxSearchListener);
    }

    /**点击搜索监听器*/
    private View.OnClickListener submitSearchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String str = actv_search_text.getText().toString();
            if(TextUtils.isEmpty(str)){
                return;
            }
            //执行搜索业务

            //保存搜索记录
            if (recentSearchStr.contains(str)){
                return;
            }
            int maxSaveCount = 10;
            if (recentSearchStr.split("##").length > maxSaveCount){
                return;
            }
            recentSearchStr += "##" + str;
            recentSearchList.add(str);
            SpUtils.getInstance(SearchItemActivity.this).save(KEY_RECENT_SEARCH, str);
            recentSearchAdapter.notifyDataSetChanged();
        }
    };

    /**点击扫描二维码监听器*/
    private View.OnClickListener scanBarcodeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent it = new Intent(SearchItemActivity.this, CaptureActivity.class);
            startActivity(it);
        }
    };

    /**安卓异步搜索监听器*/
    private TextWatcher ajaxSearchListener = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //VolleyManager.getInstance(SearchItemActivity.this).getQueue().
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            loadSearchData();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void initData(){
        hotSearchList = new ArrayList<String>();
        recentSearchList = new ArrayList<String>();
        //设置热门搜索Adapter
        for (int i = 0; i < 4; i++){
            //TextView tv = (TextView) View.inflate(this, R.layout.item_search_hot, null);
            hotSearchList.add("澳门风云");
        }
        hotSearchAdapter = new HotSearchAdapter(this, hotSearchList);
        gv_search_hot.setAdapter(hotSearchAdapter);

        recentSearchStr = SpUtils.getInstance(this).getString(KEY_RECENT_SEARCH, "");
        String items[] = recentSearchStr.split("##");
        //设置最近搜索Adapter
        for (int i = 0; i < items.length; i++){
            recentSearchList.add(items[i]);
        }
        recentSearchAdapter = new RecentSearchAdapter(this, recentSearchList);
        lv_search_recent.setAdapter(recentSearchAdapter);

        //设置自动完成Adapter
        ArrayAdapter autoCompleteAdapter = new ArrayAdapter(this, R.layout.item_search_recent, items);
        actv_search_text.setAdapter(autoCompleteAdapter);

        //初始化searchDataAdapter
        //searchDataAdapter = new ShowSearchDataAdapter();
    }

    /**
     * 加载搜索数据
     */
    public void loadSearchData(){
        String url = Url.SEARCH_URL + actv_search_text.getText().toString();
        StringRequest request = new StringRequest(Request.Method.GET, Url.SEARCH_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ll_search_data_container.setVisibility(View.VISIBLE);
                searchItemBean = new Gson().fromJson(response, SearchItemBean.class);
                searchDataAdapter = new ShowSearchDataAdapter(SearchItemActivity.this, searchItemBean);
                lv_search_data.setAdapter(searchDataAdapter);

                lv_search_recent.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleyManager.getInstance(this).addRequest(request);
    }
}
