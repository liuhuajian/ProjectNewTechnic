package messi.lhj.com.projectnewtechnic.subscrthing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import messi.lhj.com.projectnewtechnic.R;

public class SubTwoActivity extends AppCompatActivity {


    private MySubject subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_two);

        subject = MySubject.getInstance();
//        new Observer1(subject);
        new Observer2(subject);
    }

    public void sendData(View view) {
        subject.sendMsg("哈门票啊");

    }

    @Override
    protected void onStop() {
        super.onStop();
        subject.unregisterAllObserver();
    }
}
