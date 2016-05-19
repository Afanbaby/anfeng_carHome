package com.lanou3g.an.carhome.utils;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by anfeng on 16/5/11.
 */
public class GsonRequest<T> extends Request<T> {

    private Response.Listener<T> mListener;
    private Gson gson;
    //实体类
    private Class<T> mClass;

    //构造方法中,我们传入了的第一个参数:请求数据类型,第二个参数:URL,
    // 第三个参数:失败时候的回调,第四的参数:成功时候的回调,第五个参数:实体类
    public GsonRequest(int method, String url, Class<T> mClass, Response.ErrorListener listener, Response.Listener<T> mListener) {
        super(method, url, listener);
        this.mListener = mListener;
        this.mClass = mClass;
        //对Gson进行初始化
        this.gson = new Gson();
    }

    //网络回应(将字节转换为String类型,在进行解析1.response-->2.String data -->3.Gson)
    //返回的是我们想要解析的数据,在这里首先将response类型的数据转成String类型的数据,
    // 然后通过gson进行解析
    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try {
            //内部的缓存机制
            //成功之后的回调,第一个参数是我们将data直接解析,第二个参数就是我们的缓存入口(请求头,编码格式),这里的缓存是规定好的
            String data = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            //我们直接将response请求作为缓存入口
            //第一个参数是我们将data直接解析,第二个参数是实体类,第三个参数就是我们的缓存入口(请求头,编码格式)
            return Response.success(gson.fromJson(data, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            //如果失败的话,返回失败的原因
            return Response.error(new ParseError(e));
        }
    }


    //网络请求反馈
    //对请求的一个反馈,反馈的就是我们的上面定义好的接口对象
    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }


}
