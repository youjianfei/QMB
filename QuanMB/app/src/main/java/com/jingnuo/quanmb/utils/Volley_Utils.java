package com.jingnuo.quanmb.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by PC on 2017/1/9.
 */

public class Volley_Utils {
//    DEPRECATED_GET_OR_POST = -1;
//    GET = 0;
//    POST = 1;
//    PUT = 2;
//    DELETE = 3;
//    HEAD = 4;
//    OPTIONS = 5;
//    TRACE = 6;
//    PATCH = 7;

    Interface_volley_respose mInterface;

    RequestQueue mQueue;
    StringRequest mStringRequest;


    public Volley_Utils(Interface_volley_respose mInterface) {
        this.mInterface = mInterface;
    }

    public void Http(String URL, Context mContext, int Method) {
        mQueue = Volley.newRequestQueue(mContext);
        LogUtils.LOG("ceshi","调用网络请求","vollryUtils");
        mStringRequest = new StringRequest(Method, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogUtils.LOG("ceshi","网络请求成功","vollryUtils");
                if (response != null || response.length() != 0) {
                    mInterface.onSuccesses(response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.LOG("ceshi","网络请求失败","vollryUtils");
                if (error.networkResponse != null) {
                    int code = error.networkResponse.statusCode;
                    mInterface.onError(code);

                }

            }
        }) {//重写方法 解决json返回乱码
            protected Response<String>  parseNetworkResponse(NetworkResponse response)
            {
                String string;
                try {
                    string = new String(response.data,"UTF-8");
                    return Response.success(string, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return Response.error(new ParseError(e));
                }
        }
        };

        mQueue.add(mStringRequest);

    }

    public void postHttp(String URL, Context mContext, int Method, final Map<String, String> map) {
        LogUtils.LOG("ceshi","post请求触发","vollryUtils");
        mQueue = Volley.newRequestQueue(mContext);
        mStringRequest = new StringRequest(Method, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null || response.length() != 0) {
                    mInterface.onSuccesses(response);
                    LogUtils.LOG("ceshi","post成功","vollryUtils");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    int code = error.networkResponse.statusCode;
                    mInterface.onError(code);
                    LogUtils.LOG("ceshi","post请求失败","vollryUtils");
                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return map;
            }
        };

        mQueue.add(mStringRequest);

    }


    public void volleyCancle() {
        mStringRequest.cancel();
    }

}
