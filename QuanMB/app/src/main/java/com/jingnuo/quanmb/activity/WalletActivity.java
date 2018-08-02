package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingnuo.quanmb.R;
import com.jingnuo.quanmb.utils.ToastUtils;

public class WalletActivity extends BaseActivityother {
    LinearLayout Lineatlayout_wallte;
    LinearLayout Lineatlayout_mingxi;
    TextView  mTextview;

    String money="";

    @Override
    public int setLayoutResID() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        money=intent.getStringExtra("money");
        mTextview.setText("¥ "+money);
    }

    @Override
    protected void initListener() {
        Lineatlayout_mingxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent_mingxi=new Intent(WalletActivity.this,MonryMingxiActivity.class);
            startActivity(intent_mingxi);
            }
        });
        Lineatlayout_wallte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(WalletActivity.this,"暂不可用");
            }
        });

    }

    @Override
    protected void initView() {
        Lineatlayout_wallte=findViewById(R.id.linear_bankcard);
        Lineatlayout_mingxi=findViewById(R.id.linerlayout_mingxi);
        mTextview=findViewById(R.id.text_money);
    }

}
