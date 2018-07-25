package com.jingnuo.quanmb.class_;


import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;


/**
 * Created by Administrator on 2017/10/10.
 */

public class ShareGoodWeb {//直接分享商品卡片链接
    public UMShareListener umShareListener;
    private Activity activity;
    KProgressHUD mKProgressHUD;

//    SharewebJson shareJsonBean;
    String image_small="";//卡片缩略图
    String description="";//描述

    public ShareGoodWeb(Activity activity) {
        this.activity = activity;
    }

    /**
     * 微信分享方法
     */
    public  void shareapp( ) {
        mKProgressHUD=new KProgressHUD(activity);
        LogUtils.LOG("ceshi","我是拉出来的微信分享","分享");
        image_small= "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532493059729&di=416359b3ebe289b5ac6" +
                "8ac0d01a7c53a&imgtype=0&src=http%3A%2F%2Fpic.xiudodo.com%2Ffigure%2F00%2F00%2F33%2F16%2F73%2F1655bda6abbcd26.jpg";
        description="我是分享的图片";
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                LogUtils.LOG("ceshi",share_media.getName(),"onStart");

            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                ToastUtils.showToast(activity,"分享成功");
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                ToastUtils.showToast(activity,"分享取消");
            }
        };
        ShareBoardlistener shareBoardlistener = new ShareBoardlistener() {

            @Override
            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                UMImage image = new UMImage(activity, image_small);//缩略图
                UMImage thumb = new UMImage(activity,image_small );
                image.setThumb(thumb);
                if (share_media == share_media.WEIXIN_CIRCLE) {
//                    Toast.makeText(activity, "美文已复制到剪贴板", Toast.LENGTH_LONG).show();
//                    //获取剪贴板管理器：
//                    ClipboardManager cm = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
//                    // 创建普通字符型ClipData
//                    ClipData mClipData = ClipData.newPlainText("Label", shareJsonBean.getData().getSpec_info());
//                    // 将ClipData内容放到系统剪贴板里。
//                    cm.setPrimaryClip(mClipData);

                    UMWeb web = new UMWeb("https://www.baidu.com/");
                    web.setTitle("分享标题");//标题
                    web.setDescription(description);//描述
                    web.setThumb(image);
                    new ShareAction(activity).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                            .withMedia(web)
                            .setCallback(umShareListener)
                            .share();
                }
                if (share_media == share_media.WEIXIN) {


                    UMWeb web = new UMWeb("https://www.baidu.com/");
                    web.setTitle("分享标题");//标题
                    web.setDescription(description);//描述
                    web.setThumb(image);
                    new ShareAction(activity).setPlatform(SHARE_MEDIA.WEIXIN)
                            .withMedia(web)
                            .setCallback(umShareListener)
                            .share();

                }
//                if (share_media == share_media.QQ) {
//
//                    UMWeb web = new UMWeb("https://www.baidu.com/");
//                    web.setTitle("分享标题");//标题
//                    web.setThumb(thumb);  //缩略图
//                    web.setDescription(description);//描述
//                    new ShareAction(activity).setPlatform(SHARE_MEDIA.QQ)
//                            .withMedia(web)
//                            .setCallback(umShareListener)
//                            .share();
//                }


//                if (share_media == share_media.SINA) {
//
//                    new ShareAction(activity).setPlatform(SHARE_MEDIA.SINA)
//                            .withMedia(image).withText(sharegoodtitle+shareJsonBean.getData().getShort_url())
//                            .setCallback(umShareListener)
//                            .share();
//                }

//                if (snsPlatform.mShowWord.equals("sendmessage")) {
//                    Toast.makeText(activity, "发送短信按钮", Toast.LENGTH_LONG).show();
//                } else if (snsPlatform.mShowWord.equals("copyweb")) {
//
//                    Toast.makeText(activity, "复制链接成功", Toast.LENGTH_LONG).show();
//                    //获取剪贴板管理器：
//                    ClipboardManager cm = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
//                    // 创建普通字符型ClipData
//                    ClipData mClipData = ClipData.newPlainText("Label", shareJsonBean.getData().getUrl());
//                    // 将ClipData内容放到系统剪贴板里。
//                    cm.setPrimaryClip(mClipData);
//                }

            }
        };
//        if(type.equals("1")){
//            new ShareAction(activity)
//                    .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.SINA).
////                    addButton("sendmessage", "message", "sendmessage", "info_icon_1").
//        addButton("copyweb", "copy", "copyweb", "info_icon_1")
//                    .setShareboardclickCallback(shareBoardlistener)
//                    .open();
//        } else
       {
            new ShareAction(activity)
                    .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE).

//                    addButton("copyweb", "copy", "copyweb", "info_icon_1").
        setShareboardclickCallback(shareBoardlistener)
                    .open();
        }

    }

}
