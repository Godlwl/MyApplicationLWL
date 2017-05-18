package ecomoter.myapplication;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import static android.R.attr.id;
import static android.R.id.tabcontent;

public class TabHostActivity extends AppCompatActivity {
    private TabHost mTh;
    private TabWidget mTw;
    private View mContainer;
    private LocalActivityManager mLam;
    private RelativeLayout tab01,tab02,tab03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);
        mTh= (TabHost) findViewById(R.id.tb_test);
        mTw = (TabWidget) findViewById(android.R.id.tabs);
        mContainer=findViewById(android.R.id.tabcontent);
        mLam=new LocalActivityManager(this,false);
        mLam.dispatchCreate(savedInstanceState);
        mTh.setup(mLam);
        addActivity();
    }

    private void addActivity() {
//        tab01 = initTab(R.drawable.ic_menu_camera, "首页");
//        tab02 = initTab(R.drawable.ic_menu_gallery, "资讯");
//        tab03 = initTab(R.drawable.ic_menu_manage, "知识");
        Intent intent1=new Intent(this,MainActivity.class);
        TabHost.TabSpec page1= mTh.newTabSpec("page1");
        page1.setIndicator("首页");
        page1.setContent(intent1);


        Intent intent2=new Intent(this,MainActivity.class);
        TabHost.TabSpec page2= mTh.newTabSpec("page2");
        page2.setIndicator("资讯");
        page2.setContent(intent2);

        Intent intent3=new Intent(this,SecondActivity.class);
        TabHost.TabSpec page3= mTh.newTabSpec("page3");
        page3.setIndicator("知识");
        page3.setContent(intent3);

        mTh.addTab(page1);
        mTh.addTab(page2);
        mTh.addTab(page3);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLam.dispatchResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLam.dispatchPause(isFinishing());
    }

//    private RelativeLayout initTab(int iconRes, String text) {
//        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.item_tab_view, null);
//        TextView iconTV = (TextView) layout.findViewById(R.id.img);
//        iconTV.setText(text);
//        Drawable img = getResources().getDrawable(iconRes);
//        // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
//        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
//        // iconTV.setBackgroundResource(iconRes);
//        iconTV.setCompoundDrawables(null, img, null, null);
//        return layout;
//    }
}
