package ecomoter.myapplication.spalashview;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ecomoter.myapplication.R;

public class CountDownActivity extends AppCompatActivity {
    private CountDownView mCdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        mCdView= (CountDownView) findViewById(R.id.cd_view);
        mCdView.setCountDownTimerListener(new CountDownView.CountDownTimerListener() {
            @Override
            public void onStartCount() {

            }

            @Override
            public void onChangeCount(int second) {

            }

            @Override
            public void onFinishCount() {

            }
        });
        mCdView.start();
        findViewById(R.id.btn_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog=new ProgressDialog(CountDownActivity.this);
                dialog.setMessage("加载中。。。");
                dialog.show();
            }
        });
    }
}
