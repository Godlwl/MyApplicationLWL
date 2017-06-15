package ecomoter.myapplication;

import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.lzy.okgo.OkGo;
import com.squareup.leakcanary.LeakCanary;

import org.litepal.LitePalApplication;

import ecomoter.myapplication.greendao.DaoMaster;
import ecomoter.myapplication.greendao.DaoSession;
import ecomoter.myapplication.model.MyOpenHelper;

/**
 * Created by lwl on 2017/4/27.
 * Describe:
 */

public class MyApplication extends LitePalApplication {
    private static MyApplication context;

    /**
     * greenDao
     */
    private MyOpenHelper devOpenHelper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;


        /**
         * 我是来测试合并分支的
         */
        OkGo.getInstance().init(this);

        Log.d("test", "te1st");

        /**
         * 哈哈哈哈哈哈我又来合并啦
         */
        Log.d("test", "te1st");

        /**
         * greenDao
         */
        devOpenHelper = new MyOpenHelper(MyApplication.getContext(), "mydb.db", null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();

        LeakCanary.install(this);

        SDKInitializer.initialize(context);
    }

    public static MyApplication getContext() {
        return context;
    }

    public DaoSession getSession() {
        return daoSession;
    }
    public DaoMaster getDaoMaster(){
        return daoMaster;
    }
}
