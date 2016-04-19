package com.atguigu.time.bean;

import java.util.List;

/**
 * Created by SkyWalker on 2016/4/11.
 * 商城主页面感兴趣商品实体类
 */
public class InterestedShopBean {

    /**
     * count : 60
     * goodsIds : 102149,102148,101901,101900,101902,102425,102171,102426,102173,102428,
     * goodsList : [{"background":"#28C8DC","goodsId":102149,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/03/03/154128.29013294.jpg","longName":"细致工艺 柔软亲肤 透气抗压 缓解疲劳","marketPrice":12900,"minSalePrice":8800,"name":"蝙蝠侠黑暗骑士抱枕","url":"http://mall.wv.mtime.cn/#!/commerce/item/102149/"},{"background":"#4EC178","goodsId":102148,"iconText":"预售","image":"http://img31.mtime.cn/mg/2016/03/03/152738.61144444.jpg","longName":"细致工艺 柔软亲肤 透气抗压 缓解疲劳","marketPrice":12900,"minSalePrice":8800,"name":"超人钢铁之躯抱枕","url":"http://mall.wv.mtime.cn/#!/commerce/item/102148/"},{"background":"#28C8DC","goodsId":101901,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/03/02/133207.64960580.jpg","longName":"细腻材质 质感表层 贴合掌握 经久耐用","marketPrice":9900,"minSalePrice":4500,"name":"联盟之剑i6手机壳","url":"http://mall.wv.mtime.cn/#!/commerce/item/101901/"},{"background":"#28C8DC","goodsId":101900,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/03/02/132017.25086601.jpg","longName":"细腻材质 质感表层 贴合掌握 经久耐用","marketPrice":9900,"minSalePrice":4500,"name":"魔兽海报i6手机壳","url":"http://mall.wv.mtime.cn/#!/commerce/item/101900/"},{"background":"#28C8DC","goodsId":101902,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/03/02/133643.69435093.jpg","longName":"细腻材质 质感表层 贴合掌握 经久耐用","marketPrice":9900,"minSalePrice":4500,"name":"毁灭之锤i6手机壳","url":"http://mall.wv.mtime.cn/#!/commerce/item/101902/"},{"background":"#4EC178","goodsId":102425,"iconText":"预售","image":"http://img31.mtime.cn/mg/2016/02/26/133857.69905589.jpg","longName":"金属质感电镀涂装 头部内置发光装置","marketPrice":145000,"minSalePrice":145000,"name":"Hot Toys终结者5 T800骨骼","url":"http://mall.wv.mtime.cn/#!/commerce/item/102425/"},{"background":"#28C8DC","goodsId":102171,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/02/06/134016.50806555.jpg","longName":"双层杯身 环保材质 个性图案 彰显品质","marketPrice":4900,"minSalePrice":3500,"name":"疯狂动物城吸管杯","url":"http://mall.wv.mtime.cn/#!/commerce/item/102171/"},{"background":"#28C8DC","goodsId":102426,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/02/25/161724.47493377.jpg","longName":"舒适面料 精细工艺 修身透气 潮流时尚","marketPrice":23900,"minSalePrice":23900,"name":"功夫熊猫迷彩圆领套头卫衣","url":"http://mall.wv.mtime.cn/#!/commerce/item/102426/"},{"background":"#28C8DC","goodsId":102173,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/02/06/113059.42741144.jpg","longName":"做工细致 经久耐用 质感表层 手感细腻","marketPrice":12900,"minSalePrice":7800,"name":"兔子警官i6/i6s/i6P手机壳","url":"http://mall.wv.mtime.cn/#!/commerce/item/102173/"},{"background":"#28C8DC","goodsId":102428,"iconText":"新品","image":"http://img31.mtime.cn/mg/2016/02/25/194515.67710722.jpg","longName":"舒适面料 精细工艺 帅气迷彩设计 休闲时尚","marketPrice":19900,"minSalePrice":19900,"name":"功夫熊猫迷彩大印花长袖恤","url":"http://mall.wv.mtime.cn/#!/commerce/item/102428/"}]
     * pageCount : 6
     */

    private int count;
    private String goodsIds;
    private int pageCount;
    /**
     * background : #28C8DC
     * goodsId : 102149
     * iconText : 新品
     * image : http://img31.mtime.cn/mg/2016/03/03/154128.29013294.jpg
     * longName : 细致工艺 柔软亲肤 透气抗压 缓解疲劳
     * marketPrice : 12900
     * minSalePrice : 8800
     * name : 蝙蝠侠黑暗骑士抱枕
     * url : http://mall.wv.mtime.cn/#!/commerce/item/102149/
     */

    private List<GoodsListBean> goodsList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<GoodsListBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsListBean> goodsList) {
        this.goodsList = goodsList;
    }

    public static class GoodsListBean {
        private String background;
        private int goodsId;
        private String iconText;
        private String image;
        private String longName;
        private int marketPrice;
        private int minSalePrice;
        private String name;
        private String url;

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getIconText() {
            return iconText;
        }

        public void setIconText(String iconText) {
            this.iconText = iconText;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLongName() {
            return longName;
        }

        public void setLongName(String longName) {
            this.longName = longName;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public int getMinSalePrice() {
            return minSalePrice;
        }

        public void setMinSalePrice(int minSalePrice) {
            this.minSalePrice = minSalePrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
