package com.atguigu.time.bean;

import java.util.List;

/**
 * Created by vence on 16/4/9 20:01
 * 邮箱 ：vence0815@foxmail.com
 */
public class CityList {

    /**
     * id : 292
     * n : 上海
     * count : 192
     * pinyinShort : sh
     * pinyinFull : Shanghai
     */

    private List<PEntity> p;

    public void setP(List<PEntity> p) {
        this.p = p;
    }

    public List<PEntity> getP() {
        return p;
    }

    public static class PEntity {
        private int id;
        private String n;
        private int count;
        private String pinyinShort;
        private String pinyinFull;

        public void setId(int id) {
            this.id = id;
        }

        public void setN(String n) {
            this.n = n;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setPinyinShort(String pinyinShort) {
            this.pinyinShort = pinyinShort;
        }

        public void setPinyinFull(String pinyinFull) {
            this.pinyinFull = pinyinFull;
        }

        public int getId() {
            return id;
        }

        public String getN() {
            return n;
        }

        public int getCount() {
            return count;
        }

        public String getPinyinShort() {
            return pinyinShort;
        }

        public String getPinyinFull() {
            return pinyinFull;
        }
    }
}
