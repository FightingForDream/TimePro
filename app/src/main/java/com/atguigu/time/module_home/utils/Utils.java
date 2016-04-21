package com.atguigu.time.module_home.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Huanzi on 2016/4/20.
 */
public class Utils {

    /**
     * 获取网络状态
     *
     * @param context 上下文
     * @return true 网络可用，false 网络不可用
     */
    public static boolean getNetworkState(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null) {
            return info.isConnected();
        }
        return false;
    }
}
