package com.example.zcc.myapplication.rxsample.util;

public class BaseParam {
	public static String APP = "Android2.0";
	
	public static boolean localData = false;
	public static int mWinheight = 1280;
	public static int mWinwidth = 720;
	public static String versionName = "2.0";
	public static String IMAGE_PATH = "android";
	
	public static String AILC_APPKEY="26CE45F8DCEC63156DBECB47179727A9";//appkey_android AILC_SEC 1506061396673111
	public static String AILC_SEC="9052575CCDEF4C20" ; //appsecret_android 1506061396673001
	public static final String APP_CATALOGUE = "/rd/" + IMAGE_PATH + "/cache/icon/";
	
	public static String PREFERENCES_OAUTH_TOKEN = "oauth_token";
	public static String PREFERENCES_REFRESH_OKEN = "refresh_oken";
	public static String PREFERENCES_EXPIRES_IN = "expires_in";
	public static String PREFERENCES_NAME = "name";
	public static String PREFERENCES_PWD = "pwd";
	public static String PREFERENCES_TIMES = "times";
	public static String TOINVEST_TIME = "time";
	public static String PREFERENCES_UID = "uid";
	public static String PREFERENCES_MENDIAN_ID = "mendian_id";
	public static String PREFERENCES_EPAY_BILLNO = "epay_billno";
	public static String PREFERENCES_PUSH = "push";
	public static String INTENT_ID = "id";
	public static String BORROW_ID = "borrowId";
	public static final String LOCK_KEY = "lock_key";
	public static final String HAS_KEY = "has_key";
	public static final String SH_TOP = "istop";
	public static final String IS_FRIST = "isFirstIn";
	public static final String LOCK_LAST_TIME = "LOCKTIME";
	public static final String ARG_LAST_SCROLL_Y = "arg.LastScrollY";
	public static String UID = "";
	public static boolean isStart = true;//防手势密码activity启动两次
	public static boolean LOCK_CANCEL = false;//toast “取消” 关闭
	public static boolean LOCK_NO_LOGIN = false; //免重新输入手势密码
	public static long TIME;//记录时间
	public static boolean LOCK_ADD_TIME = false;
	
	
	public static final String PARAM_PROVINCE_LISTT = "province_list";
	public static final String PARAM_CITY_LIST = "city_list";
	public static final String PARAM_AREA_LIST = "area_list";
//	public static final String PARAM_NOTICE_LIST ="articleList";
	
//	public static String URL = "http://www.p2pv2.com";//http://10.0.1.123
	public static String URL = "http://ceshi.85jin.com/";//http://10.0.1.123
	public static String URL_BANK="mobile/";
	public static String URL_HOST = URL + URL_BANK;
	
	public static final String RESMSG = "resMsg";
	public static final String PARAM_APPKEY = "appkey";
	public static final String PARAM_SIGNA = "signa";
	public static final String PARAM_TS = "ts";
	
	public static final String DIALOG_INPUT_TAG_GESTURE_ERR = "请输入登录密码";
	
	public static String FRAGMENT_NAME = "fragment_name";
	//刷新登录信息
	public static String URL_REPLACE = "oauth/refresh_token.html";
	//首页
	public static String URL_INDEX = "invest/index.html";
	//标详情
	public static String URL_INVEST_DETAIL = "invest/getcontent.html";
	//投标接口
	public static String URL_TOINVEST = "user/toInvest.html";
	//标的投标记录
	public static String URL_TENDERLOG = "invest/tenderlist.html";
	//投标列表
	public static String URL_INVEST_LIST = "invest/list.html";
	//流转标项目列表
	public static String URL_FLOW_LIST = "flow/flowList.html";
	//债权列表
	public static String URL_BOND_LIST = "bond/bondList.html";
	//债权详情
	public static String URL_BOND_DETAIL = "user/bondDetail.html";
	//债权购买
	public static String URL_BOND_TENDER = "user/bondTender.html";
	//债权实际支付金额
	public static String URL_BOND_INTEREST = "user/detailInterest.html";
	//债权投资列表
	public static String URL_BOND_TENDERLIST = "bond/bondLog.html";
	//登录接口
	public static String URL_LOGIN = "oauth/oauth_token.html";
	//验证用户名
	public static String URL_CHECK_USER = "oauth/checkuser.html";
	//获取验证码
	public static String URL_GETCODE = "oauth/phonecode.html";
	//验证验证码
	public static String URL_CHECKCODE = "oauth/checkcode.html";
	//设置新密码
	public static String URL_RESETPWD = "oauth/resetpassword.html";
	//注册接口
	public static String URL_DOREGISTER = "oauth/register.html";
	//注册接口协议
	public static String URL_AGREEMENT = "user/fuwuxieyi.html";
	//资金详情
	public static String URL_ACCOUNTINFO = "account/basic.html";
	//银行卡名称列表
	public static String URL_BANKNAME = "common/getBankList.html";
	//添加银行卡
	public static String URL_ADD_BANK = "user/addBank.html";
	//删除银行卡
	public static String URL_DEL_BANK = "invest/bankcancel.html";
	//银行卡列表
	public static String URL_BANK_LIST = "invest/banklist.html";
	//消息中心
	public static String URL_NOTICE_LIST ="notice/message.html";
	//帮助中心
	public static String URL_HELP_QUES ="notice/list.html";
	//网站公告详情
	public static String URL_NOTICE_CONTENT ="notice/getcontent.html";
	//实名认证信息
	public static String URL_REAL_INFO = "account/realinfo.html";
	//实名认证
	public static String URL_REAL_NAME = "user/realName.html";
	//修改密码
	public static String URL_UPDATE_PWD = "account/resetpassword.html";
	//可用红包列表
	public static String URL_USEABLE_RED = "user/useable.html";
	//可用加息券列表
	public static String URL_USEABLE_RATE = "user/upRateUseable.html";
	//线下充值
	public static String URL_OFFLINE_RECHARGE = "user/doOfflineRecharge.html";
	//网银充值
	public static String URL_ONLINE_RECHARGE = "user/torecharge.html";
	//获取充值方式
	public static String URL_RECHARGE_INIT = "user/rechargeInit.html";
	//资金记录
	public static String URL_ACCOUNT_LOG = "sitelog/log.html";
	//充值记录
	public static String URL_RECHARGE_LIST = "sitelog/rechargelist.html";
	//提现记录
	public static String URL_WITHDRAW_LIST = "sitelog/cashlist.html";
	//绑定邮箱
	public static String URL_BIND_EMAIL = "user/email.html";
	//我的投资记录
	public static String URL_MY_TRENDLIST = "account/hastender.html";
	//可转让列表
	public static String URL_USEABLE_OBLI = "user/saleableBondList.html";
	//转让中列表
	public static String URL_TRANSFER_OBLI = "user/sellingBondList.html";
	//已转出列表
	public static String URL_OUT_OBLI = "user/soldBondList.html";
	//已转入列表
	public static String URL_IN_OBLI = "user/boughtBondList.html";
	//停止债权转让
	public static String URL_STOP_TRANSFER = "user/stopBond.html";
	//我的回款
	public static String URL_BORROW_REPAYMENT = "sitelog/collectionlist.html";
	//用户红包记录
	public static String URL_RED_LIST ="user/envelopeList.html";
	//用户加息记录
	public static String URL_RATE_LIST ="user/myUpRateList.html";
	//获取绑定银行列表
	public static String URL_BANKNAME_LIST = "common/getBankList.html";
	//获取提现条件
	public static String URL_WITHDRAWINIT = "invest/initnewcash.html";
	//提现申请
	public static String URL_DO_WITHDRAW = "user/doCash.html";
	//邀请人
	public static String URL_INVITE = "user/invite.html";
	//债权转让
	public static String URL_ADD_OBLI = "user/addBond.html";
	//投资协议
	public static String URL_BID_PREVIEW = "invest/protocolPreview.html";
	//债权协议
	public static String URL_OBLI_PREVIEW = "bond/protocolPreview.html";
	//待收列表
	public static String URL_REPAYPLAN = "user/repayPlan.html";
	//受让列表
	public static String URL_SELL_DETAIL = "user/sellDetail.html";
	
	/**
	 * 第三方地址列表
	 */
	//添加实名认证
	public static String URL_WEBVIEW_REALNAME = "webview/addrealinfo.html";
	//添加银行卡
	public static String URL_WEBVIEW_BANK = "webview/addbank.html";
	//充值
	public static String URL_WEBVIEW_RECHARGE = "invest/torecharge.html";
	//提现
	public static String URL_WEBVIEW_WITHDRAW = "webview/tonewcash.html";
	//修改支付密码
	public static String URL_WEBVIEW_APILOGIN = "webview/apiLogin.html";
	//投标
	public static String URL_WEBVIEW_TOINVEST = "webview/toinvest.html";
	
	
	//红包
	public static int REQUESTCODE_TOREDPACKET = 1001;
	public static int RESULTCODE_FROMREDPACKET = 1001;
	//加息券
	public static int REQUESTCODE_TORATE = 1002;
	public static int RESULTCODE_FROMRATE = 1002;
	//实名认证
	public static int REQUESTCODE_TOAPPORVE = 1003;
	public static int RESULTCODE_FROMAPPORVE = 1003;
	//修改密码
	public static int REQUESTCODE_TOAMEND = 1004;
	public static int RESULTCODE_FROMAMEND = 1004;
	//注册用户
	public static int REQUESTCODE_TOREGISTER = 1005;
	public static int RESULTCODE_FROMREGISTER = 1005;
	//绑定手机
	public static int REQUESTCODE_TOBINDPHONE = 1006;
	public static int RESULTCODE_FROMBINDPHONE = 1006;
	//投资
	public static int REQUESTCODE_TOINVEST = 1007;
	public static int RESULTCODE_FROMINVEST = 1007;
	//第三方网页投资
	public static int REQUESTCODE_WEBINVEST = 1008;
	public static int RESULTCODE_WEBINVEST = 1008;
	//第三方网页实名
	public static int REQUESTCODE_WEBREALNAME = 1009;
	public static int RESULTCODE_WEBREALNAME = 1009;
	//第三方网页充值
	public static int REQUESTCODE_WEBRECHARGE = 1010;
	public static int RESULTCODE_WEBRECHARGE = 1010;
	//第三方网页提现
	public static int REQUESTCODE_WEBWITHDRAW = 1011;
	public static int RESULTCODE_WEBWITHDRAW = 1011;
}
