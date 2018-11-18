package com.julyzeng.baserecycleradapterlib.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by julyzeng on 2017/2/20.
 * gridlayout spancount= 2 分割线左中右
 */
public class CenterDecoration extends RecyclerView.ItemDecoration {

    int height = 1;
    public CenterDecoration(int dividerHeight) {
        this.height = dividerHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.bottom = 0;
        outRect.top = height;
        if ((parent.getChildLayoutPosition(view))%2  == 0){
            outRect.left = height;
            outRect.right = height;
        }else {
            outRect.left = 0;
            outRect.right = height;
        }
//        if ((parent.getChildLayoutPosition(view) == parent.getAdapter().getItemCount())){
//            outRect.right = 0;
//            outRect.bottom = height;
//        }

    }
}
