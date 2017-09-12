package ecomoter.myapplication.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import ecomoter.myapplication.R;

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
        mIv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
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
            Camera.Size preSize=getBestPreviewSize(mTvWidth,mTvHeight,parameters);
            //设置预览照片的大小
            parameters.setPreviewSize(preSize.width, preSize.height);
            //设置相机预览照片帧数
            List<int[]> fps=parameters.getSupportedPreviewFpsRange();
             for (int[] f:fps){
                 for (int ff:f){
                     Log.d("fpsfps",ff+" ");
                 }
             }
            parameters.setPreviewFpsRange(30000,30000);
            //设置图片格式
            parameters.setPictureFormat(ImageFormat.JPEG);
            //设置图片的质量
            parameters.set("jpeg-quality", 100);
            //设置照片的大小
//            parameters.setPictureSize(getSmallestPictureSize(parameters).width, getSmallestPictureSize(parameters).height);
            parameters.setPictureSize(preSize.width, preSize.height);
            //连续自动对焦
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
//            parameters.getSupportedFocusModes();
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
//        takePic();
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

    private Camera.Size getSmallestPictureSize(Camera.Parameters parameters) {
        Camera.Size result = null;
        for (Camera.Size size : parameters.getSupportedPictureSizes()) {
            Log.d("CameraActivity", "initCamera:摄像头支持的pictureSizes: width = " + size.width + "height = " + size.height);
            if (result == null) {
                result = size;
            } else {
                int resultArea = result.width * result.height;
                int newArea = size.width * size.height;

                if (newArea < resultArea) {
                    result = size;
                }
            }
        }

        return (result);
    }

    private Camera.Size getBestPreviewSize(int width, int height, Camera.Parameters parameters) {
        Camera.Size result = null;
        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            Log.d("CameraActivity", "initCamera:摄像头支持的previewSizes: width = " + size.width + "height = " + size.height);
            if (size.width <= width && size.height <= height) {
                if (result == null) {
                    result = size;
                } else {
                    int resultArea = result.width * result.height;
                    int newArea = size.width * size.height;

                    if (newArea > resultArea) {
                        result = size;
                    }
                }
            }else {
                result=size;
            }
        }

        return (result);
    }


}
