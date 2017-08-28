package messi.lhj.com.projectnewtechnic;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by messi on 2017/8/28.
 */

public class CommonDialog extends Dialog {
//    @BindView(R.id.title)
//    TextView title;
//    @BindView(R.id.content)
//    TextView content;
//    @BindView(R.id.cancel)
//    TextView cancel;
//    @BindView(R.id.submit)
//    TextView submit;

    public CommonDialog(@NonNull Context context) {
        super(context);
    }

    public CommonDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        ButterKnife.bind(this);
        initTheme();
        initView();
    }

    private void initTheme() {
        setCanceledOnTouchOutside(false);
    }

    private void initView() {
//        cancel.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cancel:
                    dismiss();
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode ==KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
