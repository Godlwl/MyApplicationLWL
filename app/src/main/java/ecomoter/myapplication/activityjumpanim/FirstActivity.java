package ecomoter.myapplication.activityjumpanim;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ecomoter.myapplication.R;
import ecomoter.myapplication.startwithbrowser.SecondActivity;

public class FirstActivity extends AppCompatActivity {
    private Button mBtn;
    private Spinner mSp;
    private List<String> mData=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first2);
        findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        mSp= (Spinner) findViewById(R.id.sp);
        mData.add("test1");
        mData.add("test2");
        mData.add("test3");
        mData.add("test4");
//        ArrayAdapter<String> car_adapter = new ArrayAdapter<String>(this,R.layout.simple_spinner_item, mData);
        MyAdapter car_adapter = new MyAdapter(this,R.layout.simple_spinner_item);
//        ArrayAdapter<String> car_adapter = new ArrayAdapter<String>(this, R.layout.spinner_display_style,R.id.txtvwSpinner, mData);
//        car_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSp.setAdapter(car_adapter);
    }

    class MyAdapter extends ArrayAdapter<String>{

        public MyAdapter(Context context, int resource) {
            super(context, resource);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //修改Spinner展开后的字体颜色
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent,false);
            }

            //此处text1是Spinner默认的用来显示文字的TextView
            TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
            tv.setText(mData.get(position));
            tv.setTextSize(22f);
            tv.setTextColor(Color.RED);

            return convertView;
//            if(convertView==null){
//                convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_dropdown_item,parent,false);
//            }
//            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            //修改Spinner展开后的字体颜色
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent,false);
            }

            //此处text1是Spinner默认的用来显示文字的TextView
            TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
            tv.setText(mData.get(position));
            tv.setTextSize(22f);
            tv.setTextColor(Color.RED);

            return convertView;
        }

        @Override
        public int getCount() {
            return mData.size();
        }
    }
}
