package ecomoter.myapplication.mvpTest;

import ecomoter.myapplication.model.JsonModel;
import ecomoter.myapplication.mvpbase.BaseView;

/**
 * Created by lwl on 2017/5/23.
 * Describe:
 */

public interface GetUserInfoView extends BaseView {
    /**
     * 获取用户信息
     * @param user
     */
    void getUserInfo(JsonModel user);
}
