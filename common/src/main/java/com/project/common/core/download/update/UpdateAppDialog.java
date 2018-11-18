package com.project.common.core.download.update;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.project.common.R;
import com.project.common.core.download.update.receiver.NetworkReceiver;
import com.project.common.core.download.update.service.DownloadService;
import com.project.common.core.utils.DisplayUtil;
import com.project.common.core.utils.NotNull;

/**
 * 项目：国民健康
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2017/9/06 16:19
 * 描    述：app升级更新对话框。
 * 修订历史：
 */
public class UpdateAppDialog implements DialogInterface.OnDismissListener{

	private AlertDialog mDialog;

	private View mDialogView;

	private Activity activity;
	private TextView tvProgress;

	public UpdateAppDialog(Context context) {
		this.activity = (Activity) context;
		initView(context);
		IntentFilter intentFilter = new IntentFilter(DownloadService.ACTION_UPDATE);
		activity.registerReceiver(new LoadingReceiver(),intentFilter);
	}

	// 初始化界面
	public void initView(Context context) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mDialogView = inflater.inflate(R.layout.dialog_upate_app, null);
        tvProgress = (TextView) mDialogView.findViewById(R.id.tv_progress);
		mDialog = new AlertDialog.Builder(activity,R.style.MyDialogStyle).create();

	}
	public void updateProgress(String progress){
		tvProgress.setText(progress+"%");
	}

	// 打开dialog
	public void showDialog() {
		if (mDialog != null && !mDialog.isShowing()) {
			mDialog.show();
			// 设置布局
			mDialog.setContentView(mDialogView);
			Window w = mDialog.getWindow();
			// 进出动画
			w.setWindowAnimations(R.style.AnimBottom);
			// 全屏
			w.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			// 底部显示
			WindowManager.LayoutParams lp = w.getAttributes();
			lp.gravity = Gravity.CENTER;
			lp.width = (int) (DisplayUtil.getScreenWidthPx(activity) * 0.8);
			lp.height = 480;
			w.setAttributes(lp);
			mDialog.setCancelable(false);
			mDialog.setCanceledOnTouchOutside(false);
		}
	}

	// 关闭dialog
	public void closeDialog() {
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
		}
	}


	@Override
	public void onDismiss(DialogInterface dialog) {

	}


	class LoadingReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (DownloadService.ACTION_UPDATE.equals(intent.getAction())) {

				if (NotNull.isNotNull(intent) && NotNull.isNotNull(intent.getExtras())){
					updateProgress(intent.getExtras().getInt(NetworkReceiver.FINISH_PROGRESS)+"");

					if (intent.getExtras().getInt(NetworkReceiver.FINISH_PROGRESS) == 100){
						closeDialog();
					}
				}

			}
		}
	}

}
