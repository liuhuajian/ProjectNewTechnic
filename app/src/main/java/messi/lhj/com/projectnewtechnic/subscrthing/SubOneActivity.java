package messi.lhj.com.projectnewtechnic.subscrthing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import messi.lhj.com.projectnewtechnic.R;

public class SubOneActivity extends Activity {

    private MySubject subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_one);
        subject = MySubject.getInstance();
        new Observer1(subject);
//        new Observer2(subject);

    }

    public void sendData(View view) {
        subject.sendMsg("哇哈哈哈");

    }

    @Override
    protected void onStop() {
        super.onStop();
//        subject.unregisterAllObserver();
    }

    public void jumpPage(View view) {
        Intent intent = new Intent(SubOneActivity.this, SubTwoActivity.class);
        startActivity(intent);
    }
}
