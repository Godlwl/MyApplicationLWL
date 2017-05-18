package com.jl.framework.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * 软键盘的工具类
 * 
 */
public class ImeUtil {
	private static final String TAG = "ImeUtil";

	/**
	 * 隐藏软键盘1
	 * 
	 * @param act
	 *            act
	 */
	public static void hideSoftInput(Activity act) {
		try {
			if (act == null) {
				return;
			}
			final View v = act.getWindow().peekDecorView();
			if (v != null && v.getWindowToken() != null) {
				InputMethodManager imm = (InputMethodManager) act
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				// method 1
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				// method 2
				// imm.hideSoftInputFromWindow(act.getCurrentFocus().getWindowToken(),
				// InputMethodManager.HIDE_NOT_ALWAYS);
			}
		} catch (Exception e) {
			EvtLog.w(TAG, e);
		}
	}

	/**
	 * 输入法状态
	 * 
	 * @param ctx
	 */
	public static boolean isSoftActive(Activity act) {
		if (act.getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED) {
			return true;
		} else {
			return false;
		}
	}
}