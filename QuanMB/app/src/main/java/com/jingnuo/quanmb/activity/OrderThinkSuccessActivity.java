package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import static com.jingnuo.quanmb.data.Urls.Baseurl_index;

public class OrderThinkSuccessActivity extends BaseActivityother {
    //控件

    Button button_main;
    Button button_issue;
    ImageView image_wxShare;
    ImageView image_wxcircleShare;


    UMImage image;
    public UMShareListener umShareListener;
    @Override
    public int setLayoutResID() {
        return R.layout.activity_order_think_success;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
         image = new UMImage(OrderThinkSuccessActivity.this, "https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/512.png");//缩略图
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                LogUtils.LOG("ceshi",share_media.getName(),"onStart");

            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                ToastUtils.showToast(OrderThinkSuccessActivity.this,"分享成功");
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                ToastUtils.showToast(OrderThinkSuccessActivity.this,"分享取消");
            }
        };
    }

    @Override
    protected void initListener() {
        button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderThinkSuccessActivity.this,IssueTaskActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button_issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderThinkSuccessActivity.this,IssueTaskActivity.class);
                startActivity(intent);
                finish();
            }
        });
        image_wxShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMWeb web = new UMWeb(Baseurl_index+ Staticdata.static_userBean.getData().getAppuser().getClient_no());
                web.setTitle("全民帮|专业家政维修");//标题
                web.setDescription("一键下单，找到你的专属师傅");//描述
                web.setThumb(image);
                new ShareAction(OrderThinkSuccessActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                        .withMedia(web)
                        .setCallback(umShareListener)
                        .share();

            }
        });
        image_wxcircleShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UMWeb web = new UMWeb(Baseurl_index+ Staticdata.static_userBean.getData().getAppuser().getClient_no());
                web.setTitle("全民帮|专业家政维修");//标题
                web.setDescription("一键下单，找到你的专属师傅");//描述
                web.setThumb(image);
                new ShareAction(OrderThinkSuccessActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                        .withMedia(web)
                        .setCallback(umShareListener)
                        .share();
            }
        });
    }

    @Override
    protected void initView() {
        button_main=findViewById(R.id.button_main);
        button_issue=findViewById(R.id.button_issue);
        image_wxShare=findViewById(R.id.image_wxShare);
        image_wxcircleShare=findViewById(R.id.image_wxcircleShare);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                Intent intent=new Intent(OrderThinkSuccessActivity.this,IssueTaskActivity.class);
                startActivity(intent);
                finish();
                break;}
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(OrderThinkSuccessActivity.this,IssueTaskActivity.class);
        startActivity(intent);
        finish();
    }
}
