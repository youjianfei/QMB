package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.class_.ShareGoodWeb;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.YaoqinghaoyouBean;
import com.jingnuo.quanmb.popwinow.Popwindow_yaoqingguize;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;

public class YaoqingHaoyouActivity extends BaseActivityother {
    //控件
    TextView textview_guize;
    TextView textview_jiangli;
    TextView textview_yaoqingrenshu;//邀请人数
    TextView textview_xiadanrenshu;//下单人数
    ImageView image_button;


    YaoqinghaoyouBean yaoqinghaoyouBean;

//    Share_yaoqinghaoyou share_yaoqinghaoyou;
    ShareGoodWeb share_yaoqinghaoyou;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_yaoqing_haoyou;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        share_yaoqinghaoyou=new ShareGoodWeb(this);
    }

    @Override
    protected void initListener() {
        textview_guize.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  new Popwindow_yaoqingguize(YaoqingHaoyouActivity.this).showpopwindow();
                                              }
                                          }
        );
        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.isWxInstall(YaoqingHaoyouActivity.this)){
                    share_yaoqinghaoyou.shareapp();
                }else {
                    ToastUtils.showToast(YaoqingHaoyouActivity.this,"未安装微信");
                }

            }
        });
    }

    @Override
    protected void initView() {
        textview_guize = findViewById(R.id.textview_guize);
        textview_jiangli = findViewById(R.id.textview_jiangli);
        textview_yaoqingrenshu = findViewById(R.id.textview_yaoqingrenshu);
        textview_xiadanrenshu = findViewById(R.id.textview_xiadanrenshu);
        image_button = findViewById(R.id.image_button);
    }

    void request() {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "邀请好友");
                yaoqinghaoyouBean = new Gson().fromJson(respose, YaoqinghaoyouBean.class);
                textview_jiangli.setText(yaoqinghaoyouBean.getData().getBonus());
                textview_yaoqingrenshu.setText(yaoqinghaoyouBean.getData().getAmount());
                textview_xiadanrenshu.setText(yaoqinghaoyouBean.getData().getIs_order());
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui + Urls.userInvite + Staticdata.static_userBean.getData().getUser_token(), this, 0);
    }
}
