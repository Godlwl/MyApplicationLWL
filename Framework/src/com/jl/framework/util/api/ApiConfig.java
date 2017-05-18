package com.jl.framework.util.api;

/**
 * api设置model
 * 
 */
public abstract class ApiConfig {
	private String old_url;
	/**
	 * api地址
	 */
	private String api_url;
	/**
	 * 日志地址
	 */
	private String log_url;
	/**
	 * 人事考勤OA
	 */
	private String oa_url;
	/**
	 * 是否开发中
	 */
	private boolean is_developing;
	/**
	 * 日志是否打开
	 */
	private boolean debug_log_is_enable;

	private boolean error_log_is_enable;

	public String getOld_url() {
		return old_url;
	}

	public void setOld_url(String old_url) {
		this.old_url = old_url;
	}

	public String getApi_url() {
		return api_url;
	}

	public void setApi_url(String api_url) {
		this.api_url = api_url;
	}

	public String getLog_url() {
		return log_url;
	}

	public void setLog_url(String log_url) {
		this.log_url = log_url;
	}

	public String getOa_url() {
		return oa_url;
	}

	public void setOa_url(String oa_url) {
		this.oa_url = oa_url;
	}

	public boolean isIs_developing() {
		return is_developing;
	}

	public void setIs_developing(boolean is_developing) {
		this.is_developing = is_developing;
	}

	public boolean isDebug_log_is_enable() {
		return debug_log_is_enable;
	}

	public void setDebug_log_is_enable(boolean debug_log_is_enable) {
		this.debug_log_is_enable = debug_log_is_enable;
	}

	public boolean isError_log_is_enable() {
		return error_log_is_enable;
	}

	public void setError_log_is_enable(boolean error_log_is_enable) {
		this.error_log_is_enable = error_log_is_enable;
	}

}
