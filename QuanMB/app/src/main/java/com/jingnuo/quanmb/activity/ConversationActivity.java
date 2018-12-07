package com.jingnuo.quanmb.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.jingnuo.quanmb.R;

public class ConversationActivity extends FragmentActivity {
    private ImageView mImageview_back;
    private TextView text_title;
    String title="";//聊天标题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);//状态栏颜色
        mImageview_back =  findViewById(R.id.iv_back);
        text_title =  findViewById(R.id.textview_maintitle);
        mImageview_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title=getIntent().getData().getQueryParameter("title");
        text_title.setText(title);
    }
}
