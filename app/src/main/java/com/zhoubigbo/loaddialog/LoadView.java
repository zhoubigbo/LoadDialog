package com.zhoubigbo.loaddialog;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class LoadView extends View {

    private Paint paintB, paintG;//黑色和灰色画笔
    private RectF rectF;//矩形区域
    private float value = 0f, last = 0f;//初始值
    private boolean isState = false;//状态
    private Path pathG;//路径

    public LoadView(Context context) {
        super(context);
        initial();
    }

    public LoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initial();
    }

    //初始化
    private void initial() {
        paintB = new Paint();
        paintB.setStyle(Paint.Style.STROKE);
        paintB.setAntiAlias(true);
        paintB.setStrokeWidth(6);
        paintB.setColor(Color.BLACK);

        paintG = new Paint();
        paintG.setStyle(Paint.Style.STROKE);
        paintG.setAntiAlias(true);
        paintG.setStrokeWidth(6);
        paintG.setColor(Color.parseColor("#EEEEEE"));

        rectF = new RectF(10, 10, 200, 200);

        pathG = new Path();
        pathG.addArc(rectF, -90, 360);

        doAnimator();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(pathG, paintG);//绘制灰色圆环
        if (isState) {
            Path path = new Path();
            path.addArc(rectF, -90, value);
            canvas.drawPath(path, paintB);
        } else {
            Path path = new Path();
            path.addArc(rectF, -90, -value);
            canvas.drawPath(path, paintB);
        }
    }

    private void doAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 360f);
        valueAnimator.addUpdateListener(new addUpdate());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setDuration(3000);
        valueAnimator.start();
    }

    class addUpdate implements ValueAnimator.AnimatorUpdateListener {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            value = (float) animation.getAnimatedValue();
            System.out.println(value);
            if (value - last > 0.0) {
                isState = true;
            } else {
                isState = false;
            }
            last = value;
            invalidate();
        }
    }
}
