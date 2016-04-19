package com.atguigu.time.utils;

import com.atguigu.time.bean.CityList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 笨货 on 2016/4/10.
 */
public class CityListSort {

    public static Map<String, List<CityList.PEntity>> putListData(List<CityList.PEntity> datas) {

        //排序
        List<CityList.PEntity> p=sortDatas(datas);
//        for (int i = 0; i < p.size(); i++) {
//            Log.e("TAG", "p.name="+p.get(i).getN());
//        }
        Map<String, List<CityList.PEntity>> cityListMap = new HashMap<>();
        List<CityList.PEntity> cityItemList = new ArrayList<>();

        for (int i = 0; i < p.size(); i++) {
            //当前字母
            String currentCharactor = p.get(i).getPinyinFull().substring(0, 1).toUpperCase();

            if (i == 0) {
                cityItemList.add(p.get(0));//添加第一个
                cityListMap.put(currentCharactor, cityItemList);
            } else {
                String preCharactor = p.get(i - 1).getPinyinFull().substring(0, 1).toUpperCase();
                if (currentCharactor.equals(preCharactor)) {//表示同一个字母
                    cityItemList.add(p.get(i));
                } else {//此时到下一个字母
                    //将所有的同一字母的集合添加到map中，以首字母为key
                    cityListMap.put(preCharactor, cityItemList);
//                    cityItemList.clear();//清空集合
                    cityItemList=new ArrayList<>();
                    cityItemList.add(p.get(i));
                }
            }

        }
        return cityListMap;
    }

    public static List<CityList.PEntity> sortDatas(List<CityList.PEntity> datas) {

        //排序
        Collections.sort(datas, new Comparator<CityList.PEntity>() {
            @Override
            public int compare(CityList.PEntity lhs, CityList.PEntity rhs) {
                return lhs.getPinyinFull().substring(0,1).toUpperCase().compareTo(rhs.getPinyinFull().substring(0, 1).toUpperCase());
            }

        });
        return datas;
    }
}
