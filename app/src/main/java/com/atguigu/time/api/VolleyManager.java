package com.atguigu.time.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Volley封装类
 * Created by Huanzi, Xiaopei on 2016/4/9.
 */
public class VolleyManager {
    private static VolleyManager mInstance;
    private Context context;
    private RequestQueue queue;


    private VolleyManager(Context context) {
        this.context = context;
    }

    public RequestQueue getQueue() {
        if (queue == null){
            synchronized (this){
                if(queue == null){
                    queue = Volley.newRequestQueue(context);
                }
            }
        }
        return queue;
    }

    public void addRequest(Request request){
        queue.add(request);
    }

    public static VolleyManager getInstance(Context context){
        if (mInstance == null){
            synchronized (VolleyManager.class){
                if(mInstance == null){
                    mInstance = new VolleyManager(context);
                }
            }
        }
        return mInstance;
    }
}
