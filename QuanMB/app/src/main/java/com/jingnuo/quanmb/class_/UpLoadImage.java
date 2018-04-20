package com.jingnuo.quanmb.class_;

import android.content.Context;


import com.jingnuo.quanmb.Interface.Interface_loadImage_respose;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by PC on 2017/3/20.
 */

public class UpLoadImage {

    /**
     * 上传图片到服务器
     */

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private final OkHttpClient client = new OkHttpClient();

    Interface_loadImage_respose interface_loadImage_respose;
    Context context;
    private  static  UpLoadImage mUpLoadImage;


    public UpLoadImage(Context context) {
        this.context=context.getApplicationContext();
    }

    public  static  UpLoadImage getIntence(Context context, Interface_loadImage_respose interface_loadImage_respose){//单例模式
        if(mUpLoadImage==null){
            mUpLoadImage= new UpLoadImage(context);
            mUpLoadImage.interface_loadImage_respose=interface_loadImage_respose;//接口回调
        }
        return  mUpLoadImage;
    }


    public  void uploadImg(List<String> pathList, int  type) {


        // mImgUrls为存放图片的url集合
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i <pathList.size() ; i++) {
            File f=new File(pathList.get(i));
            if (f!=null) {

                builder.addFormDataPart("picItem", f.getName(), RequestBody.create(MEDIA_TYPE_PNG, f));
                LogUtils.LOG("ceshi","图片文件"+f.getName(),"上传图片");
            }
        }
        //添加其它信息
        if(type==0){

        }else{
            builder.addFormDataPart("type",type+"");
            LogUtils.LOG("ceshi","图片文件type:"+type,"上传图片");
        }



//        builder.addFormDataPart("type",type+"");
//        builder.addFormDataPart("mapX", SharedInfoUtils.getLongitude());
//        builder.addFormDataPart("mapY",SharedInfoUtils.getLatitude());
//        builder.addFormDataPart("name",SharedInfoUtils.getUserName());

        //构建请求
        Request request = new Request.Builder()
                .url(Urls.Baseurl_hu+Urls.upLoadImage)//地址
                .post(builder.build())//添加请求体
                .build();
        LogUtils.LOG("ceshi","上传图片网址"+Urls.Baseurl_hu+Urls.upLoadImage,"上传图片");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                LogUtils.LOG("ceshi","上传失败:e.getLocalizedMessage() = " + e.getLocalizedMessage(),"上传图片");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                interface_loadImage_respose.onSuccesses(response.body().string());

            }
        });

    }

}
