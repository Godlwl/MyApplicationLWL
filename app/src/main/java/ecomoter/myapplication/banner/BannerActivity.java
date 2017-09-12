package ecomoter.myapplication.banner;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import ecomoter.myapplication.R;

public class BannerActivity extends AppCompatActivity implements BannerAdapter.OnClickL {
    private ViewPager mVpBanner;
    private ArrayList<String> mUrl;
    private BannerAdapter mAdapter;
    private int mCurrentPotion;
    private Handler mAutoHandler;

    private Button mBtnReLoad;

    /**
     * 指示器
     */
    private PageControlView mPcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner2);
        mAutoHandler=new AutoHandler();
        initData();
        mPcv= (PageControlView) findViewById(R.id.pcv);
        mPcv.count=mUrl.size();
        mVpBanner= (ViewPager) findViewById(R.id.vp_banner);
        mAdapter=new BannerAdapter(mUrl,this);
        mVpBanner.setAdapter(mAdapter);
        mVpBanner.addOnPageChangeListener(mOnPageChangeListener);
        mAdapter.notifyDataChanged(mUrl);
        mVpBanner.setCurrentItem(1);
        mPcv.generatePageControl(mVpBanner.getCurrentItem());
        mAutoHandler.removeCallbacksAndMessages(null);
        mAutoHandler.sendMessageDelayed(mAutoHandler.obtainMessage(0,mVpBanner.getCurrentItem()),3000);

        mBtnReLoad= (Button) findViewById(R.id.btn_reLoad);
        mBtnReLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reLoad();
            }
        });

    }

    private void reLoad(){
        mUrl.clear();
        mUrl.add("http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg");
        mUrl.add("http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg");
        mUrl.add("http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg");
        mUrl.add("http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg");
        mPcv.count=mUrl.size();
        mAdapter.notifyDataChanged(mUrl);
        mPcv.generatePageControl(mVpBanner.getCurrentItem());
//        mVpBanner.setCurrentItem(mVpBanner.getCurrentItem());


    }

    private void initData() {
        mUrl=new ArrayList<>();
        mUrl.add("http://61.144.248.2:8085/carManager/html/ueditor/jsp/upload/info/2017/06/01/pic_1496319418956.jpg");
        mUrl.add("http://61.144.248.2:8085/carManager/html/ueditor/jsp/upload/info/2017/05/26/pic_1495779591458.jpg");
        mUrl.add("http://61.144.248.2:8085/carManager/html/ueditor/jsp/upload/info/2017/05/19/pic_1495179248615.jpg");
        mUrl.add("http://61.144.248.2:8085/carManager/html/ueditor/jsp/upload/info/2017/05/09/pic_1494310505350.jpg");

    }
    ViewPager.OnPageChangeListener mOnPageChangeListener=new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Log.d("ViewPagerLog",position+"onPageSelected");
            mCurrentPotion=position;
            /**
             * 如果是滑到第一页或者最后一页，会调用两次onPageSelected，第一次是系统自动调用，第二次是我们调用。
             * 所以第一次调用不执行事务
             * 否则会出现指示器绘制两次
             */
            if (position==0||position==mAdapter.getCount()-1){
                return;
            }
            mPcv.generatePageControl(mCurrentPotion);
            mAutoHandler.removeCallbacksAndMessages(null);
            mAutoHandler.sendMessageDelayed(mAutoHandler.obtainMessage(0,mVpBanner.getCurrentItem()),3000);

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d("ViewPagerLog",state+"onPageScrollStateChanged");

            /**
             * 当viewpager正在滑动时，不让它轮播
             */
            if (state== ViewPager.SCROLL_STATE_DRAGGING){
                mAutoHandler.removeCallbacksAndMessages(null);
            }
            /**
             * 若viewpager滑动未停止，直接返回
             */
            if (state != ViewPager.SCROLL_STATE_IDLE) return;

            /**
             * 若当前为第一张，设置页面为倒数第二张
             */
            if (mCurrentPotion == 0) {
                mVpBanner.setCurrentItem(mAdapter.getCount()-2,false);
            } else if (mCurrentPotion == mAdapter.getCount()-1) {
                /**
                 *  若当前为倒数第一张，设置页面为第二张
                 */
                mVpBanner.setCurrentItem(1,false);
            }

        }
    };

    @Override
    public void onClick(int potision) {
        Toast.makeText(this,potision+"",Toast.LENGTH_SHORT).show();
    }

    private class AutoHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int position= (int) msg.obj;
            mVpBanner.setCurrentItem(position+1);



        }
    }

}
