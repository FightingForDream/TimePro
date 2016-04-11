package com.atguigu.time.api;

/**
 * Created by vence on 16/4/9 10:24
 * 邮箱 ：vence0815@foxmail.com
 */
public class Url {
    //http://api.m.mtime.cn/GetCityByLongitudelatitude.api?longitude=116.386641&latitude=40.105512&cityName=
    //城市请求
    public static final String GET_CITY = "http://api.m.mtime.cn/GetCityByLongitudelatitude.api?";

    //商城页面
    public static final String MALL_URL = "http://api.m.mtime.cn/PageSubArea/MarketFirstPageNew.api?lastTime={0}";

    //商场感兴趣的更多商品
    public static final String MALL_MORE_URL = "http://api.m.mtime.cn/ECommerce/RecommendProducts.api?pageIndex=1&goodsIds=";
}
