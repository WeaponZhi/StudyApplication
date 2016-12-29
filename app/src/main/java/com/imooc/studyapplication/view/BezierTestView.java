package com.imooc.studyapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * BezierTestView 贝塞尔曲线绘制练习，用贝塞尔曲线画出一个眼睛，可以点击睁开闭合
 * <p>
 * author:张冠之<br>
 * time: 2016/12/27 13:36 <br>
 * e-mail: zhangguanzhi@csii.com.cn <br>
 * </p>
 */

public class BezierTestView extends View {

    private Paint eyePaint;//眼珠画笔
    private Paint eyelidPaint;//眼睑画笔
    private Paint eyelashedPaint;//睫毛画笔
    private Paint eyeShaderPaint;//Shader画笔

    private int duration;//眼睛睁开闭合的时间
    private boolean isOpen;//眼睛是否睁开
    private float eyelashedLength;//睫毛长度
    private BitmapShader mEyeShader;

    private float bottomEyelidControlPointHeight;//下眼睑控制点y位置
    private float topEyelidControlPointHeight;//上眼睑控制点y位置

    public BezierTestView(Context context) {
        this(context, null);
    }

    public BezierTestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData() {
        duration = 1000;
        isOpen = true;
        topEyelidControlPointHeight = 0;

        //初始化眼珠画笔
        eyePaint = new Paint();
        eyePaint.setAntiAlias(true);
        eyePaint.setColor(Color.BLACK);

        //初始化眼睑画笔
        eyelidPaint = new Paint();
        eyelidPaint.setAntiAlias(true);
        eyelidPaint.setStyle(Paint.Style.STROKE);
        eyelidPaint.setColor(Color.BLACK);//颜色为黑色
        eyelidPaint.setStrokeWidth(10);//笔刷粗细

        eyeShaderPaint = new Paint();

        eyelashedPaint = new Paint();
        eyelashedPaint.setStrokeWidth(10);
        eyelashedPaint.setColor(Color.BLACK);
        eyelashedPaint.setStyle(Paint.Style.STROKE);
    }

    float[] pos = new float[2];
    float[] tan = new float[2];
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //---------画眼珠-----------------------------------------------------------------
        Path eyePath = new Path();
        eyePath.reset();
        eyePath.moveTo(0, canvas.getHeight() / 2);
        eyePath.quadTo(canvas.getWidth() / 2, topEyelidControlPointHeight, canvas.getWidth(), canvas.getHeight() / 2);
        eyePath.lineTo(canvas.getWidth(), canvas.getHeight());
        eyePath.lineTo(0, canvas.getHeight());
        eyePath.close();
        canvas.drawPath(eyePath, eyeShaderPaint);
        //---------画两条贝赛尔曲线眼睑-----------------------------------------------------------------
        //初始化路径，这里用二阶贝塞尔曲线
        eyePath.reset();//下眼睑路径
        eyePath.moveTo(0, canvas.getHeight() / 2);
        eyePath.quadTo(canvas.getWidth() / 2, topEyelidControlPointHeight, canvas.getWidth(), canvas.getHeight() / 2);
        eyePath.moveTo(0, canvas.getHeight() / 2);
        eyePath.quadTo(canvas.getWidth() / 2, canvas.getHeight(), canvas.getWidth(), canvas.getHeight() / 2);
        canvas.drawPath(eyePath, eyelidPaint);

        float length = eyelashedLength * Math.abs((canvas.getHeight() / 2 - getTopEyelidControlPointHeight()) / (canvas.getHeight() / 2));

        PathMeasure pathMeasure = new PathMeasure(eyePath, false);

        pathMeasure.getPosTan(pathMeasure.getLength() / 8f, pos, tan);
        drawEyewinker(canvas, pos, tan, length);

        pathMeasure.getPosTan(pathMeasure.getLength() / 4f, pos, tan);
        drawEyewinker(canvas, pos, tan, length);

        pathMeasure.getPosTan(pathMeasure.getLength() / 8f * 3f, pos, tan);
        drawEyewinker(canvas, pos, tan, length);

        pathMeasure.getPosTan(pathMeasure.getLength() / 2f, pos, tan);
//        drawEyewinker(canvas, pos, tan, length);
        float tanA = -tan[0] / tan[1];
        int sign = pos[0] > canvas.getWidth() / 2f ? 1 : -1;
        canvas.drawLine(pos[0], pos[1], pos[0] + sign * (float) (length / Math.sqrt(tanA * tanA + 1))
                , pos[1] - sign * (float) (length * tanA / Math.sqrt(tanA * tanA + 1)), eyelashedPaint);

        pathMeasure.getPosTan(pathMeasure.getLength() / 8f * 5f, pos, tan);
        drawEyewinker(canvas, pos, tan, length);

        pathMeasure.getPosTan(pathMeasure.getLength() / 4f * 3f, pos, tan);
        drawEyewinker(canvas, pos, tan, length);

        pathMeasure.getPosTan(pathMeasure.getLength() / 8f * 7f, pos, tan);
        drawEyewinker(canvas, pos, tan, length);


    }

    /**
     * 根据睫毛在曲线上的位置绘制睫毛
     *
     * @param t      比例(0,1)
     * @param canvas 获取canvas对象
     */
    public void drawEyelashed(float t, Canvas canvas) {
        PointF bezierPoint = new PointF();
        bezierPoint.x = 2 * t * (1 - t) * getWidth() / 2 + t * t * getWidth();
        bezierPoint.y = (1 - t) * (1 - t) * getHeight() / 2 + 2 * t * (1 - t) * getTopEyelidControlPointHeight() + t * t * getHeight() / 2;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setBottomEyelidControlPointHeight(getHeight());
        eyelashedLength = getWidth() / 15;
        createShader();
    }

    private void createShader() {
        //--------画眼珠------------------------------------------------------------------
        //眼珠的直径
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        float eyeRadius = canvas.getWidth() / 3;
        //移动画布到放置眼珠的正方形处的左上角点
        canvas.save();
        canvas.translate(eyeRadius, eyeRadius);
        RectF Rect = new RectF(0, 0, eyeRadius, eyeRadius);
        canvas.drawArc(Rect, 0, 270, true, eyePaint);
        canvas.restore();
        mEyeShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        eyeShaderPaint.setShader(mEyeShader);
    }

    /**
     * 画睫毛
     *
     * @param canvas
     * @param pos
     * @param tan
     * @param EyewinkerLength
     */
    private void drawEyewinker(Canvas canvas, float[] pos, float[] tan, float EyewinkerLength) {
        float tanA = tan[0] / tan[1];
        int sign = pos[0] > canvas.getWidth() / 2f ? 1 : -1;
        canvas.drawLine(pos[0], pos[1], pos[0] + sign * (float) (EyewinkerLength / Math.sqrt(tanA * tanA + 1))
                , pos[1] - sign * (float) (EyewinkerLength * tanA / Math.sqrt(tanA * tanA + 1)), eyelashedPaint);
    }

    /**
     * 属性动画需要的set方法应该加上invalidate()
     *
     * @param topEyelidControlPointHeight
     */
    public void setTopEyelidControlPointHeight(float topEyelidControlPointHeight) {
        this.topEyelidControlPointHeight = topEyelidControlPointHeight;
        invalidate();
    }

    public float getTopEyelidControlPointHeight() {
        return topEyelidControlPointHeight;
    }

    public float getBottomEyelidControlPointHeight() {
        return bottomEyelidControlPointHeight;
    }

    public void setBottomEyelidControlPointHeight(float bottomEyelidControlPointHeight) {
        this.bottomEyelidControlPointHeight = bottomEyelidControlPointHeight;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
