package com.imooc.studyapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.imooc.studyapplication.Util.LogUtils;

import java.util.ArrayList;

/**
 * CanvasDemoActivity 一些有用户交互的应用
 * <p>
 * author:张冠之<br>
 * time: 2016/12/28 09:04 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class CanvasDemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CustomView(this));
    }

    class CustomView extends View {
        Paint mPaint;
        private ArrayList<PointF> graphics = new ArrayList<>();
        PointF mPointF;

        public CustomView(Context context) {
            super(context);
            mPaint = new Paint();//设置一个笔刷大小是3的黄色画笔
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(3);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);

        }

        /**
         * 当用户点击时会出现一个小点，拖动时将画出一条用细点组成的虚线
         * @param event
         * @return
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            LogUtils.logd("onTouch");
            graphics.add(new PointF(event.getX(),event.getY()));
            invalidate();
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            for (PointF point : graphics){
                canvas.drawPoint(point.x,point.y,mPaint);
            }
        }
    }
}
