package com.jingnuo.quanmb.data;

/**
 * Created by Administrator on 2018/3/29.
 */

public class Urls {
    public  static  String Baseurl="http://192.168.1.184:8080/QuanMinBang/v1.0/";
    public  static  String Baseurl_hiulin="http://192.168.1.103:8080/QuanMinBang/";

    public  static String sendyanzhengma="send/sendValidate/";//登录发送短信验证码  post
    public  static String sendzhuceyanzhengma="send/sendValidate/";//注册发送注册短信验证码  post

    public  static String login="login/doLogin/";//登陆  post
    public  static String phoneLogin="login/moblieLogin/";//手机号验证登陆
    public  static String phoneRegister="register/Register/";//注册
    public  static String findpassword="login/forgetPwd";//找回密码
    public  static String changephonenumber="user/modifyPhone1";//更换绑定手机号第一步
    public  static String bindphonenumber="user/modifyPhone2";//更换绑定手机号第er步,绑定手机号
    public  static String changepassword="user/changePwd";//更换密码

    public  static String setnickname="user/setNickName";//更换昵称



    public  static String square_default="search";//帮帮广场
    public  static String tasktype="taskTypeList";//任务类型
    public  static String Skillmenu_one="major/searchOne";//找专业一级菜单
    public  static String Skillmenu_right="major/searchTwo";//找专业二级菜单




}
