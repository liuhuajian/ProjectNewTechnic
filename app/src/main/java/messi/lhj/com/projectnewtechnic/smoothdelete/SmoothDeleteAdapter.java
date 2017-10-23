package messi.lhj.com.projectnewtechnic.smoothdelete;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import messi.lhj.com.projectnewtechnic.R;
import messi.lhj.com.projectnewtechnic.base.BaseRecyclerViewAdapter;
import messi.lhj.com.projectnewtechnic.base.BaseViewHolder;

/**
 * Created by messi on 2017/10/23.
 */

public class SmoothDeleteAdapter extends BaseRecyclerViewAdapter<String> {
    public SmoothDeleteAdapter(Context context) {
        super(context);
    }

    @Override
    public int onCreateViewLayoutId(int viewType) {
        return R.layout.item_smooth_delete;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        TextView tvContent = holder.get(R.id.item_content);
        tvContent.setText(mLists.get(position));
    }

    @Override
    public void refresh(List<String> mLists) {
        super.refresh(mLists);
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        mLists.remove(position);
        notifyItemRemoved(position);
    }

    public int getListSize(){
        return mLists.size();
    }
}
