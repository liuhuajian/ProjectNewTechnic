package com.project.common.core.utils;

import com.alibaba.fastjson.JSON;
import com.project.common.core.BaseApp;
import com.project.common.core.base.UserInfo;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目：国民健康平台
 *
 * @Creator:曾招林julyzeng
 * @创建日期： 2018/11/1 15:48
 * @版本0.2
 * @类说明：
 */

public class WYQYHelp {

    private static WYQYHelp instance;

    private WYQYHelp() {
    }

    // 单例模式中获取唯一的ExitAPPUtils实例
    public static WYQYHelp getInstance() {
        if (null == instance) {
            instance = new WYQYHelp();
        }
        return instance;
    }

    /**
     * 联系客服
     */
    public void contactCustomerService() {

        UserInfo userInfo = UserInfoHelper.userInfo;
        YSFUserInfo user = new YSFUserInfo();
        user.userId = userInfo.getAccountNo();
        user.authToken = userInfo.getToken();

        Map<String, String> map = new HashMap<>();
        map.put("key", "real_name");
        map.put("value", userInfo.getNickName());

        Map<String, String> map1 = new HashMap<>();
        map1.put("key", "avatar");
        map1.put("value", userInfo.getHeadImg());

        Map<String, String> map2 = new HashMap<>();
        map2.put("key", "mobile_phone");
        map2.put("value", userInfo.getPhoneNo());

        List<Map<String, String>> mList = new ArrayList<>();
        mList.add(map);
        mList.add(map1);
        mList.add(map2);

        user.data = JSON.toJSONString(mList);
        Unicorn.setUserInfo(user);

        String title = "客服服务";
        /**
         * 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入。
         * 三个参数分别为：来源页面的url，来源页面标题，来源页面额外信息（保留字段，暂时无用）。
         * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
         */
        ConsultSource source = new ConsultSource("个人中心", title, "用户访问客服");
        Unicorn.openServiceActivity(BaseApp.mContext, title, source);


    }
}
