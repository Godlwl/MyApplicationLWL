package ecomoter.myapplication;

import android.graphics.Color;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView mIv;
    private Button mBtnStart;
    private Button mBtnStop;
    private boolean flag;
    private int mHeight;

    private float mX;
    private float mY;


    private Button mBtnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        mIv= (ImageView) findViewById(R.id.iv_ic);
        mBtnStart= (Button) findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(this);

        mBtnStop= (Button) findViewById(R.id.btn_stop);
        mBtnStop.setOnClickListener(this);

        mBtnChange= (Button) findViewById(R.id.btn_change);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mHeight=mIv.getHeight();

        mX= mIv.getX();
        mY= mIv.getY();
        Log.d("xxxyyy","mX:"+mX+" mY:"+mY);
    }

    ObjectAnimator animator;
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_start:
                animator = ObjectAnimator.ofFloat(mIv, "rotation", 0, 360);
                animator.setDuration(500);
                animator.setRepeatMode(ValueAnimator.RESTART);
                animator.setRepeatCount(-1);
                animator.start();

                ValueAnimator animator1=ValueAnimator.ofFloat(0,1);

                animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    ArgbEvaluator argb=new ArgbEvaluator();
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float cur= (float) animation.getAnimatedValue();

                        mBtnChange.setBackgroundColor((int) argb.evaluate(cur, Color.RED,Color.GREEN));
                    }
                });
                animator1.setDuration(2000);
                animator1.start();
                break;
            case R.id.btn_stop:
                if(animator.isRunning()){
                    animator.cancel();
                }
                break;
        }

//        ObjectAnimator.ofFloat(mIv,"rotationY",0,180).setDuration(2000).start();
//        if (mIv.getVisibility()==View.VISIBLE){
//            flag=false;
//            ValueAnimator animator= ValueAnimator.ofInt(mHeight,0);
//            animator.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mIv.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//
//                }
//            });
//            animator.setDuration(2000);
//            animator.setInterpolator(new AccelerateInterpolator());
//            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    ViewGroup.LayoutParams params=mIv.getLayoutParams();
//                    params.height=(int)animation.getAnimatedValue();
//                    mIv.setLayoutParams(params);
//
//                }
//            });
//            animator.start();
//        }else {
//            flag=true;
//            mIv.setVisibility(View.VISIBLE);
//            ValueAnimator animator= ValueAnimator.ofInt(0,mHeight);
//            animator.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//
//                }
//            });
//            animator.setDuration(2000);
//            animator.setInterpolator(new DecelerateInterpolator());
//            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    ViewGroup.LayoutParams params=mIv.getLayoutParams();
//                    params.height=(int)animation.getAnimatedValue();
//                    mIv.setLayoutParams(params);
//
//                }
//            });
//            animator.start();
//        }

//        ObjectAnimator.ofFloat(mIv,"translationY",0,200).setDuration(2000).start();
    }

}
