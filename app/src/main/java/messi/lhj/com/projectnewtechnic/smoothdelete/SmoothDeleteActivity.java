package messi.lhj.com.projectnewtechnic.smoothdelete;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import messi.lhj.com.projectnewtechnic.R;

public class SmoothDeleteActivity extends Activity {

    @BindView(R.id.swipe_menu_recyclerview)
    SwipeMenuRecyclerView swipeMenuRecyclerview;
    private SmoothDeleteAdapter smoothDeleteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smooth_delete);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        swipeMenuRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        swipeMenuRecyclerview.setSwipeMenuCreator(swipeMenuCreator);
        swipeMenuRecyclerview.setSwipeMenuItemClickListener(mMenuItemClickListener);
        smoothDeleteAdapter = new SmoothDeleteAdapter(this);
        swipeMenuRecyclerview.smoothOpenRightMenu(0);
        swipeMenuRecyclerview.setAdapter(smoothDeleteAdapter);
        insertData();
    }

    private void insertData() {
        List<String> datas = new ArrayList<>();
        for (int i=0;i<30;i++){
            datas.add("我是刘华健"+i+i);
        }
        smoothDeleteAdapter.refresh(datas);
    }

    SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dimen_70dp);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(SmoothDeleteActivity.this)
                    .setImage(R.drawable.ic_action_delete) // 图标。
                    .setBackground(R.color.color_red)
                    .setText("删除") // 文字。
                    .setTextColor(Color.WHITE) // 文字颜色。
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);
        }
    };

    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                smoothDeleteAdapter.removeItem(adapterPosition);
                Toast.makeText(SmoothDeleteActivity.this, "list第" + adapterPosition + ";集合数：" + smoothDeleteAdapter.getListSize(), Toast.LENGTH_SHORT).show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(SmoothDeleteActivity.this, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };
}
