package ecomoter.myapplication.rxjava;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lwl on 2017/6/15.
 * Describe:
 */

interface MyService {
    @GET("http://sz.seasaw.cn:8085/carManager/carManager/personal!myBasic.action")
    Observable<UserInfo> getUserInfo(@Query("user_type") String userType,@Query("user_id") String userId,@Query("phone") String phone );

    @GET("http://sz.seasaw.cn:9090/jinxun-api/prize/getPrizeInfo.json")
    Observable<PrizeInfo> getPrizeInfo(@Query("prizeId") String prizeId);
}
