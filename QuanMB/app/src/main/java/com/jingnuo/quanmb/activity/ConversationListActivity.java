package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.NewMessage_Bean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

public class ConversationListActivity extends FragmentActivity {


    String  newmessageTYpe="";

    //控件
    RelativeLayout mRelativelayout_bargain;
    RelativeLayout mRelativelayout_systemmessage;
    RelativeLayout mRelativelayout_dealmessage;
    RelativeLayout mRelativelayout_tuijianrenwu;

    ImageView iv_back;

//    TextView mTextview_systemmessage;
//    TextView mTextview_bargainmessage;
//    TextView mTextview_jiaoyimeaage;
//    TextView mTextview_tuijianrenwu;

    static ImageView mImageView_dot1;
    static ImageView mImageView_dot2;
    static ImageView mImageView_dot3;
    static ImageView mImageView_dot4;


    //数据
    Map map_getnewmessage;

    NewMessage_Bean newMessage_bean;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_list);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);//状态栏颜色
        initview();
        initdata();
        setdata();
        initListener();
        ConversationListFragment listFragment = (ConversationListFragment) ConversationListFragment.instantiate(this, ConversationListFragment.class.getName());
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")
                .build();
        listFragment.setUri(uri);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //将融云的Fragment界面加入到我们的页面。
        transaction.add(R.id.conversationlist, listFragment);
        transaction.commitAllowingStateLoss();
        //为了更加直观，服务器建立连接后进入此界面，直接调用如下代码，执行单人聊天，第二个参数代表对方用户ID，第三个参数代表聊天窗口标题，为了方便测试聊天，需要两个手机测试，所以登陆第一个token的用户与第二个用户"chao"建立聊天，在运行第二个手机之前，记得改"chao"的token登录，然后聊天这里改为第一个的ID"text"。
//        RongIM.getInstance().startPrivateChat(this, "chao", "聊天中");

//        RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
//            @Override
//            public void onSuccess(List<Conversation> conversations) {
////                for (int i = 0; i < conversations.size(); i++) {
////                    getListUserInfo(ConversationListActivity.this,conversations.get(i).getTargetId());
////                    setRongUserInfo(conversations.get(i).getTargetId());
////                }
//            }
//
//            @Override
//            public void onError(RongIMClient.ErrorCode errorCode) {
//
//            }
//        });
    }

    private void initListener() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRelativelayout_bargain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intend_bargain=new Intent(ConversationListActivity.this, BarginmessageListActivity.class);
                mImageView_dot2.setVisibility(View.GONE);
                startActivity(intend_bargain);
            }
        });
        mRelativelayout_systemmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_system=new Intent(ConversationListActivity.this, SystemMessageActivity.class);
                mImageView_dot1.setVisibility(View.GONE);
                startActivity(intent_system);
            }
        });
        mRelativelayout_dealmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_deal=new Intent(ConversationListActivity.this, DealActivity.class);
                mImageView_dot3.setVisibility(View.GONE);
                startActivity(intent_deal);
            }
        });
        mRelativelayout_tuijianrenwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_tuijian=new Intent(ConversationListActivity.this, TuijianrenwuActivity.class);
                mImageView_dot4.setVisibility(View.GONE);
                startActivity(intent_tuijian);
            }
        });
    }

    private void setdata() {
        map_getnewmessage=new HashMap();
        map_getnewmessage.put("receive_client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_getnewmessage.put("user_token",Staticdata.static_userBean.getData().getUser_token());
        request();
    }

    private void initdata() {
        newmessageTYpe=getIntent().getStringExtra("newmessageTYpe");
        setDot(newmessageTYpe);
        if(Staticdata.static_userBean.getData().getAppuser().getRole().equals("0")){
            mRelativelayout_tuijianrenwu.setVisibility(View.GONE);
        }else {
            mRelativelayout_tuijianrenwu.setVisibility(View.VISIBLE);
        }
    }

    private void initview() {

        mRelativelayout_bargain=findViewById(R.id.relativelayout_Kanprice);
        mRelativelayout_systemmessage=findViewById(R.id.relativelayout_systemnotice);
        mRelativelayout_dealmessage=findViewById(R.id.relativelayout_Jiaoyinotice);
        mRelativelayout_tuijianrenwu=findViewById(R.id.relativelayout_tuijianrenwu);
        iv_back=findViewById(R.id.iv_back);
//        mTextview_systemmessage=findViewById(R.id.text_systemnotice);
//        mTextview_bargainmessage=findViewById(R.id.text_moneynotice);
//        mTextview_jiaoyimeaage=findViewById(R.id.text_jiaoyitixing);
//        mTextview_tuijianrenwu=findViewById(R.id.text_tujianrenwu);
        mImageView_dot1=findViewById(R.id.image_reddot1);
        mImageView_dot2=findViewById(R.id.image_reddot2);
        mImageView_dot3=findViewById(R.id.image_reddot3);
        mImageView_dot4=findViewById(R.id.image_reddot4);
        
    }
    public   void setDot(String num){
        LogUtils.LOG("ceshi",num+"","推送3");
        switch (num){
            case "type1":
                mImageView_dot1.setVisibility(View.VISIBLE);
                break;
            case "type2":
                mImageView_dot2.setVisibility(View.VISIBLE);
                break;
            case "type3":
                mImageView_dot3.setVisibility(View.VISIBLE);
                break;
            case "type4":
                mImageView_dot4.setVisibility(View.VISIBLE);
                break;
        }
    }
    void request(){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                newMessage_bean=new Gson().fromJson(respose,NewMessage_Bean.class);
//                mTextview_systemmessage.setText(newMessage_bean.getData().get(0).getContent());
//                mTextview_bargainmessage.setText(newMessage_bean.getData().get(1).getContent());
//                mTextview_jiaoyimeaage.setText(newMessage_bean.getData().get(2).getContent());
//                mTextview_tuijianrenwu.setText(newMessage_bean.getData().get(3).getContent());
            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl_hu+Urls.getNewmessage,ConversationListActivity.this,1,map_getnewmessage);

    }

    //设置容云用户信息
    private void setRongUserInfo(final String targetid) {
        if (RongIM.getInstance()!=null){
            RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                @Override
                public UserInfo getUserInfo(String s) {
                    if(targetid.equals(s)){
                        return new UserInfo(targetid,"郑州灯饰借", Uri.parse("http://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/avatar/ba031a18-c3c0-4e7d-8a8f-2b94c12489391534840739188.png"));
                    }
                    return null;
                }
            },true);
        }


    }
    @Override
    public void onResume() {
        super.onResume();
        if(Staticdata.isLogin){
            map_getnewmessage.put("receive_client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
            map_getnewmessage.put("user_token",Staticdata.static_userBean.getData().getUser_token());
            request();
        }
    }
}
