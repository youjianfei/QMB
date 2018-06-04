package com.jingnuo.quanmb.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.class_.PinyinComparator;
import com.jingnuo.quanmb.customview.SideBar;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.AllCityBean;
import com.jingnuo.quanmb.entityclass.LocationAddressListBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.PinyinUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationaddressActivity extends BaseActivityother {

    //控件
    ListView mListview;
    private SideBar mSideBar;
    private TextView dialog;

    List<AllCityBean.DataBean> addressName;
    List<LocationAddressListBean > mdata;

    LocationAddressListBean locationAddressListBean;
    List_LocationAddressrAdapter listLocationAddressrAdapter;

    AllCityBean allCityBean;


    @Override
    public int setLayoutResID() {
        return R.layout.activity_locationaddress;
    }

    @Override
    protected void setData() {
        requestAllcity();

    }

    @Override
    protected void initData() {
        addressName=new ArrayList<>();
        mdata=new ArrayList<>();
        listLocationAddressrAdapter=new List_LocationAddressrAdapter(mdata,this);
        mListview.setAdapter(listLocationAddressrAdapter);
    }
    void  requestAllcity(){
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                allCityBean=new Gson().fromJson(respose,AllCityBean.class);
                addressName.addAll(allCityBean.getData());
                for(int i=0;i<addressName.size();i++){
                    String pinyin = PinyinUtils.getPingYin(addressName.get(i).getName());
                    String Fpinyin = pinyin.substring(0, 1).toUpperCase();
                    locationAddressListBean = new LocationAddressListBean();
                    locationAddressListBean.setName(addressName.get(i).getName());
                    locationAddressListBean.setPinYin(pinyin);
                    locationAddressListBean.setIsselect(false);
                    if (Fpinyin.matches("[A-Z]")) {
                        locationAddressListBean.setFirstPinYin(Fpinyin);
                    } else {
                        locationAddressListBean.setFirstPinYin("#");
                    }

                    mdata.add(locationAddressListBean);
                }
                Collections.sort(mdata, new PinyinComparator());//实现排序
                listLocationAddressrAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int error) {

            }
        }).Http(Urls.Baseurl_hu+Urls.getallCity+ Staticdata.static_userBean.getData().getUser_token(),LocationaddressActivity.this,0);

    }

    @Override
    protected void initListener() {
        dialog.getBackground().setAlpha(100);
        mSideBar.setTextView(dialog);
        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // TODO Auto-generated method stub
                int position = listLocationAddressrAdapter.getPositionForSelection(s.charAt(0));

                if (position != -1) {
                    mListview .setSelection(position);
                }
            }
        });
    }

    @Override
    protected void initView() {
        mListview=findViewById(R.id.mylistview);
        mSideBar=findViewById(R.id.sidebar);
        dialog=findViewById(R.id.dialog);
    }


    public class List_LocationAddressrAdapter extends com.jingnuo.quanmb.Adapter.BaseAdapter {
        private Context context;
        private List<LocationAddressListBean> addresses;
        private LayoutInflater inflater;

        public List_LocationAddressrAdapter(List<LocationAddressListBean> addresses, Context mContext) {
            super(addresses, mContext);
            this.context = mContext;
            this.addresses = addresses;
            this.inflater = LayoutInflater.from(context);

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewholder = null;
            final LocationAddressListBean address = addresses.get(position);
            if (convertView == null) {
                viewholder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_locationaddress, null, false);
                viewholder.iv_line =  convertView.findViewById(R.id.view_lv_item_line);
                viewholder.tv_tag =  convertView.findViewById(R.id.tv_lv_item_tag);
                viewholder.tv_name =  convertView.findViewById(R.id.tv_lv_item_name);
                convertView.setTag(viewholder);
            } else {
                viewholder = (ViewHolder) convertView.getTag();
            }
            int selection = address.getFirstPinYin().charAt(0);
            int positionForSelection = getPositionForSelection(selection);
            if (position == positionForSelection) {
                viewholder.tv_tag.setVisibility(View.VISIBLE);
                viewholder.iv_line.setVisibility(View.INVISIBLE);
                viewholder.tv_tag.setText(address.getFirstPinYin());
            } else {
                viewholder.tv_tag.setVisibility(View.GONE);
                viewholder.iv_line.setVisibility(View.VISIBLE);

            }
            viewholder.tv_name.setText(address.getName());

            return convertView;
        }

        public int getPositionForSelection(int selection) {
            for (int i = 0; i < addresses.size(); i++) {
                String Fpinyin = addresses.get(i).getFirstPinYin();
                char first = Fpinyin.toUpperCase().charAt(0);
                if (first == selection) {
                    return i;
                }
            }
            return -1;
        }

        class ViewHolder {
            TextView tv_tag;
            TextView tv_name;
            TextView tv_number;
            ImageView iv_line;
        }
    }
}
