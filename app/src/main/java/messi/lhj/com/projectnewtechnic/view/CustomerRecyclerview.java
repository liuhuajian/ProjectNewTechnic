package messi.lhj.com.projectnewtechnic.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by messi on 2017/9/3.
 */

public class CustomerRecyclerview extends RecyclerView {
    public CustomerRecyclerview(Context context) {
        super(context);
        init();
    }

    public CustomerRecyclerview(Context context, @Nullable AttributeSet attrs) {
        this(context);
    }

    public CustomerRecyclerview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private int lastWidth;

    private void init() {
        setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

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
            outRect.bottom = verticalSpace;
            outRect.left = horizontalSpce;
        }
    }

    public void setGridItmSpaceVertical(int verticalSpace,int horizontalSpce){
        addItemDecoration(new SpaceItemDecoration(verticalSpace,horizontalSpce));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (getChildCount() ==1) {
            int newWidth = 0;
            for (int i = 0; i < getChildCount(); i++) {
                newWidth += getChildAt(i).getMeasuredWidth();
            }
            if (lastWidth!=newWidth) {
                lastWidth = newWidth;

                int empty = getMeasuredWidth() - newWidth;
                if (empty > 0) {
                    if (getPaddingLeft() == empty / 2) {
                        return;
                    }

                    setPadding(empty / 2, 0, empty / 2, 0);
                    //如果不再一次onLayout，子view就不会有padding
                    super.onLayout(changed, l, t, r, b);
                }
            }
        }
    }
}
