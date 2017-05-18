package ecomoter.myapplication;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {
    private WebView mWv;
    private ImageView mIv;
    private List<String> mUrl=new ArrayList<>();
    private int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_web_view);
        initView();
        initData();
        mWv.loadUrl(mUrl.get(flag));
        mWv.setWebViewClient(new WebViewClient(){
                                 @Override
                                 public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                     mWv.loadUrl(url);
                                     return true;


                                 }
                             }
        );

    }

    private void initData() {
//        mUrl.add("www.baidu.com");
        mUrl.add("http://sz.seasaw.cn:9090/jinxun-api/system/getAppInfo.action?imgUrl=advanceactivity/20170411/8264337437059800&imgType=jpg");

        mUrl.add("http://sz.seasaw.cn:9090/jinxun-api/system/getAppInfo.action?imgUrl=advanceactivity/20170323/6645048462150600&imgType=jpg");
    }

    private void initView() {
        mWv= (WebView) findViewById(R.id.wv);
        mIv= (ImageView) findViewById(R.id.iv_close);
        mWv.getSettings().setJavaScriptEnabled(true);
        mIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (flag==mUrl.size()-1){
            this.finish();
        }else if (flag<mUrl.size()){
            flag++;
            mWv.loadUrl(mUrl.get(flag));
        }

    }
}
