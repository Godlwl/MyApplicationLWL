package ecomoter.myapplication;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button mBtn;
    private Handler mHandler;
    private ImageView mIv;
    private Bitmap mBt;
    private DialogFragment1 fragment1;
    private SkillStatisticsCircleView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ProgressDialog dialog=new ProgressDialog(this);
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setMessage("正在加载中...");
//        dialog.show();
        fragment1=new DialogFragment1();
        mHandler=new Handler();
        mBtn= (Button) findViewById(R.id.btn_tz);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,WebViewActivity.class);
//                startActivity(intent);
//               overridePendingTransition(R.anim.push_bottom_in,R.anim.push_bottom_out);
//                ProgressDialog dialog=new ProgressDialog(MainActivity.this,R.style.MyProgressDialog);
//                dialog.setMessage("加载中。。。");
//                dialog.show();

//          Intent intent=new Intent(MainActivity.this,DownLoadActivity.class);
//                startActivity(intent);
//                fragment1.show(getFragmentManager(),null);
                Log.d("getDir",getFilesDir().getAbsolutePath().toString());
                Intent intent = new Intent("com.android.camera.action.CROP");

                String path = "/storage/emulated/0/DCIM/Camera/IMG_20161228_204740.jpg";

                String output= Environment.getExternalStorageDirectory().getAbsolutePath()+"/images/";
                File tempFile=new File(output);
                if (!tempFile.exists()){
                    tempFile.mkdirs();
                }
//                Uri uri = Uri.parse("file://"+ path);

                File file=new File(path);
                Uri uri=Uri.fromFile(file);
                intent.setDataAndType(uri, "image/*");

                intent.putExtra("crop", "true");
                intent.putExtra("outputX", 80);
                intent.putExtra("outputY", 80);
                intent.putExtra("return-data", false);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(output+"/1.jpg")));
                startActivityForResult(intent, 1);
            }
        });
        Log.d("THREAD","MAIN"+Thread.currentThread().getId()+"");
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("THREAD","HANDLER"+Thread.currentThread().getId()+"");
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d("THREAD","new Thread"+Thread.currentThread().getId()+"");
//                    }
//                }).start();
                Toast.makeText(MainActivity.this,"Handler",Toast.LENGTH_SHORT).show();
            }
        },2000);
        Toast.makeText(MainActivity.this,"Main",Toast.LENGTH_SHORT).show();
mIv= (ImageView) findViewById(R.id.iv_url);
        Glide.with(this).load("http://61.144.248.2:8085/carManager/html/ueditor/jsp/upload/1493273584430.gif").into(mIv);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        mBt=((BitmapDrawable)loadImageFromUrl("http://61.144.248.2:9090/upload/newcar/14326/thumbnail/1/149760397275840_2.jpg")).getBitmap();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    mHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                    mIv.setImageBitmap(mBt);
//
//                            }
//                        });
//                }
//            }).start();

        mView= (SkillStatisticsCircleView) findViewById(R.id.view_skill);
        mView.setCount(10);

    }
    public static Drawable loadImageFromUrl(String url) throws IOException {


                URL m = new URL(url);
                InputStream i = (InputStream) m.getContent();
                Drawable d = Drawable.createFromStream(i, "src");
               return d;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
