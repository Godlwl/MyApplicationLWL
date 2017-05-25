package ecomoter.myapplication;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AnimatorActivity2 extends AppCompatActivity {
    private ImageView mIv1;
    private ImageView mIv2;
    private ImageView mIv3;
    private ImageView mIv4;

    private Button mBtn;
    /**
     * 屏幕宽高
     *
     * @param savedInstanceState
     */
    private int mScreenWidth;
    private int mScreenHeight;

    /**
     * 控件初始位置
     *
     * @param savedInstanceState
     */
    private float mIv1X, mIv2X, mIv3X, mIv4X;
    private float mIv1Y, mIv2Y, mIv3Y, mIv4Y;

    /**
     * 控件宽高
     *
     * @param savedInstanceState
     */
    private float mWidth;
    private float mHeight;

    /**
     * 控件集合
     *
     * @param savedInstanceState
     */
    private List<View> mViewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator2);
        initView();

    }

    private void initAnim() {
        createAnim(mIv1, -mIv1X - mWidth, -mIv1Y - mHeight, 0, 0);
//        createAnim(mIv2,mScreenWidth-mIv2X,-mIv2Y-mHeight,0,0);
//        createAnim(mIv3,-mIv3X-mWidth,mScreenHeight-mIv3Y,0,0);
//        createAnim(mIv4,mScreenWidth-mIv4X,mScreenHeight-mIv4Y,0,0);

    }

    private void initView() {
        mIv1 = (ImageView) findViewById(R.id.iv1);
        mIv2 = (ImageView) findViewById(R.id.iv2);
        mIv3 = (ImageView) findViewById(R.id.iv3);
        mIv4 = (ImageView) findViewById(R.id.iv4);
        mBtn = (Button) findViewById(R.id.btn_start);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initAnim();
            }
        });
        mViewList.add(mIv1);
        mViewList.add(mIv2);
        mViewList.add(mIv3);
        mViewList.add(mIv4);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        getScreen();
        /**
         * X坐标
         */
        int[] screen1 = new int[2];
        /**
         * 获取到屏幕原点的距离
         */
        mIv1.getLocationInWindow(screen1);
        int[] screen2 = new int[2];
        mIv2.getLocationInWindow(screen2);
        int[] screen3 = new int[2];
        mIv3.getLocationInWindow(screen3);
        int[] screen4 = new int[2];
        mIv4.getLocationInWindow(screen4);
        mIv1X = screen1[0];
        mIv2X = screen2[0];
        mIv3X = screen3[0];
        mIv4X = screen4[0];
//        mIv2X=mIv2.getLeft();
//        mIv3X=mIv3.getLeft();
//        mIv4X=mIv4.getLeft();

        /**
         * Y坐标
         */
        mIv1Y = screen1[1];
        mIv2Y = screen2[1];
        mIv3Y = screen3[1];
        mIv4Y = screen4[1];
//        mIv2Y=mIv2.getTop();
//        mIv3Y=mIv3.getTop();
//        mIv4Y=mIv4.getTop();

        mWidth = mIv1.getWidth();
        mHeight = mIv1.getHeight();
        Log.d("tttt", "mIv1X:" + mIv1X + " mIv1Y:" + mIv1Y + " mScreenWidth：" + mScreenWidth + " mScreenHeight:" + mScreenHeight);

    }

    private void getScreen() {
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        mScreenWidth = wm.getDefaultDisplay().getWidth();
        mScreenHeight = wm.getDefaultDisplay().getHeight();
    }

    private void createAnim(final View view, float startX, float startY, float endX, float endY) {
        AnimatorSet set1 = new AnimatorSet();
        set1.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", startX, endX),
                ObjectAnimator.ofFloat(view, "translationY", startY, endY),
                ObjectAnimator.ofFloat(view, "rotation", 0, 360)

        );
        set1.setDuration(500);
        set1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (view == mViewList.get(0)) {
                    createAnim(mIv2, mScreenWidth - mIv2X, -mIv2Y - mHeight, 0, 0);


                } else if (view == mViewList.get(1)) {
                    createAnim(mIv3, -mIv3X - mWidth, mScreenHeight - mIv3Y, 0, 0);
                } else if (view == mViewList.get(2)) {
                    createAnim(mIv4, mScreenWidth - mIv4X, mScreenHeight - mIv4Y, 0, 0);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set1.start();
    }
}
