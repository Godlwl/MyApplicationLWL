package ecomoter.myapplication.sideindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lwl on 2017/8/1.
 * Describe:
 */

public class SideIndexView extends View {
    /**
     * 当前手指滑到的位置
     */
    private int mChoosed=-1;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 所有字母
     */
    private String[] mLetters=new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

    /**
     * 字母改变监听
     */
    private UpdataLetterListener mListener;

    /**
     * 选中字母提示框
     */
    private TextView mTvLetter;

    /**
     *选中圆圈画笔
     */
    private Paint mPaintCircle;

    /**
     *
     * @param context
     */
    public SideIndexView(Context context) {
        super(context);
    }

    public SideIndexView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(24);
        mPaintCircle=new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setStyle(Paint.Style.FILL);
        mPaintCircle.setColor(Color.BLUE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 多留一个空 下边界留空
         */
        int perTextHeight = getMeasuredHeight() / (mLetters.length+1);

        for (int i = 0; i < mLetters.length; i++) {
            //获取文字的宽高
            Rect rect = new Rect();
            mPaint.getTextBounds(mLetters[i], 0, 1, rect);
            int wordWidth = rect.width();
            int wordHeight=rect.height();
            if (i == mChoosed) {
                mPaint.setColor(Color.RED);
                canvas.drawCircle(getWidth()/2,((i+1)* perTextHeight-wordHeight/2),(mPaint.measureText(mLetters[i]) / 2+25),mPaintCircle);
            } else {
                mPaint.setColor(Color.BLACK);
            }
            canvas.drawText(mLetters[i], (getMeasuredWidth() - mPaint.measureText(mLetters[i])) / 2, (i + 1) * perTextHeight, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String letter = null;
        int perTextHeight = getMeasuredHeight() / mLetters.length;
        float y = event.getY();
        int currentPosition = (int) (y / perTextHeight);
        if (currentPosition > -1 && currentPosition < mLetters.length){
            letter= mLetters[currentPosition];
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.TRANSPARENT);
                mChoosed=-1;
                if (mTvLetter!=null){
                    mTvLetter.setVisibility(GONE);
                }
                break;
            default:
                setBackgroundColor(Color.parseColor("#cccccc"));
                if (currentPosition > -1 && currentPosition < mLetters.length) {
                    if (mTvLetter != null) {
                        mTvLetter.setVisibility(View.VISIBLE);
                        mTvLetter.setText(letter);
                    }
                    if (mListener != null) {
                        mListener.updataLetter(letter);
                    }
                    mChoosed = currentPosition;
                }
                break;
        }
        invalidate();
        return true;

    }
    public interface UpdataLetterListener{
        void updataLetter(String letter);
    }
    public void setLetterListener(TextView tvLetter,UpdataLetterListener listener){
        this.mTvLetter=tvLetter;
        this.mListener=listener;
    }

}
