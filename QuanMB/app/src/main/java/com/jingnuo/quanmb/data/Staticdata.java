package com.jingnuo.quanmb.data;

/**
 * Created by Administrator on 2018/3/26.
 */

public class Staticdata {
    public static String UUID = "";

    //登陆时候Rsa加密的公钥
    public static String PUBLIC_KEY_STR = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXtNyajfT+Z1+p4wLQXIzlbiSJUXG8lhS9ZeCjDHXclZa6G2xUWvliVXSPc8gAuWC1yz1OibXwspGq48kzqPoyqlNr37uBBjUArquKD/ky0BaADOeD+eHy7jnkXdshSUZAdYpIYEKuM3y+/9qSI+qkJZV/Z8xHKDLUIXYToL/AWwIDAQAB";

    //手机屏幕的宽高
    public static int ScreenHight = 0;
    public static int ScreenWidth = 0;

    //用户是否已经登陆  true表示登录   flase表示未登录    待解决   退出程序后还为true
    public static boolean isLogin = true;//默认为未登录
    //用户的token
    public static String token = "";


}
