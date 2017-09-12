package ecomoter.myapplication.suspendscroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import ecomoter.myapplication.R;

public class SuspendScrollActivity extends AppCompatActivity {
    /**
     * scrollview
     */
    private SuspendScrollView mSv;
    /**
     * 导航栏
     */
    private TextView mTvDhl;
    /**
     * 悬浮导航栏
     */
    private TextView mSusDhl;

    /**
     * 导航栏高度
     * @param savedInstanceState
     */
    private int mDhlHeight;

    /**
     * 导航栏距顶部距离
     * @param savedInstanceState
     */
    private int mDhlTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspend_scroll);
        mSv= (SuspendScrollView) findViewById(R.id.ssv);
        mTvDhl= (TextView) findViewById(R.id.tv_dhl);
        mSusDhl= (TextView) findViewById(R.id.tv_suspned_dhl);
        mSv.setScrollListener(new SuspendScrollView.SuspendScrollListener() {
            @Override
            public void onScroll(int scroollY) {
                Log.d("SuspendScrollActivity","scrollY:"+scroollY+" mDhlTop+mDhlHeight:"+mDhlTop+mDhlHeight);
                if (scroollY>=mDhlTop){
                    mSusDhl.setVisibility(View.VISIBLE);
                }else if (scroollY<=mDhlTop+mDhlHeight){
                    mSusDhl.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            mDhlHeight=mTvDhl.getMeasuredHeight();
            mDhlTop=mTvDhl.getTop();

        }
    }
}
