package messi.lhj.com.projectnewtechnic;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import messi.lhj.com.projectnewtechnic.base.BaseRecyclerViewAdapter;
import messi.lhj.com.projectnewtechnic.base.RecyclerViewHolder;
import messi.lhj.com.projectnewtechnic.inter.MyItemClickListener;

/**
 * Created by messi on 2017/8/29.
 */

public class HeaderAndFooterWrapper<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<>();

    private BaseRecyclerViewAdapter baseRecyclerViewAdapter;

    private MyItemClickListener onItemClickListener;

    private boolean isHeaderViewPos(int position){
        return position <getHeadersCount();
    }

    private boolean isFooterViewPos(int position){
        return position >= getHeadersCount() + getRealItemCount();
    }

    public void addHeaderView(View view){
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER ,view);
    }

    public void addFooterView(View view){
        mFooterViews.put(mFooterViews.size() + BASE_ITEM_TYPE_FOOTER ,view);
    }

    public int getFootersCount(){
        return mFooterViews.size();
    }


    private int getRealItemCount() {
        return baseRecyclerViewAdapter.getItemCount();
    }

    private int getHeadersCount() {
        return mHeaderViews.size();
    }


    public HeaderAndFooterWrapper(BaseRecyclerViewAdapter baseRecyclerViewAdapter){
        this.baseRecyclerViewAdapter = baseRecyclerViewAdapter;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) !=null){
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(mHeaderViews.get(viewType), onItemClickListener);
            return recyclerViewHolder;
        }
        if (mFooterViews.get(viewType) !=null){
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(mFooterViews.get(viewType), onItemClickListener);
            return recyclerViewHolder;
        }

        return baseRecyclerViewAdapter.onCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        if (isHeaderViewPos(position))
            return;
        if (isFooterViewPos(position))
            return;
        baseRecyclerViewAdapter.onBindViewHolder(holder,position-getHeadersCount());
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)){
            return mHeaderViews.keyAt(position);
        }else if (isFooterViewPos(position)){
//            return mFooterViews.keyAt(position-getHeadersCount()-getRealItemCount());
            return mFooterViews.keyAt(position);
        }

        return baseRecyclerViewAdapter.getItemViewType( position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getRealItemCount() + getFootersCount();
    }

    public void setOnItemClickListener(MyItemClickListener onItemClickListener ){
        this.onItemClickListener=onItemClickListener;
        baseRecyclerViewAdapter.setOnItemClickListener(onItemClickListener);
    }

    public void refresh(List<T> datas){
        baseRecyclerViewAdapter.refresh(datas);
    }

    public void insertData(List<T> datas){
        baseRecyclerViewAdapter.insertData(datas);
        notifyDataSetChanged();
    }
}
