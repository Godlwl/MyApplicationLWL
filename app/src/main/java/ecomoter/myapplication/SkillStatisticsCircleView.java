package ecomoter.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Description:
 * Created by MonkeyShen on 2017/3/3.
 * Mail:shenminjie92@sina.com
 */

public class SkillStatisticsCircleView extends View {

    /**
     * 默认大小
     */
    public static final int DEFAULT_WIDTH = 200;

    /**
     * 默认画笔宽度
     */
    private int mPatientWidth;

    /**
     * 默认进度差值
     */
    public static final int DEFAULT_PROGRESS_OFFSET = 2;


    /**
     * 宽高
     */
    private int mWidth;
    private int mHeight;

    /**
     * 画笔
     */
    private Paint mPatint;

    /**
     * 角度
     */
    private int mProgress = 0;

    private double mCount;


    public SkillStatisticsCircleView(Context context) {
        super(context);
        init();
    }

    public SkillStatisticsCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SkillStatisticsCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化数据
     */
    private void init() {
        mPatint = new Paint();
        mPatint.setAntiAlias(true);
        mPatint.setStyle(Paint.Style.STROKE);
        mPatint.setStrokeWidth(mPatientWidth);
    }

    /**
     * 设置数量
     */
    public void setCount(double count) {
        mCount = count;
        start();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        drawBasicCircle(canvas);
        drawBasicGrayCircle(canvas);

        if (mCount == 0) {
            return;
        }

        //如果两个数值为0，不绘制图标
        if (mProgress <= 360) {
            //计算开始角度
            //正常的角度，从-90度开始算
            //再计算正常角度划过的度数
            //得出不正常的开始角度与最后不正常开始角度的度数
            float highStartAngle = -90;
            float highSweepAngle = (float) (getHighRate() * 360);

            mPatint.setStrokeWidth(mPatientWidth);

            float left = mPatientWidth * 1.5f;
            float top = mPatientWidth * 1.5f;
            float right = mWidth - left;
            float bottom = mHeight - left;
            RectF rectF = new RectF(left, top, right, bottom);

            //判断是否是一次性描绘，还是进度描绘
            //高级
            if (mProgress != -1) {
                if (mProgress <= highSweepAngle) {
                    mPatint.setColor(Color.parseColor("#ffc700"));
                    canvas.drawArc(rectF, highStartAngle, mProgress, false, mPatint);
                }

            } else {
                mPatint.setColor(Color.parseColor("#ffc700"));
                canvas.drawArc(rectF, highStartAngle, highSweepAngle, false, mPatint);
            }

            //重新绘制
            if (mProgress != -1) {
                mProgress = mProgress + DEFAULT_PROGRESS_OFFSET;
                if (mProgress <= highSweepAngle) {
                    postInvalidate();
                } else {
                    reset();
                }
            }
        }
    }

    /**
     * 外圈线
     *
     * @param canvas
     */
    private void drawBasicGrayCircle(Canvas canvas) {
        float left = mPatientWidth * 1.5f;
        float top = mPatientWidth * 1.5f;
        float right = mWidth - left;
        float bottom = mHeight - left;
        RectF rectF = new RectF(left, top, right, bottom);
        mPatint.setStrokeWidth(mPatientWidth / 5);
        mPatint.setColor(Color.parseColor("#DDDDDD"));
        canvas.drawArc(rectF, -90, 360, false, mPatint);
    }

    /**
     * 内圈远
     *
     * @param canvas
     */
    private void drawBasicCircle(Canvas canvas) {
        float left = mPatientWidth * 1.5f + mWidth / 10;
        float top = mPatientWidth * 1.5f + mWidth / 10;
        float right = mWidth - left;
        float bottom = mHeight - left;
        RectF rectF = new RectF(left, top, right, bottom);
        mPatint.setStrokeWidth(mPatientWidth);
        mPatint.setColor(Color.parseColor("#ffc700"));
        canvas.drawArc(rectF, -90, 360, false, mPatint);
    }


    /**
     * 重新
     * 重置操作，当mProgress为-1时，一次性显示视图，不进行进度加载的显示
     */
    private void reset() {
        mProgress = -1;
    }

    /**
     * 开始画
     * 进度加载操作，当progress不为-1时，会进行进度描绘
     */
    public void start() {
        mProgress = 0;
        invalidate();
    }

    public double getHighRate() {
        return (1.0f * mCount) / 100;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //1:1 宽跟高比例1:1
        mWidth = measureWidth(widthMeasureSpec);
        mHeight = mWidth;
        mPatientWidth = mWidth / 25;
        setMeasuredDimension(mWidth, mHeight);
    }

    /**
     * 测量View的width
     *
     * @param measureSpec MeasureSpec对象
     * @return View的width
     */
    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            Log.d("specMode","MeasureSpec.EXACTLY"+"");
            result = specSize;
        } else {
            result = DEFAULT_WIDTH;
            if (specMode == MeasureSpec.AT_MOST) {
                Log.d("specMode","MeasureSpec.EXACTLY"+"");
                result = Math.min(result, specSize);
            }
        }
        return result;
    }


}
