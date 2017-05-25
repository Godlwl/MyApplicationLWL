package ecomoter.myapplication.mvpTest;


import okhttp3.Response;

/**
 * Created by lwl on 2017/5/23.
 * Describe:
 */

public interface NetCallBack<T> {

    void onResponse(Response response, T t);

    void onFail(String msg);

}
