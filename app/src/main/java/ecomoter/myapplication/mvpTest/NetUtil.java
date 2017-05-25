package ecomoter.myapplication.mvpTest;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lwl on 2017/5/23.
 * Describe:
 */

public class NetUtil{
    private static OkHttpClient okHttpClient;
    public NetUtil(){
    }
    private  OkHttpClient getInstance(){
        if (okHttpClient==null){
            synchronized (this){
                if (okHttpClient==null) {
                    okHttpClient = new OkHttpClient();
                }
            }
        }
        return okHttpClient;
    }
    public void get(String url, final NetCallBack callback, final Type type){
        Request request=new Request.Builder()
                .url(url)
                .build();
        getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFail(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                Object t = gson.fromJson(response.body().string(), type);
                callback.onResponse(response,t);
            }
        });
    }
}
