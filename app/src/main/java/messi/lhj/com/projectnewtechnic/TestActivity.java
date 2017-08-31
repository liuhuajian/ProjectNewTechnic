package messi.lhj.com.projectnewtechnic;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import messi.lhj.com.projectnewtechnic.headAndfoot.RecyclerViewActivity;
import messi.lhj.com.projectnewtechnic.refreshAndreloadmore.MainActivity;
import messi.lhj.com.projectnewtechnic.util.CheckPermissionUtils;
import messi.lhj.com.projectnewtechnic.util.Logger;

public class TestActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bugly_test);
        initPermission();
    }

    public void btnOnclick(View view) {
        new CommonDialog(this,R.style.dialog);
    }

    public void ZxBtnOnclick(View view) {
        Intent intent = new Intent(TestActivity.this, CaptureActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
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
        if (requestCode ==REQUEST_CODE){
            Logger.d("onActivityResult");
            if (data==null)
                return;
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }
            Logger.d("onActivityResult");
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String result = bundle.getString(CodeUtils.RESULT_STRING);
                Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
            } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                Toast.makeText(TestActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void recyOnClick(View view) {
        Intent intent = new Intent(TestActivity.this,RecyclerViewActivity.class);
        startActivity(intent);
    }

    public void loadMoreBtn(View view) {
        Intent intent = new Intent(TestActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
