package ecomoter.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ScrollViewActivity extends AppCompatActivity {
    private ImageView mIv1;
    private ImageView mIv2;
    private ImageView mIv3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        mIv1= (ImageView) findViewById(R.id.intro_img_top);
        mIv2= (ImageView) findViewById(R.id.intro_img_01);
        mIv3= (ImageView) findViewById(R.id.intro_img_02);

        mIv1.setImageResource(R.drawable.company_info);
        mIv2.setImageResource(R.drawable.jl_intro_10);
        mIv3.setImageResource(R.drawable.jl_intro_11);
    }
}
