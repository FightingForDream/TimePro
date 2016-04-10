package com.atguigu.time.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.time.R;
import com.atguigu.time.adapter.CityListAdapter;
import com.atguigu.time.bean.CityList;
import com.atguigu.time.view.NoScrollGridView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CityListActivity extends Activity {

//    @ViewInject(R.id.lv_city)
//    private ListView lv_city;
//
//    @ViewInject(R.id.gv_citylist)
//    private GridView gv_citylist;
//
//    @ViewInject(R.id.tv_head_location)
//    private TextView tv_head_location;

    private View headView;
    private List<String> litters;
    private Map<String, List<CityList.PEntity>> maps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
//        x.view().inject(this);
        headView = View.inflate(this, R.layout.city_list_head, null);
//        x.view().inject(this,headView);
        getDataFromServer();
        Log.e("TAG", "onCreate");
    }

    /**
     * 联网获取数据
     */
    private void getDataFromServer() {
        Log.e("TAG", "getDataFromServer");

//        RequestParams params = new RequestParams(Url.GET_CITY_LIST);
        RequestParams params = new RequestParams("http://api.m.mtime.cn/Showtime/HotCitiesByCinema.api");
        ;
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

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
        RequestParams params1 = new RequestParams("http://api.m.mtime.cn/GetCityByLongitudelatitude.api?");
        x.http().get(params1
                , new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "success");
                CityList cityList = new Gson().fromJson(result, CityList.class);
//                handlerData(cityList);
                Log.e("TAG", cityList.getP().size() + " cityList.getP().size()");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", ex.toString());
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
     * 处理得到的list数据
     *
     * @param cityList
     */
    private void handlerData(CityList cityList) {
        maps = new TreeMap<>();
        litters = new ArrayList<>();

        for (int i = 0; i < cityList.getP().size(); i++) {
            CityList.PEntity pEntity = cityList.getP().get(i);
            String startLitter = pEntity.getPinyinFull().substring(0, 1).toUpperCase();
            if (maps.containsKey(startLitter)) {
                maps.get(startLitter).add(pEntity);
            } else {
                maps.put(startLitter, new ArrayList<CityList.PEntity>());
                litters.add(startLitter);
                Log.e("TAG", litters.size() + "");
                maps.get(startLitter).add(pEntity);
            }
        }

        Collections.sort(litters);
//        Log.e("TAG", litters.size() + "");
//        gv_citylist.setAdapter(new CityListAdapter(this, cityList.getP().subList(0, 12)));
//        lv_city.removeHeaderView(headView);
//        lv_city.addHeaderView(headView);
//        lv_city.setAdapter(new MyCityListAdatapter());
    }

    class MyCityListAdatapter extends BaseAdapter {

        @Override
        public int getCount() {

            return litters.size();
        }

        @Override
        public Object getItem(int position) {
            return litters.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHoder hoder = null;
            if (convertView == null) {
                hoder = new ViewHoder();
                convertView = View.inflate(CityListActivity.this, R.layout.city_list_inner_item, null);
                hoder.gv_inner = (NoScrollGridView) convertView.findViewById(R.id.gv_inner);
                hoder.tv_title = (TextView) convertView.findViewById(R.id.tv_titile);
                convertView.setTag(hoder);
            } else {
                hoder = (ViewHoder) convertView.getTag();
            }

            hoder.tv_title.setText(litters.get(position));
            hoder.gv_inner.setAdapter(new CityListAdapter(CityListActivity.this, maps.get(litters.get(position))));

            return convertView;
        }
    }

    class ViewHoder {
        TextView tv_title;
        NoScrollGridView gv_inner;
    }
}
