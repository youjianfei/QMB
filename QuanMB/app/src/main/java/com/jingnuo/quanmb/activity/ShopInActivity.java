package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.jingnuo.quanmb.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ShopInActivity extends BaseActivityother {
    //布局
    private LinearLayout mLineatlayout_addview;

    //控件
    private Button mButton_submit;
    EditText mEditview_name;
    EditText mEditview_phonenumber;
    EditText textview_shopaddress;

    //数据
    String name="";
    String phonenumber="";
    String shopName="";//

    Map map_submit;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_shop_in;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        map_submit=new HashMap();
        //添加头部3个步骤的图片
        ImageView imageview_shopin=new ImageView(this);
        imageview_shopin.setBackgroundResource(R.mipmap.shopin_1);
        LinearLayout.LayoutParams mLayoutparams=new LinearLayout.LayoutParams(Staticdata.ScreenWidth, (int) (Staticdata.ScreenWidth*0.27));
        imageview_shopin.setLayoutParams(mLayoutparams);
        mLineatlayout_addview.addView(imageview_shopin);
    }

    @Override
    protected void initListener() {
        mButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(initmap()){
                    new Volley_Utils(new Interface_volley_respose() {
                        @Override
                        public void onSuccesses(String respose) {
                            int status = 0;
                            String msg = "";
                            String imageID="";
                            try {
                                JSONObject object = new JSONObject(respose);
                                status = (Integer) object.get("code");//登录状态
                                msg = (String) object.get("message");//登录返回信息

                                if(status==1){
                                    ToastUtils.showToast(ShopInActivity.this,msg);
//                                    Intent  intent_shopIn_next=new Intent(ShopInActivity.this,ShopInNextActivity.class);
//                                    startActivity(intent_shopIn_next);
                                    Intent intent_submit=new Intent(ShopInActivity.this,SubmitSuccessActivity.class);
                                    intent_submit.putExtra("state","1");
                                    startActivity(intent_submit);
                                    finish();
                                }else {
                                    ToastUtils.showToast(ShopInActivity.this,"提交申请失败，请稍候重试");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(int error) {

                        }
                    }).postHttp(Urls.Baseurl+Urls.shopIn,ShopInActivity.this,1,map_submit);
                }

            }
        });


    }

    @Override
    protected void initView() {
        mLineatlayout_addview=findViewById(R.id.linrarlayout_shop);
        mButton_submit=findViewById(R.id.button_submit);
        mEditview_name=findViewById(R.id.edit_shopname);
        mEditview_phonenumber=findViewById(R.id.edit_shopphonenumber);
        textview_shopaddress=findViewById(R.id.textview_shopaddress);
    }
    boolean initmap(){
        name=mEditview_name.getText()+"";
        if(name.equals("")){
            ToastUtils.showToast(this,"请输入姓名");
        return false;
        }
        phonenumber=mEditview_phonenumber.getText()+"";
        if(phonenumber.equals("")){
            ToastUtils.showToast(this,"请输入联系电话");
            return false;
        }
        shopName=mEditview_phonenumber.getText()+"";
        if(phonenumber.equals("")){
            ToastUtils.showToast(this,"请输入商铺名称");
            return false;
        }
        map_submit.put("name",name);
        map_submit.put("mobile_no",phonenumber);
        map_submit.put("business_name",shopName);
        map_submit.put("user_token",Staticdata.static_userBean.getData().getUser_token());
        return true;
    }
}
