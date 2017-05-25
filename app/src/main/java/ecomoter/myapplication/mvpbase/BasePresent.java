package ecomoter.myapplication.mvpbase;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by lwl on 2017/5/22.
 * Describe:
 */

public class BasePresent<T extends BaseView> {
    /**
     * 弱引用
     */
    private Reference<T> mRef;

    public BasePresent(){

    }

    /**
     * 绑定view
     * @param view
     */
    public void attachView(T view){
        mRef=new WeakReference<T>(view);
    }

    /**
     * 解绑
     */
    public void detachView(){
        if (mRef!=null){
            mRef.clear();
            mRef=null;
        }
    }

    /**
     * 判断是否已经绑定
     * @return
     */
    public boolean isAttach(){
     return mRef!=null;
    }

    /**
     * 获取view
     * @return
     */
    public T getView(){
        return mRef.get();
    }

    /**
     * 获取上下文
     * @return
     */
    public Context getContext() {
        if (getView() == null) {
            return null;
        }
        if (getView() instanceof Fragment) {
            return ((Fragment) getView()).getActivity();
        } else if (getView() instanceof Activity) {
            return (Activity) getView();
        }
        return null;
    }
}
