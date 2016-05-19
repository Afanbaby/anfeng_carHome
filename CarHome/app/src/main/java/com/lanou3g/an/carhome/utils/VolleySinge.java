package com.lanou3g.an.carhome.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lanou3g.an.carhome.main.MyApplication;

/**
 * Created by anfeng on 16/5/16.
 */
public class VolleySinge {
    private static Context mContext;
    private RequestQueue requestQueue;//请求队列

    private static VolleySinge ourInstance = new VolleySinge();

    //getInstance:获取单例的对象
    public static VolleySinge getInstance() {
        return ourInstance;
    }

    private VolleySinge() {
        //获取Application里面的context
        mContext = MyApplication.getContext();
        //初始化我们的请求队列
        requestQueue = getRequestQueue();
    }

    //提供一个方法新建请求队列
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }
        return requestQueue;
    }

    //方便管理
    public static final String TAG = "VollaySingleton";

    //添加请求
    public <T> void addRequest(Request<T> request) {
        //为我们的请求添加标签,方便管理
        request.setTag(TAG);
        //将请求添加到队列当中
        requestQueue.add(request);
    }

    public <T> void _addRequest(Request<T> request, Object tag) {
        request.setTag(tag);
        requestQueue.add(request);
    }

    //添加StringRequest
    //三个参数:第一个是URL网址,第二个成功时的回调,第三个参数是失败时的回调
    public void _addRequest(String url,
                           Response.Listener<String> listener,//这里为我们成功时的回调加上String类型的泛型
                           Response.ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(url, listener, errorListener);
            //将请求加入到队列
        addRequest(stringRequest);
    }

    public <T> void _addRequest(String url, Class<T> mClass, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        GsonRequest gsonRequest = new GsonRequest(Request.Method.GET, url, mClass, errorListener, listener);
        addRequest(gsonRequest);
    }

    //这个方法是将请求移除队列
    public void RemoveRequest(Object tag) {
        //根据不同的tag移除出队列
        requestQueue.cancelAll(tag);
    }

    public static void addRequest(String url,
                                  Response.Listener<String> listener,
                                  Response.ErrorListener errorListener) {
        //获取单例对象,调用添加的请求的方法(这个StringRequest的请求)
        getInstance()._addRequest(url, listener, errorListener);
    }

    //Gson请求
    public static <T> void addRequest(String url,
                                      Class<T> mClass,
                                      Response.Listener<T> listener,
                                      Response.ErrorListener errorListener) {
        //同上方法将GsonRequest请求加入单例的队列里
        getInstance()._addRequest(url, mClass, listener, errorListener);
    }
}
