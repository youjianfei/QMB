package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jingnuo.quanmb.Interface.InterfacePopwindow_SkillType;
import com.jingnuo.quanmb.class_.Popwindow_SkillType;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.ToastUtils;

public class IssueTaskActivity extends BaseActivityother {

    //控件
    TextView mTextview_taskAddress;//地图返回地点
    TextView mTextview_choose;
    TextView mTextview_time;
    EditText mEditview_addressDetail;//详细地址
    EditText mEditview_title;
    EditText mEditview_taskdetails;
    EditText mEditview_taskmoney;
    Button mButton_sub;


    //对象
    Popwindow_SkillType mPopwindow_skilltype;


    //数据
    String task_name = "";
    String task_description = "";
    String task_type = "";
    String task_StartDate = "";
    String task_EndDate = "";
    String client_no = "";
    String release_address = "";
    String commission = "";
    String task_Img_id = "";
    String detailed_address = "";
    String task_Status_code = "";


    @Override
    public int setLayoutResID() {
        return R.layout.activity_issue_task;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTextview_taskAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent_map = new Intent(IssueTaskActivity.this, LocationMapActivity.class);
                startActivityForResult(mIntent_map, 2018418);
            }
        });
        mTextview_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopwindow_skilltype = new Popwindow_SkillType(IssueTaskActivity.this, new InterfacePopwindow_SkillType() {
                    @Override
                    public void onSuccesses(String type, int id) {
                        mTextview_choose.setText(type);
                        task_type=id+"";
                    }
                });
                mPopwindow_skilltype.showPopwindow();
            }
        });

        mButton_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    protected void initView() {
        mTextview_taskAddress = findViewById(R.id.text_chooseaddress);
        mTextview_choose = findViewById(R.id.text_chooce);
        mEditview_title = findViewById(R.id.edit_tasktitle);
        mTextview_time = findViewById(R.id.edit_tasktime);
        mEditview_addressDetail = findViewById(R.id.edit_detailaddress);
        mEditview_taskdetails = findViewById(R.id.edit_detailtask);
        mEditview_taskmoney = findViewById(R.id.edit_charges);
        mButton_sub = findViewById(R.id.button_submitsave);

    }
    boolean initmap(){
        task_name=mEditview_title.getText()+"";
        if(task_name.equals("")){
            ToastUtils.showToast(this,"请填写任务标题");
            return false;
        }
        task_description=mEditview_taskdetails.getText()+"";
        if(task_description.equals("")){
            ToastUtils.showToast(this,"请填写任务说明");
            return false;
        }
        task_type=mTextview_choose.getText()+"";
        if(task_type.equals("请选择")){
            return  false;
        }
        task_EndDate=mTextview_time.getText()+"";
        if(task_StartDate.equals("请选择服务预约时间")){
            return false;
        }
        release_address="郑州";//TODO
        commission=mEditview_taskmoney.getText()+"";
        if(commission.equals("")){
            ToastUtils.showToast(this,"请填写任务佣金");
            return  false;
        }
        detailed_address=mEditview_addressDetail.getText()+"";
        if (mEditview_addressDetail.equals("")){
            ToastUtils.showToast(this,"请填写详细地址");
            return false;
        }


        return true;
    }

















    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2018418 && resultCode == 2018418) {
            String address = data.getStringExtra("address");
            mTextview_taskAddress.setText(address);
        }

    }
}
