package messi.lhj.com.projectnewtechnic.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import messi.lhj.com.projectnewtechnic.interfaces.MyItemClickListener;
import messi.lhj.com.projectnewtechnic.util.Logger;

/**
 * Created by messi on 16/11/25.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder>{
    protected Context context;
    protected List<T> mLists;
    protected ImageLoader imageLoader;

    protected MyItemClickListener onItemClickListener;

    public BaseRecyclerViewAdapter(Context context){
        mLists = new ArrayList<>();
        this.context = context;
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(onCreateViewLayoutId(viewType), parent, false);
        Logger.d("onCreateViewHolder");
        return new RecyclerViewHolder(view ,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        onBindViewHolder(holder.getBaseViewHolder(),position);
    }

    @Override
    public int getItemCount() {
        return mLists ==null ? 0:mLists.size() ;
    }

    public abstract int onCreateViewLayoutId(int viewType);
    public abstract void onBindViewHolder(BaseViewHolder holder, int position);

    public void setOnItemClickListener(MyItemClickListener onItemClickListener ){
        this.onItemClickListener=onItemClickListener;
    }

    public void refresh(List<T> mLists){
        this.mLists = mLists;
    }

    public void insertData(List<T> mLists){

    }

}
