package ecomoter.myapplication.wxdatabase;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import ecomoter.myapplication.MyApplication;
import ecomoter.myapplication.R;

public class GetWxInfoActivity extends AppCompatActivity {
    private Button mBtnQuery;
    private TextView mTvInfo;
    /**
     * 微信根目录路径
     */
    public static final String WX_ROOT_PATH = "/data/data/com.tencent.mm/";
    /**
     * 微信数据库路径
     */
    private static final String WX_DB_DIR_PATH = WX_ROOT_PATH + "MicroMsg";
//    private List<File> mWxDbPathList = new ArrayList<>();
    /**
     * 微信数据库名称
     */
    private static final String WX_DB_FILE_NAME = "EnMicroMsg.db";

    /**
     * 微信uin码存储文件路径
     */
    private static final String WX_DB_UIN_PATH = WX_ROOT_PATH + "shared_prefs" + "/" + "auth_info_key_prefs.xml";

    /**
     * 当前apk包路径
     */
    private String mCurrApkPath = "/data/data/" + MyApplication.getContext().getPackageName() + "/";
    /**
     * 复制后的数据库名称
     */
    private static final String COPY_WX_DATA_DB = "wx_data.db";
    private static final String COPY_WX_SHARED_PREFS = "auth_info_key_prefs.xml";

    /**
     * 微信uin码
     *
     * @param savedInstanceState
     */
    private String mUin;

    /**
     * 手机imei码
     *
     * @param savedInstanceState
     */
    private String mImei;

    /**
     * 微信数据库密码
     *
     */
    private String mPwd;

    /**
     * LogName
     *
     */
    private static final String LOG_NAME = "GetWxInfoActivity";

    /**
     * 联系人列表
     */
    private RecyclerView mRv;

    /**
     * 适配器
     * @param savedInstanceState
     */
    private WxContactAdapter mWxAdapter;

    /**
     * 联系人数据集合
     * @param savedInstanceState
     */
    private List<ContactEntity> mContactDatas=new ArrayList<>();

    /**
     *加载框
     */
    private ProgressDialog mLoadingDialog;

    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_wx_info);
        mBtnQuery = (Button) findViewById(R.id.btn_query);
        mTvInfo = (TextView) findViewById(R.id.tv_info);
        mRv= (RecyclerView) findViewById(R.id.rv_contact);
//        mRv.findChildViewUnder(0,0);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        mRv.setLayoutManager(manager);
        mWxAdapter=new WxContactAdapter(mContactDatas);
        mRv.setAdapter(mWxAdapter);
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        mLoadingDialog=new ProgressDialog(this);
        mLoadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setMessage("加载中...");
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mLoadingDialog.dismiss();
                mWxAdapter.notifyDataSetChanged();
            }
        };
        mBtnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                execRootCmd("chmod 777 -R " + WX_ROOT_PATH);
                execRootCmd("chmod 777 " + WX_DB_UIN_PATH);
                mLoadingDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        queryInfo();
                    }
                }).start();
            }
        });
    }

    /**
     * 执行linux指令
     *
     * @param paramString
     */
    public void execRootCmd(String paramString) {
        try {
            Process localProcess = Runtime.getRuntime().exec("su");
            Object localObject = localProcess.getOutputStream();
            DataOutputStream localDataOutputStream = new DataOutputStream((OutputStream) localObject);
            String str = String.valueOf(paramString);
            localObject = str + "\n";
            localDataOutputStream.writeBytes((String) localObject);
            localDataOutputStream.flush();
            localDataOutputStream.writeBytes("exit\n");
            localDataOutputStream.flush();
            localProcess.waitFor();
            localObject = localProcess.exitValue();
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    private void queryInfo() {

//        copyDb(WX_DB_UIN_PATH,mCurrApkPath+COPY_WX_SHARED_PREFS);
        getWxUin();
        getImei();
        getDbPwd(mImei, mUin);
        /**
         * 先找到微信数据库位置并复制到其它地方
         * 直接操作原数据库会导致微信崩溃
         */
        copyDb(getDbFile(), mCurrApkPath + COPY_WX_DATA_DB);

        /**
         * 查询数据库
         */
        File wxDbFile = new File(mCurrApkPath + COPY_WX_DATA_DB);
        if (wxDbFile.exists()) {
            openWxDb(wxDbFile);

        }

    }




    /**
     * 复制数据库
     */
    private void copyDb(String oldPath, String newPath) {
        File oldFile = new File(oldPath);
        try {
            int byteRead = 0;
            if (oldFile.exists()) {
                InputStream inputStream = new FileInputStream(oldFile);
                OutputStream outputStream = new FileOutputStream(newPath);
                byte[] bytes = new byte[1024];
                while ((byteRead = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, byteRead);
                }
                outputStream.flush();
                inputStream.close();
                outputStream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(LOG_NAME, "复制数据库失败");
        }
    }

    /**
     * 获取数据库位置
     * 数据库父目录文件名称命名是：“mm”+uin码经过md5加密取得
     */
    private String getDbFile() {
        return WX_DB_DIR_PATH + "/" + md5("mm" + mUin) + "/" + WX_DB_FILE_NAME;
    }

    /**
     * 获取微信uin码
     */
    private void getWxUin() {
        File file = new File(WX_DB_UIN_PATH);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(file);
            Element element = document.getRootElement();
            List<Element> elements = element.elements();
            for (Element element1 : elements) {
                if ("_auth_uin".equals(element1.attributeValue("name"))) {
                    mUin = element1.attributeValue("value");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(LOG_NAME, "获取uin码失败");
        }

    }

    /**
     * 获取手机imei码
     */
    private void getImei() {
        TelephonyManager tm = (TelephonyManager) MyApplication.getContext().getSystemService(TELEPHONY_SERVICE);
        mImei = tm.getDeviceId();
    }

    /**
     * 根据imei和uin生成的md5码，获取数据库的密码（去前七位的小写字母）
     *
     * @param imei
     * @param uin
     * @return
     */
    private void getDbPwd(String imei, String uin) {
        if (TextUtils.isEmpty(imei) || TextUtils.isEmpty(uin)) {
            Log.d(LOG_NAME, "初始化数据库密码失败：imei或uid为空");
            return;
        }
        String md5 = md5(imei + uin);
        String password = md5.substring(0, 7).toLowerCase();
        mPwd = password;
    }

    /**
     * md5加密
     *
     * @param content
     * @return
     */
    private String md5(String content) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(content.getBytes("UTF-8"));
            byte[] encryption = md5.digest();//加密
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    sb.append("0").append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    sb.append(Integer.toHexString(0xff & encryption[i]));
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 连接数据库
     */
    private void openWxDb(File dbFile) {
        SQLiteDatabase.loadLibs(getApplicationContext());
        SQLiteDatabaseHook hook = new SQLiteDatabaseHook() {
            @Override
            public void preKey(SQLiteDatabase sqLiteDatabase) {

            }

            @Override
            public void postKey(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.rawExecSQL("PRAGMA cipher_migrate;"); //兼容2.0的数据库
            }
        };
        try {
            /**
             * 打开数据库连接
             */

            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile, mPwd, null, hook);
            Cursor cursor = db.rawQuery("select * from rcontact where verifyFlag = 0 and type != 4 and type != 2 and nickname != '' limit 20, 9999", null);
            while (cursor.moveToNext()) {
                ContactEntity contactEntity=new ContactEntity();
                contactEntity.setUserName(cursor.getString(cursor.getColumnIndex("username")));
                contactEntity.setNickName(cursor.getString(cursor.getColumnIndex("nickname")));
                mContactDatas.add(contactEntity);
//                mTvInfo.setText(cursor.getString(cursor.getColumnIndex("nickname")));
            }
            cursor.close();
            db.close();
            handler.sendEmptyMessage(0);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(LOG_NAME, "读取数据库信息失败" + e.toString());

        }

    }


}
