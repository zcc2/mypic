package com.example.zcc.myapplication.rxsample.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zcc.myapplication.MyApplication;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint("SimpleDateFormat")
public class AppTools {

    // 科学计数法转换成数字
    public static String scienceTurnNum(Double value) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }

    public static String scienceTurnNumOne(Double value) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(value);
    }

    public static boolean checkIdCard(String idCard) {
        if (idCard.length() < 18)
            return false;
        String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
        return Pattern.matches(regex, idCard);
    }

    // 小数点后两位
    public static boolean checkPoint(String idCard) {
        String regex = "^[0-9]+$|^[0-9]+\\.[0-9]{1,2}$";
        return Pattern.matches(regex, idCard);
    }

    public static boolean checkMobile(String mobile) {
        String regex = "(\\+\\d+)?1[34578]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }

    public static boolean checkOkPwd(String mobile) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        return Pattern.matches(regex, mobile);
    }

    /**
     * 验证手机格式
     */
    public static boolean checkMobileNO(String mobiles) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
        String telRegex = "[1][34578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        // if (TextUtils.isEmpty(mobiles))
        // return false;
        // else
        return mobiles.matches(telRegex);
    }

    // 接口访问加密
    @SuppressLint("DefaultLocale")
    public static String httpPermission(long time) {
        return encryption(encryption(String.valueOf(BaseParam.AILC_SEC + time)) + BaseParam.AILC_APPKEY).toUpperCase();
    }

    /**
     * 读取assets 中文件
     */
    public static String readLocalJson(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(
                    MyApplication.getInstance().getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            inputReader.close();
            bufReader.close();
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * toast 过滤 3s内禁止重复出现
     */
    private static final long Interval = 3 * 1000;
    private static SoftMap<String, Long> map = new SoftMap<String, Long>();

    private static Toast CURR_TOAST;

    public static void toast(String msg) {
        long preTime = 0;
        if (map.containsKey(msg)) {
            preTime = map.get(msg);
        }
        final long now = System.currentTimeMillis();
        if (now >= preTime + Interval) {
            if (CURR_TOAST != null) {
                CURR_TOAST.cancel();
            }
            Toast toast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT);
            toast.show();

            map.put(msg, now);

            CURR_TOAST = toast;
        }
    }

    /**
     * 判断网络连接状况
     */
    public static boolean isConnect(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            Log.d("error", e.toString());
        }
        toast("网络错误");
        return false;
    }

    // 32MD5加密
    public static String encryption(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    // 时间戳转换
    public static String timestampTotime(long timestamp, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        return sdf.format(timestamp);
    }

    // 普通计算收益
    public static double calculate(String rate, String time, String tender, int repayment_type, Boolean isDay) {

        double yearRate = 0;// 年利率
        double money = 0;// 投资金额
        double earnMoney = 0; // 收益
        float limitTime = 0;
        int type = -1;

        yearRate = Float.valueOf(rate);
        money = Float.valueOf(tender);
        limitTime = Float.valueOf(time);
        type = Integer.valueOf(repayment_type);
        switch (type) {
            case 1:
                type = 3;
                break;
            case 2:
                type = 3;
                break;
            case 3:
                type = 0;
                break;
            case 4:
                type = 2;
                break;
            case 5:
                type = 3;
                break;
            case 6:
                type = 0;
                break;
            default:
                type = 3;
                break;
        }
        if (isDay) {
            type = 4;
        }
        if (limitTime > 0 && yearRate > 0) {
            switch (type) {
                case 0:// 按月分期还款
                    earnMoney = money * yearRate * limitTime / 1200.00;
                    // earnMoney = money * yearRate / 1200.00
                    // * Math.pow((1 + yearRate / 1200.00), limitTime)
                    // / (Math.pow((1 + yearRate / 1200.00), limitTime) - 1)
                    // * limitTime - money;
                    break;
                case 1:// 按季还本息
                    double season = Math.ceil(limitTime / 3.00);
                    earnMoney = money * yearRate * (1 + season) / 800.00;
                    break;
                case 2:// 按月还息到期还本
                    earnMoney = money * yearRate * limitTime / 1200.00;
                    break;
                case 3:// 一次性还款
                    earnMoney = money * yearRate * limitTime / 1200.00;
                    break;
                case 4:// 按日
                    earnMoney = money * yearRate * limitTime / 36500.00;
                    break;
                default:
                    break;
            }
            DecimalFormat df = new DecimalFormat("#.000");
            earnMoney = Double.parseDouble(df.format(earnMoney));
            return (earnMoney) * 100 / 100.00;
        } else {
            return money * 100 / 100.00;
        }
    }

    // 开通金融计算收益
    public static double calculateNew(String rate, String time, String tender, int repayment_type, Boolean isDay, int
            yearsDays) {

        double yearRate = 0;// 年利率
        double money = 0;// 投资金额
        double earnMoney = 0; // 收益
        float limitTime = 0;
        int type = -1;
        int yearDays = 365;

        yearRate = Float.valueOf(rate);
        money = Float.valueOf(tender);
        limitTime = Float.valueOf(time);
        type = Integer.valueOf(repayment_type);
        yearDays = yearsDays;
        switch (type) {
            case 1:
                type = 3;
                break;
            case 2:
                type = 3;
                break;
            case 3:
                type = 0;
                break;
            case 4:
                type = 2;
                break;
            case 5:
                type = 3;
                break;
            case 6:
                type = 0;
                break;
            default:
                type = 3;
                break;
        }
        if (isDay) {
            type = 4;
        }
        if (limitTime > 0 && yearRate > 0) {
            switch (type) {
                case 0:// 按月分期还款
                    earnMoney = money * yearRate * limitTime / 1200.00;
                    // earnMoney = money * yearRate / 1200.00
                    // * Math.pow((1 + yearRate / 1200.00), limitTime)
                    // / (Math.pow((1 + yearRate / 1200.00), limitTime) - 1)
                    // * limitTime - money;
                    break;
                case 1:// 按季还本息
                    double season = Math.ceil(limitTime / 3.00);
                    earnMoney = money * yearRate * (1 + season) / 800.00;
                    break;
                case 2:// 按月还息到期还本
                    earnMoney = money * yearRate * limitTime / 1200.00;
                    break;
                case 3:// 一次性还款
                    earnMoney = money * yearRate * limitTime / 1200.00;
                    break;
                case 4:// 按日
//                    earnMoney = money * yearRate * limitTime / 36500.00;
                    earnMoney = money * yearRate * limitTime / (yearDays * 100);
                    break;
                default:
                    break;
            }
            DecimalFormat df = new DecimalFormat("#.000");
            earnMoney = Double.parseDouble(df.format(earnMoney));
            return (earnMoney) * 100 / 100.00;
        } else {
            return money * 100 / 100.00;
        }
    }

    // 判断金额
    public static boolean checkMoney(String paramString) {
        return Pattern.compile("-?[0-9]*$?").matcher(paramString).matches();
    }

    // 判断金额2
    public static boolean checkMoney1(String paramString) {
        return Pattern.compile("/^\\+?(0|[1-9][0-9]*)$/").matcher(paramString).matches();
    }

    // 获取接口名,读取asset文件名
    public static String urlToFilename(String url) {
        String[] apiName = url.split("/");
        String api_h = apiName[apiName.length - 1];
        return api_h.replace("html", "xml");
    }

    // 判断字符串是否为空
    public static boolean checkStringNoNull(String paramString) {
        return null != paramString && paramString.length() > 0;
    }

    // 判断字符串组是否为空
    public static boolean checkStrsNoNull(String... strings) {
        boolean flag = true;
        for (String paramString : strings) {
            if (paramString == null || paramString.length() == 0) {
                flag = false;
            }
        }
        return flag;
    }

    // 验证邮箱
    public static boolean checkEmail(String email) {
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, email);
    }

    /**
     * 文件转化为字节数组
     *
     * @EditTime 2007-8-13 上午11:45:28
     */
    public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1) {
                out.write(b, 0, n);
            }
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本名
     */
    public static String getVersion() {
        Context context = MyApplication.getInstance();
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return "版本号" + version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本名
     */
    public static String getVersionName() {
        Context context = MyApplication.getInstance();
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionCode = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    // 正则表达式判断用户名是否合法
    public static boolean checkPwd(String name) {
        if (name.length() >= 8 && name.length() <= 16) {
            Pattern p1 = Pattern.compile("[a-zA-Z]+");
            Pattern p2 = Pattern.compile("[0-9]+");
            Matcher m = p1.matcher(name);
            if (!m.find()) {
                return false;
            } else {
                // m = p2.matcher(name);
                m.reset().usePattern(p2);
                return m.find();
            }
        } else {
            return false;
        }

    }

    // 判断密码是否合法
    public static boolean checkedPwd(String pwd) {
        String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(pwd);
        return matcher.find();
    }

    // 按钮点击频次控制800毫秒内点击无效
    public static long lastClickTime = 0;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) { // 800毫秒内按钮无效，这样可以控制快速点击，自己调整频率
            return true;
        }
        lastClickTime = time;
        return false;
    }

    //检查版本
    public static boolean checkVersion() {
        // Build.VERSION.SDK_INT 当前系统版本
        //Build.VERSION_CODES.M 6.0版本
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    //屏幕分辨率获取
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dpToPx(final Context context, final float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    public static void setCbText(CheckBox useInfo, String str, int begin, int end, ClickableSpan onClickListener) {
        SpannableString spStr = new SpannableString(str);
        spStr.setSpan(onClickListener, begin, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        useInfo.setText(spStr);
        useInfo.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static String urlEncode(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");//转码
        } catch (UnsupportedEncodingException mE) {
            mE.printStackTrace();
        }
        return string;
    }

    // 启动应用的设置
    public void startAppSettings(Activity mActivity) {
        Uri packageURI = Uri.parse("package:" + "com.rd.bgy");
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
        mActivity.startActivity(intent);
    }
    public static boolean checkNumSixPwd(String pwd){
        if(!equalStr(pwd)&&!equalAddStr(pwd)&&!equalMinStr(pwd)){
            return true;
        }else {
            return false;
        }
    }
    //重复数字
    public static boolean equalStr(String numOrStr){
        boolean flag = true;
        char str = numOrStr.charAt(0);
        for (int i = 0; i < numOrStr.length(); i++) {
            if (str != numOrStr.charAt(i)) {
                flag = false;
                break;
            }
        }
        return flag;
    }
    //递加  递减数字
    public static boolean equalAddStr(String numOrStr){
        boolean flag = true;
        for (int i = 0; i < numOrStr.length(); i++) {
            if(i>0){
                int num = Integer.parseInt(numOrStr.charAt(i)+"");
                int numAdd = Integer.parseInt(numOrStr.charAt(i-1)+"")+1;
                if (num!=numAdd) {
                    flag = false;
                    break;
                }
            }

        }
        return flag;
    }
    //递减数字
    public static boolean equalMinStr(String numOrStr){
        boolean flag = true;
        for (int i = 0; i < numOrStr.length(); i++) {
            if(i>0){
                int num = Integer.parseInt(numOrStr.charAt(i)+"");
                int num_ = Integer.parseInt(numOrStr.charAt(i-1)+"")-1;
                if (num != num_) {
                    flag = false;
                    break;
                }
            }

        }
        return flag;
    }
    public static void setEditDel(final ImageView imageViews, final EditText editTexts){
        editTexts.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        imageViews.setVisibility(View.VISIBLE);
                    } else {
                        imageViews.setVisibility(View.GONE);
                    }
                }
            });
            imageViews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editTexts.setText("");
                }
            });
    }
    public static void setEditsDel(final List<ImageView> imageViews, final List<EditText> editTexts){
        for (int i=0;i<imageViews.size();i++){
            final  int finalInt=i;
            editTexts.get(i).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        imageViews.get(finalInt).setVisibility(View.VISIBLE);
                    } else {
                        imageViews.get(finalInt).setVisibility(View.GONE);
                    }
                }
            });
            imageViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editTexts.get(finalInt).setText("");
                }
            });
        }
    }
    //获取ip
    public static String getLocalIpAddress2(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int i = wifiInfo.getIpAddress();
            return int2ip(i);
        } catch (Exception ex) {
            return " 获取IP出错了!!!!请保证是WIFI,或者请重新打开网络!\n" + ex.getMessage();
        }
        // return null;
    }
    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }
}
