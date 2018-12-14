package com.jingnuo.quanmb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import com.jingnuo.quanmb.R;

public class SystemmessageDetailActivity extends BaseActivityother {
    TextView mTextview_contenttitle;
    TextView mTextview_title;
    TextView mTextview_content;
    TextView mTextview_time;

    String title="";
    String content="";
    String time="";
    String url="";



    @Override
    public int setLayoutResID() {
        return R.layout.activity_systemmessage_detail;
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        title= intent.getStringExtra("title");
        content=intent.getStringExtra("content");
        time=intent.getStringExtra("time");
        url=intent.getStringExtra("url");


        mTextview_contenttitle.setText(title);
        mTextview_time.setText(time);
        mTextview_content.setText(content);



        if(!url.equals("")){
            SpannableString spannableString = new SpannableString(content+"点击查看");
            spannableString.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Intent intent_kefuzhongxin = new Intent(SystemmessageDetailActivity.this, ZixunKefuWebActivity.class);
                    intent_kefuzhongxin.putExtra("webtitle","");
                    intent_kefuzhongxin.putExtra("type", "系统通知");
                    intent_kefuzhongxin.putExtra("URL", url);
                    startActivity(intent_kefuzhongxin);
                }
            },spannableString.length()-4,spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue2)), spannableString.length()-4,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTextview_content.setMovementMethod(LinkMovementMethod.getInstance());
            mTextview_content.setText(spannableString);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        mTextview_title=findViewById(R.id.textview_maintitle);
        mTextview_contenttitle=findViewById(R.id.text_title);
        mTextview_content=findViewById(R.id.text_content);
        mTextview_time=findViewById(R.id.text_time);
        mTextview_title.setText("系统消息");
    }
}
