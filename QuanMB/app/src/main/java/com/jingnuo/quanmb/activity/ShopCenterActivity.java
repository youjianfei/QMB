package com.jingnuo.quanmb.activity;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.HelpterInfoBean;
import com.jingnuo.quanmb.entityclass.ShopcenterBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import de.hdodenhof.circleimageview.CircleImageView;


public class ShopCenterActivity extends BaseActivityother {
    //控件
    CircleImageView imageview_head;
    TextView mTextview_tt;
    TextView mTextview_name;  //名字
    TextView mTextview_namenext;//
    TextView mTextview_money;//佣金
    TextView mTextview_level;//等级
    TextView mTextview_text_tui_count;//推广币个数
    TextView mTextview_text_huiyuan;//会员到期时间

    RelativeLayout mRealtivelayout_issue;
    RelativeLayout mRealtivelayout_myissue;
    RelativeLayout mRealtivelayout_myorder;
    RelativeLayout mRealtivelayout_mytuiguangbi;
    RelativeLayout mRealtivelayout_myauthentication;
    RelativeLayout mRealtivelayout_huiyuan;
    Button mButtonCash;

    //对象
    ShopcenterBean shopcenterBean;//商户
    HelpterInfoBean helpterInfoBean;//帮手


    //数据
    int type = 0;  //1 帮手  2  商户

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

    }

    @Override
    protected void initView() {
        imageview_head = findViewById(R.id.image_shoppeoplepic);
        mTextview_tt = findViewById(R.id.textview_tt);
        mTextview_name = findViewById(R.id.text_shopname);
        mTextview_namenext = findViewById(R.id.textview_address);
        mTextview_text_tui_count = findViewById(R.id.text_tui_count);
        mTextview_money = findViewById(R.id.textview_money);
        mTextview_level = findViewById(R.id.text_level);
        mRealtivelayout_issue = findViewById(R.id.relative_issuetask);
        mRealtivelayout_myissue = findViewById(R.id.relative_myissue);
        mRealtivelayout_myorder = findViewById(R.id.myorder);
        mRealtivelayout_mytuiguangbi = findViewById(R.id.mytuiguangbi);
        mTextview_text_huiyuan = findViewById(R.id.text_huiyuan);
        mRealtivelayout_myauthentication = findViewById(R.id.myauthentication);
        mRealtivelayout_huiyuan = findViewById(R.id.huiyuan);
        mButtonCash = findViewById(R.id.button_cash);
        mTextview_level.setText("lv.1");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
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
                    Glide.with(ShopCenterActivity.this).load(helpterInfoBean.getData().getList().getAvatar_url()).into(imageview_head);
                    mTextview_name.setText(helpterInfoBean.getData().getList().getHelper_name());
//                    mTextview_level.setText(helpterInfoBean.getData().getList().geth());
                    mTextview_text_tui_count.setText(helpterInfoBean.getData().getList().getSpread_b()+"个");
                    if(helpterInfoBean.getData().getList().getMember_enddate()!=null){
                        mTextview_text_huiyuan.setText(helpterInfoBean.getData().getList().getMember_enddate().substring(0,10)+"到期");
                    }
                    mTextview_namenext.setVisibility(View.GONE);
                    mTextview_money.setText(helpterInfoBean.getData().getList().getCommission()+"");

                } else {
                    shopcenterBean = new Gson().fromJson(respose, ShopcenterBean.class);
                    Glide.with(ShopCenterActivity.this).load(shopcenterBean.getData().getList().getAvatar_url()).into(imageview_head);
                    mTextview_name.setText(shopcenterBean.getData().getList().getBusiness_name());
                    mTextview_namenext.setText(shopcenterBean.getData().getList().getBusiness_address()+" | ");
                    mTextview_money.setText(shopcenterBean.getData().getList().getCommission()+"");
                    mTextview_text_tui_count.setText(shopcenterBean.getData().getList().getSpread_b()+"个");
                    mTextview_text_huiyuan.setText(shopcenterBean.getData().getList().getMember_enddate().substring(0,10)+"到期");
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
