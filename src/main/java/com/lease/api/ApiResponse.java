package com.lease.api;

import java.io.Serializable;

public class ApiResponse implements Serializable {

	private static final long serialVersionUID = 1189991146334068265L;

	public static final ApiResponse SUCCESS = new ApiResponse();
	public static final ApiResponse ERROR = new ApiResponse(ResponseInfo.ERROR);

	private int code; // 返回响应码:0=成功返回，其他=错误返回

	private String msg; // 返回响应消息

	private Object data; // 返回数据

	private Object cfgData; // 配置数据
	
	public static ApiResponse getInstance( Object data) {
		ApiResponse response = new ApiResponse();
		return response.addData(data);
	}
	
	public static ApiResponse getInstance( Object data,Object cfgData) {
		ApiResponse response = new ApiResponse();
		return response.addData(data).addCfgData(cfgData);
	}

	public ApiResponse() {
		this(ResponseInfo.SUCCESS);
	}


	public ApiResponse(ResponseInfo responseInfo) {
		this.code = responseInfo.code;
		this.msg = responseInfo.msg;
	}

	public ApiResponse(Integer code,String msg) {
		this.code = code;
		this.msg = msg;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public ApiResponse addData(Object data) {
		this.data = data;
		return this;
	}
	
	public ApiResponse addCfgData(Object cfgData) {
		this.cfgData = cfgData;
		return this;
	}
	

	/**
	 * @return the cfgData
	 */
	public Object getCfgData() {
		return cfgData;
	}

	/**
	 * @param cfgData
	 *            the cfgData to set
	 */
	public void setCfgData(Object cfgData) {
		this.cfgData = cfgData;
	}

}
