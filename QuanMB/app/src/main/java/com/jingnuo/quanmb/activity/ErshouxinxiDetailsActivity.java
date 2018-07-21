package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_Gridviewpic_skillsdetails;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.LoveTaskDetailsBean;
import com.jingnuo.quanmb.popwinow.Popwindow_lookpic;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ErshouxinxiDetailsActivity extends BaseActivityother {
    //控件
    CircleImageView mImageview_head;//头像
    TextView mtextview_title;//标题
    TextView mtextview_time;//time
    TextView mtextview_peoplename;//求助人
    TextView mtextview_taskdetails;//任务详情

    MyGridView myGridView;//图片表
    Adapter_Gridviewpic_skillsdetails adapter_gridviewpic;

    String taskid="";//传过来的任务id；
    LoveTaskDetailsBean loveTaskDetailsBean;
    String image_url="";
    List<String> imageview_urllist;

    RequestManager glide;
    Popwindow_lookpic popwindow_lookpic;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_ershouxinxi_details;
    }

    @Override
    protected void setData() {
        popwindow_lookpic=new Popwindow_lookpic(this);
    }

    @Override
    protected void initData() {
        glide= Glide.with(ErshouxinxiDetailsActivity.this);
        taskid=getIntent().getStringExtra("taskid");
        imageview_urllist=new ArrayList<>();
        adapter_gridviewpic=new Adapter_Gridviewpic_skillsdetails(imageview_urllist,this);
        myGridView.setAdapter(adapter_gridviewpic);
        request();
    }

    @Override
    protected void initListener() {
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popwindow_lookpic.showPopwindow(position,imageview_urllist);
            }
        });
    }

    @Override
    protected void initView() {
        mImageview_head=findViewById(R.id.image_task);
        mtextview_title=findViewById(R.id.text_tasktitle);
        mtextview_time=findViewById(R.id.text_tasktime);
        mtextview_peoplename=findViewById(R.id.text_name);
        mtextview_taskdetails=findViewById(R.id.text_taskdetail);
        myGridView=findViewById(R.id.GridView_PIC);
    }
    void request(){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi",respose,"ErshouxinxiDetailsActivity");
                loveTaskDetailsBean=new Gson().fromJson(respose,LoveTaskDetailsBean.class);
                image_url=loveTaskDetailsBean.getData().getTask_ImgUrl();
                setImage(image_url);
                glide.load(loveTaskDetailsBean.getData().getHeadUrl()).into(mImageview_head);
                mtextview_title.setText(loveTaskDetailsBean.getData().getTask_name());
                mtextview_time.setText("发布时间："+loveTaskDetailsBean.getData().getCreateDate());
                mtextview_peoplename.setText(loveTaskDetailsBean.getData().getNick_name());
                mtextview_taskdetails.setText(loveTaskDetailsBean.getData().getTask_description());

            }
            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui+Urls.ErshoushichangDetails+ Staticdata.static_userBean.getData().getUser_token()+
                "&task_id="+taskid,ErshouxinxiDetailsActivity.this,0);



    }
    void setImage(String  image){
        if(image==null||image.equals("")){

        }else {
            String []images=image.split(",");
            int len=images.length;
            LogUtils.LOG("ceshi","图片的个数"+images.length,"SkillDetailActivity分隔图片");
            for(int i=0;i<len;i++){
                imageview_urllist.add(images[i]);
            }
            adapter_gridviewpic.notifyDataSetChanged();

        }

    }
}