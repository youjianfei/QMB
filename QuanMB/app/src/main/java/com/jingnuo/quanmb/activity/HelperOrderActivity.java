package com.jingnuo.quanmb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.Popwindow_complatetask;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.Utils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import de.hdodenhof.circleimageview.CircleImageView;

public class HelperOrderActivity extends BaseActivityother {

    CircleImageView mImageview_head;
    TextView mTextview_state;//状态
    TextView mTextview_titile;//标题
    TextView mTextview_money;//佣金
    TextView mTextview_time;//发布时间
    TextView mTextview_peoplename;//雇主
    TextView mTextview_taskDetail;//任务描述
    TextView mTextview_resttime;//剩余时间
    TextView mTextview_address;//地点
    TextView mTextview_phonenumber;//客户电话

    Button mButton_queren;

    //对象
    Popwindow_complatetask popwindow_complatetask;







    @Override
    public int setLayoutResID() {
        return R.layout.activity_helper_order;
    }

    @Override
    protected void setData() {
        popwindow_complatetask=new Popwindow_complatetask(this, new Interence_complteTask() {
            @Override
            public void onResult(boolean result) {
                if(result){
//                    new  Volley_Utils(new Interface_volley_respose() {
//                        @Override
//                        public void onSuccesses(String respose) {
//
//                        }
//
//                        @Override
//                        public void onError(int error) {
//
//                        }
//                    }).Http(Urls.Baseurl_cui+Urls.completetask+Staticdata.static_userBean.getData().getUser_token()+"&order_no="+);

                }

            }
        });

    }

    @Override
    protected void initData() {
        if(Staticdata.queRenHelp_bean!=null){//todo   确认帮助接口返回信息不全
            Glide.with(this).load(Staticdata.queRenHelp_bean.getDate().getHeadUrl()).into(mImageview_head);
            mTextview_titile.setText(Staticdata.queRenHelp_bean.getDate().getTask_name());
            mTextview_state.setText(Staticdata.queRenHelp_bean.getDate().getStatus_name());
            mTextview_money.setText("佣金："+Staticdata.queRenHelp_bean.getDate().getOrder_Amount()+"元");
            mTextview_time.setText(Staticdata.queRenHelp_bean.getDate().getCreateDate());

            long now = Long.parseLong(Utils.getTime(Utils.getTimeString()));//系统当前时间
            long ago = Long.parseLong(Utils.getTime(Staticdata.queRenHelp_bean.getDate().getOrder_EndDate()));//任务过期时间
            String time = Utils.getDistanceTime(ago, now);//算出的差值
            mTextview_resttime.setText(time);

            mTextview_time.setText("发布时间："+Staticdata.queRenHelp_bean.getDate().getCreateDate());
            mTextview_peoplename.setText(Staticdata.queRenHelp_bean.getDate().getNick_name());
            mTextview_taskDetail.setText(Staticdata.queRenHelp_bean.getDate().getTask_description());
            mTextview_address.setText(Staticdata.queRenHelp_bean.getDate().getDetailed_address());
            mTextview_phonenumber.setText(Staticdata.queRenHelp_bean.getDate().getMobile_no());
        }

    }

    @Override
    protected void initListener() {
        mButton_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popwindow_complatetask.showPopwindow();
            }
        });

    }

    @Override
    protected void initView() {
        mImageview_head=findViewById(R.id.image_task);
        mTextview_state=findViewById(R.id.text_taskstate);
        mTextview_titile=findViewById(R.id.text_tasktitle);
        mTextview_money=findViewById(R.id.text_taskmoney_);
        mTextview_time=findViewById(R.id.text_tasktime);
        mTextview_peoplename=findViewById(R.id.text_name);
        mTextview_taskDetail=findViewById(R.id.text_taskdetail);
        mTextview_resttime=findViewById(R.id.text_time);
        mTextview_address=findViewById(R.id.text_address);
        mTextview_phonenumber=findViewById(R.id.text_number);
        mButton_queren=findViewById(R.id.button_bargain);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Staticdata.queRenHelp_bean=null;
    }
}
