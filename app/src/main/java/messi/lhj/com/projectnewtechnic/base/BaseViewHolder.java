package messi.lhj.com.projectnewtechnic.base;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by messi on 16/11/25.
 */
public class BaseViewHolder {

    private View view;
    private SparseArray<View> mViews;

    private BaseViewHolder(View view){
        this.view = view;
        mViews = new SparseArray<>();
        view.setTag(mViews);
    }

    public static BaseViewHolder getViewHolder(View view){
        BaseViewHolder baseViewHolder = (BaseViewHolder) view.getTag();
        if (baseViewHolder ==null) {
            baseViewHolder = new BaseViewHolder(view);
            view.setTag(baseViewHolder);
        }
        return baseViewHolder;
    }

    public <T extends View> T get(int viewId){
        View view = mViews.get(viewId);
        if (view ==null){
            view = this.view.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    public View getConvertView(){
        return view;
    }
}
