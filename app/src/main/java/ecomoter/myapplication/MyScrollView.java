package ecomoter.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.Toast;

/**
 * Created by lwl on 2017/4/18.
 * Describe:
 */

public class MyScrollView extends ScrollView {
    private View mFirst;
    private View mSecond;
    private View mParent;
    private Context context;
    private boolean canScroll=true;
    /**
     * 加载web标志
     */
    private boolean canLoadWeb=true;
    private CanLoadWebListener mLoadWebListener;
    public MyScrollView(Context context) {
        super(context);
        this.context=context;
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        /**
         * 滑到顶部或底部后不再继续滑动
         */
        setOverScrollMode(OVER_SCROLL_NEVER);
        Log.d("yyy","初始化2");
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        setOverScrollMode(OVER_SCROLL_NEVER);
        Log.d("yyy","初始化3");
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int firstToBottom = mFirst.getBottom();
        int secondToBottom = mFirst.getMeasuredHeight()-getHeight()+mSecond.getMeasuredHeight();
        if (secondToBottom <= t) {
            if(canLoadWeb){
                mLoadWebListener.loadWeb();
            }
            canLoadWeb=false;

            Log.d("yyy","展示web");
        }
        if (getScrollY()<=0||getScrollY()==secondToBottom){
            canScroll=false;
        }else {
            canScroll=true;
        }
        Log.d("yyy",secondToBottom+"");
        Log.d("yyy","t:"+t+"");
        Log.d("yyy","getScrolly"+getScrollY());

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mParent=getChildAt(0);
        mFirst=((ViewGroup)getChildAt(0)).getChildAt(0);
        mSecond=((ViewGroup)getChildAt(0)).getChildAt(1);
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
    /**
     * px转换dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
            return super.onTouchEvent(ev);


    }
    public void setCanScrollListener(CanLoadWebListener loadWebListener){
        mLoadWebListener=loadWebListener;
    }


    interface CanLoadWebListener{
        void loadWeb();
    }
}
