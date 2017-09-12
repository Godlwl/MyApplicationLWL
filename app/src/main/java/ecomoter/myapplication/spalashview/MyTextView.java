package ecomoter.myapplication.spalashview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lwl on 2017/8/15.
 * Describe:
 */

public class MyTextView extends View {
    private Paint mPaint;
    private Rect mRectText;
    private String mText="我是自定义View";

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(20);
        mRectText=new Rect();
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.getTextBounds(mText,0,mText.length(),mRectText);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);
        mPaint.setColor(Color.BLUE);
//        canvas.drawText(mText,getWidth()/2-mRectText.width()/2,getHeight()/2+mRectText.height()/2,mPaint);
        canvas.drawText(mText,getWidth()/2,getHeight()/2,mPaint);
    }
}
