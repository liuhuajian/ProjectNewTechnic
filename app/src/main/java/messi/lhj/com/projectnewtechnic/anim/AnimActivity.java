package messi.lhj.com.projectnewtechnic.anim;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import messi.lhj.com.projectnewtechnic.R;
import messi.lhj.com.projectnewtechnic.util.Logger;

public class AnimActivity extends AppCompatActivity {

    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.recyclerview)
    ImageView recyclerview;
    private float touchYStart;
    private float touchYEnd;
    private boolean entryState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        ButterKnife.bind(this);
    }

    public void btnStart(View view) {
        displayViewAnim(true);
    }

    private void displayViewAnim(final boolean isStart) {
        int start = 0;
        int end = 0;
        if (isStart) {
            start = 0;
            end = -imageview.getHeight();
        } else {
            start = -imageview.getHeight();
            end = 0;
        }
        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.setDuration(400)
                .start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float worini = (float) animation.getAnimatedValue();
                imageview.setTranslationY(worini);
                recyclerview.setTranslationY(worini);
            }
        });
    }

    public void btnEnd(View view) {
        displayViewAnim(false);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                touchYStart = event.getY();
                Logger.d("onTouchEvent--touchYStart-->"+ touchYStart);
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                touchYEnd = event.getY();
                float inter = touchYEnd - touchYStart;
                Logger.d("onTouchEvent--touchYEnd-->"+ touchYEnd);
                Logger.d("onTouchEvent--inter-->"+inter);
                if (inter >getValue(100) &&!entryState){
                    entryState = true;
                    displayViewAnim(true);
                }else if (inter < getValue(-100) &&entryState){
                    entryState = false;
                    displayViewAnim(false);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private float getValue(float value){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,getResources().getDisplayMetrics());
    }
}
