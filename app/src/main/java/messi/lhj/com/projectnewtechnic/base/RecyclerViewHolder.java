package messi.lhj.com.projectnewtechnic.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import messi.lhj.com.projectnewtechnic.interfaces.MyItemClickListener;
import messi.lhj.com.projectnewtechnic.util.Logger;


/**
 * Created by messi on 16/11/25.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private BaseViewHolder baseViewHolder;
    private MyItemClickListener listener;

    public RecyclerViewHolder(View itemView ,MyItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        baseViewHolder = BaseViewHolder.getViewHolder(itemView);
        itemView.setOnClickListener(this);
    }

    public BaseViewHolder getBaseViewHolder(){
        return baseViewHolder;
    }

    @Override
    public void onClick(View v) {
        Logger.d("RecyclerViewHolder");
        if (listener!=null) {
            listener.onItemClick(v ,getLayoutPosition());
        }else {
            Logger.d("listener is null");
        }
    }
}
