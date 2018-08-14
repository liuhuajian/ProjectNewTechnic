package messi.lhj.com.projectnewtechnic.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import messi.lhj.com.projectnewtechnic.util.Logger;

/**
 * 项目：国民健康平台
 *
 * @Creator:liuhuajian
 * @创建日期： 2018/8/14 16:41
 * @版本0.2
 * @类说明：
 */
public class MyLineView extends View {

    private int viewSize;
    private float left;
    private float top;
    private float right;
    private float bottom;

    private Paint linePaint;// 线条画笔和点画笔

    private Canvas mCanvas;

    private TextPaint mTextPaint;// 文字画笔

    private Path mPath;// 路径对象
    public MyLineView(Context context) {
        super(context);
        init();
    }

    public MyLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //第一步，初始化对象
        linePaint = new Paint();
        linePaint.setColor(Color.YELLOW);//线条的颜色
        linePaint.setStrokeWidth(8);//线条的宽度
        linePaint.setAntiAlias(true);//取消锯齿
        linePaint.setStyle(Paint.Style.STROKE);//粗线

        //初始化Path
        mPath = new Path();

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.LINEAR_TEXT_FLAG);
        mTextPaint.setColor(Color.WHITE);

        mCanvas = new Canvas();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewSize = w;
        Logger.d("viewSize-->"+viewSize);
        left = viewSize * (1/16f);
        top = viewSize * (1/16f);
        right = viewSize * (15/16f);
        bottom = viewSize * (8/16f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //锁定画布
        canvas.save();

        drawXY(canvas);

        drawXYElement(canvas);
    }

    private void drawXYElement(Canvas canvas) {
        // 锁定画布
        canvas.save();
        mTextPaint.setTextSize(36);//文字大小

        /*
        * Y轴文字提示
        * drawText(String ,x,y,TextPaint)
        * (lift,top)
        * */
        mTextPaint.setTextAlign(Paint.Align.LEFT);//左对齐
        canvas.drawText("Y",left+20,top,mTextPaint);

        /*
        * X轴文字提示
        * drawText(String ,right,buttom,TextPaint)
        * */
        mTextPaint.setTextAlign(Paint.Align.RIGHT);//右对齐
        canvas.drawText("X",right,bottom+50,mTextPaint);
        // 释放画布
        canvas.restore();
    }

    private void drawXY(Canvas canvas) {
    /*
    * 第三步，我们来通过viewSize尺寸来获取三个坐标点
    * 第一个（X,Y）--(lift,top)
    * 第二个（X,Y）--(lift,button)
    * 第三个个（X,Y）--(right,buttom)
    * */
        mPath.moveTo(left, top);
        mPath.lineTo(left, bottom);
        mPath.lineTo(right, bottom);

        //使用Path链接这三个坐标
        canvas.drawPath(mPath, linePaint);
        // 释放画布
        canvas.restore();
    }
}
