package com.jl.framework.app;

import com.jl.framework.orm.ActiveAndroid;
import com.jl.framework.util.api.ApiConfig;

import android.app.Application;

/**
 * 全局应用程序
 * 
 * 
 */
public abstract class JLApplicationBase extends Application {
	private static JLApplicationBase CONTEXT;

	/**
	 * 当前的配置项
	 */
	protected ApiConfig mApiConfig;

	// 单例模式中获取唯一的MyApp 实例
	public static synchronized JLApplicationBase getInstance() {
		return CONTEXT;

	}

	@Override
	public void onCreate() {
		super.onCreate();
		CONTEXT = this;
		/**
		 * 将初始化放到线程中
		 * 可以提高app启动速度
		 */
		setApiConfig();
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				ActiveAndroid.initialize(getApplicationContext());
//				setApiConfig();
//			}
//		}).start();


	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		ActiveAndroid.dispose();
	}

	/**
	 * 获取ApiConfig
	 * 
	 * @return
	 */
	public abstract ApiConfig getApiConfig();

	/**
	 * 设置Application配置策略
	 */
	public abstract void setApiConfig();

}
