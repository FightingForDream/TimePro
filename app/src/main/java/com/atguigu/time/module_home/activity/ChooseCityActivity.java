package com.atguigu.time.module_home.activity;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.atguigu.time.R;
import com.atguigu.time.bean.City;
import com.atguigu.time.bean.CityList;
import com.atguigu.time.module_home.adapter.CityListAdapter;
import com.atguigu.time.module_home.utils.Utils;
import com.atguigu.time.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseCityActivity extends AppCompatActivity implements Constants{
    /**城市列表ListView*/
    private ListView lv_city_list;
    /**成分列表装配器*/
    private CityListAdapter cityListAdapter;
    /**城市列表数据*/
    private List<CityList.PEntity> pEntities;
    /**整理好的数据*/
    private Map<Integer, List<CityList.PEntity>> cityMap;
    private Integer index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);

        findViewById();
        initData();
    }

    /**
     * 初始化视图组件
     */
    public void findViewById() {
        lv_city_list = (ListView) findViewById(R.id.lv_city_list);
        //lv_city_list.addHeaderView();
    }

    /**
     * 加载数据
     */
    public void initData() {
        //if(Utils.getNetworkState(this)) {
            //从网络加载位置信息列表
            //loadServerCityData();
        //}else{
            //从本地加载位置信息列表
            loadLocalCityData();
        //}
        //对城市数据升序排序
        sortCityList();
        arrangeCityData();
        //设置Adapter
        cityListAdapter = new CityListAdapter(this, pEntities, cityMap);
        lv_city_list.setAdapter(cityListAdapter);
    }

    /**
     * 从本地加载城市数据
     */
    public void loadLocalCityData(){
        AssetManager assetManager = getAssets();
        try {
            InputStream is = assetManager.open(CITY_LIST_FILENAME);
            CityList cityList = new Gson().fromJson(new InputStreamReader(is), CityList.class);
            pEntities = cityList.getP();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从网路加载城市数据
     */
    public void loadServerCityData(){

    }

    /**
     * 对城市列表数据进行一次A-Z排序
     */
    public void sortCityList(){
        Collections.sort(pEntities, new Comparator<CityList.PEntity>() {
            @Override
            public int compare(CityList.PEntity pEntity, CityList.PEntity t1) {
                if (pEntity.getPinyinShort().toCharArray()[0] > t1.getPinyinShort().toCharArray()[0]){
                    return 1;
                }else if(pEntity.getPinyinShort().toCharArray()[0] == t1.getPinyinShort().toCharArray()[0]){
                    return 0;
                }else {
                    return -1;
                }
            }
        });
    }
    /**
     * 对城市数据进行一次整理
     */
    public void arrangeCityData(){
        index = 0;
        cityMap = new HashMap<Integer, List<CityList.PEntity>>();
        String keyword = pEntities.get(0).getPinyinShort().substring(0, 1);
        List<CityList.PEntity> subList01 = new ArrayList<CityList.PEntity>();
        cityMap.put(0, subList01);
        for (int i = 0; i < pEntities.size(); i++){
            String letter = pEntities.get(i).getPinyinShort().substring(0, 1);
            if(!letter.equals(keyword)){
                index++;
                List<CityList.PEntity> subList = new ArrayList<CityList.PEntity>();
                subList.add(pEntities.get(i));
                cityMap.put(index, subList);
            }else {
                cityMap.get(index).add(pEntities.get(i));
            }
            keyword = letter;
        }
    }
}
