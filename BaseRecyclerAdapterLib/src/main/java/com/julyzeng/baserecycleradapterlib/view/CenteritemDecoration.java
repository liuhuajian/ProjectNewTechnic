package com.julyzeng.baserecycleradapterlib.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by julyzeng on 2017/2/20.
 */
public class CenteritemDecoration extends RecyclerView.ItemDecoration {

    int height = 1;
    public CenteritemDecoration(int dividerHeight) {
        this.height = dividerHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if(parent.getChildLayoutPosition(view) !=0) {

            outRect.bottom = height;
            outRect.top = 0;
            if ((parent.getChildLayoutPosition(view)) % 2 == 0) {
                outRect.left = 0;
            } else {
                outRect.left = height;
            }
            outRect.right = height;

        }else {
            outRect.bottom = height;
        }
    }
}
