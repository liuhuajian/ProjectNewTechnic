package com.project.common.core.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.common.R;
import com.project.common.core.utils.NotNull;


/** julyzeng
 * 自定义对话框。
 */
public class CustomAlertDialog extends Dialog {
	
	public static CustomAlertDialog dialog;

    public CustomAlertDialog(Context context, int theme) {
        super(context, theme);
    }

    public CustomAlertDialog(Context context) {
        super(context);
    }

    /**
     * Helper class for creating a custom dialog
     */
        public static class Builder {

        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private int layoutId;

        private OnClickListener positiveButtonClickListener, negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Set the Dialog message from String
         *
         * @param message
         * @return
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param message
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Set a custom content view for the Dialog. If a message is set, the
         * contentView is not added to the Dialog...
         *
         * @param v
         * @return
         */
        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setContentView(int layoutId) {
            this.layoutId = layoutId;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the positive button text and it's listener
         *
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the negative button resource and it's listener
         *
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * Set the negative button text and it's listener
         *
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * Create the custom dialog
         */
        public CustomAlertDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final CustomAlertDialog dialog = new CustomAlertDialog(context, R.style.MyDialogStyle);
            dialog.setCanceledOnTouchOutside(false);
            View layout = inflater.inflate(R.layout.custom_dialog_layout, null);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER;
            WindowManager m =((Activity) context).getWindowManager();
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
            WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); // 获取对话框当前的参数值
            p.width = (int) (d.getWidth() * 0.75); // 宽度设置为屏幕的0.7
            dialog.getWindow().setAttributes(p);
            layoutParams.weight = p.width;
            dialog.addContentView(layout,layoutParams);
            // set the dialog titl
            if (null == title || "".equals(title)) {
                layout.findViewById(R.id.ll_title).setVisibility(View.GONE);
            } else {
                ((TextView) layout.findViewById(R.id.title)).setText(title);
                layout.findViewById(R.id.content).setBackgroundResource(R.drawable.bg_middle_custom_dialog);
            }
            // set the confirm button
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.positiveButton)).setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.positiveButton)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(View.GONE);
                layout.findViewById(R.id.content).setBackgroundResource(R.drawable.bg_bottom_custom_dialog);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.negativeButton)).setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.negativeButton)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            } else {
                // if no confirm button just set the visibility to GONE
//            	 layout.findViewById(R.id.content).setBackgroundResource(R.color.transparent);
                    layout.findViewById(R.id.negativeButton).setVisibility(View.GONE);
//                    layout.findViewById(R.id.dialog_view_line).setVisibility(View.GONE);
            }
            // set the content message
            if (message != null) {
                ((TextView) layout.findViewById(R.id.message)).setText(message);
            } else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                ((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();
                if (null != contentView.getParent()) {
                    ((ViewGroup) contentView.getParent()).removeView(contentView);
                }
                ((LinearLayout) layout.findViewById(R.id.content)).addView(contentView, layoutParams);
            }

            if ((positiveButtonText != null && negativeButtonText == null) || (positiveButtonText == null && negativeButtonText != null) ){

                layout.findViewById(R.id.content).setBackgroundResource(R.drawable.bg_top_custom_dialog);
                layout.findViewById(R.id.dialog_view_line).setVisibility(View.VISIBLE);
            }

            dialog.setContentView(layout);

            return dialog;
        }

        public CustomAlertDialog create(int resId) {
            final CustomAlertDialog dialog = new CustomAlertDialog(context, R.style.MyDialogStyle);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setContentView(resId);
            return dialog;
        }

    }

    public static void showDialog(Context mContext, Integer messageId, Integer confirmId, OnClickListener confirmListener) {
        showDialog(mContext, null, messageId, confirmId, null, confirmListener);
    }

    public static void showDialog(Context mContext, Integer titleId, Integer messageId, Integer confirmId, Integer cancelid,
                                  OnClickListener confirmListener) {
        showDialog(mContext, null, titleId, messageId, confirmId, cancelid, confirmListener);
    }

    public static void showDialog(Context mContext, View contentView, Integer titleId, Integer messageId, Integer confirmId, Integer cancelid,
                                  OnClickListener confirmListener) {
        showDialog(mContext, contentView, NotNull.isNotNull(titleId) ? mContext.getString(titleId) : null,
                NotNull.isNotNull(messageId) ? mContext.getString(messageId) : null, NotNull.isNotNull(confirmId) ? mContext.getString(confirmId)
                        : null, NotNull.isNotNull(cancelid) ? mContext.getString(cancelid) : null, confirmListener);
    }

    public static void showDialog(Context mContext, String message, OnClickListener confirmListener) {
        showDialog(mContext, null, null, message, mContext.getString(R.string.btn_sure), mContext.getString(R.string.btn_cancel), confirmListener);
    }

    public static void showDialog(Context mContext, String message, String confirm, String cancel, OnClickListener confirmListener) {
        showDialog(mContext, null, null, message, confirm, cancel, confirmListener);
    }
    
    public static void showDialog(Context mContext, String message, String confirm, OnClickListener confirmListener) {
    	showDialog(mContext, null, null, message, confirm, null, confirmListener);
    }
    

    public static void showDialog(Context mContext,  View contentView, String title) {
    	showDialog(mContext, contentView, title, null, null, null, null);
    }

    /**
     *
     * 方法描述<优惠券专用，>
     * @version 1.0
     * @createTime 2016年1月25日,下午2:33:26
     * @updateTime 2016年1月25日,下午2:33:26
     * @createAuthor 曾招林
     * @updateAuthor
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param mContext
     * @param  contentView 显示的布局
     *
     */
    public static float widthF =0,heightF=0;
    public static void showDialog(Context mContext, View contentView,float width,float height) {
        widthF = width;
        heightF = height;
        showDialog(mContext, contentView, null);
    }

    public static void showDialog(final Context mContext, final View contentView, String title, String message, String confirm, String cancel,
                                  OnClickListener confirmListener) {
        final Builder customBuilder = new Builder(mContext);
        if (NotNull.isNotNull(title)) {
            customBuilder.setTitle(title);
        }

        if (NotNull.isNotNull(message)) {
            customBuilder.setMessage(message);
        } else if (contentView != null) {
            customBuilder.setContentView(contentView);
        }

        if (NotNull.isNotNull(confirm)) {
            customBuilder.setPositiveButton(confirm, confirmListener);
        }

        if (NotNull.isNotNull(cancel)) {
            customBuilder.setNegativeButton(cancel, confirmListener);
        }

        dialog = customBuilder.create();

        Window dialogWindow = dialog.getWindow();
        if (widthF >0 && heightF>0) {

            WindowManager m = ((Activity) mContext).getWindowManager();
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
            WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
            /*
         * 将对话框的大小按屏幕大小的百分比设置
         */
            p.height = (int) (d.getHeight() * heightF); // 高度设置为屏幕的0.6
            p.width = (int) (d.getWidth() * widthF); // 宽度设置为屏幕的0.9
            dialogWindow.setAttributes(p);
        }
        dialog.show();
        if (dialog != null && dialogWindow != null) {
            Window window = dialog.getWindow() ;
            Display display =((Activity) mContext).getWindowManager().getDefaultDisplay();

            WindowManager.LayoutParams params = window.getAttributes() ;
            params.width =(int) (display.getWidth()*0.75); // 宽度设置为屏幕的0.7
            window.setAttributes(params);
        }
    }

    public static void dismissDialog(){

    	if (dialog!=null && dialog.isShowing()) {
		    dialog.dismiss();
		}
    }

}