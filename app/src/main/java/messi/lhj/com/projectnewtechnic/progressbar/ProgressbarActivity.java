package messi.lhj.com.projectnewtechnic.progressbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import messi.lhj.com.projectnewtechnic.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ProgressbarActivity extends AppCompatActivity {

    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.progressbar_horizontal)
    ProgressBar progressbarHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Observable.interval(0,50, TimeUnit.MILLISECONDS)
                .filter(new Func1<Long, Boolean>() {
                    @Override
                    public Boolean call(Long aLong) {
                        return progressbarHorizontal.getProgress()<100;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        progressbarHorizontal.setProgress(progressbarHorizontal.getProgress()+1);
                    }
                });
    }
}
