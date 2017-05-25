package ecomoter.myapplication;

import android.app.Application;
import android.util.Log;

import com.lzy.okgo.OkGo;

/**
 * Created by lwl on 2017/4/27.
 * Describe:
 */

public class MyApplication extends Application {
    private static MyApplication context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;


        /**
         * 我是来测试合并分支的
         */
        OkGo.init(this);

        Log.d("test","te1st");
    }

    public static MyApplication getContext(){
        return context;
    }
}
