package ecomoter.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LeakcanaryActivity extends AppCompatActivity {
    private static TestLeak mTestLeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leakcanary);
        if (mTestLeak==null){
            mTestLeak=new TestLeak();
        }
    }

    class TestLeak{}

}
