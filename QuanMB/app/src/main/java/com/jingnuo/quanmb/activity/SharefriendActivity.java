package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SizeUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import static com.jingnuo.quanmb.data.Urls.Baseurl_index;

public class SharefriendActivity extends BaseActivityother {

    LinearLayout linearLayout_iamge;
    TextView textview_maintitle;

    ImageView image_wxShare;
    ImageView image_wxcircleShare;

    UMImage image;
    public UMShareListener umShareListener;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_sharefriend;
    }

    @Override
    protected void setData() {
        image = new UMImage(this, "https://qmb-img.oss-cn-hangzhou.aliyuncs.com/image/icon/512.png");//缩略图
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                LogUtils.LOG("ceshi", share_media.getName(), "onStart");

            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                ToastUtils.showToast(SharefriendActivity.this, "分享成功");
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                ToastUtils.showToast(SharefriendActivity.this, "分享取消");
            }
        };
    }

    @Override
    protected void initData() {
        ImageView image = new ImageView(this);
        image.setBackgroundResource(R.mipmap.sharebg);
//        int w= Staticdata.ScreenWidth- SizeUtils.dip2px(this,20);
        int w = Staticdata.ScreenWidth;
        int h = (int) (w * 1.2);
        LinearLayout.LayoutParams mLayoutparams = new LinearLayout.LayoutParams(w, h);
        image.setLayoutParams(mLayoutparams);
        linearLayout_iamge.addView(image);
    }

    @Override
    protected void initListener() {
        image_wxShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMWeb web = new UMWeb(Baseurl_index + Staticdata.static_userBean.getData().getAppuser().getClient_no());
                web.setTitle("全民帮|专业家政维修");//标题
                web.setDescription("一键下单，找到你的专属师傅");//描述
                web.setThumb(image);
                new ShareAction(SharefriendActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                        .withMedia(web)
                        .setCallback(umShareListener)
                        .share();

            }
        });
        image_wxcircleShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UMWeb web = new UMWeb(Baseurl_index + Staticdata.static_userBean.getData().getAppuser().getClient_no());
                web.setTitle("全民帮|专业家政维修");//标题
                web.setDescription("一键下单，找到你的专属师傅");//描述
                web.setThumb(image);
                new ShareAction(SharefriendActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                        .withMedia(web)
                        .setCallback(umShareListener)
                        .share();
            }
        });

    }

    @Override
    protected void initView() {
        textview_maintitle = findViewById(R.id.textview_maintitle);
        textview_maintitle.setText("邀请好友");
        linearLayout_iamge = findViewById(R.id.linearLayout_iamge);
        image_wxShare = findViewById(R.id.image_wxShare);
        image_wxcircleShare = findViewById(R.id.image_wxcircleShare);
    }
}
