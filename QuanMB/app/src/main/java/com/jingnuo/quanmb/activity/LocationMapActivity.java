package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jingnuo.quanmb.Adapter.Adapter_SearchAddress;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.ToastUtils;


public class LocationMapActivity extends BaseActivityother {


    //控件
    EditText mEdit_location;
    EditText mEdit_search;
    TextView mTextview_nowaddress;
    Button mBUtton_queding;

    private ListView mListview_searchaddress;
    ImageView mImageview_cancle;
    //数据
    Adapter_SearchAddress  mAdapter_address;
    // 定位相关
    boolean isFirstLoc = true; // 是否首次定位

    //POI城市检索


    String finallocation;//poi名称
    String address;//地址
    @Override
    public int setLayoutResID() {
        return R.layout.activity_location_map;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
//        mAdapter_address=new Adapter_SearchAddress(mData_searchaddress,this);
//        mListview_searchaddress.setAdapter(mAdapter_address);

    }

    @Override
    protected void initListener() {
        //监听键盘确定按钮，以便直接搜索
        mEdit_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    LogUtils.LOG("ceshi", "点击了确定按钮", "百度地图搜索地址");
                    String address=mEdit_search.getText()+"";
                    if(address.equals("")){
                    }else {

                    }

                }
                return false;
            }
        });
        mBUtton_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result=new Intent();
                result.putExtra("address", mTextview_nowaddress.getText()+"");
                String add=mEdit_location.getText()+"";
                if(add.equals("")){
                    ToastUtils.showToast(LocationMapActivity.this,"请输入自定义名称");
                    return;
                }
                result.putExtra("address2", add);
                setResult(2018418,result);
                finish();
            }
        });
        mImageview_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImageview_cancle.setVisibility(View.GONE);
                mListview_searchaddress.setVisibility(View.GONE);
            }
        });

        mListview_searchaddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    @Override
    protected void initView() {
        mListview_searchaddress=findViewById(R.id.list_searchaddresslist);
        mEdit_location= findViewById(R.id.textview_location);
        mEdit_search= findViewById(R.id.edit_searchaddress);
        mImageview_cancle=findViewById(R.id.iamge_cancle);
        mTextview_nowaddress=findViewById(R.id.text_mapget);
        mBUtton_queding=findViewById(R.id.button_submit);
    }
    /**
     * 定位SDK监听函数
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理

    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理

        finallocation=mEdit_location.getText()+"";
    }
}
