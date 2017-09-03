package messi.lhj.com.projectnewtechnic.headAndfoot;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import messi.lhj.com.projectnewtechnic.R;
import messi.lhj.com.projectnewtechnic.base.BaseRecyclerViewAdapter;
import messi.lhj.com.projectnewtechnic.base.BaseViewHolder;

/**
 * Created by messi on 2017/8/29.
 */

public class HeaderAndFootAdapter extends BaseRecyclerViewAdapter<String> {
    public HeaderAndFootAdapter(Context context) {
        super(context);
    }

    @Override
    public int onCreateViewLayoutId(int viewType) {
        return R.layout.item_recyclerview;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        if (mLists.size() ==1){

        }
        String string = mLists.get(position);
        TextView textView = holder.get(R.id.item_textview);
        textView.setText(string);
        textView.setHeight(100 + (position % 3) * 30);
    }

    public void refresh(List<String> mLists){
        this.mLists = mLists;
        notifyDataSetChanged();
    }

    public void insertData(List<String> mLists){
        this.mLists.addAll(mLists);
        notifyDataSetChanged();
    }
}
