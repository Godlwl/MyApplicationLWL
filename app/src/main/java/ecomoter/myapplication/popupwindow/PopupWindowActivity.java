package ecomoter.myapplication.popupwindow;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import ecomoter.myapplication.R;

public class PopupWindowActivity extends AppCompatActivity {
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        mBtn= (Button) findViewById(R.id.btn_show);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popupWindow=new PopupWindow(PopupWindowActivity.this);
                popupWindow.setWidth(mBtn.getWidth());
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setContentView(LayoutInflater.from(PopupWindowActivity.this).inflate(R.layout.popup_layout,null));
                popupWindow.setOutsideTouchable(false);
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
//                popupWindow.showAsDropDown(mBtn);
                popupWindow.showAtLocation(mBtn.getRootView(), Gravity.CENTER,0,0);
            }
        });
    }
}
