package messi.lhj.com.projectnewtechnic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DialogTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bugly_test);
    }

    public void btnOnclick(View view) {
        new CommonDialog(this,R.style.dialog);
    }
}
