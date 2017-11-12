package messi.lhj.com.projectnewtechnic.sonic;

import android.content.Context;
import android.widget.BaseAdapter;

import messi.lhj.com.projectnewtechnic.base.BaseRecyclerViewAdapter;
import messi.lhj.com.projectnewtechnic.base.BaseViewHolder;

/**
 * Created by messi on 2017/11/12.
 */

public class MyAdapter extends BaseRecyclerViewAdapter<Entity> {
    public MyAdapter(Context context) {
        super(context);
    }

    @Override
    public int onCreateViewLayoutId(int viewType) {
        return 0;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }


}
