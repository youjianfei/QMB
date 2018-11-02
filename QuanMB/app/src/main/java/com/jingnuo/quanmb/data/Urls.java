package com.jingnuo.quanmb.data;

/**
 * Created by Administrator on 2018/3/29.
 */

public class Urls {
    public static String Baseurl_downShop = "https://app.quanminbang.top/QMB_Business/v1.0/uploadIge/downLoad";//下载商户版本
    public static String Baseurl_zhuye = "https://app.quanminbang.top/QuanMinBang/";//官网主页
    public static String Baseurl_index = "http://v3.rabbitpre.com/m2/aUe1ZjN6ls";//分享的主页
    public static String Baseurl_zixunkefu = "http://www.quanminbang.top/QuanMinBang/v1.0/uploadIge/toH5";//客服咨询
    public static String newpeoplecoupon = "";//新用户活动领取点击接口
    //正式
    public static String Baseurl = "https://app.quanminbang.top/QuanMinBang/v1.0/";
    public static String Baseurl_cui = "https://app.quanminbang.top/QuanMinBang/v1.0/";
    public static String Baseurl_hu = "https://app.quanminbang.top/QuanMinBang/v1.0/";


    //  测试
//    public static String Baseurl = "http://www.quanminbang.top/QuanMinBang/v1.0/";
//    public static String Baseurl_cui = "http://www.quanminbang.top/QuanMinBang/v1.0/";
//    public static String Baseurl_hu = "http://www.quanminbang.top/QuanMinBang/v1.0/";


    //  本地
//    public static String Baseurl = "http://192.168.0.186:8080/QuanMinBang/v1.0/";
//    public static String Baseurl_cui = "http://192.168.0.186:8080/QuanMinBang/v1.0/";
//    public  static  String Baseurl_hu="http://192.168.0.147:8080/QuanMinBang/v1.0/";

    public static String sendyanzhengma = "send/sendValidate";//登录发送短信验证码  post
    public static String sendzhuceyanzhengma = "send/sendValidate";//注册发送注册短信验证码  post
    public static String upLoadImage = "uploadIge/headImgUpload";//上传图片  1.证件图片 2.任务图片 3.头像图片 4.商户证件图片 5.图标 6.发布专业图片


    public static String autoupdata = "version/getAppVersion";//自动更新
    /**
     * 登录注册相关
     */
    public static String login = "login/doLogin";//登陆  post
    public static String wechatlogin = "login/weChatLogin";//微信登陆  post
    public static String phoneLogin = "login/moblieLogin";//手机号验证登陆
    public static String phoneRegister = "register/Register";//注册
    public static String registerBind = "login/registerBind";//三方登录注册绑定
    public static String existsBind = "login/existsBind";//三方登录已有账号绑定
    public static String logout = "login/LoginOut?user_token=";//退出登录
    /**
     * 用户资料设置  商户资料设置更改
     */
    public static String setnickname = "user/setNickName";//更换昵称
    public static String setheadPic = "user/setheadimage";//设置头像
    public static String changephonenumber = "user/modifyPhone1";//更换绑定手机号第一步
    public static String bindphonenumber = "user/modifyPhone2";//更换绑定手机号第er步,绑定手机号
    public static String changepassword = "user/changePwd";//更换密码
    public static String findpassword = "login/forgetPwd";//找回密码
    public static String setshophead = "business/setBusinessAvatar";//设置商户头像
    public static String editshopinfo = "business/editBusinessInfo";//编辑商户信息

    /**
     * 审核
     */
    public static String shopIn = "business/commitApply";//商家入驻审核    post
    public static String shopIn_state = "business/queryBusinessStatus?user_token=";//商家入驻审核状态
    public static String woderenzheng = "business/queryAuthInfo?user_token=";// 我的认证
    public static String authenticationHelper = "helper/helperReal";//帮手认证
    public static String helpIn_state = "helper/getHelperAuditState";//帮手认证审核状态


    public static String shouyePic = "plaza/getSlideShow?type=";//首页轮播广告  type=1广成首页   2爱心帮
    public static String shouyeRadios = "radio/getAllRadios";//首页轮播喇叭


    //任务模块
    public static String square_default = "task/querySpecialty/search";//帮帮广场
    public static String tasktype = "task/querySpecialty/taskTypeList";//任务类型列表一级
    public static String tasksort = "task/querySort/getSortBy";//任务智能排序方式
    public static String taskdetails = "task/taskDetail/getTaskDetail";//任务详情  ?id=
    public static String taskdetailscancle = "task/taskCancel";//撤消任务
    public static String helptask = "order/taskHelp";//确认帮助 ?id=
    public static String barginmonry = "bargain/taskBargain";//广场单还价
    public static String barginPiPei = "bargain/businessBid";//匹配单出价
    public static String applycompletetask = "order/requestOk?user_token=";//广场申请任务完成
    public static String applyPipeicompletetask = "order/matchingRequestOk?user_token=";//匹配单申请任务完成
    public static String completetask = "order/affirmOk?user_token=";//广场确认任务完成
    public static String completePipeitask = "order/matchingAffirmOk?user_token=";//匹配单确认任务完成
    public static String chooseBusiness = "order/chooseBusiness";//选择商户
    public static String checkissuetask = "task/querySpecialty/checkAnnouncementList";//检验任务内容是否有违规
    public static String gettaskid = "task/getTaskId?user_token=";//获取任务号
    public static String Issue_again = "task/taskBackon?user_token=";//重新上架任务
    public static String apply_longtime = "order/applyforDelay";//帮手商家申请延时
    public static String agreeOrrefuse_longtime = "task/delayAcceptOrNo";//雇主同意或者拒接延时
    public static String taskaddCommission = "task/addCommission";//任务加价
    public static String issuetask = "task/querySpecialty/TaskAnnouncementList";//发布个性任务
    public static String jiazhengweixiuTYpe = "task/querySpecialty/getMaintainType?user_token=";//发布任务   家政维修子专业
    public static String issuetask_zhaoshanghu = "task/querySpecialty/addMatchingTask";//发布匹配任务
    public static String issuetask_huanyipi = "task/querySpecialty/switchBusiness?user_token=";//换一批商户   task_id
    public static String issuetask_getprice = "task/querySpecialty/priceBusiness";//请求商户价格
    //专业/商家模块

    public static String hotbackImage = "https://quanminbang-img.oss-cn-beijing.aliyuncs.com/image/icon/";//热门图片写在本地
    public static String Skillmenu_one = "major/searchOne";//找专业一级菜单
    public static String Skillmenu_right = "major/searchTwo";//找专业二级菜单list
    public static String Skillmenulist = "major/searchPost";//二级菜单点开所有服务列表    参数  specialty_id
    public static String searchSkill = "major/queryPostByTitle";//按标题搜索服务
    public static String BHSkissAll = "user/querySpecialtyList";//某个商家或帮手的所有服务
    public static String FromShequSkiss = "major/queryALLServiceByOne";//显示某1级专业下的所有2级专业下的所有服务接口
    public static String zhidiengShequSkiss = "major/querySpecificService";//显示某几个指定2级专业下的所有服务-电脑，手机，空调，家电维修接口


    public static String setColltctSkill = "user/myCollection";//收藏、取消收藏接口
    public static String ColltctSkillList = "user/getCollectionList?user_token=";//收藏列表

    //商户

    public static String shopcenter = "business/queryBusinessInfo?user_token=";//商家info    get     client_no  user_token
    public static String shoporder = "business/queryBusinessOrder?user_token=";//商家接收的任务
    public static String shoporderdetail = "business/queryOrderDetail?user_token=";//商家接收的任务详情
    public static String shopkill = "business/queryMyRelease?user_token=";//商户发布的服务
    public static String shopCancleSkill = "business/cancelPost?user_token=";//撤消发布专业
    public static String shopIssueSkill = "business/releaseSpecialty";//发布专业    post
    public static String shopkilldetail = "business/queryPostDetail";//服务内容详情    参数  id  get请求

    public static String businessputongshuaxin = "business/handRefresh?user_token=";//普通刷新   服务
    public static String businesszhinengshuaxin = "business/autoRefresh?user_token=";//智能刷新   服务
    public static String businesszhiding = "business/setTopSpecialty?user_token=";//置顶   服务
    public static String push_on_off = "business/pushOnOff?user_token=";//推荐任务推送开关


    //商户 帮手通用
    public static String shuaxinchoseDays = "major/choiceRefreshDayList?user_token=";// 智能刷新选择的天数
    public static String zhidingchoseDays = "major/choiceTopDayList?user_token=";// 智能刷新选择的天数


    //帮手模块

    public static String helperInfo = "helper/queryHelperInfo?user_token=";//帮手信息
    public static String helporder = "helper/queryHelperOrder?user_token=";//帮手接收的任务
    public static String helporderdetail = "helper/queryOrderDetail?user_token=";//商家接收的任务详情
    public static String helpskill = "helper/queryMyRelease?user_token=";//帮手发布的服务
    public static String helperIssueSkill = "helper/releaseSpecialty";//发布专业    post
    public static String helperSkilldetail = "helper/queryPostDetail";//服务内容详情    参数  id  get请求
    public static String orderthink = "order/orderEvaluate";//评价帮手
    public static String BaoSuccess = "helper/after_PayMargin?user_token=";//缴纳保证金成功之后调用
    public static String helperputongshuaxin = "helper/handRefresh?user_token=";//普通刷新   服务
    public static String helperzhinengshuaxin = "helper/autoRefresh?user_token=";//智能刷新   服务
    public static String helperzhiding = "helper/setTopSpecialty?user_token=";//置顶   服务
    public static String helper_type = "helper/updateHelperType?user_token=";//更新帮手服务类型
    public static String helper_isHavehelper = "helper/getStatuAboutHelperType?user_token=";//帮手是否选择过服务类型

    //消息模块

    public static String pushMessage = "messsageCon/getMessageByType";//消息类型 1系统消息，2还价消息，3交易消息，4推荐任务
    public static String getNewmessage = "messsageCon/getUserNewsByType";//消息类型 1系统消息，2还价消息，3交易消息，4推荐任务


    public static String bargainmessage = "bargain/bargainMessage";//还价详情
    public static String acceptORrefuse = "bargain/bargainAccpetOrNo";//接受拒绝还价
    public static String helpterbargain = "bargain/taskBargain";//帮手还价
    public static String kehubargain = "bargain/bargainEmployer";//雇主还价


    //个人中心
    public static String personinfo = "user/queryUserInfo?user_token=";//  post
    public static String myorderlist = "task/taskQueryMyList";//  post
    public static String mytaskdetails = "task/getTaskDetail";//任务详情  ?id=
    public static String myLianxiren = "user/getContactsList";//常用联系人
    public static String deleteLianxiren = "user/deleteContacts?user_token=";//删除联系人
    public static String setmorenLianxiren = "user/setIsDefault?user_token=";//设为默认联系人
    public static String addLianxiren = "user/addContacts";//新增联系人
    public static String editLianxiren = "user/editContacts";//编辑联系人
    public static String findMorenlianxiren = "user/getIsDefaultContacts?user_token=";//默认联系人
    public static String mySuggest = "user/myComplaints";//服务投诉
    public static String mySFeedBack = "user/addFeedBack";//反馈
    public static String myJubao = "task/querySpecialty/informAdd";//我的举报
    public static String setSafepassword = "user/setSecurityCode";//设置支付密码
    public static String checkSafepassword = "user/checkSecurityCode";//校验支付密码
    public static String updataXYDU = "user/updateXYDu?user_token=";//更新经纬度


    public static String couponPopwindow= "coupon/getCouponWindow?user_token=";//新用户弹窗
    public static String myCoupon = "coupon/getMyCoupon?user_token=";//我的优惠券
    public static String useableCoupon = "coupon/getUsableCoupon?user_token=";//可用于当前任务的优惠券




    //支付
    public static String balancePay = "balance/payByBalance";//余额支付
    public static String wechatPay = "AppPay/WexinChatPay";//微信支付
    public static String zhifubaoPay = "ALiPay/dozhiAlipay";//支付宝支付
    public static String getBalance = "balance/getUserBalance?user_token=";//查询钱包余额
    public static String cashmoney = "ALiPay/aliTiZhuan";//提现
    public static String jiaoyiMingxi = "balance/getTradeDetails";//查询交易明细
    public static String tui_taocan = "balance/getAllSpreadPags?user_token=";//充值推广币套餐列表接口
    public static String huiyuan_taocan = "balance/getAllVIPPackages?user_token=";//购买会员套餐接口

    //爱心帮
    public static String IssueLovetask = "loveTask/addLoveTask";//发布爱心帮
    public static String LovetaskList = "loveTask/search";//爱心帮列表
    public static String LovetaskDetails = "loveTask/loveTaskDetail?task_id=";//爱心帮任务详情
    public static String loveTaskType = "loveTask/loveTaskType";//爱心帮任务类型接口

    //二手市场
    public static String Ershoushichang = "secondaryMarket/search";//二手市场列表
    public static String AddErshoushichang = "secondaryMarket/addSecondary";//二手信息
    public static String ErshoushichangDetails = "secondaryMarket/secondaryDetail?user_token=";//二手信息单挑条详细信息

    //社区
    public static String addLiuyan = "communityMsg/saveWallMsg";//添加留言
    public static String getliuyan = "communityMsg/getAllMsg?user_token=";//获取留言墙列表
    public static String shuidianfeidaijiao = "Community/PayCommunityFee";//教水电费
    public static String getCommunityList = "Community/getMyCommunity";//模糊查询所有社区
    public static String bindCommunity = "Community/RequiredCom";//绑定社区
    public static String CommunityNotice = "Community/getCommunityNotice?user_token=";//社区公告


    //省市县
    public static String getallCity = "area/getAllCity";//所有城市


}
