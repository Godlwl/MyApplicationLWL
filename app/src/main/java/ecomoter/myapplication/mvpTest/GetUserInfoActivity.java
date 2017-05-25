package ecomoter.myapplication.mvpTest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import ecomoter.myapplication.R;
import ecomoter.myapplication.model.JsonModel;

public class GetUserInfoActivity extends AppCompatActivity implements GetUserInfoView, View.OnClickListener {
    private GetUserInfoPresent mPresent;
    private NetUtil mNet;

    private TextView mTvName;
    private TextView mTvSex;
    private TextView mTvAge;
    private Button mBtnGet;

    private String mUrl="http://sz.seasaw.cn:8085/carManager/carManager/personal!myBasic.action?";

    /**
     * 参数
     * @param savedInstanceState
     */

    private HashMap<String,String> mValue=new HashMap<>();

    private ProgressDialog mDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_info);
        mNet=new NetUtil();
        mPresent=new GetUserInfoPresent(mNet);
        mPresent.attachView(this);
        initView();

    }

    @Override
    public void getUserInfo(final JsonModel user) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
        if (user!=null) {
            mTvName.setText("姓名： " + user.getName());
            mTvSex.setText("性别： " + user.getSex());
            mTvAge.setText("年龄： " + user.getAge());
        }
//            }
//        });


    }

    @Override
    public void initView() {
        mTvName= (TextView) findViewById(R.id.tv_name);
        mTvSex= (TextView) findViewById(R.id.tv_sex);
        mTvAge= (TextView) findViewById(R.id.tv_age);
        mBtnGet= (Button) findViewById(R.id.btn_get);
        mBtnGet.setOnClickListener(this);

        mDialog=new ProgressDialog(this);
        mDialog.setCancelable(false);
        mDialog.setMessage("正在请求数据");

    }

    @Override
    public void showLoading() {
        if (mDialog!=null&&!mDialog.isShowing()){
            mDialog.show();
        }

    }

    @Override
    public void hideLoading() {
        if (mDialog!=null&&mDialog.isShowing()){
            mDialog.hide();
        }
    }

    @Override
    public void showLoadDataSuccess() {

    }

    @Override
    public void showLoadDataFailed(String msg) {

    }

    @Override
    public void onClick(View v) {
        mValue.put("user_type","1");
        mValue.put("user_id","13560788876");
        mValue.put("phone","13560788876");
        mPresent.getUser(mUrl,mValue);
    }
}
