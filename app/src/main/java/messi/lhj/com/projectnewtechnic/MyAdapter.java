package messi.lhj.com.projectnewtechnic;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import messi.lhj.com.projectnewtechnic.base.BaseRecyclerViewAdapter;
import messi.lhj.com.projectnewtechnic.base.BaseViewHolder;

/**
 * Created by messi on 2017/8/29.
 */

public class MyAdapter extends BaseRecyclerViewAdapter<String> {
    public MyAdapter(Context context) {
        super(context);
    }

    @Override
    public int onCreateViewLayoutId(int viewType) {
        return R.layout.item_recyclerview;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        String string = mLists.get(position);
        TextView textView = holder.get(R.id.item_textview);
        textView.setText(string);
    }

    public void refresh(List<String> mLists){
        this.mLists = mLists;
        notifyDataSetChanged();
    }
}
