package com.jl.framework.app;

/**
 * 动作执行结果封装基类
 * 
 * @author tan.xx
 * @version 2013-3-12 下午2:12:05 tan.xx
 */
public class BaseActionResult {

	public static final String DEFAULT_ERROR_MSG = "error";
	/**
	 * 运行成功
	 */
	public static final String RESULT_CODE_SUCCESS = "1";
	/**
	 * 账号未授权
	 */
	public static final String RESULT_CODE_ACCESS_ERROR = "2";
	/**
	 * 运行失败
	 */
	public static final String RESULT_CODE_ERROR = "0";
	/**
	 * 未登录
	 */
	public static final String RESULT_CODE_NOLOGIN = "3";
	/**
	 * 需要客户端二次确认
	 */
	public static final String RESULT_CODE_NEXT_ACTION = "4";
	/**
	 * 系统未知异常
	 */
	public static final String RESULT_CODE_UNKNOW = "5";

	/**
	 * 结果状态
	 */
	public String ResultCode = "0";
	/**
	 * 结果状态码
	 */
	public String ResultStateCode;
	/**
	 * 结果对象
	 */
	public Object ResultObject;

	/*
	 * 存储未登录、未授权数据
	 */
	public String errorString;

	/**
	 * 动作执行结果
	 */
	public BaseActionResult() {
	}
}
