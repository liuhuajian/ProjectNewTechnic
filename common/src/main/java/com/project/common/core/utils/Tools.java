package com.project.common.core.utils;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.common.R;
import com.project.common.core.BaseApp;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @Description TODO<一些页面操作的工具类>
 * @author 曾招林
 * @date 2015年9月21日
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class Tools {


    /*** 判断手机号的正则表达式 ***/
    public static final String phone_judge = "^1[3|4|5|6|8|7][0-9]\\d{8}$";
    /*** 判断银行卡号16位的正则表达式 ***/
    public static final String bankNums = "^\\d{16}$";
    /*** 判断银行卡号19位的正则表达式 ***/
    public static final String bankNums19 = "^\\d{19}$";

    /**
     *
     * @description<activity切换公共方法>
     * @version 1.0
     * @createTime 2015年9月21日,下午5:33:54时间
     * @updateTime 2015年9月21日,下午5:33:54
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo ()
     * @param context
     * @param activityclass 目标class
     * @param bundle 参数传递
     */
//    public static void changeActivity(Context context, Class<?> activityclass, Bundle bundle) {
//        Intent intent = new Intent(context, activityclass);
//        if (NotNull.isNotNull(bundle)) {
//            intent.putExtras(bundle);
//        }
//        if (NotNull.isNotNull(context)) {
//            context.startActivity(intent);
//        }
//        ((Activity) context).overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
//    }
//
//    public static void changeActivityForResult(Activity activity, Class<?> activityclass, Bundle bundle, int requestCode) {
//        Intent intent = new Intent(activity, activityclass);
//        if (NotNull.isNotNull(bundle)) {
//            intent.putExtras(bundle);
//        }
//        activity.startActivityForResult(intent, requestCode);
//        activity.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
//    }
//
//    public static void changeActivitysetResult(Activity activity, Class<?> activityclass, Bundle bundle, int requestCode) {
//        Intent intent = new Intent(activity, activityclass);
//        if (NotNull.isNotNull(bundle)) {
//            intent.putExtras(bundle);
//        }
//        activity.setResult(requestCode, intent); // 这理有2个参数(int resultCode,
//        // Intent intent)
//        activity.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
//    }

    /**
     * String 填空
     * @param id
     * @param value
     * @return
     */
    public static String strFormat(int id, Object... value) {
        if (!NotNull.isNotNull(value))
            return "";
        return String.format(BaseApp.mContext.getString(id), value);
    }
    /**
     * 动态切换fragment
     *
     * @author july.zeng 2014年4月28日 上午10:58:41
     * @param id
     * @param fragment
     * @param fm
     */
    public static void replaceFragment(int id, Fragment fragment, FragmentManager fm) {
        replaceFragment(id, null, fragment, fm);
    }

    /**
     * 动态替换fragment
     * @param id
     * @param tag
     * @param fragment
     * @param fm
     */
    public static void replaceFragment(int id, String tag, Fragment fragment, FragmentManager fm) {
        if (NotNull.isNotNull(tag)) {
            fm.beginTransaction().replace(id, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(tag).commit();
        } else {
            fm.beginTransaction().replace(id, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
        }
    }

    /**
     *  时间戳转成月/日。
     * @param time
     * @return
     */
    public static String getlongToDate(long time){
        if (NotNull.isNotNull(time)) {
            Date d = new Date(time);
            SimpleDateFormat sf = new SimpleDateFormat("MM/dd");
            return sf.format(d);
        }
        return "";
    }

    /**
     *  时间戳转成小时。
     * @param time
     * @return
     */
    public static String getlongToHourM(long time){
        if (NotNull.isNotNull(time)) {
            Date d = new Date(time);
            SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
            return sf.format(d);
        }
        return "";
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time) {
        if (NotNull.isNotNull(time)) {
            Date d = new Date(time);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sf.format(d);
        }
        return "";
    }
    /*时间戳转换成时分秒*/
    public static String getDateToStringHMS(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
        return sf.format(d);
    }
    /*时间戳转换成字符窜*/
    public static String getDateToString2(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    public static String caculateTime(Context ctx,String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        String newTime = "";
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timeMillis = date.getTime();
        long currentTimeMillis = System.currentTimeMillis();
        long caculTime = ((currentTimeMillis - timeMillis) / 1000);
        if (caculTime < 1) {
            newTime = 1 + ctx.getString(R.string.second);
        } else if (caculTime < 60 && caculTime >= 1) { // 秒
            newTime = caculTime + ctx.getString(R.string.second);
        } else if (caculTime >= 60 && caculTime < 60 * 60) { // 分
            caculTime /= 60;
            newTime = caculTime + ctx.getString(R.string.minute);
        } else if (caculTime >= 60 * 60 && caculTime < 60 * 60 * 24) { // 时
            caculTime /= 60 * 60;
            newTime = caculTime + ctx.getString(R.string.hour);
        } else if (caculTime >= 60 * 60 * 24 && caculTime < 60 * 60 * 24 * 4) { // 天
            caculTime /= 60 * 60 * 24;
            newTime = caculTime + ctx.getString(R.string.day);
        } else {
            SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm");
            newTime = df.format(date);
        }

        return newTime;
    }



    /**
     * Date 转  字符串
     */
    @SuppressLint("SimpleDateFormat")
    public static String getDateToString(Date d) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    /**
     *
     * 方法描述<字母或者数字>
     * @version 1.0
     * @createTime 2015年11月18日,上午11:12:53
     * @updateTime 2015年11月18日,上午11:12:53
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param str
     * @return
     */
    public static boolean isCharOrNumbers(String str) {
        Pattern p = Pattern.compile("[a-z]*[A-Z]*[0-9]");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     *
     * 方法描述<字符串转日期>
     * @version 1.0
     * @createTime 2015年10月20日,上午9:06:51
     * @updateTime 2015年10月20日,上午9:06:51
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param time
     * @return
     */
    public static Date getStringToDate(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(time, pos);
        return strtodate;
    }

    /**
     *
     * 方法描述<字符串转日期>
     * @version 1.0
     * @createTime 2015年10月20日,上午9:06:51
     * @updateTime 2015年10月20日,上午9:06:51
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param time
     * @return
     */
    public static long getStringToDateLong(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(time, pos);
        return strtodate.getTime()/1000;
    }

    /**
     * 方法描述<日期格式转换>
     * @version 1.0
     * @createTime 2015年11月3日,下午1:54:56
     * @updateTime
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param time 2015-10-29 10:50:44
     * @return  10-29 10:50
     */
    public static String getStringDate(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(time, pos);
        SimpleDateFormat fot = new SimpleDateFormat("MM-dd HH:mm");
        return fot.format(strtodate);
    }
    /**
     * 方法描述<日期格式转换>
     * @version 1.0
     * @createTime 2015年11月3日,下午1:54:56
     * @updateTime
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param time 2015-10-29 10:50:44
     * @return  10-29 10:50
     */
    public static String stringDateToMonth(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(time, pos);
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        return  sdf.format(strtodate);
    }


    /**
     *
     * @createAuthor
     * @param time "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String string2date(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(time, pos);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return  sdf.format(strtodate);
    }


    public static String getDate(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(time, pos);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return  sdf.format(strtodate);
    }
    /**
     *
     * 方法描述<日期转星期    0-6 分别代表   星期日 ~星期六>
     * @version 1.0
     * @createTime 2015年10月20日,上午8:54:52
     * @updateTime
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo ()
     * @param dt
     * @return
     */

    public static String getWeekString(Date dt) {
        String [] weekDays = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


    /**
     * 日期转星期    0-6 分别代表   星期日 ~星期六
     * @createAuthor spideman
     * @param dt
     * @return
     */
    public static int getWeekOfDate(Date dt) {
        int [] weekDays = {0, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static  String  dateOpt(int i){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+i);
        return  getDateToString(calendar.getTime());
    }




    /**
     * 判断字符串是否纯数字
     *
     * @author july.zeng
     * @date 2013-12-30 下午12:55:54
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        return str.matches("[0-9]*");
    }

    /**
     * 判断密码不能为空，不能为连续或重复数字
     *
     * @author july.zeng
     * @date 2014-7-16 上午11:17:45
     * @param num
     * @return
     */
    public static boolean judgePsw(Context mContext, String num) {
        if (!NotNull.isNotNull(num)) {
            ToastUtil.showLong(mContext, "密码不能为空");
            return false;
        }
        if (num.length() < 6) {
            ToastUtil.showLong(mContext, "密码长度不能小于6位");
            return false;
        } else if (num.length() > 16) {
            ToastUtil.showLong(mContext, "密码长度不能大于16位");
            return false;
        } else if (Tools.isNum(num)) {
            char[] c = num.toCharArray();
            boolean isRepeatNumber = true;
            boolean isContinuousNumber = true;
            for (int i = 0; i < c.length - 1; i++) {
                if (isRepeatNumber && c[i] != c[i + 1]) {
                    isRepeatNumber = false;
                }
                if (isContinuousNumber && c[i] + 1 != c[i + 1]) {
                    isContinuousNumber = false;
                }
            }
            if (isRepeatNumber) {
                ToastUtil.showLong(mContext, "密码不能是重复数字");
                return false;
            }
//			if (isContinuousNumber) {
//				Tools.openToastLong(mContext, "密码不能是连续数字");
//				return false;
//			}
        }
        return true;
    }


    /**
     * 判断是不是手机号
     *
     * @author july.zeng
     * @date 2014年6月17日 上午9:19:03
     * @param
     * @return
     */
    public static boolean isMobileNum(String num) {
        return Pattern.compile(phone_judge).matcher(num).matches();
    }

    /**
     *
     * 方法描述<判断银行卡号>
     * @version 1.0
     * @createTime 2015年12月2日,下午2:55:11
     * @updateTime 2015年12月2日,下午2:55:11
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param cardNum
     * @return
     */
    public static boolean isBankNums(String cardNum){
        return Pattern.compile(bankNums).matcher(cardNum).matches()||  Pattern.compile(bankNums19).matcher(cardNum).matches();
    }

    /**
     *
     * @description<格式化银行卡账号只显示最后4位 前面用"****"显示>
     * @version 1.0
     * @createTime 2015年9月29日,上午8:59:47
     * @updateTime 2015年9月29日,上午8:59:47
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo ()
     * @param value
     * @return
     */
    public static String getBankCardNum(String value) {
        if (NotNull.isNotNull(value)) {
            value = "*** **** ****" + (String) value.subSequence(value.toString().length() - 4, value.toString().length());
        }
        return value;
    }

    /**
     * 金额格式化
     *
     * @author july.zeng 2014年4月28日 上午10:49:06
     * @param price
     * @return
     */
    public static String formatPriceStr(Double price) {
        if (NotNull.isNotNull(price)) {
            return new DecimalFormat("0.00").format(price);
        }
        return "0.00";
    }

    /**
     * @description<获取明天的格式化后的日期>
     * @version 1.0
     * @createTime 2015年10月8日,下午12:53:02
     * @updateTime
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo ()
     * @return to:10/08(格式）
     */
    public static String getTomorrowDayDate(){
        SimpleDateFormat sf = new SimpleDateFormat("MM/dd");
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.roll(Calendar.DAY_OF_MONTH, 1);
        return sf.format(mCalendar.getTime());
    }
    /**
     *
     * 方法描述<获取明天的格式化后的日期  例：2015-10-19>
     * @version 1.0
     * @createTime 2015年10月19日,上午11:17:30
     * @updateTime
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     * @return
     */
    public static String getTomorrowDayDate2(){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.roll(Calendar.DAY_OF_MONTH, 1);
        return sf.format(mCalendar.getTime());
    }


    /**
     *
     * 方法描述<根据接口返回内容设置控件显示和隐藏>
     * @version 1.0
     * @createTime 2015年10月19日,下午7:00:05
     * @updateTime 2015年10月19日,下午7:00:05
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param tv
     * @param text
     */
    public static void setText(TextView tv,String text){
        if (NotNull.isNotNull(text)) {
            tv.setText(text);
        }else {
            tv.setVisibility(View.GONE);
        }
    }

    /**
     *
     * 方法描述<根据接口返回内容设置控件显示和隐藏>
     * @version 1.0
     * @createTime 2015年10月19日,下午7:00:05
     * @updateTime
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo ()
     * @param tv 控件
     * @param info 提示内容
     * @param text 后段内容
     */
    public static void setText(Context mContext,TextView tv,String info,String text){
        if (NotNull.isNotNull(text)) {
            tv.setText(getColorText(mContext, info+text, R.color.black, 0, info.length()));
        }else {
            tv.setVisibility(View.GONE);
        }
    }

    /**
     *
     * 方法描述<动态设置字体颜色>
     * @version 1.0
     * @createTime 2015年10月23日,下午1:44:41
     * @updateTime 2015年10月23日,下午1:44:41
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param context
     * @param string
     * @param color
     * @param start
     * @param end
     * @return
     */
    public static void setColorText(Context context, String string, int color, int start, int end, TextView tv) {
        tv.setText(getColorText(context, string, color, start, end));
    }

    public static SpannableString getColorText(Context context, String string, int color, int start, int end) {
        SpannableString ss = new SpannableString(string);
        ss.setSpan(new ForegroundColorSpan(context.getResources().getColor(color)), start, end == -1 ? string.length() : end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置文字中间划横线
     */
    public static Spannable getTextCenterLine(String content){
        // 数字横线 原价
        Spannable spanStrikethrough = new SpannableString(content);
        StrikethroughSpan stSpan = new StrikethroughSpan();
        spanStrikethrough.setSpan(stSpan, 0, content.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return spanStrikethrough;
    }

    public static void showView(View view) {
        view.setVisibility(View.VISIBLE);
    }

    public static void hideView(View view) {
        view.setVisibility(View.GONE);
    }

    public static void inVisibleView(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    /**
     * @createAuthor spideman
     * @param str  电话号码
     * @return
     */
    public static String  getPhoneNumber(String str){
        if(str!=null && !"".equals(str) && str.length()==11){
            String str1 = str.substring(0,3);
            String str2 = str.substring(7);
            return str1+"****"+str2;
        }else{
            return null;
        }

    }

    /**
     * 格式化价格 保留两位小数
     *
     * @author july.zeng 2014年4月28日 上午10:49:20
     * @param price
     * @return
     */
    public static Double formatPrice(Double price) {
        if (NotNull.isNotNull(price)) {
            return new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return 0.00d;
    }

    /***
     * 检查网络
     *
     * @param context
     * @return
     */
    public static boolean checkNet(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对像
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info == null || !info.isAvailable()) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /***
     * 切换编码格式
     */
    public static String changeCode(String code){
        if (code !=null) {
            String code2 = URLDecoder.decode(code);
            return  code2;
        }
        return "";
    }

    /**
     *   设置公共的空视图显示，注意ListView的最外层布局为RelativieLayout
     * @param mContext
     * @param content
     * @param mListView
     */
    public static void setCommonEmptyView(Context mContext,String content, ListView mListView){
        if (mContext !=null) {
            TextView emptyView = new TextView(mContext);
            emptyView.setText(content);

            RelativeLayout.LayoutParams parm = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            parm.addRule(RelativeLayout.CENTER_IN_PARENT);
            emptyView.setLayoutParams(parm);
            if (((ViewGroup) mListView.getParent()).getChildAt(0) != emptyView) {
                if (mListView.getHeaderViewsCount() > 0) {
                    mListView.removeAllViewsInLayout();
                }
                ((ViewGroup) mListView.getParent()).addView(emptyView);
                mListView.setEmptyView(emptyView);
            } else {
                //移除之前添加过的emptyView
                ((ViewGroup) mListView.getParent()).removeView(emptyView);
            }
        }
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }


    /**
     *
     * @author  将限时抢购获取到的毫秒数转化为String
     *
     */
    public static String formatTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long mss = ms / 1000;
        long seconds = mss % 60;
        long minutes = (mss % 3600) / 60;
        long hours = mss / 3600;
        String strDay = day < 10 ? "0" + day : "" + day; // 天
        String strHour = hours < 10 ? "0" + hours : "" + hours;// 小时
        String strMinute = minutes < 10 ? "0" + minutes : "" + minutes;// 分钟
        String strSecond = seconds < 10   ? "0" + seconds : "" + seconds;// 秒
        return strDay + ":" + strHour + ":" + strMinute + ":" + strSecond;
    }
    public static void saveLocalCityName(String cityName){
        BaseApp.editor.putString("address", cityName).commit();
    }
    public static String getLocalCityName(){
        return BaseApp.sp.getString("address", "");
    }
    public static void saveLocalCityNameToshow(String cityName){
        BaseApp.editor.putString("cityName", cityName).commit();
    }
    public static String getLocalCityNameToshow(){
        return BaseApp.sp.getString("cityName", "");
    }
    public static void saveUserType(String userType){

        BaseApp.editor.putString("userType", userType).commit();
    }
    public static String getUserType(){
        return BaseApp.sp.getString("userType", "");
    }
    public static void saveChoseType(String choseType){

        BaseApp.editor.putString("choseType", choseType).commit();
    }
    public static String getChoseType(){
        return BaseApp.sp.getString("choseType", "");
    }
    /**
     * @Description TODO<获取定位城市名>
     * @version 1.0
     * @createTime 2015年10月14日,上午11:24:50
     * @updateTime 2015年10月14日,上午11:24:50
     * @createAuthor
     * @updateAuthor
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     * @return
     */
    public static String getLocalCity(){
        return BaseApp.sp.getString("city", "");
    }
    /**
     *
     * @Description TODO<保存定位城市名>
     * @version 1.0
     * @createTime 2015年10月14日,上午11:24:33
     * @updateTime 2015年10月14日,上午11:24:33
     * @createAuthor
     * @updateAuthor
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param city
     */
    public static void saveLocalCity(String city){

        BaseApp.editor.putString("city", city).commit();
    }


    public static void saveToken(String token){

        BaseApp.editor.putString("token", token).commit();
    }

    /**
     * 拿到本地存储的refreshToken
     * @return
     */
    public static String getTokenString(){

       return BaseApp.sp.getString("token", "");
    }
    /**
     * 拿到本地存储的用户手机号
     * @return
     */
    public static String getUserPhone(){

       return BaseApp.sp.getString("phone", "");
    }
    /**
     * 存储的用户手机号
     * @return
     */
    public static void saveUserPhone(String phone){

       BaseApp.editor.putString("phone", phone).commit();
    }

    public static void saveSpStringData(String key, String value){
        BaseApp.editor.putString(key, value).commit();
    }

    public static String getSpStringData(String key){
        return BaseApp.sp.getString(key, "");
    }
}
