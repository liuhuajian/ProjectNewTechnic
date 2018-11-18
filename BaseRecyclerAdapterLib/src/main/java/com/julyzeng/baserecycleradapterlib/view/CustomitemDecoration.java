package com.julyzeng.baserecycleradapterlib.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *  自定义边距item
 */
public class CustomitemDecoration extends RecyclerView.ItemDecoration {

    int l = 1;
    int r = 1;
    int t = 1;
    int b = 1;
    public CustomitemDecoration(int l,int t,int r,int b) {
        this.l = l;
        this.r = r;
        this.t = t;
        this.b = b;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.set(l,t,r,b);
    }
}
