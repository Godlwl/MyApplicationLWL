package ecomoter.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by lwl on 2017/4/21.
 * Describe:
 */

public class MyView extends View {
    /**
     * 画笔
     */
    private Paint mPaint;

    private Paint mPaintText;

    /**
     * 数据
     */
    private ArrayList<ViewDataModel> mDatas = new ArrayList<>();

    /**
     * 宽
     * 高
     */
    private int mWidth, mHeight;

    // 饼状图初始绘制角度
    private float mStartAngle = -90;

    private float mProgress=0;

    private Rect mBound;

    private String mTitleText="0%";

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
//        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);

        mPaintText=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setColor(Color.parseColor("#DBDBDB"));
        mPaintText.setTextSize(50f);
        mBound = new Rect();

    }



    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mDatas){
            return;
        }
        float currentStartAngle=mStartAngle;
//        canvas.translate(mWidth / 2, mHeight / 2);                // 将画布坐标原点移动到中心位置

        float r = (float) (Math.min(mWidth, mHeight) / 2);  // 饼状图半径
        Log.d("R","第一次R"+2*r);
//        RectF rect = new RectF(-r, -r, r, r);                     // 饼状图绘制区域
        RectF rect = new RectF(r*0.2f,r*0.2f, mWidth-r*0.2f,mWidth-r*0.2f);
//        RectF rect = new RectF(0,0, mWidth,mWidth);
        Log.d("R","第二次R"+mWidth);// 饼状图绘制区域

//        for (int i = 0; i < mDatas.size(); i++) {
//            ViewDataModel pie = mDatas.get(i);
//            mPaint.setColor(pie.getColor());
//            canvas.drawArc(rect, currentStartAngle, pie.getAngel(), false, mPaint);
//            currentStartAngle += pie.getAngel();
//        }

        /**
         * 数字
         */
        //文字的x轴坐标
        if (mProgress<=360) {
            mPaintText.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            canvas.drawText(mTitleText, (getWidth() - mBound.width()) / 2, (getHeight() + mBound.height()) / 2, mPaintText);
        }else {
            canvas.drawText("100%", (getWidth() - mBound.width()) / 2, (getHeight() + mBound.height()) / 2, mPaintText);
        }
        /**
         * 整个圆圈
         */
        mPaint.setColor(0xFFDBDBDB);
        RectF rectCir=new RectF(r*0.4f,r*0.4f,mWidth-r*0.4f,mWidth-r*0.4f);
        canvas.drawArc(rectCir,currentStartAngle,360,false,mPaint);
//        /**
//         * 內圈圆
//         */
        mPaint.setColor(mDatas.get(0).getColor());
        RectF rectF=new RectF(r*0.4f,r*0.4f,mWidth-r*0.4f,mWidth-r*0.4f);
        if (mProgress<=360) {
            canvas.drawArc(rectF, currentStartAngle, mProgress, false, mPaint);
//            currentStartAngle=mProgress;
        }else {
                 canvas.drawArc(rectF, currentStartAngle, 360, false, mPaint);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public void setData(ArrayList<ViewDataModel> datas) {
        this.mDatas = datas;
//        invalidate();

    }

    public void setProgress(){
        mProgress+=5;
        mTitleText=((int)((mProgress/360)*100))+"%";
        postInvalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        event.getX();
        event.getRawX();
        return super.onTouchEvent(event);

    }
}
