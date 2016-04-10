package com.atguigu.time.bean;

import java.util.List;

/**
 * 作者：徐阿海 on 2016/4/9:18:56
 * QQ:845514573
 * 功能：影院数据
 */
public class CinemaBean {

    /**
     * id : 1811
     * name : 北京万达国际影城天通苑店
     * address : 北京昌平区 立汤路186号龙德广场五层
     * tele : 010-84844742
     * latitude : 40.06145
     * longitude : 116.4176
     * baiduLatitude : 40.06669
     * baiduLongitude : 116.4223
     * districtId : 1361
     * cityId : 290
     * provinceId : 290
     * postCode : 102218
     * route : 地铁5号线天通苑南站，地铁13号线立水桥站；公交426,464,985,417,984,751,966,358,643,465,758
     */

    private List<CinemasEntity> cinemas;
    /**
     * busId : 40
     * cId : 1000
     */

    private List<BusinessCinemasEntity> businessCinemas;
    /**
     * id : 2
     * name : 管庄
     * provinceID : 290
     * cityId : 290
     * districtId : 1362
     */

    private List<BusinessAreasEntity> businessAreas;
    /**
     * id : 1362
     * name : 朝阳区
     */

    private List<DistrictsEntity> districts;
    /**
     * id : 56
     * name : 一号线
     * img : [i31]2013/02/23/141707.28730853
     * cinemaCount : 20
     * stationCount : 23
     * lineMap : http://img31.mtime.cn/mg/2013/02/23/141707.28730853.jpg
     * stations : [{"stId":1081,"stName":"古城路","cinemaCount":1,"latitude":39.90727,"longitude":116.1907,"orderNum":2},{"stId":1083,"stName":"八宝山","cinemaCount":1,"latitude":39.90744,"longitude":116.2357,"orderNum":4},{"stId":1085,"stName":"五棵松","cinemaCount":1,"latitude":39.90746,"longitude":116.274,"orderNum":6},{"stId":1086,"stName":"万寿路","cinemaCount":1,"latitude":39.90747,"longitude":116.2951,"orderNum":7},{"stId":1089,"stName":"木樨地","cinemaCount":1,"latitude":39.90737,"longitude":116.3378,"orderNum":10},{"stId":1091,"stName":"复兴门","cinemaCount":1,"latitude":39.90701,"longitude":116.3562,"orderNum":12},{"stId":1092,"stName":"西单","cinemaCount":3,"latitude":39.90749,"longitude":116.3742,"orderNum":13},{"stId":1095,"stName":"王府井","cinemaCount":3,"latitude":39.90806,"longitude":116.4115,"orderNum":16},{"stId":1096,"stName":"东单","cinemaCount":4,"latitude":39.90829,"longitude":116.42,"orderNum":17},{"stId":1098,"stName":"永安里","cinemaCount":1,"latitude":39.90846,"longitude":116.4504,"orderNum":19},{"stId":1099,"stName":"国贸","cinemaCount":2,"latitude":39.90926,"longitude":116.4618,"orderNum":20},{"stId":1100,"stName":"大望路 ","cinemaCount":1,"latitude":39.90818,"longitude":116.4766,"orderNum":21}]
     */

    private List<SubwaysEntity> subways;
    /**
     * cId : 1027
     * subwayId : 56
     * stationId : 1081
     * distance : 438.2
     */

    private List<SubwayCinemasEntity> subwayCinemas;

    public void setCinemas(List<CinemasEntity> cinemas) {
        this.cinemas = cinemas;
    }

    public void setBusinessCinemas(List<BusinessCinemasEntity> businessCinemas) {
        this.businessCinemas = businessCinemas;
    }

    public void setBusinessAreas(List<BusinessAreasEntity> businessAreas) {
        this.businessAreas = businessAreas;
    }

    public void setDistricts(List<DistrictsEntity> districts) {
        this.districts = districts;
    }

    public void setSubways(List<SubwaysEntity> subways) {
        this.subways = subways;
    }

    public void setSubwayCinemas(List<SubwayCinemasEntity> subwayCinemas) {
        this.subwayCinemas = subwayCinemas;
    }

    public List<CinemasEntity> getCinemas() {
        return cinemas;
    }

    public List<BusinessCinemasEntity> getBusinessCinemas() {
        return businessCinemas;
    }

    public List<BusinessAreasEntity> getBusinessAreas() {
        return businessAreas;
    }

    public List<DistrictsEntity> getDistricts() {
        return districts;
    }

    public List<SubwaysEntity> getSubways() {
        return subways;
    }

    public List<SubwayCinemasEntity> getSubwayCinemas() {
        return subwayCinemas;
    }

    public static class CinemasEntity {
        private int id;
        private String name;
        private String address;
        private String tele;
        private double latitude;
        private double longitude;
        private double baiduLatitude;
        private double baiduLongitude;
        private int districtId;
        private int cityId;
        private int provinceId;
        private String postCode;
        private String route;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setTele(String tele) {
            this.tele = tele;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public void setBaiduLatitude(double baiduLatitude) {
            this.baiduLatitude = baiduLatitude;
        }

        public void setBaiduLongitude(double baiduLongitude) {
            this.baiduLongitude = baiduLongitude;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public void setRoute(String route) {
            this.route = route;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getTele() {
            return tele;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public double getBaiduLatitude() {
            return baiduLatitude;
        }

        public double getBaiduLongitude() {
            return baiduLongitude;
        }

        public int getDistrictId() {
            return districtId;
        }

        public int getCityId() {
            return cityId;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public String getPostCode() {
            return postCode;
        }

        public String getRoute() {
            return route;
        }
    }

    public static class BusinessCinemasEntity {
        private int busId;
        private int cId;

        public void setBusId(int busId) {
            this.busId = busId;
        }

        public void setCId(int cId) {
            this.cId = cId;
        }

        public int getBusId() {
            return busId;
        }

        public int getCId() {
            return cId;
        }
    }

    public static class BusinessAreasEntity {
        private int id;
        private String name;
        private int provinceID;
        private int cityId;
        private int districtId;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setProvinceID(int provinceID) {
            this.provinceID = provinceID;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getProvinceID() {
            return provinceID;
        }

        public int getCityId() {
            return cityId;
        }

        public int getDistrictId() {
            return districtId;
        }
    }

    public static class DistrictsEntity {
        private int id;
        private String name;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class SubwaysEntity {
        private int id;
        private String name;
        private String img;
        private int cinemaCount;
        private int stationCount;
        private String lineMap;
        /**
         * stId : 1081
         * stName : 古城路
         * cinemaCount : 1
         * latitude : 39.90727
         * longitude : 116.1907
         * orderNum : 2
         */

        private List<StationsEntity> stations;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setCinemaCount(int cinemaCount) {
            this.cinemaCount = cinemaCount;
        }

        public void setStationCount(int stationCount) {
            this.stationCount = stationCount;
        }

        public void setLineMap(String lineMap) {
            this.lineMap = lineMap;
        }

        public void setStations(List<StationsEntity> stations) {
            this.stations = stations;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getImg() {
            return img;
        }

        public int getCinemaCount() {
            return cinemaCount;
        }

        public int getStationCount() {
            return stationCount;
        }

        public String getLineMap() {
            return lineMap;
        }

        public List<StationsEntity> getStations() {
            return stations;
        }

        public static class StationsEntity {
            private int stId;
            private String stName;
            private int cinemaCount;
            private double latitude;
            private double longitude;
            private int orderNum;

            public void setStId(int stId) {
                this.stId = stId;
            }

            public void setStName(String stName) {
                this.stName = stName;
            }

            public void setCinemaCount(int cinemaCount) {
                this.cinemaCount = cinemaCount;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public void setOrderNum(int orderNum) {
                this.orderNum = orderNum;
            }

            public int getStId() {
                return stId;
            }

            public String getStName() {
                return stName;
            }

            public int getCinemaCount() {
                return cinemaCount;
            }

            public double getLatitude() {
                return latitude;
            }

            public double getLongitude() {
                return longitude;
            }

            public int getOrderNum() {
                return orderNum;
            }
        }
    }

    public static class SubwayCinemasEntity {
        private int cId;
        private int subwayId;
        private int stationId;
        private double distance;

        public void setCId(int cId) {
            this.cId = cId;
        }

        public void setSubwayId(int subwayId) {
            this.subwayId = subwayId;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public int getCId() {
            return cId;
        }

        public int getSubwayId() {
            return subwayId;
        }

        public int getStationId() {
            return stationId;
        }

        public double getDistance() {
            return distance;
        }
    }
}
