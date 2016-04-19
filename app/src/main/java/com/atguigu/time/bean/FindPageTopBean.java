package com.atguigu.time.bean;
/**
 * 发现页面顶部数据
 * @author xpl
 * created at 2016/4/10 15:20
 */
public class FindPageTopBean {


    /**
     * imageUrl : http://img31.mtime.cn/mg/2016/04/10/085944.96898790.jpg
     * newsID : 1554256
     * title : 影市玩家扎堆发片单放大招
     * type : 0
     */

    private NewsEntity news;
    /**
     * imageUrl : http://img31.mtime.cn/mg/2016/03/15/095415.85345975.jpg
     * movieName : 神战：权力之眼
     * posterUrl : http://img31.mtime.cn/mt/2016/03/04/095907.69694487_1280X720X2.jpg
     * reviewID : 7950788
     * title : 上古神话的好莱坞式解读
     */

    private ReviewEntity review;
    /**
     * id : 1348
     * imageUrl : http://img31.mtime.cn/mg/2016/03/05/111735.12026988.jpg
     * title : 亚视经典剧集回顾
     * type : 0
     */

    private TopListEntity topList;
    /**
     * hightUrl : http://vfx.mtime.cn/Video/2016/04/07/mp4/160407215402781505.mp4
     * imageUrl : http://img31.mtime.cn/mg/2016/04/08/101426.95062026.jpg
     * movieId : 214815
     * mp4Url : http://vfx.mtime.cn/Video/2016/04/07/mp4/160407215402781505_480.mp4
     * title : 《侠盗一号》中文版预告片
     * trailerID : 59778
     */

    private TrailerEntity trailer;
    /**
     * id : 10792
     * imageUrl : http://img31.mtime.cn/mg/2015/03/31/100230.43767720.jpg
     */

    private ViewingGuideEntity viewingGuide;

    public void setNews(NewsEntity news) {
        this.news = news;
    }

    public void setReview(ReviewEntity review) {
        this.review = review;
    }

    public void setTopList(TopListEntity topList) {
        this.topList = topList;
    }

    public void setTrailer(TrailerEntity trailer) {
        this.trailer = trailer;
    }

    public void setViewingGuide(ViewingGuideEntity viewingGuide) {
        this.viewingGuide = viewingGuide;
    }

    public NewsEntity getNews() {
        return news;
    }

    public ReviewEntity getReview() {
        return review;
    }

    public TopListEntity getTopList() {
        return topList;
    }

    public TrailerEntity getTrailer() {
        return trailer;
    }

    public ViewingGuideEntity getViewingGuide() {
        return viewingGuide;
    }

    public static class NewsEntity {
        private String imageUrl;
        private int newsID;
        private String title;
        private int type;

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setNewsID(int newsID) {
            this.newsID = newsID;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getNewsID() {
            return newsID;
        }

        public String getTitle() {
            return title;
        }

        public int getType() {
            return type;
        }
    }

    public static class ReviewEntity {
        private String imageUrl;
        private String movieName;
        private String posterUrl;
        private int reviewID;
        private String title;

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public void setPosterUrl(String posterUrl) {
            this.posterUrl = posterUrl;
        }

        public void setReviewID(int reviewID) {
            this.reviewID = reviewID;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getMovieName() {
            return movieName;
        }

        public String getPosterUrl() {
            return posterUrl;
        }

        public int getReviewID() {
            return reviewID;
        }

        public String getTitle() {
            return title;
        }
    }

    public static class TopListEntity {
        private int id;
        private String imageUrl;
        private String title;
        private int type;

        public void setId(int id) {
            this.id = id;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getTitle() {
            return title;
        }

        public int getType() {
            return type;
        }
    }

    public static class TrailerEntity {
        private String hightUrl;
        private String imageUrl;
        private int movieId;
        private String mp4Url;
        private String title;
        private int trailerID;

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public void setMp4Url(String mp4Url) {
            this.mp4Url = mp4Url;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTrailerID(int trailerID) {
            this.trailerID = trailerID;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getMovieId() {
            return movieId;
        }

        public String getMp4Url() {
            return mp4Url;
        }

        public String getTitle() {
            return title;
        }

        public int getTrailerID() {
            return trailerID;
        }
    }

    public static class ViewingGuideEntity {
        private String id;
        private String imageUrl;

        public void setId(String id) {
            this.id = id;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getId() {
            return id;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }
}
