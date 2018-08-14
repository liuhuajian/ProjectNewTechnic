package messi.lhj.com.projectnewtechnic;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import messi.lhj.com.projectnewtechnic.anim.AnimActivity;
import messi.lhj.com.projectnewtechnic.chart.LineChartActivity;
import messi.lhj.com.projectnewtechnic.circlehead.CircleHeadActivity;
import messi.lhj.com.projectnewtechnic.common.Constants;
import messi.lhj.com.projectnewtechnic.gaode.GaodeActivity;
import messi.lhj.com.projectnewtechnic.headAndfoot.RecyclerViewActivity;
import messi.lhj.com.projectnewtechnic.nfc.NFCDemoActivity;
import messi.lhj.com.projectnewtechnic.progressbar.ProgressbarActivity;
import messi.lhj.com.projectnewtechnic.refreshAndreloadmore.MainActivity;
import messi.lhj.com.projectnewtechnic.smoothdelete.SmoothDeleteActivity;
import messi.lhj.com.projectnewtechnic.sonic.SonicActivity;
import messi.lhj.com.projectnewtechnic.util.CheckPermissionUtils;
import messi.lhj.com.projectnewtechnic.util.Logger;
import messi.lhj.com.projectnewtechnic.util.Utils;
import messi.lhj.com.projectnewtechnic.zxing.CaptureActivity;

public class TestActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    @BindView(R.id.loadMoreBtn)
    Button loadMoreBtn;
    @BindView(R.id.btnOnclick)
    Button btnOnclick;
    @BindView(R.id.ZxBtnOnclick)
    Button ZxBtnOnclick;
    @BindView(R.id.recyOnClick)
    Button recyOnClick;
    @BindView(R.id.circleImag)
    Button circleImag;
    @BindView(R.id.gaodebtn)
    Button gaodebtn;
    @BindView(R.id.progressbar)
    Button progressbar;
    @BindView(R.id.smoothdelete)
    Button smoothdelete;
    @BindView(R.id.smalltest)
    Button smalltest;
    @BindView(R.id.getappstore)
    Button getappstore;
    @BindView(R.id.animTest)
    Button animTest;
    @BindView(R.id.nfctest)
    Button nfctest;
    @BindView(R.id.lineChart)
    Button lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bugly_test);
        ButterKnife.bind(this);
        initPermission();
        initView();
    }

    private void initView() {
        loadMoreBtn.setOnClickListener(onClickListener);
        btnOnclick.setOnClickListener(onClickListener);
        ZxBtnOnclick.setOnClickListener(onClickListener);
        recyOnClick.setOnClickListener(onClickListener);
        circleImag.setOnClickListener(onClickListener);
        gaodebtn.setOnClickListener(onClickListener);
        progressbar.setOnClickListener(onClickListener);
        smoothdelete.setOnClickListener(onClickListener);
        smalltest.setOnClickListener(onClickListener);
        getappstore.setOnClickListener(onClickListener);
        animTest.setOnClickListener(onClickListener);
        nfctest.setOnClickListener(onClickListener);
        lineChart.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.loadMoreBtn:
                    Intent intent = new Intent(TestActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btnOnclick:
                    new CommonDialog(TestActivity.this, R.style.dialog);
                    break;
                case R.id.ZxBtnOnclick:
                    intent = new Intent(TestActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                    break;
                case R.id.recyOnClick:
                    intent = new Intent(TestActivity.this, RecyclerViewActivity.class);
                    startActivity(intent);
                    break;
                case R.id.circleImag:
                    intent = new Intent(TestActivity.this, CircleHeadActivity.class);
                    startActivity(intent);
                    break;
                case R.id.gaodebtn:
                    intent = new Intent(TestActivity.this, GaodeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.progressbar:
                    intent = new Intent(TestActivity.this, ProgressbarActivity.class);
                    startActivity(intent);
                    break;
                case R.id.smoothdelete:
                    intent = new Intent(TestActivity.this, SmoothDeleteActivity.class);
                    startActivity(intent);
                    break;
                case R.id.smalltest:
                    intent = new Intent(TestActivity.this, SonicActivity.class);
                    startActivity(intent);
                    break;
                case R.id.getappstore:
                    try {
                        getAppStore();
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.animTest:
                    intent = new Intent(TestActivity.this, AnimActivity.class);
                    startActivity(intent);
                    break;
                case R.id.nfctest:
                    intent = new Intent(TestActivity.this, NFCDemoActivity.class);
                    startActivity(intent);
                    break;
                case R.id.lineChart:
                    intent = new Intent(TestActivity.this, LineChartActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    private void getAppStore() throws PackageManager.NameNotFoundException {

        ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
        String currChannel = applicationInfo.metaData.getString("UMENG_CHANNEL");
//        String sss = Constants.channels[i];
        Logger.d("getAppStore-->" + currChannel);
        if (Utils.getAllInstallApp(this, currChannel)) {
            jumpToStore(currChannel);
            return;
        } else {
            for (int i = 0; i < Constants.channels.length; i++) {
                String channel = Constants.channels[i];
                if (channel.equals(channel))
                    continue;
                if (Utils.getAllInstallApp(this, channel)) {
                    jumpToStore(currChannel);
                    return;
                }
            }
        }
        // TODO: 2017/11/19 未找到应用商店
    }

    private void jumpToStore(String channel) {
        String packageName = Constants.packages[Arrays.asList(Constants.channels).indexOf(channel)];
    }


    private void initPermission() {
        //检查权限
        String[] permissions = CheckPermissionUtils.checkPermission(this);
        if (permissions.length == 0) {
            //权限都申请了
            //是否登录
        } else {
            //申请权限
            ActivityCompat.requestPermissions(this, permissions, 100);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.d("onActivityResult");
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                //扫描后的业务逻辑
                String code = data.getStringExtra("SCAN_RESULT");
                Logger.d("code-->" + code);
            } else if (resultCode == 300) {
                //从本地相册扫描后的业务逻辑
                String code = data.getStringExtra("LOCAL_PHOTO_RESULT");
                Logger.d("code-->" + code);
            }
        }
    }

}
