package com.myweb.springmvc.service.common.exception;

@SuppressWarnings("serial")
public class ServiceException extends RuntimeException  {
	/**
	 * 에러 메시지
	 */
	private String message;
	
	public ServiceException(){
		
	}
	public ServiceException(String message){
		setMessage(message);
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}
}
