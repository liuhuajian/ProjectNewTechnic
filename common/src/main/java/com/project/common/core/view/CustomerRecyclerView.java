package com.project.common.core.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.AttributeSet;
import android.view.View;

import com.project.common.core.utils.DisplayUtil;
import com.project.common.core.utils.MyLogger;

/**
 * Created by messi on 16/11/25.
 */
public class CustomerRecyclerView extends RecyclerView {

    private int lastWidth;
    private Context context;
    private boolean restState;
    private SpaceItemDecoration itemDecoration;

    public CustomerRecyclerView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CustomerRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context ,attrs);
        this.context = context;
        init();
        addOnScrollListener(onScrollListener);
    }

    private void init() {
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        ((SimpleItemAnimator)getItemAnimator()).setSupportsChangeAnimations(false);
    }

    OnScrollListener onScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    /**
     * 设置间隔
     */
    class SpaceItemDecoration extends RecyclerView.ItemDecoration{
        private int verticalSpace;
        private int horizontalSpce;
        public SpaceItemDecoration(int verticalSpace ,int horizontalSpce){
            this.verticalSpace = verticalSpace;
            this.horizontalSpce = horizontalSpce;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            LayoutManager layoutManager = parent.getLayoutManager();
            int childAdapterPosition = parent.getChildAdapterPosition(view);
            if (layoutManager instanceof GridLayoutManager){
                int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
                MyLogger.i("spanCount-->"+spanCount);
                if (childAdapterPosition !=0 ||childAdapterPosition !=1){
                    outRect.top = verticalSpace;
                }

                if (childAdapterPosition != parent.getChildCount()-1 ||childAdapterPosition != parent.getChildCount() -2){
                    outRect.bottom = verticalSpace;
                }

                if (childAdapterPosition %spanCount !=0){
                    outRect.left = horizontalSpce;
                }

                if ((childAdapterPosition+1) %spanCount !=0){
                    outRect.right = horizontalSpce;
                }
            }else if (layoutManager instanceof LinearLayoutManager){
//                if (childAdapterPosition !=0){
//                    outRect.top = verticalSpace;
//                }
                if (childAdapterPosition !=0){
                    if (((LinearLayoutManager) layoutManager).getOrientation() ==LinearLayoutManager.HORIZONTAL){
                        outRect.left = horizontalSpce;
                    }else{
                        outRect.top = verticalSpace;
                    }
                }
            }
        }
    }

    /**
     * 关闭默认局部刷新动画
     */
    public void closeDefaultAnimator() {
        this.getItemAnimator().setAddDuration(0);
        this.getItemAnimator().setChangeDuration(0);
        this.getItemAnimator().setMoveDuration(0);
        this.getItemAnimator().setRemoveDuration(0);
        ((SimpleItemAnimator) this.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    public void setGridItmSpaceVertical(int verticalSpace,int horizontalSpce){
        verticalSpace = DisplayUtil.dip2px(context,verticalSpace);
        horizontalSpce = DisplayUtil.dip2px(context,horizontalSpce);
        itemDecoration = new SpaceItemDecoration(verticalSpace, horizontalSpce);
        addItemDecoration(itemDecoration);
    }

    public void setGridItmSpaceVerticalPix(int verticalSpace,int horizontalSpce){
        itemDecoration = new SpaceItemDecoration(verticalSpace, horizontalSpce);
        addItemDecoration(itemDecoration);
    }

    public void removeGridItemSpaceVertical(){
        removeItemDecoration(itemDecoration);
    }
    public void setUnRestLayout(boolean restState){
        this.restState = restState;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        restState = false;
        if (!restState) {
            return;
        }
        if (getChildCount() ==1) {
            int newWidth = 0;
            for (int i = 0; i < getChildCount(); i++) {
                newWidth += getChildAt(i).getMeasuredWidth();
            }
            if (lastWidth!=newWidth) {
                lastWidth = newWidth;

                int empty = getMeasuredWidth() - newWidth;
                MyLogger.d("getMeasuredWidth-->"+getMeasuredWidth()+"--newWidth-->"+newWidth);
                if (empty > 0) {
                    if (getPaddingLeft() == empty / 2) {
                        return;
                    }
                    MyLogger.d("onLayout-->"+"empty-->"+empty+"--getPaddingLeft-->"+getPaddingLeft());
                    //加50是为了完全显示item，不加需要滑动才完全显示
                    int current = DisplayUtil.dip2px(context, 15);
                    int paddLeft = empty / 2 ;
                    MyLogger.d("current-->"+current);
                    setPadding(paddLeft, 0, 0, 0);
                    //如果不再一次onLayout，子view就不会有padding
                    super.onLayout(changed, l, t, r, b);
                }
            }
        }
    }


}
