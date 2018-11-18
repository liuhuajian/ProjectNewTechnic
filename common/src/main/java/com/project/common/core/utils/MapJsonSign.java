package com.project.common.core.utils;

import java.util.Map.Entry;
import java.util.TreeMap;

public class MapJsonSign {

    public static String signCheck(TreeMap<String, String> map) {
        int num = 0;
        StringBuffer strb = new StringBuffer();
        for (Entry<String, String> m : map.entrySet()) {
            strb.append(m.getKey());
            strb.append("=");
            strb.append(m.getValue());
            if (num < map.size() - 1) {
                strb.append("&");
            }
            num++;
        }
        strb.append("07c940a6d6f525714aab0a39ac09be6b");

        return MD5Util.MD5Encode(strb.toString(), "UTF-8");
    }
}
