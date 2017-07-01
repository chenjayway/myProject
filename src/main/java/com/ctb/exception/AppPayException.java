/**
 * Copyright (c) 2015 iKang Healthcare Group, Inc. All rights reserved.
*/

package com.ctb.exception;



/**
 * 异常类
 */
@SuppressWarnings("serial")
public class AppPayException extends RuntimeException {
	/**
	 * 异常编号
	 */
	private String code;
	
	/**
	 * 异常详细信息
	 */
	private String msg;


	public AppPayException(){
		super();
	}

	public AppPayException(String code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
	
	public AppPayException(Throwable cause){
		super(cause);
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
