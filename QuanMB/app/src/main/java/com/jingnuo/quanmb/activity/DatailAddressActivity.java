package com.jingnuo.quanmb.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingnuo.quanmb.Adapter.BaseAdapter;
import com.jingnuo.quanmb.Interface.Interface_volley_respose;
import com.jingnuo.quanmb.data.Staticdata;
import com.jingnuo.quanmb.data.Urls;
import com.jingnuo.quanmb.entityclass.LianxirenBean;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;
import com.jingnuo.quanmb.utils.Volley_Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatailAddressActivity extends BaseActivityother {
    //控件
    Button mButton_addnewaddress;
    ListView mListview_address;

    Adapter_DatlyAddressList mAdapter_datilyaddress;

    //数据
    List<LianxirenBean.DataBean.ListBean> mData_address;
    Map map_lianxiren;

    LianxirenBean lianxirenBean;


    String isLianxiren = "";


    @Override
    public int setLayoutResID() {
        return R.layout.activity_datail_address;
    }

    @Override
    protected void setData() {
        request();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        isLianxiren = intent.getStringExtra("lianxiren");
        mData_address = new ArrayList<>();
        mAdapter_datilyaddress = new Adapter_DatlyAddressList(mData_address, this);
        mListview_address.setAdapter(mAdapter_datilyaddress);
    }

    @Override
    protected void initListener() {
        mButton_addnewaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_addnewaddress = new Intent(DatailAddressActivity.this, AddAddressActivity.class);
                intent_addnewaddress.putExtra("type","xinzeng");
                startActivity(intent_addnewaddress);
            }
        });
        mListview_address.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isLianxiren != null && isLianxiren.equals("lianxiren")) {
                    Intent intent_lianxiren = new Intent();
                    intent_lianxiren.putExtra("name", mData_address.get(position).getName());
                    intent_lianxiren.putExtra("phonenumber", mData_address.get(position).getMobile_no());
                    intent_lianxiren.putExtra("sex", mData_address.get(position).getSex());
                    intent_lianxiren.putExtra("is_default", mData_address.get(position).getIs_default());
                    DatailAddressActivity.this.setResult(20180530, intent_lianxiren);
                    finish();
                }
            }
        });
    }

    @Override
    protected void initView() {
        mButton_addnewaddress = findViewById(R.id.button_addnewaddress);
        mListview_address = findViewById(R.id.list_datiladdress);

    }

    void request() {
        map_lianxiren = new HashMap();
        map_lianxiren.put("client_no", Staticdata.static_userBean.getData().getAppuser().getClient_no());
        map_lianxiren.put("user_token", Staticdata.static_userBean.getData().getUser_token());
        LogUtils.LOG("ceshi", Urls.Baseurl + Urls.myLianxiren, "联系人列表");
        LogUtils.LOG("ceshi", map_lianxiren.toString(), "联系人列表");
        new Volley_Utils(new Interface_volley_respose() {
            @Override
            public void onSuccesses(String respose) {
                int status = 0;
                String msg = "";
                try {
                    JSONObject object = new JSONObject(respose);
                    status = (Integer) object.get("code");
                    msg = (String) object.get("msg");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                lianxirenBean = new Gson().fromJson(respose, LianxirenBean.class);
                mData_address.clear();
                if (status == 1) {
                    mData_address.addAll(lianxirenBean.getData().getList());
                    mAdapter_datilyaddress.notifyDataSetChanged();
                } else {
                    mAdapter_datilyaddress.notifyDataSetChanged();
                    ToastUtils.showToast(DatailAddressActivity.this, msg);
                }

            }

            @Override
            public void onError(int error) {

            }
        }).postHttp(Urls.Baseurl + Urls.myLianxiren, this, 1, map_lianxiren);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        request();
    }


    class Adapter_DatlyAddressList extends BaseAdapter {
        private Context mContext;
        private List<LianxirenBean.DataBean.ListBean> mData;
        private LayoutInflater mInflater;

        public Adapter_DatlyAddressList(List mDatas, Context mContext) {
            super(mDatas, mContext);
            this.mData = mDatas;
            this.mContext = mContext;
            mInflater = LayoutInflater.from(mContext);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_datiladdress_list, null, false);
                holder.mText_name = convertView.findViewById(R.id.text_name);
                holder.mText_sex = convertView.findViewById(R.id.text_sex);
                holder.mText_number = convertView.findViewById(R.id.text_phonenumber);
                holder.mText_delete = convertView.findViewById(R.id.textview_delete);
                holder.mTextview_edit = convertView.findViewById(R.id.textview_edit);
                holder.mLinearlayout_setmoren = convertView.findViewById(R.id.linearlayout_setmoren);
                holder.mImageview_chose=convertView.findViewById(R.id.image_choose);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.mText_name.setText(mData.get(position).getName());
            if (mData.get(position).getSex() == 0) {
                holder.mText_sex.setText("男");
            } else {
                holder.mText_sex.setText("女");
            }
            holder.mText_number.setText(mData.get(position).getMobile_no());
            holder.mImageview_chose.setSelected(mData.get(position).getIs_default().equals("Y")? true:false);

            holder.mText_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Volley_Utils(new Interface_volley_respose() {
                        @Override
                        public void onSuccesses(String respose) {
                            LogUtils.LOG("ceshi", respose, "删除联系人");
                            int status = 0;
                            String msg = "";
                            try {
                                JSONObject object = new JSONObject(respose);
                                status = (Integer) object.get("code");
                                msg = (String) object.get("msg");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            ToastUtils.showToast(mContext, msg);
                            request();

                        }

                        @Override
                        public void onError(int error) {

                        }
                    }).Http(Urls.Baseurl + Urls.deleteLianxiren +
                            Staticdata.static_userBean.getData().getUser_token() + "&id=" +
                            mData.get(position).getId(), mContext, 0);

                }
            });
            holder.mTextview_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_edit=new Intent(mContext,AddAddressActivity.class);
                    intent_edit.putExtra("type","buanji");
                    intent_edit.putExtra("name",mData.get(position).getName());
                    intent_edit.putExtra("phonenumber",mData.get(position).getMobile_no());
                    intent_edit.putExtra("sex",mData.get(position).getSex());
                    intent_edit.putExtra("id",mData.get(position).getId()+"");
                    startActivity(intent_edit);
                }
            });
            holder.mLinearlayout_setmoren.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Volley_Utils(new Interface_volley_respose() {
                        @Override
                        public void onSuccesses(String respose) {
                            LogUtils.LOG("ceshi", respose, "设为默认联系人");
                            int status = 0;
                            String msg = "";
                            try {
                                JSONObject object = new JSONObject(respose);
                                status = (Integer) object.get("code");
                                msg = (String) object.get("msg");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            ToastUtils.showToast(mContext, msg);
                            request();

                        }
                        @Override
                        public void onError(int error) {

                        }
                    }).Http(Urls.Baseurl + Urls.setmorenLianxiren +
                            Staticdata.static_userBean.getData().getUser_token() + "&id=" +
                            mData.get(position).getId()+"&client_no="
                            +Staticdata.static_userBean.getData().getAppuser().getClient_no(), mContext, 0);
                }
            });

            return convertView;
        }

        class ViewHolder {
            TextView mText_name;
            TextView mText_sex;
            TextView mText_number;
            TextView mText_delete;
            TextView mTextview_edit;
            ImageView mImageview_chose;
            LinearLayout mLinearlayout_setmoren;
        }
    }

}
