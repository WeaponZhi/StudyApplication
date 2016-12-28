package com.imooc.studyapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.imooc.studyapplication.Util.DeviceUtil;

/**
 * BezierTestView 贝塞尔曲线绘制练习
 * <p>
 * author:张冠之<br>
 * time: 2016/12/27 13:36 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class BezierTestView extends View {
    private Context mContext;

    public BezierTestView(Context context) {
        super(context);
        mContext = context;
    }

    public BezierTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public BezierTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        //在wrap_content的情况下默认长度为200dp
        int minSize = DeviceUtil.dip2px(mContext, 200);
        // wrap_content的specMode是AT_MOST模式，这种情况下宽/高等同于specSize  
        // 查表得这种情况下specSize等同于parentSize，也就是父容器当前剩余的大小  
        // 在wrap_content的情况下如果特殊处理，效果等同match_parent  
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(minSize, minSize);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(minSize, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, minSize);
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.YELLOW);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3.0f);

        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
        canvas.drawCircle(0, 0, 400, paint);

        Path path = new Path();
        path.addArc(new RectF(-300, -300, 300, 300), -150, 150);
        Paint citePaint = new Paint(paint);
        citePaint.setTextSize(50);
        citePaint.setStrokeWidth(2);
        canvas.drawTextOnPath("zhangguanzhi is handsome!", path, 0, 0, citePaint);

        Paint tmpPaint = new Paint(paint);
        tmpPaint.setTextSize(40);
        tmpPaint.setStrokeWidth(1);

        float y = 400;
        int count = 60;

        for (int i = 0; i < count; i++) {
            if (i % 5 == 0) {
                canvas.drawLine(0, y, 0, y + 40, paint);
                canvas.drawText(String.valueOf(i / 5 + 1), -8f, y + 80f, tmpPaint);
            } else {
                canvas.drawLine(0f, y, 0f, y + 20, tmpPaint);
            }
            canvas.rotate(360 / count, 0f, 0f);
        }

        tmpPaint.setColor(Color.GRAY);
        tmpPaint.setStrokeWidth(4);
        canvas.drawCircle(0, 0, 28, tmpPaint);
        tmpPaint.setStyle(Paint.Style.FILL);
        tmpPaint.setColor(Color.YELLOW);
        canvas.drawCircle(0, 0, 20, tmpPaint);
        canvas.drawLine(0, 40, 0, -260, paint);
    }
}
