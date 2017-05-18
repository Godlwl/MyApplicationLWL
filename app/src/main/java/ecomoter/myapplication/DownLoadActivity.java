package ecomoter.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.download.DownloadInfo;
import com.lzy.okserver.download.DownloadManager;
import com.lzy.okserver.download.DownloadService;
import com.lzy.okserver.download.db.DownloadDBManager;
import com.lzy.okserver.listener.DownloadListener;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class DownLoadActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar mPb;
    private TextView mTvProgress;
    private Button mBtnDown;
    private Button mBtnPause;
    private Button mBtnCancel;
    private String mDownUrl="http://61.144.248.2:9090/upload/apk/JLCar3.5.8.apk";


    /**
     * 下载管理
     * 断点下载和下载状态管理
     */
    private DownloadManager mDownManager;

    /**
     * 下载管理信息
     * @param savedInstanceState
     */
    private DownloadInfo mDownInfo;

    /**
     * 下载监听
     * @param savedInstanceState
     */
    private DownListen mListen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        initView();
        mDownManager= DownloadService.getDownloadManager();
        mDownInfo = mDownManager.getDownloadInfo(mDownUrl);
        mListen=new DownListen();
        if (mDownInfo != null) {
            //如果任务存在，把任务的监听换成当前页面需要的监听
            mDownInfo.setListener(mListen);
            //需要第一次手动刷一次，因为任务可能处于下载完成，暂停，等待状态，此时是不会回调进度方法的
//            refreshUi(downloadInfo);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 一开启就设置进度，之前未下载完的
         */
        if (mDownInfo != null) {
            mTvProgress.setText((int) (mDownInfo.getProgress() * 100) + "%");
            mPb.setProgress((int) (mDownInfo.getProgress() * 100));
        }
        /**
         * 刷新按钮状态
         */
        refreshButton(mDownInfo);
    }


    private void initView() {
        mPb= (ProgressBar) findViewById(R.id.pb_download);
        mTvProgress= (TextView) findViewById(R.id.tv_progress);
        mBtnDown= (Button) findViewById(R.id.btn_down);
        mBtnPause= (Button) findViewById(R.id.btn_pause);
        mBtnCancel= (Button) findViewById(R.id.btn_cancel);
        mBtnDown.setOnClickListener(this);
        mBtnPause.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        /**
         * 点击先获取下载管理信息
         */
        mDownInfo=mDownManager.getDownloadInfo(mDownUrl);
        switch (v.getId()){
            case R.id.btn_down:
                if (mDownInfo==null){
                    GetRequest request=OkGo.get(mDownUrl).tag(this);
                    mDownManager.addTask(mDownUrl, request,mListen);
//                    mBtnDown.setText("重新下载");
                    return;
                } else {
                    mDownManager.restartTask(mDownUrl);
//                    mBtnPause.setText("暂停");
//                    mBtnDown.setText("重新下载");
                }

                /**
                 * 不使用下载管理
                 */
//                request.tag(this)
//                        .execute(new FileCallback() {
//                            @Override
//                            public void onSuccess(File file, Call call, Response response) {
//
//                            }
//
//                            @Override
//                            public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
//                                super.downloadProgress(currentSize, totalSize, progress, networkSpeed);
//                                mPb.setProgress((int)(progress*100));
//                                mTvProgress.setText((int)(progress*100)+"%");
//                            }
//                        });


                break;
            case R.id.btn_pause:
                if (mDownInfo==null){
                    Toast.makeText(DownLoadActivity.this,"请先开始下载",Toast.LENGTH_SHORT).show();
                }else {
                    switch (mDownInfo.getState()) {
                        case DownloadManager.PAUSE:
                            Log.d("STATE","PAUSE");
                            mDownManager.addTask(mDownInfo.getUrl(), mDownInfo.getRequest(), mListen);
//                            mBtnPause.setText("暂停");
                            break;
                        case DownloadManager.NONE:
                            Log.d("STATE","NONE");
                            mDownManager.addTask(mDownInfo.getUrl(), mDownInfo.getRequest(), mListen);
                            break;
                        case DownloadManager.ERROR:
                            Log.d("STATE","ERROR");
                            mDownManager.addTask(mDownInfo.getUrl(), mDownInfo.getRequest(), mListen);
//                            mBtnPause.setText("暂停");
                            break;
                        case DownloadManager.DOWNLOADING:
                            Log.d("STATE","DOWNLOADING");
                            mDownManager.pauseTask(mDownUrl);
//                            mBtnPause.setText("继续");
                            break;
                        case DownloadManager.FINISH:
                            Log.d("STATE","FINISH");
//                            if (ApkUtils.isAvailable(this, new File(downloadInfo.getTargetPath()))) {
//                                ApkUtils.uninstall(this, ApkUtils.getPackageName(this, downloadInfo.getTargetPath()));
//                            } else {
//                                ApkUtils.install(this, new File(downloadInfo.getTargetPath()));
//                            }
                            break;
                    }
                }

                break;
            case R.id.btn_cancel:
//                mDownManager.stopTask(mDownUrl);
//                OkGo.getInstance().cancelTag(this);

                if (mDownInfo == null) {
                    Toast.makeText(this, "请先下载任务", Toast.LENGTH_SHORT).show();
                    return;
                }
                mDownManager.removeTask(mDownInfo.getUrl());
                mPb.setProgress(0);
                mTvProgress.setText("0%");
                mBtnDown.setText("下载");
                mBtnPause.setText("暂停");
                Log.d("STATE",mDownInfo.getState()+"");
                break;
        }

    }

    class DownListen extends DownloadListener{

        @Override
        public void onProgress(DownloadInfo downloadInfo) {
            mPb.setProgress((int)(downloadInfo.getProgress()*100));
            mTvProgress.setText((int)(downloadInfo.getProgress()*100)+"%");
            refreshButton(downloadInfo);
        }

        @Override
        public void onFinish(DownloadInfo downloadInfo) {
//            installApk(new File(downloadInfo.getTargetPath()));
        }

        @Override
        public void onError(DownloadInfo downloadInfo, String errorMsg, Exception e) {

        }
    }

    /**
     * 更新按钮状态
     * @param mDownInfo
     */
    public void refreshButton(DownloadInfo mDownInfo){
        if (mDownInfo==null){
            mBtnDown.setText("下载");
        }else {
            mBtnDown.setText("重新下载");

            switch (mDownInfo.getState()) {
                case DownloadManager.PAUSE:
                    Log.d("STATE","PAUSE");
                    mBtnPause.setText("继续");
                    break;
                case DownloadManager.NONE:
                    Log.d("STATE","NONE");
                    mBtnPause.setText("继续");
                    break;
                case DownloadManager.ERROR:
                    Log.d("STATE","ERROR");
//                    mBtnPause.setText("暂停");
                    break;
                case DownloadManager.DOWNLOADING:
                    Log.d("STATE","DOWNLOADING");
                    mBtnPause.setText("暂停");
                    break;
                case DownloadManager.FINISH:
                    Log.d("STATE","FINISH");
                    break;
            }

        }
    }

    /**
     * 安装APK文件
     */
    private void installApk(File file) {
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        this.startActivity(i);
    }

}
