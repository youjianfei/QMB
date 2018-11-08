package com.jingnuo.quanmb.Interface;

import com.jingnuo.quanmb.entityclass.UserBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/3/30.
 */

public interface PostInterface_retrofit {

    @POST(".")
    @FormUrlEncoded
    Call<Object> getCall(@FieldMap Map<String,String> map) ;
    //采用@Post表示Post方法进行请求（传入部分url地址）
    // 采用@FormUrlEncoded注解的原因:API规定采用请求格式x-www-form-urlencoded,即表单形式
    // 需要配合@Field 向服务器提交需要的字段

}
