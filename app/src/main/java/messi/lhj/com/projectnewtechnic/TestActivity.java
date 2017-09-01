package messi.lhj.com.projectnewtechnic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import messi.lhj.com.projectnewtechnic.circlehead.CircleHeadActivity;
import messi.lhj.com.projectnewtechnic.headAndfoot.RecyclerViewActivity;
import messi.lhj.com.projectnewtechnic.refreshAndreloadmore.MainActivity;
import messi.lhj.com.projectnewtechnic.util.CheckPermissionUtils;
import messi.lhj.com.projectnewtechnic.util.Logger;
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
            }
        }
    };


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
