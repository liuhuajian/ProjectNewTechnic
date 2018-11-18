package messi.lhj.com.projectnewtechnic.maopao;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 项目：国民健康平台
 *
 * @Creator:liuhuajian
 * @创建日期： 2018/9/18 17:30
 * @版本0.2
 * @类说明：
 */
public class MaoPaoView extends View {

    private Paint paint;
    private Path path;

    public MaoPaoView(Context context) {
        super(context);
        init();
    }

    public MaoPaoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);//取消锯齿
        paint.setColor(Color.BLUE);//画笔的颜色
        paint.setStyle(Paint.Style.STROKE);//画笔的粗细
        paint.setStrokeWidth(4);//画笔的宽度

        path = new Path();
        path.moveTo(100,500);
        path.quadTo(300,100,600,500);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLocation(canvas);
        canvas.drawPath(path,paint);
    }

    /**
     * 绘制坐标轴
     */
    private void drawLocation(Canvas canvas) {
//        canvas.drawLine();
    }
}
