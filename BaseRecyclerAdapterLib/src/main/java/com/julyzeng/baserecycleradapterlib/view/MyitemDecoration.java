package com.julyzeng.baserecycleradapterlib.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by julyzeng on 2017/2/20.
 */
public class MyitemDecoration extends RecyclerView.ItemDecoration {

    int height = 1;
    public MyitemDecoration(int dividerHeight) {
        this.height = dividerHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0,0,0,height);

    }
}
