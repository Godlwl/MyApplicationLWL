package ecomoter.myapplication.suspendscroll;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by lwl on 2017/9/9.
 * Describe:
 */

public class SuspendScrollView extends ScrollView {
    /**
     * 滑动监听
     */
    private SuspendScrollListener mScrollListener;

    /**
     *最后滑动的位置
     */
    private int mLastScrollY;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int scrolly=getScrollY();
            Log.d("SuspendScrollView","handlerscrolly"+scrolly);
            if (scrolly!=mLastScrollY){
                mLastScrollY=scrolly;
                mHandler.sendMessageDelayed(Message.obtain(),5);
            }
            if (mScrollListener!=null){
                mScrollListener.onScroll(mLastScrollY);
            }

        }
    };
    public SuspendScrollView(Context context) {
        super(context);
    }

    public SuspendScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                if (mScrollListener!=null){
                    mScrollListener.onScroll(getScrollY());
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.d("SuspendScrollView","ACTION_UP"+getScrollY());
                mHandler.sendMessageDelayed(Message.obtain(),5);
                break;
        }
        return super.onTouchEvent(ev);

    }

    public void setScrollListener(SuspendScrollListener scrollListener){
        this.mScrollListener=scrollListener;

    }
    interface SuspendScrollListener{
        void onScroll(int scroollY);
    }
}
