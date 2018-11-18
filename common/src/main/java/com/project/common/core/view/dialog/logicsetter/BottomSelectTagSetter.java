package com.project.common.core.view.dialog.logicsetter;

import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.project.common.R;
import com.project.common.core.http.bean.BaseTagBean;
import com.project.common.core.utils.DisplayUtil;
import com.project.common.core.utils.ToastUtil;
import com.project.common.core.view.dialog.CommonFragmentDialog;
import com.project.common.core.view.dialog.CommonFragmentDialog.ILogicSetter;
import com.project.common.core.view.dialog.data.BaseBottomTagBean;

import java.util.List;

/**
 * 项目 国民健康
 *
 * @Create by yexm
 * @创建日期 2018/8/23 17:04
 * @版本 0.2
 * @类说明: 选择标签
 */

public class BottomSelectTagSetter<T extends BaseTagBean> implements ILogicSetter {
    private ILogicSetterClickLisenter onConfirmClickListener;

    private List<T> mData;
    private String mTitle;
    private String mTagId;

    public BottomSelectTagSetter(List<T> datas) {
        this(datas, "");
    }

    public BottomSelectTagSetter(List<T> datas, String title) {
        this(datas, title, "");
        mData = datas;
    }

    public BottomSelectTagSetter(List<T> datas, String title, String mTypeId) {
        mData = datas;
        this.mTitle = title;
        this.mTagId = mTypeId;
    }

    @Override
    public ILogicSetter setLogic(final CommonFragmentDialog fragmentDialog, View rootView) {
        final RecyclerView recyclerView = rootView.findViewById(R.id.rv_dialog_select);
        recyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), 3, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = DisplayUtil.dip2px(parent.getContext(), 9);
                outRect.bottom = DisplayUtil.dip2px(parent.getContext(), 9);
                if (parent.getChildAdapterPosition(view) % 3 == 0) {
                    outRect.right = DisplayUtil.dip2px(parent.getContext(), 10);
                    outRect.left = DisplayUtil.dip2px(parent.getContext(), 0);
                }
                if (parent.getChildAdapterPosition(view) % 3 == 1) {
                    outRect.right = DisplayUtil.dip2px(parent.getContext(), 5);
                    outRect.left = DisplayUtil.dip2px(parent.getContext(), 5);
                }
                if (parent.getChildAdapterPosition(view) % 3 == 2) {
                    outRect.right = DisplayUtil.dip2px(parent.getContext(), 0);
                    outRect.left = DisplayUtil.dip2px(parent.getContext(), 10);
                }
                if (parent.getChildAdapterPosition(view) / 3 == 0) {
                    outRect.top = DisplayUtil.dip2px(parent.getContext(), 40);
                }
            }
        });
        TextView cancel = rootView.findViewById(R.id.tv_cancel);
        TextView comfirm = rootView.findViewById(R.id.tv_comfirm);
        TextView title = rootView.findViewById(R.id.tv_title);
        title.setText(mTitle);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentDialog.dismiss();
            }
        });
        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((SelectDialogAdapter) recyclerView.getAdapter()).getmCurrentPositionItem() == null) {
                    ToastUtil.getInstance().showToast("请选择条目");
                    return;
                }
                if (onConfirmClickListener != null) {
                    onConfirmClickListener.click(((SelectDialogAdapter) recyclerView.getAdapter()).getmCurrentPositionItem());
                }

                fragmentDialog.dismiss();
            }
        });
        recyclerView.setAdapter(new SelectDialogAdapter(mData, mTagId));
        return this;
    }

    public BottomSelectTagSetter setLogicSetterComfirmLisenter(ILogicSetterClickLisenter onClickListener) {
        if (onClickListener != null) {
            this.onConfirmClickListener = onClickListener;
        }
        return this;
    }


    public interface ILogicSetterClickLisenter<T extends BaseTagBean> {
        void click(T t);
    }

    private class SelectDialogAdapter extends BaseQuickAdapter<T, BaseViewHolder> {

        public T getmCurrentPositionItem() {
            return mCurrentPosition;
        }

        //当前选中的位置
        private T mCurrentPosition;

        public SelectDialogAdapter(@Nullable List<T> data) {
            super(R.layout.item_select_tag, data);
        }

        public SelectDialogAdapter(@Nullable List<T> data, String currentItemTagId) {
            super(R.layout.item_select_tag, data);
            for (T t : data) {
                if (t.getTagId().equals(currentItemTagId)) {
                    mCurrentPosition = t;
                }
            }


        }

        @Override
        protected void convert(BaseViewHolder helper, final T item) {
            TextView textView = helper.getView(R.id.tv_select_tag);
            textView.setText(item.getTagName());
            if (item == mCurrentPosition) {
                textView.setSelected(true);
            } else {
                textView.setSelected(false);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCurrentPosition = item;
                    notifyDataSetChanged();
                }
            });
        }
    }
}
