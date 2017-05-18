package ecomoter.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CameraActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener, View.OnClickListener {
    private TextureView mTvPre;
    private Camera mCamera;
    /**
     * 拍完照的预览图
     */
    private ImageView mIv;

    /**
     * TextureView的宽高
     * @param savedInstanceState
     */
    private int mTvWidth;
    private int mTvHeight;

    /**
     * 是否点击了屏幕（点击并对焦成功才拍照）
     * @param savedInstanceState
     */
    private boolean mIsClick=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mTvPre= (TextureView) findViewById(R.id.tv_preview);
        mTvPre.setSurfaceTextureListener(this);
        mTvPre.setOnClickListener(this);
        mIv= (ImageView) findViewById(R.id.iv_pre);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mCamera=Camera.open();
        initCamera();
        try {
            mCamera.setPreviewTexture(surface);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化相机
     */
    private void initCamera() {
        mCamera.setDisplayOrientation(90);//摄像头进行旋转90°
        if (mCamera != null) {
            Camera.Parameters parameters = mCamera.getParameters();
            //设置预览照片的大小
            parameters.setPreviewSize(mTvHeight, mTvWidth);
            //设置相机预览照片帧数
            parameters.setPreviewFpsRange(4, 10);
            //设置图片格式
            parameters.setPictureFormat(ImageFormat.JPEG);
            //设置图片的质量
            parameters.set("jpeg-quality", 90);
            //设置照片的大小
            parameters.setPictureSize(mTvWidth, mTvHeight);
            //连续自动对焦
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            mCamera.setParameters(parameters);
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        Log.d("Surface","onSurfaceTextureSizeChanged");
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (mCamera!=null) {
            mCamera.stopPreview();
            mCamera.release();
        }
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        Log.d("Surface1111","onSurfaceTextureUpdated");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mTvWidth=mTvPre.getWidth();
        mTvHeight=mTvPre.getHeight();

    }

    @Override
    public void onClick(View v) {
        mIsClick=true;
        takePic();
    }

    /**
     * 拍照
     */
    private void takePic() {
        if (mCamera!=null){
            mCamera.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean success, Camera camera) {
                    /**
                     * 对焦成功且点击了拍照才开始拍照
                     * 因为预览的时候都会自动对焦 会监听到
                     */
                    if (success&&mIsClick){
                        mCamera.takePicture(null, null, new Camera.PictureCallback() {
                            @Override
                            public void onPictureTaken(byte[] data, Camera camera) {
                                Bitmap bitmap=BitmapFactory.decodeByteArray(data,0,data.length);

                                final Matrix matrix = new Matrix();
                                matrix.setRotate(90);
                                final Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                                if (bitmap != null) {
                                    mIv.setImageBitmap(bitmap1);
                                    saveToSd2(bitmap1);
                                }
                                bitmap.recycle();
                                mCamera.startPreview();
                            }
                        });
                        mIsClick=false;
                    }
                }
            });
        }
    }

    /**
     * 直接用字节保存会默认保存横屏照片
     * @param data
     */
    public void saveToSd(byte[] data){
        File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"test.jpg");
        try {
            OutputStream output=new FileOutputStream(file);
            output.write(data);
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用旋转过的bitmap保存图片，保存后也是旋转过的
     * @param bitmap
     */
    public void saveToSd2(Bitmap bitmap){
        File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"test.jpg");
        try {
            BufferedOutputStream output=new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,output);
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
