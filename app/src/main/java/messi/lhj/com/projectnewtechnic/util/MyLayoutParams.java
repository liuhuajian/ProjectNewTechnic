package messi.lhj.com.projectnewtechnic.util;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by messi on 2017/8/29.
 */

public class MyLayoutParams {
    public static void setViewMatch(View headView){
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headView.setLayoutParams(layoutParams);
    }
}
