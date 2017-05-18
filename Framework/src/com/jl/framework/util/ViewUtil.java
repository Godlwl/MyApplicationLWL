package com.jl.framework.util;

import java.lang.reflect.Method;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * ViewUtil.java <br>
 * Description:视图处理工具类
 *
 * @author huang.b
 * @data 2014-8-25
 */

public class ViewUtil {
    private static final String TAG = "ViewUtil";
    // 控件ListView在有些手机上弹性滑动问题的三个常量
    public static final int OVER_SCROLL_ALWAYS = 0;
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;
    public static final int OVER_SCROLL_NEVER = 2;

    public static void setOverScrollMode(ListView lv, int type) {
        try {
            // 禁止某些机型上，listView存在弹性效果问题
            Method method = AbsListView.class.getMethod("setOverScrollMode", int.class);
            method.invoke(lv, type);
        } catch (Exception e) {
            EvtLog.d(TAG, "反射，禁止listVIew弹性滑动时出错");
        }
    }

    /**
     * 根据宽高比来设置view大小(宽度为屏幕宽度)
     *
     * @param width  宽比
     * @param height 高比
     */
    public static void setViewHeighWithScreenWidthByRate(View v, int width, int height) {
        if (v == null) {
            return;
        }
        LayoutParams params = v.getLayoutParams();
        WindowManager wm = (WindowManager) v.getContext().getSystemService(Context.WINDOW_SERVICE);
        int width1 = wm.getDefaultDisplay().getWidth();
        int height1 = (int) (((float) width1 / width) * height);
        params.height = height1;
        v.setLayoutParams(params);

    }

    public static void setViewHeight(View v, int height) {
        LayoutParams params = v.getLayoutParams();
        params.height = height;
        v.setLayoutParams(params);
    }

    public static void setViewWidth(View v, int width) {
        LayoutParams params = v.getLayoutParams();
        params.width = width;
        v.setLayoutParams(params);
    }

    public static void setHeightWithScreenWidth(View v) {
        LayoutParams params = v.getLayoutParams();
        WindowManager wm = (WindowManager) v.getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        params.height = width;
        v.setLayoutParams(params);
    }

    /**
     * 根据宽度与比率设置高度
     *
     * @param v     需要设置的view
     * @param width 宽度
     * @param ratio 高宽比率
     */
    public static void setHeightWithWidthRatio(View v, int width, float ratio) {
        LayoutParams params = v.getLayoutParams();
        params.height = (int) (width * ratio);
        params.width = width;
        v.setLayoutParams(params);
    }

    public static int getScreenWidth(View v) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) v.getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
