package com.jingnuo.quanmb.utils;

import android.content.Context;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.LoginActivity;
import com.jingnuo.quanmb.activity.LoginActivityPhone;
import com.jingnuo.quanmb.data.Staticdata;

import org.json.JSONException;
import org.json.JSONObject;

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

    public void Http(String URL, final Context mContext, int Method) {
        mQueue = Volley.newRequestQueue(mContext);
        LogUtils.LOG("ceshi","调用网络请求"+URL,"vollryUtils");
        mStringRequest = new StringRequest(Method, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogUtils.LOG("ceshi","网络请求成功","vollryUtils");
                if (response != null || response.length() != 0) {
                    int status = 0;
//                    String msg = "";
                    try {
                        JSONObject object = new JSONObject(response);
                        status = (Integer) object.get("code");
//                        msg = (String) object.get("msg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(status==-5){
                        SharedPreferencesUtils.putString(mContext,"QMB","password","");
                        Staticdata. static_userBean=null;
                        ToastUtils.showToast(mContext,"登录过期，请重新登录");
                        Staticdata.isLogin=false;//将登录状态改为未登录
                        mContext.startActivity(new Intent(mContext, LoginActivityPhone.class));
                    }else {

                        mInterface.onSuccesses(response);
                    }
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

    public void postHttp(String URL, final Context mContext, int Method, final Map<String, String> map) {
        LogUtils.LOG("ceshi","post请求触发"+URL+map.toString(),"vollryUtils");
        mQueue = Volley.newRequestQueue(mContext);

        mStringRequest = new StringRequest(Method, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null || response.length() != 0) {


                    LogUtils.LOG("ceshi","post成功"+response,"vollryUtils");
                    int status = 0;
//                    String msg = "";
                    try {
                        JSONObject object = new JSONObject(response);
                        status = (Integer) object.get("code");
//                        msg = (String) object.get("msg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(status==-5){
                        SharedPreferencesUtils.putString(mContext,"QMB","password","");
                        Staticdata. static_userBean=null;
                        ToastUtils.showToast(mContext,"登录过期，请重新登录");
                        Staticdata.isLogin=false;
                        mContext.startActivity(new Intent(mContext, LoginActivityPhone.class));


                    }else {
                        mInterface.onSuccesses(response);
                }
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
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        mQueue.add(mStringRequest);

    }


    public void volleyCancle() {
        mStringRequest.cancel();
    }

}
