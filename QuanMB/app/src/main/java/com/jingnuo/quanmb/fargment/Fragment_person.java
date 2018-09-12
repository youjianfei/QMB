package com.jingnuo.quanmb.fargment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.Adapter_menu;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.activity.CashoutActivity;
import com.jingnuo.quanmb.activity.DatailAddressActivity;
import com.jingnuo.quanmb.activity.LoginActivity;
import com.jingnuo.quanmb.activity.MyOrderActivity;
import com.jingnuo.quanmb.activity.MySkillCollectActivity;
import com.jingnuo.quanmb.activity.PersonInfoActivity;
import com.jingnuo.quanmb.activity.RechargeActivity;
import com.jingnuo.quanmb.activity.SettingActivity;
import com.jingnuo.quanmb.activity.ShopCenterActivity;
import com.jingnuo.quanmb.activity.ShopInActivity;
import com.jingnuo.quanmb.activity.SubmitSuccessActivity;
import com.jingnuo.quanmb.activity.WalletActivity;
import com.jingnuo.quanmb.activity.ZixunKefuWebActivity;
import com.jingnuo.quanmb.class_.Chengweibangshou;
import com.jingnuo.quanmb.customview.MyListView;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.MenuBean;
import com.jingnuo.quanmb.entityclass.UserBean;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.SharedPreferencesUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.jingnuo.quanmb.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Fragment_person extends Fragment implements View.OnClickListener {
    View rootview;
    private UMShareAPI mShareAPI;//第三方登录登录

    //控件
//    RelativeLayout mTextview_shopcenter;
//    RelativeLayout mTextview_address;
//    RelativeLayout mTextview_banghsou;
//    RelativeLayout mTextview_myorder;
//    RelativeLayout mTextview_mycollect;
//    RelativeLayout mTextview_aboutus;
//    RelativeLayout mTextview_logout;
    CircleImageView mCircleImage;
    ImageView mimage_chengwei;
    TextView mTextview_nickname;
    TextView mTextview_moneycount;
    TextView mTextview_chengwei;

    TextView mButton_rechange;//充值
    TextView mButton_cashout;//提现
    TextView mTextview_logout;//退出
//    LinearLayout mLearlayout_wallet;

    MyListView myGridview_menu;
    List<MenuBean> menuList;
    Adapter_menu mAdapter_menu;


    Chengweibangshou chengweibangshou;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_person, container, false);
        initview();
        setview();
        initdata();
        setdata();
        initlistener();

        return rootview;
    }

    private void initlistener() {
        mTextview_logout.setOnClickListener(this);
        mCircleImage.setOnClickListener(this);
        mButton_rechange.setOnClickListener(this);
        mButton_cashout.setOnClickListener(this);
        mTextview_moneycount.setOnClickListener(this);
        myGridview_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://我的发布
                        Intent intent_myorder = new Intent(getActivity(), MyOrderActivity.class);
                        getActivity().startActivity(intent_myorder);
                        break;
//                    case 1://常用联系人
//                        Intent intent_datiladdress = new Intent(getActivity(), DatailAddressActivity.class);
//                        getActivity().startActivity(intent_datiladdress);
//                        break;
                    case 1://我是帮手
                        chengweibangshou.chengweibangshou();
                        break;
                    case 2://商户中心
                        LogUtils.LOG("ceshi", Urls.Baseurl + Urls.shopIn_state + Staticdata.static_userBean.getData().getUser_token(), "检测商户审核状态接口");
                        if (Staticdata.static_userBean.getData().getAppuser().getRole().contains("2")) {
                            LogUtils.LOG("ceshi", "检测商户审核状态dfgdfsgfd", "检测商户审核状态");

                            Intent intent_shopcenter = new Intent(getActivity(), ShopCenterActivity.class);
                            intent_shopcenter.putExtra("type", 2);//2  商户
                            getActivity().startActivity(intent_shopcenter);

                        } else {
                            LogUtils.LOG("ceshi", "检测商户审核状态" + Staticdata.static_userBean.getData().getAppuser().getRole(), "检测商户审核状态");
                            new Volley_Utils(new Interface_volley_respose() {
                                @Override
                                public void onSuccesses(String respose) {

                                    int status = 0;
                                    String msg = "";
                                    String state = "";
                                    try {
                                        JSONObject object = new JSONObject(respose);
                                        status = (Integer) object.get("code");//
                                        msg = (String) object.get("message");//
                                        state = (String) object.get("status");//
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    if (state.equals("00")) {//审核通过
                                        Intent intent_shopcenter = new Intent(getActivity(), ShopCenterActivity.class);
                                        intent_shopcenter.putExtra("type", 2);//2  商户
                                        getActivity().startActivity(intent_shopcenter);
                                    } else if (state.equals("01")) {//没提交
                                        Intent intent_shopin = new Intent(getActivity(), ShopInActivity.class);
                                        getActivity().startActivity(intent_shopin);

                                    } else if (state.equals("02")) {//正在审核
//                            Intent intent_shopinext=new Intent(getActivity(), ShopInNextActivity.class);
//                            getActivity().startActivity(intent_shopinext);
                                        Intent intent_submit = new Intent(getActivity(), SubmitSuccessActivity.class);
                                        intent_submit.putExtra("state", "2");
                                        startActivity(intent_submit);

                                    } else if (state.equals("03")) {//没提交审核
                                        Intent intent_shopin = new Intent(getActivity(), ShopInActivity.class);
                                        getActivity().startActivity(intent_shopin);
                                    }
                                }

                                @Override
                                public void onError(int error) {

                                }
                            }).Http(Urls.Baseurl + Urls.shopIn_state + Staticdata.static_userBean.getData().getUser_token(), getContext(), 0);

                        }
                        break;
                    case 3://我的收藏
                        Intent intent_collect = new Intent(getActivity(), MySkillCollectActivity.class);
                        startActivity(intent_collect);
                        break;
                    case 5://设置
                        Intent intent_aboutus = new Intent(getActivity(), SettingActivity.class);
                        startActivity(intent_aboutus);
                        break;
                    case 4://客服中心
                        Intent intent_kefuzhongxin = new Intent(getActivity(), ZixunKefuWebActivity.class);
                        startActivity(intent_kefuzhongxin);
                        break;

                }
            }
        });
    }


    private void setdata() {

    }

    private void initdata() {//初始化个人中心菜单
        chengweibangshou = new Chengweibangshou(getActivity());
        menuList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0://我的发布
                    MenuBean menuBean0 = new MenuBean();
                    menuBean0.setMenu_name("我的发布");
                    Bitmap bitmap0 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.myrelease);
                    menuBean0.setmBitmap(bitmap0);
                    menuList.add(menuBean0);
                    break;
//                case 1://常用联系人
//                    MenuBean menuBean1 = new MenuBean();
//                    menuBean1.setMenu_name("常用联系人");
//                    Bitmap bitmap1 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.lianxiren);
//                    menuBean1.setmBitmap(bitmap1);
//                    menuList.add(menuBean1);
//                    break;
                case 1://我是帮手
                    MenuBean menuBean2 = new MenuBean();
                    menuBean2.setMenu_name("我是帮手");
                    Bitmap bitmap2 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.people11);
                    menuBean2.setmBitmap(bitmap2);
                    menuList.add(menuBean2);
                    break;
                case 2://商户中心
                    MenuBean menuBean3 = new MenuBean();
                    menuBean3.setMenu_name("商户中心");
                    Bitmap bitmap3 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.shopiii);
                    menuBean3.setmBitmap(bitmap3);
                    menuList.add(menuBean3);
                    break;
                case 3://我的收藏
                    MenuBean menuBean4 = new MenuBean();
                    menuBean4.setMenu_name("我的收藏");
                    Bitmap bitmap4 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.shoucang11);
                    menuBean4.setmBitmap(bitmap4);
                    menuList.add(menuBean4);
                    break;
                case 5://设置
                    MenuBean menuBean5 = new MenuBean();
                    menuBean5.setMenu_name("设置");
                    Bitmap bitmap5 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.setttt);
                    menuBean5.setmBitmap(bitmap5);
                    menuList.add(menuBean5);
                    break;
                case 4://客服中心
                    MenuBean menuBean6 = new MenuBean();
                    menuBean6.setMenu_name("客服中心");
                    Bitmap bitmap6 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.kefuzhongxin);
                    menuBean6.setmBitmap(bitmap6);
                    menuList.add(menuBean6);
                    break;
            }
        }
        mAdapter_menu = new Adapter_menu(menuList, getActivity());
        myGridview_menu.setAdapter(mAdapter_menu);
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.LOG("ceshi", "onResume", "fragmentperson");
        if (Staticdata.isLogin) {
            requestInfo();
        }
    }

    void requestInfo() {
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                String state = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");//
                    msg = (String) object.get("msg");//
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (status == 1) {
                    LogUtils.LOG("ceshi", respose, "个人中心");
                    Staticdata.static_userBean = new Gson().fromJson(respose, UserBean.class);
                    mTextview_nickname.setText(Staticdata.static_userBean.getData().getAppuser().getNick_name());
                    mTextview_chengwei.setText(Staticdata.static_userBean.getData().getAppellation_name());
                    LogUtils.LOG("ceshi", Staticdata.static_userBean.getData().getImg_url(), "touxaing");
                    Glide.with(getActivity()).load(Staticdata.static_userBean.getData().getImg_url()).into(mCircleImage);
                    Glide.with(getActivity()).load(Staticdata.static_userBean.getData().getIconImgUrl()).into(mimage_chengwei);
                    mTextview_moneycount.setText(Staticdata.static_userBean.getData().getAppuser().getBalance() + "");
                }

            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl + Urls.personinfo + Staticdata.static_userBean.getData().getUser_token(), getActivity(), 0);
    }

    private void setview() {
//        requestInfo();
    }

    private void initview() {
        mTextview_moneycount = rootview.findViewById(R.id.textview_2);
        mCircleImage = rootview.findViewById(R.id.image_userpic);
        mTextview_nickname = rootview.findViewById(R.id.text_username);
        mTextview_chengwei = rootview.findViewById(R.id.textview_phonenumber);
        mButton_rechange = rootview.findViewById(R.id.button_recharge);
        mButton_cashout = rootview.findViewById(R.id.button_tixian);
        mimage_chengwei = rootview.findViewById(R.id.image_chengwei);
        mTextview_logout = rootview.findViewById(R.id.text_logout);
        myGridview_menu = rootview.findViewById(R.id.mygridview_menu);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_logout://退出
                logout();
                SharedPreferencesUtils.putString(getActivity(), "QMB", "password", "");
                Staticdata.isLogin = false;
                Staticdata.static_userBean.setData(null);//用户信息清空
                Intent intent_logout = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent_logout);
                getActivity().finish();
                break;
            case R.id.button_tixian://提现
                Intent intent_cash = new Intent(getActivity(), CashoutActivity.class);
                intent_cash.putExtra("money", Staticdata.static_userBean.getData().getAppuser().getBalance() + "");
                intent_cash.putExtra("TransferType", "1");
                startActivity(intent_cash);

                break;
            case R.id.button_recharge://充值
                Intent intent_recharge = new Intent(getActivity(), RechargeActivity.class);
                startActivity(intent_recharge);

                break;

            case R.id.image_userpic:
                Intent intent_personInfo = new Intent(getActivity(), PersonInfoActivity.class);
                getActivity().startActivity(intent_personInfo);
                break;
            case R.id.textview_2:
                Intent intent_wa = new Intent(getActivity(), WalletActivity.class);
                intent_wa.putExtra("money", Staticdata.static_userBean.getData().getAppuser().getBalance() + "");
                getActivity().startActivity(intent_wa);
                break;


        }

    }

    public void logout() {

        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                LogUtils.LOG("ceshi", respose, "退出登录");
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl + Urls.logout + Staticdata.static_userBean.getData().getUser_token(), getActivity(), 0);
        //登出注销微信授权
        mShareAPI = UMShareAPI.get(getActivity());
        mShareAPI.deleteOauth(getActivity(), SHARE_MEDIA.WEIXIN, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }


}
