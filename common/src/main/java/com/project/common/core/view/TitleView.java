package com.project.common.core.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.common.R;
import com.project.common.core.utils.DisplayUtil;
import com.project.common.core.utils.NotNull;


/**
 * @author 曾招林
 * @Description TODO<通用的titlebar>
 * @date 2015年9月21日
 * @Copyright: julyzeng
 */
public class TitleView extends  RelativeLayout {
	/** 上下文环境 */
	private Context context;
	/**
	 * 文本
	 */
	private String titleText, leftBtnText, rightBtnText;
	/**
	 * 是否有返回按钮、左侧按钮、右侧按钮
	 */
	private boolean hasBackBtn, hasLeftBtn, hasRightBtn;

    public LinearLayout getBackLayout() {
        return backLayout;
    }

    public void setBackLayout(LinearLayout backLayout) {
        this.backLayout = backLayout;
    }

    private LinearLayout backLayout;
    private ImageButton ibRight_toLeft;

    public ImageView getIvBack() {
        return ivBack;
    }

    private ImageView ivBack;
    private TextView btnRight;

    public TextView getTvTitle() {
        return tvTitle;
    }

    private TextView tvTitle;
    private Button btnLeft;
    private RelativeLayout rootView;


    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.title_view, this);
        backLayout = (LinearLayout) findViewById(R.id.back_layout);
        rootView = (RelativeLayout) findViewById(R.id.rootView);
        ivBack = (ImageView) findViewById(R.id.ib_back);
        ibRight_toLeft = (ImageButton) findViewById(R.id.ib_btn_right_toleft);
        btnLeft = (Button) findViewById(R.id.btn_left);
        btnRight = (TextView) findViewById(R.id.btn_right);
        tvTitle = (TextView) findViewById(R.id.tv_title);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleView, defStyleAttr, 0);
        int n = ta.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = ta.getIndex(i);
            if (attr == R.styleable.TitleView_titleText) {
                titleText = ta.getString(attr);

            } else if (attr == R.styleable.TitleView_leftBtnText) {
                leftBtnText = ta.getString(attr);

            } else if (attr == R.styleable.TitleView_leftBtnTextSize) {
                btnLeft.setTextSize(ta.getDimension(attr, DisplayUtil.sp2px(context, 16)));

            } else if (attr == R.styleable.TitleView_rightBtnText) {
                rightBtnText = ta.getString(attr);

            } else if (attr == R.styleable.TitleView_hasLeftBtn) {// 是否有左侧按钮，默认设置为false
                hasLeftBtn = ta.getBoolean(attr, false);

            } else if (attr == R.styleable.TitleView_hasRightBtn) {// 是否有右侧按钮，默认设置为false
                hasRightBtn = ta.getBoolean(attr, false);

			} else if (attr == R.styleable.TitleView_RightBtnIcon) {// 设置右侧按钮为图标（当可点击状态时）
				if (hasRightBtn && ta.getResourceId(attr, -1) != -1) {
					if (rightBtnText == null) {
						btnRight.setCompoundDrawables(null, null, null, null);
					}
					btnRight.setBackgroundResource(ta.getResourceId(attr, -1));
					LayoutParams layout = new LayoutParams(DisplayUtil.dip2px(context, 26), DisplayUtil.dip2px(context, 26));
					// layout.topMargin = DisplayUtil.dip2px(context, 10);
					// layout.bottomMargin = DisplayUtil.dip2px(context, 10);
					// layout.leftMargin = DisplayUtil.dip2px(context, 10);
					layout.rightMargin = DisplayUtil.dip2px(context, 15);
					layout.addRule(RelativeLayout.CENTER_VERTICAL);
					layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
					btnRight.setGravity(Gravity.CENTER_VERTICAL);
					btnRight.setLayoutParams(layout);
				}

			} else if (attr == R.styleable.TitleView_RightBtnToLeftIcon) {
				ibRight_toLeft.setBackgroundResource(ta.getResourceId(attr, -1));

            } else if (attr == R.styleable.TitleView_hasBackBtn) {// 是否有返回按钮，默认设置为false
                hasBackBtn = ta.getBoolean(attr, false);

            } else if (attr == R.styleable.TitleView_custom_background) {// 设置自定义背景色
                rootView.setBackgroundColor(ta.getColor(attr, getResources().getColor(R.color.white)));

            } else if (attr == R.styleable.TitleView_custom_text_color) {// 设置自定义颜色色
                tvTitle.setTextColor(ta.getColor(attr, getResources().getColor(R.color.gray)));
                btnLeft.setTextColor(ta.getColor(attr, getResources().getColor(R.color.gray)));
                btnRight.setTextColor(ta.getColor(attr, getResources().getColor(R.color.gray)));

            }
        }
        ta.recycle();

        if (NotNull.isNotNull(titleText)) {
            tvTitle.setText(titleText);
        }
        if (NotNull.isNotNull(leftBtnText)) {
            btnLeft.setText(leftBtnText);
        }
        if (NotNull.isNotNull(rightBtnText)) {
            btnRight.setText(rightBtnText);
            // android.widget.RelativeLayout.LayoutParams layout = new
            // android.widget.RelativeLayout.LayoutParams(android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT,android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
            // btnRight.setPadding(25, 25, 25, 10);
        }

        if (hasBackBtn) {// back和left只存在一个
            btnLeft.setVisibility(View.GONE);
            //tvBack.setOnClickListener(new BackClick());
            backLayout.setOnClickListener(new BackClick());
        } else {
            backLayout.setVisibility(View.GONE);
        }

        if (!hasRightBtn) {
            btnRight.setVisibility(View.GONE);
        }
    }


    /**
     * 返回按钮统一处理
     */
    private class BackClick implements OnClickListener {

        @Override
        public void onClick(View v) {
            ((Activity) getContext()).finish();
        }

    }

    public void removeRightBtn() {
        if (NotNull.isNotNull(btnRight)) {
            btnRight.setText("");
            btnRight.setClickable(false);
        }
    }

    public TextView getBtnRight() {
        return btnRight;
    }

    /**
     * 设置右边是否有按钮显示
     *
     * @param isHave
     */
    public void setIsHaveRightBtn(boolean isHave) {
        if (isHave) {
            hasRightBtn = true;
            btnRight.setVisibility(View.VISIBLE);
        } else {
            hasRightBtn = false;
            btnRight.setVisibility(View.GONE);
        }
    }

	public void setTitleText(String text) {
		tvTitle.setText(text);
	}

	public void setLeftButtonText(String text) {
		btnLeft.setText(text);
	}

	public void setTitleClickListener(OnClickListener l) {
		tvTitle.setClickable(true);
		tvTitle.setOnClickListener(l);
	}

	public void setLeftButtonListener(OnClickListener l) {
		backLayout.setOnClickListener(l);
	}

	public void setRightImageResource(int resId) {
		//开启了设置右边是否有按钮才显示
		if (hasRightBtn) {
			btnRight.setBackgroundResource(resId);
		}
	}

	public void setRightButtonText(String text) {
		btnRight.setText(text);
	}

	public void setRightButtonTextColor(int color){
        btnRight.setTextColor(color);
    }

	public void setRightBtnTextAndListener(String text, OnClickListener l) {
		btnRight.setText(text);
		if (btnRight.isClickable()) {
			btnRight.setOnClickListener(l);
		}
	}

	public void setRightButtonListener(OnClickListener l) {
		btnRight.setFocusable(true);
		btnRight.setOnClickListener(l);
	}

	public void setRightAlignToLeftButtonListener(OnClickListener l) {
		ibRight_toLeft.setOnClickListener(l);
	}

	public void setRightAlignToLeftImageResource(int resId) {
		ibRight_toLeft.setBackgroundResource(resId);
	}

	public void showInput(boolean show) {
		try {
			if (show) {
				InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInputFromInputMethod(((Activity) context).getCurrentFocus().getApplicationWindowToken(), 0);
			} else {
				InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getApplicationWindowToken(), 0);
			}
		} catch (NullPointerException e1) {

			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
