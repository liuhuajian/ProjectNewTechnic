package messi.lhj.com.projectnewtechnic.pages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import messi.lhj.com.projectnewtechnic.Constants;
import messi.lhj.com.projectnewtechnic.HeaderAndFooterWrapper;
import messi.lhj.com.projectnewtechnic.MyAdapter;
import messi.lhj.com.projectnewtechnic.R;
import messi.lhj.com.projectnewtechnic.inter.MyItemClickListener;
import messi.lhj.com.projectnewtechnic.util.MyLayoutParams;

public class RecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private MyAdapter myAdapter;
    private HeaderAndFooterWrapper headerAndFooterWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this);
        headerAndFooterWrapper = new HeaderAndFooterWrapper(myAdapter);
        View headView = LayoutInflater.from(this).inflate(R.layout.headview,null);
        MyLayoutParams.setViewMatch(headView);

        View headView2 = LayoutInflater.from(this).inflate(R.layout.headview2,null);
        MyLayoutParams.setViewMatch(headView2);
        headerAndFooterWrapper.addHeaderView(headView);
        headerAndFooterWrapper.addHeaderView(headView2);

        headerAndFooterWrapper.addFooterView(headView2);
        headerAndFooterWrapper.addFooterView(headView);
        recyclerview.setAdapter(headerAndFooterWrapper);
        refresh();
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
}
