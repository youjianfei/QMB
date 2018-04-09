package com.jingnuo.quanmb.activity;


import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.jingnuo.quanmb.quanmb.R;


public class ShopDetailActivity extends BaseActivityother {
    //控件
    TextView mTextview_more;
    TextView mTextview_skillintroduction;


    //数据
    private final int MAX_LINE_COUNT = 3;
    private final int STATE_UNKNOW = -1;
    private final int STATE_NOT_OVERFLOW = 1; //文本行数不超过限定行数
    private final int STATE_COLLAPSED = 2; //文本行数超过限定行数,处于折叠状态
    private final int STATE_EXPANDED = 3; //文本行数超过限定行数,被点击全文展开




    @Override
    public int setLayoutResID() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTextview_skillintroduction.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mTextview_skillintroduction.getViewTreeObserver().removeOnPreDrawListener(this);
                if(mTextview_skillintroduction.getLineCount()>MAX_LINE_COUNT){
                    mTextview_skillintroduction.setMaxLines(MAX_LINE_COUNT);
                    mTextview_more.setVisibility(View.VISIBLE);
                    mTextview_more.setText("展开全文");
                }else {
                    mTextview_more.setVisibility(View.GONE);
                }

                return true;
            }
        });
        mTextview_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTextview_more.getText().equals("展开全文")){
                    mTextview_skillintroduction.setMaxLines(Integer.MAX_VALUE);
                    mTextview_more.setText("收起");
                }else {
                    mTextview_skillintroduction.setMaxLines(MAX_LINE_COUNT);
                    mTextview_more.setText("展开全文");
                }
            }
        });


    }

    @Override
    protected void initView() {
        mTextview_more=findViewById(R.id.text_more);
        mTextview_skillintroduction=findViewById(R.id.text_servicedetail);

    }
}
