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

    public static final String MALL_MORE_URL = "http://api.m.mtime.cn/ECommerce/RecommendProducts.api?pageIndex=1&goodsIds=102314";
    public static final String MALL_BASE_MORE_URL = "http://api.m.mtime.cn/ECommerce/RecommendProducts.api?pageIndex=";

    /*---------------------------------------------首页模块链接地址------------------------------------------*/
    /**首页ListView内容链接*/
    public static final String HOME_CONTENT = "http://api.m.mtime.cn/PageSubArea/GetHomeFeed.api";
    /**首页正在售票链接*/
    public static final String HOME_GALLERY = "http://api.m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=290";
    /**首页广告链接*/
    public static final String HOME_HEADER = "http://api.m.mtime.cn/PageSubArea/GetFirstPageAdvAndNews.api";
    /*----------------------------------------------------------------------------------------------*/

    //发现页面四个页面顶部数据
    public static final String TOP_Find = "http://api.m.mtime.cn/PageSubArea/GetRecommendationIndexInfo.api";
    //发现页面新闻页面listView数据
    public static final java.lang.String FIND_NEWSLIST = "http://api.m.mtime.cn/News/NewsList.api?pageIndex=1";

    //得到城市列表的数据
    public static final String CITY_LIST = "http://api.m.mtime.cn/Showtime/HotCitiesByCinema.api";

    /**-----------------------------------------搜索模块链接地址------------------------------------------*/
    public static final String SEARCH_URL = "http://api.m.mtime.cn/Search/SearchSuggestionNew.api?keyword=";
}
