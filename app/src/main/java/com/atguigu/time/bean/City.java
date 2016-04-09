package com.atguigu.time.bean;

import java.io.Serializable;

/**
 * Created by vence on 16/4/9 10:31
 * 邮箱 ：vence0815@foxmail.com
 */
public class City implements Serializable{

    /**
     * cityId : 290
     * name : 北京
     * pinyinShort : bj
     */

    private int cityId;
    private String name;
    private String pinyinShort;

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", name='" + name + '\'' +
                ", pinyinShort='" + pinyinShort + '\'' +
                '}';
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPinyinShort(String pinyinShort) {
        this.pinyinShort = pinyinShort;
    }

    public int getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }

    public String getPinyinShort() {
        return pinyinShort;
    }
}
