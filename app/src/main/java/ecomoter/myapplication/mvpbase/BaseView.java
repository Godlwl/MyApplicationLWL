package ecomoter.myapplication.mvpbase;

/**
 * Created by lwl on 2017/5/22.
 * Describe:
 */
public interface BaseView {
    /**
     * 初始化view
     */
    void initView();

    /**
     * 显示loading界面
     */
    void showLoading();

    /**
     * 关闭Loading
     */
    void hideLoading();

    /**
     * 加载数据成功
     */
    void showLoadDataSuccess();

    /**
     * 加载数据失败
     */
    void showLoadDataFailed(String msg);

}
