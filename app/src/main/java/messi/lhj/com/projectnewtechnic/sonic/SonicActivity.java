package messi.lhj.com.projectnewtechnic.sonic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import messi.lhj.com.projectnewtechnic.R;

public class SonicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonic);
        initView();
    }

    private void initView() {

    }

    public void btnNormal(View view) {
        Intent intent = new Intent(this,WebViewActivity.class);
        startActivity(intent);
    }

    public void btnSonic(View view) {
        Intent intent = new Intent(this,WebViewActivity.class);
        intent.putExtra("type",1);
        startActivity(intent);
    }
}
