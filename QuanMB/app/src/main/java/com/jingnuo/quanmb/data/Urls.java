package com.jingnuo.quanmb.data;

/**
 * Created by Administrator on 2018/3/29.
 */

public class Urls {

//    public static String Baseurl = "http://www.quanminbang.top/v1.0/";
//    public static String Baseurl_cui = "http://www.quanminbang.top/v1.0/";
    public static String Baseurl_hu = "http://www.quanminbang.top/v1.0/";


    public static String Baseurl = "http://192.168.1.184:8080/QuanMinBang/v1.0/";
    public static String Baseurl_cui = "http://192.168.1.103:8080/QuanMinBang/v1.0/";
//    public  static  String Baseurl_hu="http://192.168.1.151:8080/QuanMinBang/v1.0/";

    public static String sendyanzhengma = "send/sendValidate";//登录发送短信验证码  post
    public static String sendzhuceyanzhengma = "send/sendValidate";//注册发送注册短信验证码  post
    public static String upLoadImage = "uploadIge/headImgUpload";//上传图片


    public static String login = "login/doLogin";//登陆  post
    public static String wechatlogin = "login/weChatLogin";//微信登陆  post
    public static String phoneLogin = "login/moblieLogin";//手机号验证登陆
    public static String phoneRegister = "register/Register";//注册
    public static String registerBind = "login/registerBind";//三方登录注册绑定
    public static String existsBind = "login/existsBind";//三方登录已有账号绑定
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
    public static String applycompletetask = "order/requestOk?user_token=";//申请任务完成
    public static String completetask = "order/affirmOk?user_token=";//确认任务完成
    public static String issuetask = "task/querySpecialty/TaskAnnouncementList";//发布任务
    public static String checkissuetask = "task/querySpecialty/checkAnnouncementList";//检验任务内容是否有违规

    //专业/商家模块
    public static String Skillmenu_one = "major/searchOne";//找专业一级菜单
    public static String Skillmenu_right = "major/searchTwo";//找专业二级菜单list
    public static String Skillmenulist = "major/searchPost";//二级菜单点开所有服务列表    参数  specialty_id
    public static String searchSkill = "major/queryPostByTitle";//按标题搜索服务


    public static String setColltctSkill = "user/myCollection";//收藏、取消收藏接口



    //商户
    public static String shopIn = "business/commitApply";//商家入驻审核    post
    public static String shopIn_state = "business/queryBusinessStatus?user_token=";//商家入驻审核状态

    public static String shopcenter = "business/queryBusinessInfo?user_token=";//商家info    get     client_no  user_token
    public static String  shoporder= "business/queryBusinessOrder?user_token=";//商家接收的任务
    public static String  shoporderdetail= "business/queryOrderDetail?user_token=";//商家接收的任务详情
    public static String  shopkill= "business/queryMyRelease?user_token=";//商户发布的服务
    public static String  shopCancleSkill = "business/cancelPost?user_token=";//撤消发布专业
    public static String shopIssueSkill = "business/releaseSpecialty";//发布专业    post
    public static String shopkilldetail = "business/queryPostDetail";//服务内容详情    参数  id  get请求



    //帮手模块
    public static String authenticationHelper = "helper/helperReal";//帮手认证
    public static String helpIn_state = "helper/getHelperAuditState";//帮手认证审核状态
    public static String helperInfo = "helper/queryHelperInfo?user_token=";//帮手信息
    public static String  helporder= "helper/queryHelperOrder?user_token=";//帮手接收的任务
    public static String  helporderdetail= "helper/queryOrderDetail?user_token=";//商家接收的任务详情
    public static String  helpskill= "helper/queryMyRelease?user_token=";//帮手发布的服务
    public static String helperIssueSkill = "helper/releaseSpecialty";//发布专业    post
    public static String helperSkilldetail = "helper/queryPostDetail";//服务内容详情    参数  id  get请求


    //消息模块

    public static String pushMessage = "messsageCon/getMessageByType";//消息类型 1系统消息，2还价消息，3交易消息，4推荐任务
    public static String getNewmessage = "messsageCon/getUserNewsByType";//消息类型 1系统消息，2还价消息，3交易消息，4推荐任务


    public static String bargainmessage="bargain/bargainMessage";//还价详情
    public static String acceptORrefuse="bargain/bargainAccpetOrNo";//接受拒绝还价
    public static String helpterbargain="bargain/taskBargain";//帮手还价
    public static String kehubargain="bargain/bargainEmployer";//雇主还价




    //个人中心
    public static String myorderlist = "task/taskQueryMyList";//  post
    public static String mytaskdetails = "task/getTaskDetail";//任务详情  ?id=


    //支付
    public static String wechatPay = "AppPay/WeChatPreOrder";//微信支付

}
