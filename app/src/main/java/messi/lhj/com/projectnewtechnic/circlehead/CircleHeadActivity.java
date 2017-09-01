package messi.lhj.com.projectnewtechnic.circlehead;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import butterknife.BindView;
import butterknife.ButterKnife;
import messi.lhj.com.projectnewtechnic.R;

public class CircleHeadActivity extends AppCompatActivity {

    @BindView(R.id.circleImag)
    CircleImageView circleImag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_head);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        ImageLoader.getInstance().displayImage("http://img1.2345.com/duoteimg/qqTxImg/11/2012091910313510745.jpg",circleImag);
    }
}
