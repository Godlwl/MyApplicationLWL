package ecomoter.myapplication.mvpTest;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ecomoter.myapplication.model.JsonModel;
import ecomoter.myapplication.mvpbase.BasePresent;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lwl on 2017/5/23.
 * Describe:
 */

public class GetUserInfoPresent extends BasePresent<GetUserInfoView> {
    private NetUtil mNet;
    public GetUserInfoPresent(NetUtil net){
        this.mNet=net;
    }

    public void getUser(String url, HashMap<String ,String> param){
        getView().showLoading();

        /**
         * get请求参数拼接
         */
        StringBuffer buffer=new StringBuffer();
        for(Map.Entry<String,String> entry:param.entrySet()){
            buffer.append(entry.getKey()+"="+entry.getValue()+"&");
        }
        url=url+buffer.toString();

        mNet.get(url,new NetCallBack<JsonModel>(){

            @Override
            public void onResponse(Response response, final JsonModel jsonModel) {
                ((Activity)getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isAttach()){
                            getView().getUserInfo(jsonModel);
                        }
                        getView().hideLoading();
                    }
                });

            }

            @Override
            public void onFail(String msg) {
                ((Activity)getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getView().hideLoading();
                    }
                });

            }
        },new TypeToken<JsonModel>(){}.getType());
    }


}
