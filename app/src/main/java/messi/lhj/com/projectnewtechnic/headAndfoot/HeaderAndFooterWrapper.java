package messi.lhj.com.projectnewtechnic.headAndfoot;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import messi.lhj.com.projectnewtechnic.base.BaseRecyclerViewAdapter;
import messi.lhj.com.projectnewtechnic.base.RecyclerViewHolder;
import messi.lhj.com.projectnewtechnic.interfaces.MyItemClickListener;

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
            return mFooterViews.keyAt(position-getHeadersCount()-getRealItemCount());
//            return mFooterViews.keyAt(position);
        }

        return baseRecyclerViewAdapter.getItemViewType( position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getRealItemCount() + getFootersCount();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        baseRecyclerViewAdapter.onAttachedToRecyclerView(recyclerView);
        //修复当设置为gridview时添加的headview或者footview被当作item的现象
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position);
                    if (mHeaderViews.get(viewType) !=null){
                        return gridLayoutManager.getSpanCount();
                    }
                    if (mFooterViews.get(viewType) !=null){
                        return gridLayoutManager.getSpanCount();
                    }
                    if (spanSizeLookup !=null)
                        return spanSizeLookup.getSpanSize(position);
                    return 1;
                }
            });
            //不懂这一步啥意思
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        baseRecyclerViewAdapter.onViewAttachedToWindow(holder);
        //修复当设置为StaggeredGridLayoutManager时添加的headview或者footview不显示的情况
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFooterViewPos(position)){
            ViewGroup.LayoutParams layoutParams = holder.getBaseViewHolder().getConvertView().getLayoutParams();
            if (layoutParams !=null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams){
                StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                lp.setFullSpan(true);
            }
        }
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
