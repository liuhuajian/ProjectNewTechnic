package messi.lhj.com.projectnewtechnic.headAndfoot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import messi.lhj.com.projectnewtechnic.common.Constants;
import messi.lhj.com.projectnewtechnic.R;
import messi.lhj.com.projectnewtechnic.interfaces.MyItemClickListener;
import messi.lhj.com.projectnewtechnic.util.MyLayoutParams;
import messi.lhj.com.projectnewtechnic.view.CustomerRecyclerview;

public class RecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    CustomerRecyclerview recyclerview;
    private HeaderAndFootAdapter myAdapter;
    private HeaderAndFooterWrapper headerAndFooterWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
//        recyclerview.setLayoutManager(new GridLayoutManager(this,2));
//        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        myAdapter = new HeaderAndFootAdapter(this);
        headerAndFooterWrapper = new HeaderAndFooterWrapper(myAdapter);
        View headView = LayoutInflater.from(this).inflate(R.layout.headview,null);
        MyLayoutParams.setViewMatch(headView);

        View headView2 = LayoutInflater.from(this).inflate(R.layout.headview2,null);
        MyLayoutParams.setViewMatch(headView2);
//        headerAndFooterWrapper.addHeaderView(headView);
//        headerAndFooterWrapper.addHeaderView(headView2);

//        headerAndFooterWrapper.addFooterView(headView2);
//        headerAndFooterWrapper.addFooterView(headView);
        recyclerview.setAdapter(headerAndFooterWrapper);
        refresh();
        recyclerview.setGridItmSpaceVertical(0,50);
        //滑动后居中
//        SnapHelper snapHelper = new LinearSnapHelper();
        //滑动后居左
//        SnapHelper snapHelper = new StartSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerview);
    }

    private void refresh() {
        List<String> datas = new ArrayList<>();
        for (int i=0;i< Constants.text_content.length;i++){
            String content = Constants.text_content[i];
            datas.add(content);
        }
        headerAndFooterWrapper.refresh(datas);
        headerAndFooterWrapper.setOnItemClickListener(myItemClickListener);
    }

    MyItemClickListener myItemClickListener = new MyItemClickListener() {
        @Override
        public void onItemClick(View view, int postion) {
            Toast.makeText(RecyclerViewActivity.this,"position="+postion,Toast.LENGTH_SHORT).show();
        }
    };

    public void loadOnClick(View view) {
        List<String> datas = new ArrayList<>();
        for (int i=0;i<5;i++){
            datas.add("liuhuajian"+i);
        }
        headerAndFooterWrapper.insertData(datas);
    }
}
