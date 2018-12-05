package com.jingnuo.quanmb.fargment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingnuo.quanmb.Adapter.BaseAdapter;
import com.jingnuo.quanmb.Interface.Interence_complteTask;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.activity.IssueTaskNextActivity;
import com.jingnuo.quanmb.activity.MainActivity;
import com.jingnuo.quanmb.activity.MytaskDetailActivity;
import com.jingnuo.quanmb.activity.SkillDetailActivity;
import com.jingnuo.quanmb.customview.MyGridView;
import com.jingnuo.quanmb.customview.SimpleRatingBar;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.Matchshoplistbean;
import com.jingnuo.quanmb.popwinow.Popwindow_Tip;
import com.jingnuo.quanmb.utils.SizeUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;
import com.master.permissionhelper.PermissionHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

import static io.rong.imlib.model.Conversation.ConversationType.PRIVATE;

@SuppressLint("ValidFragment")
public class Fragment_shopdetail extends Fragment{
    View  rootview;
    TextView text_name;
    TextView text_type;
    TextView text_orders;
    ImageView text_vip;
    SimpleRatingBar simpleRatingBar;
    CircleImageView image_head;
    MyGridView gridview_userthink;

    //对象
    Adapter_userthing adapter_userthing;
    List <String>  mData;


    String task_id="";



    //数据
    Matchshoplistbean.DataBean.MatchingBean matchingBean;
    @SuppressLint("ValidFragment")
    public Fragment_shopdetail(Matchshoplistbean.DataBean.MatchingBean matchingBean,String task_id) {
        this.matchingBean = matchingBean;
        this.task_id = task_id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_shopdetails, container, false);
        initview();
        setdata();
        initlistenner();


        return rootview;
    }

    private void initlistenner() {

    }

    private void setdata() {
        text_name.setText(matchingBean.getBusiness_name());
        text_type.setText("主营："+matchingBean.getSpecialty_name());
        Glide.with(getActivity()).load(matchingBean.getHeadUrl()).into(image_head);
        if(!matchingBean.getMemberImgUrl().equals("")){
            text_vip.setVisibility(View.VISIBLE);
            Glide.with(getActivity()).load(matchingBean.getMemberImgUrl()).into(text_vip);

        }else {
            text_vip.setVisibility(View.INVISIBLE);
        }
        text_orders.setText(matchingBean.getOverCount()+"单");
        setstar((float) matchingBean.getEvaluation_star());
        mData=new ArrayList<>();
        mData.addAll(matchingBean.getEvaluate());
        adapter_userthing=new Adapter_userthing(mData,getContext());
        gridview_userthink.setAdapter(adapter_userthing);
    }

    private void initview() {
        text_name=rootview.findViewById(R.id.text_name);
        text_type=rootview.findViewById(R.id.text_main);
        text_orders=rootview.findViewById(R.id.text_orders);
        text_vip=rootview.findViewById(R.id.text_vip);
        simpleRatingBar=rootview.findViewById(R.id.SimpleRatingBar);
        image_head=rootview.findViewById(R.id.image_head);
        gridview_userthink=rootview.findViewById(R.id.gridview_userthink);


    }
    void setstar(float count) {
        simpleRatingBar.setNumberOfStars(5);
        simpleRatingBar.setFillColor(getResources().getColor(R.color.yellow_jianbian_start));
        simpleRatingBar.setStarBackgroundColor(getResources().getColor(R.color.gray_background));
        simpleRatingBar.setStepSize((float) 0.1);
        simpleRatingBar.setRating(count);
        simpleRatingBar.setDrawBorderEnabled(false);
        simpleRatingBar.setStarsSeparation(1);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    class Adapter_userthing extends BaseAdapter {
        List<String> mData;
        Context mContext;
        LayoutInflater mInflater;

        public Adapter_userthing(List mDatas, Context mContext) {
            super(mDatas, mContext);
            this.mContext=mContext;
            this.mData=mDatas;
            mInflater=LayoutInflater.from(mContext);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Viewholder  viewholder=null;
            if(convertView==null){
                viewholder=new Viewholder();
                convertView=mInflater.inflate(R.layout.item_text4,null,false);
                viewholder.mtextview_choose=convertView.findViewById(R.id.text_text);
                convertView.setTag(viewholder);
            }else {
                viewholder= (Viewholder) convertView.getTag();
            }
            viewholder.mtextview_choose.setText(mData.get(position));

            return convertView;
        }
        class Viewholder {
            TextView mtextview_choose;
        }
    }
}
