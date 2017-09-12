package ecomoter.myapplication.HorizonSelect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ecomoter.myapplication.R;

public class MyScrollViewActivity extends AppCompatActivity {
    private MyScrollView mSc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scroll_view);
        mSc= (MyScrollView) findViewById(R.id.sc);
    }
}
