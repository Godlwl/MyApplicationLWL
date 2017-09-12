package ecomoter.myapplication.sideindex;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import ecomoter.myapplication.R;

public class SideIndexActivity extends AppCompatActivity {
    private SideIndexView mSiv;
    private TextView mTvL;

    private TextView mTvAddress;
    private TextView mTvLoc;

    // 定位相关
    protected LocationClient mLocClient;
    protected MyLocationListenner myListener = new MyLocationListenner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_index);
        mSiv= (SideIndexView) findViewById(R.id.siv);
        mTvL= (TextView) findViewById(R.id.tv_letter);

        mSiv.setLetterListener(mTvL, new SideIndexView.UpdataLetterListener() {
            @Override
            public void updataLetter(String letter) {

            }
        });

        findViewById(R.id.btn_navi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent();

// 驾车导航

                i1.setData(Uri.parse("baidumap://map/navi?query=故宫"));

                startActivity(i1);
            }
        });
        mTvAddress= (TextView) findViewById(R.id.tv_address);
        mTvLoc= (TextView) findViewById(R.id.tv_lo);
        mLocClient = new LocationClient(getApplicationContext());
        mLocClient.registerLocationListener(myListener);
        setLocationOption();
        mLocClient.start();

    }
    /**
     * 监听函数，有新位置的时候，格式化成字符串，输出到屏幕中
     */
    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            try {
                mTvLoc.setText(location.getLongitude()+","+location.getLatitude());
                mTvAddress.setText(location.getAddrStr());
                Log.d("MyLocationListenner",location.getLongitude()+","+location.getLatitude());
                mLocClient.stop();
            } catch (Exception e) {
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }

        // @Override
        // public void onReceivePoi(BDLocation poiLocation) {
        // if (poiLocation == null) {
        // return;
        // } else {
        // // vibrator.vibrate(1000);
        // }
        // }
    }
    /**
     * 设置相关参数
     */
    private void setLocationOption() {
        LocationClientOption option = new LocationClientOption();

        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll");// 设置坐标类型
        // option.setServiceName("com.baidu.location.service_v2.9");
        option.setIsNeedAddress(true);
        option.setScanSpan(3000);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        // option.setPriority(LocationClientOption.NetWorkFirst);// 设置网络优先

        mLocClient.setLocOption(option);
    }
}
