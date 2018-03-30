package com.jingnuo.quanmb.utils;

import com.jingnuo.quanmb.Interface.PostInterface_retrofit;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.UserBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2018/3/30.
 */

public class Request_retrofit {
  public static  Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Urls.login) // 设置 网络请求 Url
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static void retrofit_post(Map map){

        // 步骤5:创建 网络请求接口 的实例
        PostInterface_retrofit request = retrofit.create(PostInterface_retrofit.class);

        //对 发送请求 进行封装
        Call<UserBean> call = request.getCall(map);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                LogUtils.LOG("ceshi","成功retrofit_post"+response.body(),"request_retrUtils");

            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {

                LogUtils.LOG("ceshi","失败retrofit_post"+t.getMessage(),"request_retrUtils");

            }
        });
    }


}
