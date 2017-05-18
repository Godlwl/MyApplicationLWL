package com.jl.framework.util;

import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import org.apache.http.conn.ConnectTimeoutException;

import android.util.Log;

import com.jl.framework.app.JLApplicationBase;
import com.jl.framework.util.api.ApiConfig;

/**
 * 
 * @author EVT
 * 
 */
public class EvtLog {
	public static boolean IS_DEBUG_LOGGABLE = true;
	public static boolean IS_ERROR_LOGGABLE = true;
	static {
		ApiConfig mApiConfig = JLApplicationBase.getInstance().getApiConfig();
		if (mApiConfig != null) {
			IS_DEBUG_LOGGABLE = mApiConfig.isDebug_log_is_enable();
			IS_ERROR_LOGGABLE = mApiConfig.isError_log_is_enable();
		}
	}

	/**
	 * 输出debug信息
	 * 
	 * @param tag
	 *            标签
	 * @param msg
	 *            信息
	 * 
	 */
	public static void d(String tag, String msg) {
		if (IS_DEBUG_LOGGABLE && msg != null) {
			Log.d(tag, msg);
		}
	}

	/**
	 * @param tag
	 *            标签
	 * @param msg
	 *            信息
	 * 
	 */
	public static void i(String tag, String msg) {
		if (IS_DEBUG_LOGGABLE && msg != null) {
			Log.i(tag, msg);
		}
	}

	/**
	 * @param tag
	 *            标签
	 * @param msg
	 *            信息
	 * 
	 */
	public static void w(String tag, String msg) {
		if (IS_DEBUG_LOGGABLE && msg != null) {
			Log.w(tag, msg);
		}
	}

	/**
	 * 输出错误信息
	 * 
	 * @param tag
	 *            标签
	 * @param exception
	 *            输出异常信息到控制台
	 */
	public static void w(String tag, Throwable exception) {
		if (IS_DEBUG_LOGGABLE && exception != null) {
			Log.w(tag, exception);
		}
	}

	/**
	 * 输出error信息并在程序中toast显示，该错误不会记录在日志文件中
	 * 
	 * @param tag
	 *            标签
	 * @param msg
	 *            信息
	 * 
	 */
	public static void e(String tag, String msg) {
		if (IS_ERROR_LOGGABLE && msg != null) {
			Log.e(tag, msg);
		}
	}

	/**
	 * 
	 * @param tag
	 *            tag
	 * @param msg
	 *            msg
	 * @param isShowDialog
	 *            isShowDialog
	 */
	public static void e(String tag, String msg, boolean isShowDialog) {
		if (IS_ERROR_LOGGABLE && msg != null) {
			Log.e(tag, msg);
		}
	}

	/**
	 * 输出错误信息
	 * 
	 * @param tag
	 *            标签
	 * @param exception
	 *            异常对象：如果是MessageException，则会抛出提示信息；
	 *            如果是NetworkException，只会在控制台输出；
	 *            如果是其他的Exception，则会记录日志信息，并输出通用的提示信息
	 */
	public static void e(String tag, Throwable exception) {
		String message = "";
		if (IS_ERROR_LOGGABLE) {
			if (exception != null) {
				exception.printStackTrace();
			}
		}
	}

	// 根据sdk文档列出的java.net包中会抛出的所有异常，来进行对比
	private static boolean isNetworkException(Throwable exception) {
		if (exception == null) {
			return false;
		}
		if (exception instanceof BindException
				|| exception instanceof ConnectException
				|| exception instanceof HttpRetryException
				|| exception instanceof MalformedURLException
				|| exception instanceof NoRouteToHostException
				|| exception instanceof PortUnreachableException
				|| exception instanceof ProtocolException
				|| exception instanceof SocketException
				|| exception instanceof SocketTimeoutException
				|| exception instanceof UnknownHostException
				|| exception instanceof UnknownServiceException
				|| exception instanceof URISyntaxException
				|| exception instanceof ConnectTimeoutException) {
			return true;
		}
		return false;
	}
}
