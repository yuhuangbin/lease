package com.lease.api;

public enum ResponseInfo {

	SUCCESS(0, "success"),
	LOGIN_EXPRIED(-1000, "请重新登录"),
	ERROR(-1, "系统繁忙");


	public final int code;

	public final String msg;

	private ResponseInfo(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getMsg(){
		return msg;
	}
}
