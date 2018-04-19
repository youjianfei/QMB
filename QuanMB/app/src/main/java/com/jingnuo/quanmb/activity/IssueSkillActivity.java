package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jingnuo.quanmb.Interface.InterfacePopwindow_SkillType;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.Popwindow_SkillType;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class IssueSkillActivity extends BaseActivityother {


    //控件
    TextView mTextview_chooce;
    TextView mTextview_address;
    EditText mEditview_title;
    EditText mEditview_addressdetail;
    EditText mEditview_skilldetail;
    EditText mEditview_name;
    EditText mEditview_phonenumber;

    Button mButton_submit;


    //对象
    Popwindow_SkillType mPopwindow_skilltype;

    //数据
    String  specialty_id ="";//专业类形
    String tittle="";//
    String description="";//
    String release_address="郑州";//发布地点// TODO: 地点实现
    String detail_address="";//详细地点
    String img_id="daiwancheng";//图片                   // TODO: 图片
    String contacts="";//联系人
    String mobile_no="";//电话


    Map map_issueSkill;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_issue_skill;
    }

    @Override
    protected void setData() {


    }

    @Override
    protected void initData() {
        map_issueSkill=new HashMap();

    }

    @Override
    protected void initListener() {
        mTextview_chooce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopwindow_skilltype=new Popwindow_SkillType(IssueSkillActivity.this, new InterfacePopwindow_SkillType() {
                    @Override
                    public void onSuccesses(String type, int id) {
                        mTextview_chooce.setText(type);
                        specialty_id=id+"";
                    }
                });
                mPopwindow_skilltype.showPopwindow();
            }
        });
        mButton_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean Nonull= checknull();//判空方法
                if(Nonull){
                    request(map_issueSkill);
                }

            }
        });

    }

    @Override
    protected void initView() {
        mTextview_chooce=findViewById(R.id.text_chooce);
        mTextview_address=findViewById(R.id.textview_skilladdress);
        mEditview_title=findViewById(R.id.edit_skilltitle);
        mEditview_addressdetail=findViewById(R.id.edit_skilldetailaddress);
        mEditview_skilldetail=findViewById(R.id.edit_detailskill);
        mEditview_name=findViewById(R.id.edit_skillpeoplename);
        mEditview_phonenumber=findViewById(R.id.edit_skillpeoplename);
        mButton_submit=findViewById(R.id.button_submit);
    }
    boolean checknull(){
        if(mTextview_chooce.getText().equals("请选择")){
            ToastUtils.showToast(this,"请选择技能类型");
            return false;
        }
        tittle=mEditview_title.getText()+"";
        if(tittle.equals("")){
            ToastUtils.showToast(this,"请填写标题");
            return false;
        }
        description=mEditview_skilldetail.getText()+"";
        if(description.equals("")){
            ToastUtils.showToast(this,"请填写详细描述");
            return false;
        }
        detail_address=mEditview_addressdetail.getText()+"";
        if(detail_address.equals("")){
            ToastUtils.showToast(this,"请填写详细地址");
            return false;
        }
        contacts=mEditview_name.getText()+"";
        if(detail_address.equals("")){
            ToastUtils.showToast(this,"请填写联系人");
            return false;
        }
        mobile_no=mEditview_phonenumber.getText()+"";
        if (mobile_no.equals("")){
            ToastUtils.showToast(this,"请填写手机号码");
            return false;
        }
        map_issueSkill.put("specialty_id",specialty_id);
        map_issueSkill.put("title",tittle);
        map_issueSkill.put("description",description);
        map_issueSkill.put("release_address",release_address);
        map_issueSkill.put("detail_address",detail_address);
        map_issueSkill.put("img_id","1");
        map_issueSkill.put("contacts",contacts);
        map_issueSkill.put("mobile_no",mobile_no);
        map_issueSkill.put("client_no","90000000007");//客户号
        map_issueSkill.put("business_no","1");//商户号
        map_issueSkill.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        map_issueSkill.put("service_area","郑州");
        //todo 从登录信息中获得商户号  客户号  post
        return true;
    }
    void request (Map map){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("status");//登录状态
                    msg = (String) object.get("message");//登录返回信息
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status==1){
                    ToastUtils.showToast(IssueSkillActivity.this,msg);
                    finish();
                }else {
                    ToastUtils.showToast(IssueSkillActivity.this,msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl+Urls.IssueSkill,this,1,map);
    }

}
