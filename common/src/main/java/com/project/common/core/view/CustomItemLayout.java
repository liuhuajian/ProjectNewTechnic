package com.project.common.core.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.common.R;
import com.project.common.core.utils.BitmapUtil;
import com.project.common.core.utils.DisplayUtil;
import com.project.common.core.utils.ImageLoader;
import com.project.common.core.utils.NotNull;

/**
 * 
 * @Description TODO<自定义通用布局>
 * @author 曾招林
 * @date 2015年9月22日
 * @Copyright:
 */

public class CustomItemLayout extends FrameLayout {

	// 文字内容
	private String leftText,secondName, centerText;
	private CharSequence rightText;
	// item左侧图标,右侧图标,右侧箭头
	private ImageView ivItemIcon, ivRightImage, ivArrow;
	private TextView tvLeft,tvSecondName, tvCenter, tvRight;
	private int marginRight;
	private Context mContext;

	public CustomItemLayout(Context context) {
		this(context, null);
	}

	public CustomItemLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		this.mContext = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.custom_common_item_layout, this);
		ivItemIcon = (ImageView) findViewById(R.id.iv_item_icon);
		ivItemIcon.setVisibility(GONE);
		ivRightImage = (ImageView) findViewById(R.id.iv_right_image);
		ivArrow = (ImageView) findViewById(R.id.iv_arrow);
		tvLeft = (TextView) findViewById(R.id.tv_left);
		tvSecondName = (TextView) findViewById(R.id.tv_seconde_name);
		tvCenter = (TextView) findViewById(R.id.tv_center);
		tvRight = (TextView) findViewById(R.id.tv_right);

		TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.ItemLayout, defStyleAttr, 0);

		int n = typeArray.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = typeArray.getIndex(i);
			if (attr == R.styleable.ItemLayout_hasArrow) {
				if (!typeArray.getBoolean(attr, false)) {
					ivArrow.setVisibility(View.GONE);
				}

				// item右侧箭头设置不同图片
			} else if (attr == R.styleable.ItemLayout_rightArrowSrc) {
				int resId = typeArray.getResourceId(attr, -1);
				ivArrow.setImageResource(resId);

				// item图标
			} else if (attr == R.styleable.ItemLayout_ItemIcon) {
				int resourceId = typeArray.getResourceId(attr, 0);
				ivItemIcon.setImageResource(resourceId);
				ivItemIcon.setVisibility(VISIBLE);
				// item左侧描述
			} else if (attr == R.styleable.ItemLayout_LeftText) {
				leftText = typeArray.getString(attr);

				// item左侧描述字体颜色
			} else if (attr == R.styleable.ItemLayout_LeftTextColor) {// 设置左侧显示内容的字体颜色，默认黑色
				tvLeft.setTextColor(typeArray.getColor(attr, mContext.getResources().getColor(R.color.black)));


				// item左侧描述字体大小
			} else if (attr == R.styleable.ItemLayout_LeftTextSize) {
				float leftTextSize = typeArray.getDimension(attr, 12);
				tvLeft.setTextSize(leftTextSize / 2);

				// item左侧第二名称描述
			} else if (attr == R.styleable.ItemLayout_SecondNameText) {
				secondName = typeArray.getString(attr);

				// item左侧第二名称描述字体颜色
			} else if (attr == R.styleable.ItemLayout_SecondNameTextColor) {// 设置左侧显示内容的字体颜色，默认黑色
				tvSecondName.setTextColor(typeArray.getColor(attr, mContext.getResources().getColor(R.color.black)));


				// item左侧第二名称描述字体大小
			} else if (attr == R.styleable.ItemLayout_SecondNameTextSize) {
				float SecondNameTextSize = typeArray.getDimension(attr, 12);
				tvSecondName.setTextSize(SecondNameTextSize / 2);

				// item左侧显示内容
			} else if (attr == R.styleable.ItemLayout_TextName) {
				centerText = typeArray.getString(attr);

				// 右侧显示内容
			} else if (attr == R.styleable.ItemLayout_RightText) {
				rightText = typeArray.getString(attr);

				// 右侧显示文字颜色
			} else if (attr == R.styleable.ItemLayout_RightTextColor) {// 设置右侧显示内容的字体颜色，默认灰色
				tvRight.setTextColor(typeArray.getColor(attr, mContext.getResources().getColor(R.color.text_gray)));

				// 右侧显示文字右边距
			} else if (attr == R.styleable.ItemLayout_RightTextmarginRight) {// 设置右侧显示内容的右边距，默认10dp
				int dp = DisplayUtil.px2dip(context, 10);
				marginRight = (int) typeArray.getDimension(attr, dp);
				tvRight.setPadding(0, 0, marginRight, 0);

				// 右侧显示文字背景
			} else if (attr == R.styleable.ItemLayout_RightTextBgColor) {// 设置右侧显示内容的背景，默认无背景
				int bg_color = typeArray.getColor(attr, 0xffffff);
				tvRight.setBackgroundColor(bg_color);

			} else if (attr == R.styleable.ItemLayout_RightTextSrc) {// 设置右侧显示内容的背景图，默认无背景图
				int ResourceId = typeArray.getResourceId(attr, 0);
				tvRight.setBackgroundResource(ResourceId > 0 ? ResourceId : -1);

			} else if (attr == R.styleable.ItemLayout_RightTextImage) {// 设置右侧显示图片，默认
				int RId = typeArray.getResourceId(attr, 0);
				ivRightImage.setBackgroundResource(RId != 0 ? RId : -1);
				ivRightImage.setVisibility(View.VISIBLE);

				// 右侧显示文字大小
			} else if (attr == R.styleable.ItemLayout_RightTextSize) {// 设置右侧显示内容的字体大小，默认大小12
				int textSize = (int) typeArray.getDimension(attr, 12);
				tvRight.setTextSize(textSize);

			}
		}
		if (NotNull.isNotNull(leftText)) {
			tvLeft.setText(leftText);
		}
		if (NotNull.isNotNull(secondName)) {
			tvSecondName.setVisibility(VISIBLE);
			tvSecondName.setText(secondName);
		}

		setTextName(centerText);
		setRightText(rightText);
		typeArray.recycle();

	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @author 曾招林
	 * @date 2015-1-9 上午10:36:48
	 * @param url
	 */
	public void setImageFormUrl(String url) {
		ivRightImage.setVisibility(View.VISIBLE);
		if (NotNull.isNotNull(url)) {
			ImageLoader.loadCircleImageView((Activity) mContext,url,ivRightImage);
		} else {
			ivRightImage.setImageResource(R.drawable.bg_image_default);
		}
	}
	
	public void setImageFormUri(Uri uri) {
		ivRightImage.setVisibility(View.VISIBLE);
		if (NotNull.isNotNull(uri)) {
			ivRightImage.setImageBitmap(BitmapUtil.getBitmap(mContext, uri));
		} else {
			ivRightImage.setImageResource(R.drawable.icon_no);
		}
	}
	
	/**
	 * 
	 * @description< 设置左边描述内容>
	 * @version 1.0
	 * @createTime 2015年10月10日,上午10:12:44
	 * @updateTime 
	 * @createAuthor 曾招林
	 * @updateAuthor
	 * @updateInfo ()
	 * @param
	 */
	public void setLeftText(String leftText) {
		if (NotNull.isNotNull(leftText)) {
			tvLeft.setText(leftText);
		}
	}
	public void setSecondNameText(String secondName) {
		if (NotNull.isNotNull(secondName)) {
			tvSecondName.setVisibility(VISIBLE);
			tvSecondName.setText(secondName);
		}
	}

	public TextView getRightTextView() {
		return tvRight;
	}

	public void setRightText(CharSequence text) {
		rightText = text;
		if (NotNull.isNotNull(rightText)) {
			tvRight.setText(rightText);
			tvRight.setVisibility(View.VISIBLE);
		} else {
			tvRight.setVisibility(View.GONE);
		}
	}
	
	public void setRightTextColor(int color) {
		if (NotNull.isNotNull(rightText)) {
			tvRight.setTextColor(color);
			tvRight.setVisibility(View.VISIBLE);
		} else {
			tvRight.setVisibility(View.GONE);
		}
	}

	public void setTextName(String text) {
		centerText = text;
		if (NotNull.isNotNull(centerText)) {
			tvCenter.setText(centerText);
			tvCenter.setVisibility(View.VISIBLE);
		} else {
			tvCenter.setVisibility(View.GONE);
		}
	}

	public void setRightTextSrc(int resId) {
		tvRight.setBackgroundResource(resId > 0 ? resId : -1);
	}
	/**
	 * @description<设置图片>
	 * @version 1.0
	 * @createTime 2015年9月29日,下午5:37:52
	 * @updateTime 2015年9月29日,下午5:37:52
	 * @createAuthor 曾招林
	 * @updateAuthor
	 * @updateInfo ()
     * @param
     * @param bitmap
     */
	@SuppressLint("NewApi") 
	public void setRightImageBitmap(Bitmap bitmap) {
		ivRightImage.setImageBitmap(bitmap);
	}

	// 省略号的位置设置
	public void setTextNameMaquee(TruncateAt where) {
		tvCenter.setEllipsize(where);
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//		int resultW = widthSize;
//		int resultH = DisplayUtil.dip2px(mContext,44);
//
//		if ( heightMode == MeasureSpec.EXACTLY ) {
//				resultH = MeasureSpec.getSize(heightMeasureSpec);
//		}
//
//		setMeasuredDimension(resultW, resultH);
		super.onMeasure(widthMeasureSpec,heightMeasureSpec);
	}
}
