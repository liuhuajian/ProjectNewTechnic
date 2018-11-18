package messi.lhj.com.projectnewtechnic.annotation;

import android.app.Activity;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import messi.lhj.com.projectnewtechnic.R;
import messi.lhj.com.projectnewtechnic.http.ApiWrapper;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.internal.schedulers.SchedulerLifecycle;
import rx.schedulers.Schedulers;

public class AnnotationActivity extends Activity {

    View view=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
    }

    private void testRxJava() {
        new ApiWrapper().getMovie("hhh","10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Movie>() {
                    @Override
                    public void call(Movie movie) {

                    }
                });
        Map<String,String> map = new ConcurrentHashMap<>();
    }

    public void btnHeap(View view) {

    }

    public void btnStack(View view) {

    }
}
