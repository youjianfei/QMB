package com.jingnuo.quanmb.utils;

import java.security.PublicKey;

import static com.jingnuo.quanmb.data.Staticdata.PUBLIC_KEY_STR;

/**
 * Created by Administrator on 2018/3/30.
 */

public class PasswordJiami {
    public  static PublicKey publicKey;
    public  static String passwordjiami( String password){
        //获取公钥
        publicKey = RsaUtils.keyStrToPublicKey(PUBLIC_KEY_STR);
        //公钥加密结果
      String  publicEncryptedResult = RsaUtils.encryptDataByPublicKey(password.getBytes(), publicKey);
        //私钥解密结果
//       String privateDecryptedResult = RsaUtils.decryptedToStrByPrivate(publicEncryptedResult,privateKey);
        return publicEncryptedResult;
    }



}
