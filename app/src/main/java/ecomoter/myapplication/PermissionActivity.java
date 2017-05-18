package ecomoter.myapplication;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class PermissionActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    private final static int REQUEST = 1;
    private Button mBtnPic;
    private Toolbar mTb;
    private boolean flag=true;

    private ProgressBar mPb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        mTb= (Toolbar) findViewById(R.id.tb_bar);
        setSupportActionBar(mTb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTb.setTitle("ToolBar");
//        mTb.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=!flag;
                Toast.makeText(PermissionActivity.this,flag+"",Toast.LENGTH_SHORT).show();

            }
        });
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        Uri data = Uri.parse("tel:" + "10086");
//        intent.setData(data);
//        startActivity(intent);
        mBtnPic = (Button) findViewById(R.id.btn_pic);
        mBtnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(PermissionActivity.this);
//                builder.setTitle("哈哈哈哈");
//                builder.setMessage("测试测试测试");
//                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                builder.show();
                takePic();
            }
        });

        mPb= (ProgressBar) findViewById(R.id.pro);
        mPb.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);

    }

    private void takePic() {
        /**
         * 原始方法（未封装）
         */
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
//              requestPermissions(new String[]{Manifest.permission.CALL_PHONE},REQUEST);
//        }else {
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        Uri data = Uri.parse("tel:" + "10086");
//        intent.setData(data);
//        startActivity(intent);
//        Toast.makeText(this,"已授权",Toast.LENGTH_SHORT).show();
//        }

//        /**
//         * 框架
//         */
        String[] perms = {Manifest.permission.CAMERA};
//        if (EasyPermissions.hasPermissions(this, perms)) {
//            //...
//            Toast.makeText(this,"已授权",Toast.LENGTH_SHORT).show();
//        } else {
//            EasyPermissions.requestPermissions(this, "拍照需要摄像头权限",
//                    REQUEST, perms);
//        }

        /**
         * 框架封装
         */
        performCodeWithPermission("拍照需要摄像头权限", REQUEST, perms, new PermissionCallback() {
            @Override
            public void hasPermission(List<String> allPerms) {
                Toast.makeText(PermissionActivity.this,"已授权",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void noPermission(List<String> deniedPerms, List<String> grantedPerms, Boolean hasPermanentlyDenied) {

                if (hasPermanentlyDenied){
//                    Toast.makeText(PermissionActivity.this,"未授权并且不再提醒",Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(PermissionActivity.this)
                            .setMessage("调用拍照功能需要获取权限，是否跳转权限设置？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent localIntent = new Intent();
                                    localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                    localIntent.setData(Uri.fromParts("package", getPackageName(), null));
                                    PermissionActivity.this.startActivity(localIntent);
                                }

                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                             .show();

                }
//                Toast.makeText(PermissionActivity.this,"未授权",Toast.LENGTH_SHORT).show();

            }
        });
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//    }
//
//    @Override
//    public void onPermissionsGranted(int requestCode, List<String> perms) {
//        Toast.makeText(this,"已授权",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        Toast.makeText(this,"未授权",Toast.LENGTH_SHORT).show();
//        /**
//         * 点击不在提醒按钮
//         */
//        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//
//        }
//    }
//        switch (requestCode){
//            case REQUEST:
//                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                    Toast.makeText(this,"已授权",Toast.LENGTH_SHORT).show();
//
//                }else {
//                    Toast.makeText(this,"未授权",Toast.LENGTH_SHORT).show();
//                }
//                return;
//        }
//    }

}
