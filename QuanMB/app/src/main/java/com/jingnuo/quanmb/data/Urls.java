package com.jingnuo.quanmb.data;

/**
 * Created by Administrator on 2018/3/29.
 */

public class Urls {
    public static String Baseurl = "http://www.quanminbang.top/v1.0/";
    public static String Baseurl_cui = "http://www.quanminbang.top/v1.0/";
    public static String Baseurl_hu = "http://www.quanminbang.top/v1.0/";
//    public  static  String Baseurl_hu="http://192.168.1.197:8080/QuanMinBang/v1.0/";

    public static String sendyanzhengma = "send/sendValidate";//登录发送短信验证码  post
    public static String sendzhuceyanzhengma = "send/sendValidate";//注册发送注册短信验证码  post
    public static String upLoadImage = "uploadIge/headImgUpload";//上传图片


    public static String login = "login/doLogin";//登陆  post
    public static String phoneLogin = "login/moblieLogin";//手机号验证登陆
    public static String phoneRegister = "register/Register";//注册
    public static String findpassword = "login/forgetPwd";//找回密码
    public static String changephonenumber = "user/modifyPhone1";//更换绑定手机号第一步
    public static String bindphonenumber = "user/modifyPhone2";//更换绑定手机号第er步,绑定手机号
    public static String changepassword = "user/changePwd";//更换密码

    public static String setnickname = "user/setNickName";//更换昵称
    public static String setheadPic = "user/setheadimage";//设置头像

    //任务模块
    public static String square_default = "task/querySpecialty/search";//帮帮广场
    public static String tasktype = "task/querySpecialty/taskTypeList";//任务类型列表一级
    public static String tasksort = "task/querySort/getSortBy";//任务智能排序方式
    public static String taskdetails = "task/taskDetail/getTaskDetail";//任务详情  ?id=
    public static String taskdetailscancle = "task/taskCancel";//撤消任务
    public static String helptask = "order/taskHelp";//确认帮助 ?id=
    public static String barginmonry = "bargain/taskBargain";//还价

    public static String issuetask = "task/querySpecialty/TaskAnnouncementList";//发布任务

    //专业/商家模块
    public static String Skillmenu_one = "major/searchOne";//找专业一级菜单
    public static String Skillmenu_right = "major/searchTwo";//找专业二级菜单list
    public static String Skillmenulist = "major/searchPost";//二级菜单点开列表    参数  specialty_id
    public static String Skilldetail = "business/queryPostDetail";//服务内容详情    参数  id  get请求
    public static String IssueSkill = "business/releaseSpecialty";//发布专业    post

    //商户
    public static String shopIn = "business/commitApply";//商家入驻审核    post
    public static String shopcenter = "business/queryBusinessInfo?user_token=";//商家info    get     client_no  user_token


    //帮手模块
    public static String authenticationHelper = "helper/helperReal";//帮手认证


    //消息模块
    public static String pushMessage = "messsageCon/getMessageByType";//消息类型 1系统消息，2还价消息，3交易消息，4推荐任务


    //个人中心
    public static String myorderlist = "task/taskQueryMyList";//  post


}
