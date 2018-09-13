package com.jingnuo.quanmb.activity;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.customview.SimpleRatingBar;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.HelpterInfoBean;
import com.jingnuo.quanmb.entityclass.ShopcenterBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopCenterActivity extends BaseActivityother {
    //控件
    CircleImageView imageview_head;
    SimpleRatingBar simpleRatingBar;

    ImageView mImageview_lv;
    ImageView mImageview_vip;
    ImageView image_tujianrenwukaiguan;//是否开启推荐任务
    TextView text_guize;//规则
    TextView mTextview_tt;
    TextView mTextview_name;  //名字
//
    TextView mTextview_money;//佣金
//    TextView mTextview_level;//等级
    TextView mTextview_text_tui_count;//推广币个数
    TextView mTextview_text_huiyuan;//会员到期时间

    RelativeLayout mRealtivelayout_issue;
    RelativeLayout mRealtivelayout_myissue;
    RelativeLayout mRealtivelayout_myorder;
    RelativeLayout mRealtivelayout_mytuiguangbi;
    RelativeLayout mRealtivelayout_myauthentication;
    RelativeLayout mRealtivelayout_huiyuan;
    RelativeLayout relative_istujianrenwu;
    Button mButtonCash;
    Button button_tuiyajin;

    //对象
    ShopcenterBean shopcenterBean;//商户
    HelpterInfoBean helpterInfoBean;//帮手


    //数据
    int type = 0;  //1 帮手  2  商户


    boolean isfirst=true;

    @Override
    public int setLayoutResID() {
        return R.layout.activity_shop_center;
    }

    @Override
    protected void setData() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.yellow_background), 0);//状态栏颜色

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        mTextview_tt.setText(type == 1 ? "帮手中心" : "商户中心");
        if (type==1){
            text_guize.setText("帮手细则");
            button_tuiyajin.setVisibility(View.VISIBLE);
            relative_istujianrenwu.setVisibility(View.INVISIBLE);
            isfirst= SharedPreferencesUtils.getBoolean(this, "QMB", "bangshou");
            if(isfirst){
                Intent intent_shopcenter=new Intent(this, HelperguizeActivity.class);
                intent_shopcenter.putExtra("title","帮手细则");
                startActivity(intent_shopcenter);
            }

        }else {
            text_guize.setText("接单规则");
            button_tuiyajin.setVisibility(View.INVISIBLE);
            relative_istujianrenwu.setVisibility(View.VISIBLE);
            isfirst= SharedPreferencesUtils.getBoolean(this, "QMB", "shanghu");
            if (isfirst){
                Intent intent_shopcenter=new Intent(this, HelperguizeActivity.class);
                intent_shopcenter.putExtra("title","接单规则");
                startActivity(intent_shopcenter);
            }

        }
        request();
    }
    @Override
    protected void initListener() {
        mRealtivelayout_issue.setOnClickListener(this);
        mRealtivelayout_myissue.setOnClickListener(this);
        mRealtivelayout_myorder.setOnClickListener(this);
        mRealtivelayout_myauthentication.setOnClickListener(this);
        mRealtivelayout_mytuiguangbi.setOnClickListener(this);
        mRealtivelayout_huiyuan.setOnClickListener(this);
        mButtonCash.setOnClickListener(this);
        imageview_head.setOnClickListener(this);
        text_guize.setOnClickListener(this);
        image_tujianrenwukaiguan.setOnClickListener(this);
        button_tuiyajin.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        imageview_head = findViewById(R.id.image_shoppeoplepic);
        simpleRatingBar = findViewById(R.id.SimpleRatingBar);
        mImageview_lv=findViewById(R.id.image_lv);
        mImageview_vip=findViewById(R.id.image_vip);
        image_tujianrenwukaiguan=findViewById(R.id.image_tujianrenwukaiguan);
        mTextview_tt = findViewById(R.id.textview_tt);
        text_guize = findViewById(R.id.text_guize);
        mTextview_name = findViewById(R.id.text_shopname);
        mTextview_text_tui_count = findViewById(R.id.text_tui_count);
        mTextview_money = findViewById(R.id.textview_money);
        mRealtivelayout_issue = findViewById(R.id.relative_issuetask);
        mRealtivelayout_myissue = findViewById(R.id.relative_myissue);
        mRealtivelayout_myorder = findViewById(R.id.myorder);
        mRealtivelayout_mytuiguangbi = findViewById(R.id.mytuiguangbi);
        mTextview_text_huiyuan = findViewById(R.id.text_huiyuan);
        mRealtivelayout_myauthentication = findViewById(R.id.myauthentication);
        mRealtivelayout_huiyuan = findViewById(R.id.huiyuan);
        relative_istujianrenwu = findViewById(R.id.relative_istujianrenwu);
        mButtonCash = findViewById(R.id.button_cash);
        button_tuiyajin = findViewById(R.id.button_tuiyajin);

    }
    void setstar(float stars){
        simpleRatingBar.setNumberOfStars(5);
        simpleRatingBar.setFillColor(getResources().getColor(R.color.srb_golden_stars));
        simpleRatingBar.setStarBackgroundColor(getResources().getColor(R.color.white));
        simpleRatingBar.setStepSize((float) 0.1);
        simpleRatingBar.setRating(stars);
        simpleRatingBar.setDrawBorderEnabled(false);
        simpleRatingBar.setStarsSeparation(1);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case  R.id.button_tuiyajin://退押金
                Intent intent_tuiyajin=new Intent(this,TuiyajinActivity.class);
                startActivity(intent_tuiyajin);
                break;
            case R.id.text_guize://接单规则界面
                if (type==1){
                        Intent intent_shopcenter=new Intent(this, HelperguizeActivity.class);
                        intent_shopcenter.putExtra("title","帮手细则");
                        startActivity(intent_shopcenter);

                }else {
                        Intent intent_shopcenter=new Intent(this, HelperguizeActivity.class);
                        intent_shopcenter.putExtra("title","接单规则");
                        startActivity(intent_shopcenter);
                }
                break;
            case  R.id.image_tujianrenwukaiguan://推荐任务推送的开关
                if(image_tujianrenwukaiguan.isSelected()){
                    image_tujianrenwukaiguan.setSelected(false);
                    requestTuisongstate("N");

                }else {
                    image_tujianrenwukaiguan.setSelected(true);
                    requestTuisongstate("Y");
                }
                break;
            case R.id.huiyuan://会员充值
                Intent intent_huiyuan = new Intent(ShopCenterActivity.this, HuiyuanRechargeActivity.class);
                startActivity(intent_huiyuan);
                break;
            case R.id.image_shoppeoplepic://商户信息更改
                if (type == 2) {
                    Intent intent_shopcenterinfo = new Intent(ShopCenterActivity.this, ShopCenterInfoActivity.class);
                    startActivity(intent_shopcenterinfo);
                }
                break;

            case R.id.button_cash://提现
                Intent intent_cash = new Intent(this, CashoutActivity.class);
                if(type==1){
                    intent_cash.putExtra("money",helpterInfoBean.getData().getList().getCommission()+"");
                    intent_cash.putExtra("TransferType","2");
                }
                if(type==2){
                    intent_cash.putExtra("money",shopcenterBean.getData().getList().getCommission()+"");
                    intent_cash.putExtra("TransferType","3");
                }
                startActivity(intent_cash);
                break;
            case R.id.relative_issuetask://发布服务
                Intent intend_issue_skill = new Intent(ShopCenterActivity.this, IssueSkillActivity.class);
                intend_issue_skill.putExtra("type", type);
                ShopCenterActivity.this.startActivity(intend_issue_skill);
                break;

            case R.id.relative_myissue://我的发布
                Intent intent_myorder = new Intent(ShopCenterActivity.this, MySkillActivity.class);
                intent_myorder.putExtra("type", type);
                startActivity(intent_myorder);
                break;

            case R.id.myorder://我的订单
                Intent intent_mytodo = new Intent(ShopCenterActivity.this, MyTodoActivity.class);
                intent_mytodo.putExtra("type", type);
                startActivity(intent_mytodo);
                break;

            case R.id.myauthentication://我的认证
                Intent intent_myrenzheng = new Intent(ShopCenterActivity.this, MyAuthenticationActivity.class);
                intent_myrenzheng.putExtra("type", type);
                startActivity(intent_myrenzheng);
                break;
            case R.id.mytuiguangbi://我的推广币  充值
                Intent intent_mytuiguangbi = new Intent(ShopCenterActivity.this, TuiguangbiWalletActivity.class);
                intent_mytuiguangbi.putExtra("type", type);
                intent_mytuiguangbi.putExtra("tuiguangbi", mTextview_text_tui_count.getText()+"");
                startActivity(intent_mytuiguangbi);
                break;
        }


    }
    void  requestTuisongstate(String state){
        new  Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                LogUtils.LOG("ceshi", respose, "推送开关");
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("message");//
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (status == 1) {
                    ToastUtils.showToast(ShopCenterActivity.this, msg);
                    request();
                } else {
                    ToastUtils.showToast(ShopCenterActivity.this, msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_cui+Urls.push_on_off+Staticdata.static_userBean.getData().getUser_token()+"&push_on_off="+state,this,0);




    }

    void request() {
        String url_info = type == 1 ? Urls.Baseurl + Urls.helperInfo + Staticdata.static_userBean.getData()
                .getUser_token() + "&client_no=" + Staticdata.static_userBean.getData().getAppuser()
                .getClient_no()
                :
                Urls.Baseurl + Urls.shopcenter + Staticdata.static_userBean.getData()
                        .getUser_token() + "&client_no=" + Staticdata.static_userBean.getData().getAppuser()
                        .getClient_no();
        LogUtils.LOG("ceshi", "帮手 商户网址：" + url_info, "ShopCenterActivity");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", "商户中心：" + respose, "ShopCenterActivity");
                if (type == 1) {
                    helpterInfoBean = new Gson().fromJson(respose, HelpterInfoBean.class);
                    if(helpterInfoBean.getData().getList().getMemberImgUrl()==null||helpterInfoBean.getData().getList().getMemberImgUrl().equals("")){
                        mImageview_vip.setVisibility(View.GONE);
                    }else {
                        mImageview_vip.setVisibility(View.VISIBLE);
                        Glide.with(ShopCenterActivity.this).load(helpterInfoBean.getData().getList().getMemberImgUrl()).error(R.mipmap.vip1).into(mImageview_vip);
                    }
                    Glide.with(ShopCenterActivity.this).load(helpterInfoBean.getData().getList().getAvatar_url()).into(imageview_head);
                    Glide.with(ShopCenterActivity.this).load(helpterInfoBean.getData().getList().getIconImgUrl()).error(R.mipmap.lv1).into(mImageview_lv);
                    mTextview_name.setText(helpterInfoBean.getData().getList().getHelper_name());
//                    mTextview_level.setText(helpterInfoBean.getData().getList().geth());
                    mTextview_text_tui_count.setText(helpterInfoBean.getData().getList().getSpread_b()+"个");
                    if(helpterInfoBean.getData().getList().getMember_enddate()!=null){
                        mTextview_text_huiyuan.setText(helpterInfoBean.getData().getList().getMember_enddate().substring(0,10)+"到期");
                    }
//                    mTextview_namenext.setVisibility(View.GONE);
                    mTextview_money.setText(helpterInfoBean.getData().getList().getCommission()+"");
                    setstar((float) helpterInfoBean.getData().getList().getEvaluation_star());

                } else {
                    shopcenterBean = new Gson().fromJson(respose, ShopcenterBean.class);
                    if(shopcenterBean.getData().getList().getPush_on_off().equals("Y")){//推送开关状态
                        image_tujianrenwukaiguan.setSelected(true);
                    }else {
                        image_tujianrenwukaiguan.setSelected(false);
                    }
                    if(shopcenterBean.getData().getList().getMemberImgUrl()==null||shopcenterBean.getData().getList().getMemberImgUrl().equals("")){
                        mImageview_vip.setVisibility(View.GONE);
                    }else {
                        mImageview_vip.setVisibility(View.VISIBLE);
                        Glide.with(ShopCenterActivity.this).load(shopcenterBean.getData().getList().getMemberImgUrl()).error(R.mipmap.vip1).into(mImageview_vip);
                    }
                    Glide.with(ShopCenterActivity.this).load(shopcenterBean.getData().getList().getAvatar_url()).into(imageview_head);
                    Glide.with(ShopCenterActivity.this).load(shopcenterBean.getData().getList().getIconImgUrl()).error(R.mipmap.lv1).into(mImageview_lv);
                    mTextview_name.setText(shopcenterBean.getData().getList().getBusiness_name());
//                    mTextview_namenext.setText(shopcenterBean.getData().getList().getBusiness_address()+" | ");
                    mTextview_money.setText(shopcenterBean.getData().getList().getCommission()+"");
                    mTextview_text_tui_count.setText(shopcenterBean.getData().getList().getSpread_b()+"个");
                    if(shopcenterBean.getData().getList().getMember_enddate()!=null){
                        mTextview_text_huiyuan.setText(shopcenterBean.getData().getList().getMember_enddate().substring(0,10)+"到期");
                    }
                    setstar((float) shopcenterBean.getData().getList().getEvaluation_star());
                }

            }

            @Override
            public void onError(int error) {

            }
        }).Http(url_info, ShopCenterActivity.this, 0);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        request();
    }
}
