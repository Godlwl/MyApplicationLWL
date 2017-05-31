package ecomoter.myapplication;

import android.app.Application;
import android.util.Log;

import com.lzy.okgo.OkGo;

import org.litepal.LitePalApplication;

import ecomoter.myapplication.model.DaoMaster;
import ecomoter.myapplication.model.DaoSession;
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
        OkGo.init(this);

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
