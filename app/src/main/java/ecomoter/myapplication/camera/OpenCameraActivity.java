package ecomoter.myapplication.camera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ecomoter.myapplication.R;

public class OpenCameraActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtn1;
    private Button mBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_camera);
        mBtn1= (Button) findViewById(R.id.btn_camera1);
        mBtn2= (Button) findViewById(R.id.btn_camera2);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_camera1:
                Intent intent=new Intent(this,CameraActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_camera2:
                Intent intent1=new Intent(this,Camera2Activity.class);
                startActivity(intent1);
                break;
        }
    }
}
