package messi.lhj.com.projectnewtechnic.pages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import messi.lhj.com.projectnewtechnic.Constants;
import messi.lhj.com.projectnewtechnic.R;
import messi.lhj.com.projectnewtechnic.refreshAndreloadmore.MyAdapter;
import messi.lhj.com.projectnewtechnic.refreshAndreloadmore.Picture;
import messi.lhj.com.projectnewtechnic.refreshAndreloadmore.Student;
import messi.lhj.com.projectnewtechnic.util.http.ApiWrapper;
import messi.lhj.com.projectnewtechnic.util.http.Response;
import messi.lhj.com.projectnewtechnic.util.Logger;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.ptrclassic_framelayout)
    PtrClassicFrameLayout mPtrFrame;
    private MyAdapter myAdapter;
    private Random random;
    private int indexLoadMore;
    private int indexRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        initView();
//        initData();
//        updateData();
    }



    private void initView() {
        random = new Random(7);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                loadMore();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refresh();
            }

        });
        // the following are default settings
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_student, null);
        mPtrFrame.setLastUpdateTimeHeaderKey("上次更新时间。。。。");
//        mPtrFrame.setLastUpdateTimeFooterRelateObject();
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);
    }

    private void loadMore() {
        try {
            JSONArray jsonArray = new JSONArray(Constants.peripheralDataExtra);
            if (indexLoadMore <jsonArray.length()){
                JSONArray array = jsonArray.getJSONArray(indexLoadMore++);
                Student student = new Student();
                student.name = array.getString(0);
                student.desc = array.getString(1);
                myAdapter.insert2Foot(student);
            }
            recyclerview.smoothScrollToPosition(myAdapter.getItemCount()-1);
            mPtrFrame.refreshComplete();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void refresh() {
        try {
            JSONArray jsonArray = new JSONArray(Constants.peripheralDataExtra);
            if (indexRefresh <jsonArray.length()){
                JSONArray array = jsonArray.getJSONArray(indexRefresh++);
                Student student = new Student();
                student.name = array.getString(0);
                student.desc = array.getString(1);
                myAdapter.insert2Head(student);
            }
            recyclerview.smoothScrollToPosition(0);
            mPtrFrame.refreshComplete();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateData() {
        Logger.d("updateData");
        List<Student> students = getStudents();
        myAdapter.refresh(students);
    }

    private List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(Constants.peripheralData);

            int index = random.nextInt(7)+1;
            Logger.d("index-->"+index);
            for (int i = 0; i < (jsonArray.length() -index); i++) {
                JSONArray array = jsonArray.getJSONArray(i);
                Student student = new Student();
                student.name = array.getString(0);
                student.desc = array.getString(1);
                students.add(student);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return students;
    }

    private void initData() {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this);
        recyclerview.setAdapter(myAdapter);
    }

    public void btnOnclick(View view) {
        Observable<Response> pictures = new ApiWrapper().getPictures();
        pictures.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response>() {
                    @Override
                    public void call(Response response) {
                        List<Picture> list = response.data.list;
                        myAdapter.updatePic(list);
                    }
                });
    }

}
