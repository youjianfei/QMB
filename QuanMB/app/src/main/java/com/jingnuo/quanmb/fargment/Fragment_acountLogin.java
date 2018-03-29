package com.jingnuo.quanmb.fargment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jingnuo.quanmb.activity.FindPasswordActivity;
import com.jingnuo.quanmb.activity.MainActivity;
import com.jingnuo.quanmb.activity.RegisterActivity;
import com.jingnuo.quanmb.quanmb.R;
import com.jingnuo.quanmb.utils.LogUtils;
import com.jingnuo.quanmb.utils.RsaUtils;
import com.jingnuo.quanmb.utils.ToastUtils;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import static com.jingnuo.quanmb.data.Staticdata.PUBLIC_KEY_STR;
import static com.jingnuo.quanmb.data.Staticdata.isLogin;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Fragment_acountLogin extends Fragment {
    View rootview;
    //控件
    Button mButton_login;
    TextView mtextview_forgotpassword;
    EditText medit_account, medit_password;

    //数据
    String account = "";
    String password = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_acount_login, container, false);
        initview();
        initlinster();


        return rootview;

    }

    private void initlinster() {
        mButton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account = medit_account.getText().toString();
                password = medit_password.getText().toString();
                if (account.equals("") || password.equals("")) {
                    ToastUtils.showToast(getActivity(), "请填写账号密码");
                } else {
                    //获取公钥
                    PublicKey publicKey = RsaUtils.keyStrToPublicKey(PUBLIC_KEY_STR);

                    //公钥加密结果
                    String publicEncryptedResult = RsaUtils.encryptDataByPublicKey(password.getBytes(), publicKey);
//                    //私钥解密结果
//                    String privateDecryptedResult = RsaUtils.decryptedToStrByPrivate(publicEncryptedResult,privateKey);

                    LogUtils.LOG("ceshi", publicEncryptedResult + "1111111111", "fragment_account");
                    //登陆成功后 设置全局变量islogin为 true
                    isLogin = true;
                    Intent intent_login = new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(intent_login);

                }


            }
        });
        mtextview_forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_findpassword = new Intent(getActivity(), FindPasswordActivity.class);
                getActivity().startActivity(intent_findpassword);
            }
        });
    }

    private void initview() {
        medit_account = rootview.findViewById(R.id.edit_account);
        medit_password = rootview.findViewById(R.id.edit_password);
        mButton_login = rootview.findViewById(R.id.button_login);
        mtextview_forgotpassword = rootview.findViewById(R.id.textview_forgotpassword);
    }

}
