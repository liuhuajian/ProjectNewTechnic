package com.project.common.core.download.update;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.common.R;
import com.project.common.core.download.update.entity.AutoUpdateEntity;


/**
 * Created by julyzeng on 2016-08-19.
 */
@SuppressWarnings("ALL")
public class AutoUpdateDialog extends AlertDialog{
    private static String TAG ="AutoUpdateDialog";
    private Context context;
    private AutoUpdateEntity entity;
    private AutoUpdateDialogListener listener;
    private AutoUpdateInterface updateInterface;
    public final static int UPDATE = 1;
    public final static int CANCEL = 2;
    private View dialogView;
    private TextView tvViersionName;
    private TextView tvUpdateMessage;
    private TextView btnCancel;
    private TextView btnSure;

    public AutoUpdateDialog(Context context,
                            AutoUpdateEntity entity,
                            AutoUpdateDialogListener listener,
                            AutoUpdateInterface updateInterface) {
        super(context);
        this.context = context;
        this.entity = entity;
        this.listener = listener;
        this.updateInterface = updateInterface;
        dialogView = LayoutInflater.from(context).inflate(R.layout.auto_update_dialog, null);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(dialogView);
        init();
    }

    private void init() {
        if (context != null && entity != null) {
            this.setCancelable(false);
            tvViersionName = obtainView(R.id.tv_version_name);
            tvUpdateMessage = obtainView(R.id.tv_update_message);
            btnCancel = obtainView(R.id.btn_cancel);
            btnSure = obtainView(R.id.btn_update);

            if (entity.isforce()){
                btnCancel.setVisibility(View.GONE);
            }else {
                btnCancel.setVisibility(View.VISIBLE);
            }
            tvViersionName.setText(context.getString(R.string.tv_new_version)+entity.getVersionName());
            if (entity.getUpdateMessage() !=null) {
                tvUpdateMessage.setText(entity.getUpdateMessage());
            }
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(CANCEL);
                    AutoUpdateDialog.this.dismiss();
                    Log.e(TAG,"init------CANCEL");
                }
            });
            btnSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(UPDATE);
                    AutoUpdateDialog.this.dismiss();
                    Log.e(TAG, "init------UPDATE");
                    updateInterface.autoUpdateComfirm(entity.getApkUrl());
                }
            });
        }
    }

    public interface AutoUpdateDialogListener {
        void onClick(int status);
    }

    public void showAutoUpdateDialog() {
        show();
    }

    private <T extends View> T obtainView(int id) {
        return (T) dialogView.findViewById(id);
    }
}
