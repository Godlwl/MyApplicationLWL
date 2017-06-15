package ecomoter.myapplication.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ecomoter.myapplication.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaAndRetrofitActivity extends AppCompatActivity {

    private Object userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_and_retrofit);
//        getUserInfo();
        getPrizeInfoByUser();
    }

    /**
     * Rxjava和Retrofit2基本使用
     */
    public void getUserInfo() {
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://sz.seasaw.cn:8085/carManager/")
                .build();
        MyService service=retrofit.create(MyService.class);
        service.getUserInfo("1","13670028397","13670028397")
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<UserInfo>() {
                    @Override
                    public void call(UserInfo userInfo) {

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        Toast.makeText(RxJavaAndRetrofitActivity.this,userInfo.getName(),Toast.LENGTH_LONG).show();

                    }
                })
                                                            ;

    }

    private void getPrizeInfoByUser(){
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://sz.seasaw.cn:9090/jinxun-api/")
                .build();
        final MyService service=retrofit.create(MyService.class);
        service.getUserInfo("1","13670028397","13670028397")
                .flatMap(new Func1<UserInfo, Observable<PrizeInfo>>() {
                    @Override
                    public Observable<PrizeInfo> call(UserInfo userInfo) {
                        return service.getPrizeInfo("374");
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<PrizeInfo>() {
                    @Override
                    public void call(PrizeInfo prizeInfo) {

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PrizeInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(PrizeInfo prizeInfo) {
                        Toast.makeText(RxJavaAndRetrofitActivity.this,prizeInfo.getData().getPrizeName(),Toast.LENGTH_LONG).show();

                    }
                })
        ;
    }
}
