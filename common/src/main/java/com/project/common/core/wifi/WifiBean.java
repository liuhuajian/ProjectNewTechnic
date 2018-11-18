package com.project.common.core.wifi;

import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * 项目：国民健康平台
 *
 * @Creator:liuhuajian
 * @创建日期： 2018/10/9 14:47
 * @版本0.2
 * @类说明：
 */
public class WifiBean implements Comparable<WifiBean> {

    public String wifiName;
    public String level;
    public String state;  //已连接  正在连接  未连接 三种状态
    public String capabilities;//加密方式

    @Override
    public int compareTo(@NonNull WifiBean o) {
        int level1 = Integer.parseInt(this.level);
        int level2 = Integer.parseInt(o.level);
        return level1 - level2;
    }

    @Override
    public String toString() {
        return "WifiBean{" +
                "wifiName='" + wifiName + '\'' +
                ", level='" + level + '\'' +
                ", state='" + state + '\'' +
                ", capabilities='" + capabilities + '\'' +
                '}';
    }
}
