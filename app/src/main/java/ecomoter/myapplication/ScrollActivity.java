package ecomoter.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ScrollView;

public class ScrollActivity extends AppCompatActivity {
    private WebView mWv;
    private MyScrollView mMyScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        mWv= (WebView) findViewById(R.id.wv);
        mMyScrollView= (MyScrollView) findViewById(R.id.activity_scroll);
        mMyScrollView.setCanScrollListener(new MyScrollView.CanLoadWebListener() {
            @Override
            public void loadWeb() {
                mWv.loadUrl("http://sz.seasaw.cn:9090/jinxun-api/system/getAppInfo.action?imgUrl=advanceactivity/20170411/8264337437059800&imgType=jpg");
            }
        });
        mWv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWv.loadUrl(url);
                return true;


            }
        });
    }
}
