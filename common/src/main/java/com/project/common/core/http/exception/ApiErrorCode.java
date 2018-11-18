package com.project.common.core.http.exception;

/**
 * 项目：xxx_xxx
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2018/6/10 16:23
 * 描    述：网络异常的code码
 * 修订历史：
 */

public class ApiErrorCode {

    /** 网络异常 **/
    public static final String ERROR_NO_INTERNET = "202";
    public static final String ERROR_SERVER_EXCEPTION = "500";
    public static final String ERROR_AUTHER_EXCEPTION = "401";
    public static final String ERROR_WITH_DRAW_NUMS = "1507";//1507超过当日提现次数限制5次
    public static final String ERROR_WITH_DRAW_MONEY_MAX = "1508";//1508超过当日提现最高限额200,000
    public static final String ERROR_WITH_DRAW_MONEY_MIN = "1509";//1509低于提现最低限额10元
    public static final String ERROR_WITH_DRAW_MONEY_LIMIT = "1510";//1510输入金额大于可提现金额
    public static final String ERROR_WITH_DRAW_MONEY_NO_FEE = "1511";//1511扣除提现金额后无法支付手续费


}
