package ecomoter.myapplication;

import android.app.Application;

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
        OkGo.init(this);
    }

    public static MyApplication getContext(){
        return context;
    }
}
