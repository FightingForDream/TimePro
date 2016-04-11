package com.atguigu.time.bean;

import java.util.List;

/**
 * Created by SkyWalker on 2016/4/9.
 * 商城的数据类
 */
public class Mall {


    /**
     * iconTitle1 : 家居
     * iconTitle2 :
     * img1 : http://img31.mtime.cn/mg/2016/03/29/181648.75801373.jpg
     * img2 :
     * url : #!/commerce/list/?c1=43
     */

    private NavigatorFirthIconBean navigatorFirthIcon;
    /**
     * url : http://mall.wv.mtime.cn/#!/commerce/item/101565/
     * img : http://img31.mtime.cn/mg/2016/04/09/113937.49062835.jpg
     * goodsId : 101565
     * startTime : 1460167200
     * longTime : 1460260800
     * warmup : 1
     * title :
     * subTitle :
     * titleColor :
     */

    private CellABean cellA;
    /**
     * url : http://feature.mtime.cn/mobile/item/2015/preironman/
     * img : http://img31.mtime.cn/mg/2016/03/18/110520.87708263.jpg
     * goodsId : 0
     * startTime : 0
     * longTime : 0
     * warmup : 0
     * title :
     * subTitle :
     * titleColor :
     */

    private CellBBean cellB;
    private CellCBean cellC;
    private AdvHeadImgBean advHeadImg;
    /**
     * isNewAdd : false
     * msg :
     */

    private GoodsCouponBean goodsCoupon;
    /**
     * url : http://feature.mtime.com/mobile/item/2016/0408_mtime/
     * image : http://img31.mtime.cn/mg/2016/04/08/200604.75326312.jpg
     */

    private List<ScrollImgBean> scrollImg;
    /**
     * iconTitle : 玩具
     * url : #!/commerce/list/?c1=25
     * image : http://img31.mtime.cn/mg/2016/03/29/180821.36534746.jpg
     */

    private List<NavigatorIconBean> navigatorIcon;
    /**
     * titleCn : 功夫熊猫
     * titleEn : Kung Fu Panda
     * url : http://mall.wv.mtime.cn/#!/commerce/list/?q=功夫熊猫
     * goodsId : 0
     * checkedImage : http://img31.mtime.cn/mg/2016/01/29/163425.87972160.jpg
     * uncheckImage : http://img31.mtime.cn/mg/2016/01/29/163427.96666490.jpg
     * backgroupImage : http://img31.mtime.cn/mg/2016/01/29/163552.48354218.jpg
     * subList : [{"title":"功夫熊猫Q萌充电宝","goodsId":101778,"image":"http://img31.mtime.cn/goods/2015/12/09/153047.41310181_600X600X1.jpg","url":""},{"title":"功夫熊猫双宝环保袋","goodsId":101779,"image":"http://img31.mtime.cn/goods/2015/12/09/162449.41318990_600X600X1.jpg","url":""},{"title":"双宝练功i6手机壳","goodsId":101848,"image":"http://img31.mtime.cn/goods/2016/01/29/145133.60874505_600X600X1.jpg","url":""},{"title":"翻滚阿宝陶瓷杯","goodsId":101835,"image":"http://img31.mtime.cn/goods/2015/12/24/105839.33267889_600X600X1.jpg","url":""},{"title":"功夫熊猫POP公仔","goodsId":102378,"image":"http://img31.mtime.cn/goods/2016/01/29/140959.35743350_600X600X1.jpg","url":""},{"title":"阿宝练功多功能雕像","goodsId":101838,"image":"http://img31.mtime.cn/goods/2016/01/07/181644.21735698_600X600X1.jpg","url":""}]
     */

    private List<TopicBean> topic;
    /**
     * goodsId : 0
     * moreUrl : http://mall.wv.mtime.cn/#!/commerce/list/?c1=25
     * imageUrl : http://mall.wv.mtime.cn/#!/commerce/list/?q=FUNKO
     * image : http://img31.mtime.cn/mg/2016/04/08/202915.67616666.jpg
     * name : 玩具模型
     * colorValue : #FFB90F
     * subList : [{"title":"蝙超3件装","goodsId":102486,"image":"http://img31.mtime.cn/mg/2016/04/08/202936.71962757.jpg","url":""},{"title":"复联2摇头公仔","goodsId":100939,"image":"http://img31.mtime.cn/mg/2016/04/08/203007.52716356.jpg","url":""},{"title":"COSBABY死侍","goodsId":102395,"image":"http://img31.mtime.cn/mg/2016/04/08/203106.46000558.jpg","url":""}]
     */

    private List<CategoryBean> category;

    public NavigatorFirthIconBean getNavigatorFirthIcon() {
        return navigatorFirthIcon;
    }

    public void setNavigatorFirthIcon(NavigatorFirthIconBean navigatorFirthIcon) {
        this.navigatorFirthIcon = navigatorFirthIcon;
    }

    public CellABean getCellA() {
        return cellA;
    }

    public void setCellA(CellABean cellA) {
        this.cellA = cellA;
    }

    public CellBBean getCellB() {
        return cellB;
    }

    public void setCellB(CellBBean cellB) {
        this.cellB = cellB;
    }

    public CellCBean getCellC() {
        return cellC;
    }

    public void setCellC(CellCBean cellC) {
        this.cellC = cellC;
    }

    public AdvHeadImgBean getAdvHeadImg() {
        return advHeadImg;
    }

    public void setAdvHeadImg(AdvHeadImgBean advHeadImg) {
        this.advHeadImg = advHeadImg;
    }

    public GoodsCouponBean getGoodsCoupon() {
        return goodsCoupon;
    }

    public void setGoodsCoupon(GoodsCouponBean goodsCoupon) {
        this.goodsCoupon = goodsCoupon;
    }

    public List<ScrollImgBean> getScrollImg() {
        return scrollImg;
    }

    public void setScrollImg(List<ScrollImgBean> scrollImg) {
        this.scrollImg = scrollImg;
    }

    public List<NavigatorIconBean> getNavigatorIcon() {
        return navigatorIcon;
    }

    public void setNavigatorIcon(List<NavigatorIconBean> navigatorIcon) {
        this.navigatorIcon = navigatorIcon;
    }

    public List<TopicBean> getTopic() {
        return topic;
    }

    public void setTopic(List<TopicBean> topic) {
        this.topic = topic;
    }

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public static class NavigatorFirthIconBean {
        private String iconTitle1;
        private String iconTitle2;
        private String img1;
        private String img2;
        private String url;

        public String getIconTitle1() {
            return iconTitle1;
        }

        public void setIconTitle1(String iconTitle1) {
            this.iconTitle1 = iconTitle1;
        }

        public String getIconTitle2() {
            return iconTitle2;
        }

        public void setIconTitle2(String iconTitle2) {
            this.iconTitle2 = iconTitle2;
        }

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public String getImg2() {
            return img2;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class CellABean {
        private String url;
        private String img;
        private int goodsId;
        private int startTime;
        private int longTime;
        private int warmup;
        private String title;
        private String subTitle;
        private String titleColor;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getLongTime() {
            return longTime;
        }

        public void setLongTime(int longTime) {
            this.longTime = longTime;
        }

        public int getWarmup() {
            return warmup;
        }

        public void setWarmup(int warmup) {
            this.warmup = warmup;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getTitleColor() {
            return titleColor;
        }

        public void setTitleColor(String titleColor) {
            this.titleColor = titleColor;
        }
    }

    public static class CellBBean {
        private String url;
        private String img;
        private int goodsId;
        private int startTime;
        private int longTime;
        private int warmup;
        private String title;
        private String subTitle;
        private String titleColor;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getLongTime() {
            return longTime;
        }

        public void setLongTime(int longTime) {
            this.longTime = longTime;
        }

        public int getWarmup() {
            return warmup;
        }

        public void setWarmup(int warmup) {
            this.warmup = warmup;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getTitleColor() {
            return titleColor;
        }

        public void setTitleColor(String titleColor) {
            this.titleColor = titleColor;
        }
    }

    public static class CellCBean {
        /**
         * url : http://mall.wv.mtime.cn/#!/commerce/item/102314/
         * image : http://img31.mtime.cn/mg/2016/04/08/104937.27610780.jpg
         * title :
         * subTitle :
         * titleColor :
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {

            /**
             * url : http://mall.wv.mtime.cn/#!/commerce/item/102314/
             * image : http://img31.mtime.cn/mg/2016/04/08/104937.27610780.jpg
             * title :
             * subTitle :
             * titleColor :
             */

            private String url;
            private String image;
            private String title;
            private String subTitle;
            private String titleColor;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
            }

            public String getTitleColor() {
                return titleColor;
            }

            public void setTitleColor(String titleColor) {
                this.titleColor = titleColor;
            }
        }
    }

    public static class AdvHeadImgBean {
    }

    public static class GoodsCouponBean {
        private boolean isNewAdd;
        private String msg;

        public boolean isIsNewAdd() {
            return isNewAdd;
        }

        public void setIsNewAdd(boolean isNewAdd) {
            this.isNewAdd = isNewAdd;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public static class ScrollImgBean {
        private String url;
        private String image;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public static class NavigatorIconBean {
        private String iconTitle;
        private String url;
        private String image;

        public String getIconTitle() {
            return iconTitle;
        }

        public void setIconTitle(String iconTitle) {
            this.iconTitle = iconTitle;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public static class TopicBean {
        private String titleCn;
        private String titleEn;
        private String url;
        private int goodsId;
        private String checkedImage;
        private String uncheckImage;
        private String backgroupImage;
        /**
         * title : 功夫熊猫Q萌充电宝
         * goodsId : 101778
         * image : http://img31.mtime.cn/goods/2015/12/09/153047.41310181_600X600X1.jpg
         * url :
         */

        private List<SubListBean> subList;

        public String getTitleCn() {
            return titleCn;
        }

        public void setTitleCn(String titleCn) {
            this.titleCn = titleCn;
        }

        public String getTitleEn() {
            return titleEn;
        }

        public void setTitleEn(String titleEn) {
            this.titleEn = titleEn;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getCheckedImage() {
            return checkedImage;
        }

        public void setCheckedImage(String checkedImage) {
            this.checkedImage = checkedImage;
        }

        public String getUncheckImage() {
            return uncheckImage;
        }

        public void setUncheckImage(String uncheckImage) {
            this.uncheckImage = uncheckImage;
        }

        public String getBackgroupImage() {
            return backgroupImage;
        }

        public void setBackgroupImage(String backgroupImage) {
            this.backgroupImage = backgroupImage;
        }

        public List<SubListBean> getSubList() {
            return subList;
        }

        public void setSubList(List<SubListBean> subList) {
            this.subList = subList;
        }

        public static class SubListBean {
            private String title;
            private int goodsId;
            private String image;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public static class CategoryBean {
        private int goodsId;
        private String moreUrl;
        private String imageUrl;
        private String image;
        private String name;
        private String colorValue;
        /**
         * title : 蝙超3件装
         * goodsId : 102486
         * image : http://img31.mtime.cn/mg/2016/04/08/202936.71962757.jpg
         * url :
         */

        private List<SubListBean> subList;

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getMoreUrl() {
            return moreUrl;
        }

        public void setMoreUrl(String moreUrl) {
            this.moreUrl = moreUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColorValue() {
            return colorValue;
        }

        public void setColorValue(String colorValue) {
            this.colorValue = colorValue;
        }

        public List<SubListBean> getSubList() {
            return subList;
        }

        public void setSubList(List<SubListBean> subList) {
            this.subList = subList;
        }

        public static class SubListBean {
            private String title;
            private int goodsId;
            private String image;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
