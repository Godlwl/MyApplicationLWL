package ecomoter.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    private ArrayList<ViewDataModel> datas=new ArrayList<>();
    private MyView mMyView;
    private Handler mHandler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        mMyView=(MyView)findViewById(R.id.view_my);
        initData();
        mMyView.setData(datas);
        mHandler=new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mMyView.setProgress();
                mHandler.postDelayed(this,100);
            }
        }, 0);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i=0;i<20;i++){
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//
//                }
//
//            }
//        }).start();

    }



    private void initData() {
        ViewDataModel model1=new ViewDataModel();
        model1.setColor(0xFFCCFF00);
        model1.setPercent(0.3f);
        model1.setAngel(0.3f*360f);

        ViewDataModel model2=new ViewDataModel();
        model2.setColor(0xFF6495ED);
        model2.setPercent(0.2f);
        model2.setAngel(0.2f*360f);

        ViewDataModel model3=new ViewDataModel();
        model3.setColor(0xFFE32636);
        model3.setPercent(0.4f);
        model3.setAngel(0.4f*360f);

        ViewDataModel model4=new ViewDataModel();
        model4.setColor(0xFF800000);
        model4.setPercent(0.1f);
        model4.setAngel(0.1f*360f);

        datas.add(model1);
        datas.add(model2);
        datas.add(model3);
        datas.add(model4);


    }
}
